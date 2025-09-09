package com.fincore.gateway.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.ReactiveStringRedisTemplate;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.time.Duration;

/**
 * TokenSessionValidator
 *
 * - Keys:
 *    USR:<username>  => current jti for that user (single-active-session)
 *    BL:<jti>        => blacklisted token flag (value "true")
 *
 * - TOKEN_TTL is used for both stored USR:<username> and BL:<jti> expirations.
 *
 * This class provides:
 *  - registerUserSession(...) : set USR:<username> -> newJti, blacklisting old JTI if present
 *  - blacklistToken(...)      : mark BL:<jti> with TTL
 *  - clearUserSession(...)    : delete USR:<username>
 *  - revokeTokenAndClearSessionIfMatches(...) : blacklists jti then clears USR:<username> only if it equals that jti
 *  - validateWithRedis(...)   : used during request filtering to ensure token is not blacklisted and matches latest session
 */
@Component
public class TokenSessionValidator {

    private static final Logger log = LoggerFactory.getLogger(TokenSessionValidator.class);

    private final ReactiveStringRedisTemplate redisTemplate;

    // Key prefixes
    private static final String USER_PREFIX = "USR:";
    private static final String BLACKLIST_PREFIX = "BL:";

    // TTL used to store session and blacklist entries. Should match JWT lifetime.
    private static final Duration TOKEN_TTL = Duration.ofMinutes(15);

    public TokenSessionValidator(ReactiveStringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * Register a new session for the user:
     *  - If an old JTI exists, blacklist it
     *  - Set USR:<username> -> newJti (with TTL)
     */
    public Mono<Void> registerUserSession(String username, String newJti) {
        String userKey = USER_PREFIX + username;
        log.info("registerUserSession for userKey={}", userKey);

        return redisTemplate.opsForValue().get(userKey)
                .flatMap(oldJti -> {
                    if (oldJti != null && !oldJti.isBlank()) {
                        log.info("Found old session for user={} -> blacklisting oldJti={}", username, oldJti);
                        return blacklistToken(oldJti).then();
                    }
                    return Mono.empty();
                })
                .then(redisTemplate.opsForValue()
                        .set(userKey, newJti, TOKEN_TTL)
                        .doOnSuccess(v -> log.info("Registered new session in Redis for user={} jti={}", username, newJti))
                        .then()
                );
    }

    /**
     * Blacklist a token JTI (store BL:<jti> = "true" with TTL).
     * Returns Mono<Boolean> that resolves to true if set succeeded.
     */
    public Mono<Boolean> blacklistToken(String jti) {
        String key = BLACKLIST_PREFIX + jti;
        return redisTemplate.opsForValue()
                .set(key, "true", TOKEN_TTL)
                .doOnSuccess(v -> log.info("Blacklisted token jti={}", jti));
    }

    /**
     * Clear the user session (delete USR:<username>)
     */
    public Mono<Boolean> clearUserSession(String username) {
        String userKey = USER_PREFIX + username;
        return redisTemplate.delete(userKey)
                .doOnNext(deleted -> {
                    if (deleted > 0) log.info("Cleared session for user={}", username);
                })
                .map(deleted -> deleted > 0);
    }

    /**
     * Revoke (blacklist) the token and then, only if the user's stored JTI equals this jti,
     * remove the USR:<username> key. This avoids removing a newer session that replaced the token.
     *
     * This is used by logout: it guarantees that after logout the token is blacklisted and
     * user session is cleared only when appropriate.
     */
    public Mono<Void> revokeTokenAndClearSessionIfMatches(String username, String jti) {
        String userKey = USER_PREFIX + username;
        String blKey = BLACKLIST_PREFIX + jti;

        // Blacklist first, then conditionally delete user key if it matches this jti
        return blacklistToken(jti)
                .then(redisTemplate.opsForValue().get(userKey).defaultIfEmpty(null))
                .flatMap(currentJti -> {
                    if (currentJti != null && currentJti.equals(jti)) {
                        // Current stored jti equals the token being revoked -> delete the user key
                        return redisTemplate.delete(userKey).then();
                    }
                    // user key not present or different (means user already has a newer session) -> nothing to delete
                    return Mono.empty();
                })
                .then();
    }

    // -------------------------
    // validateWithRedis(...) remains the same in your project:
    // - ensures BL:<jti> does not exist
    // - ensures USR:<username> equals the token jti
    // Keep your existing validateWithRedis method (not shown here) or include it
    // -------------------------
}


xxxx


package com.fincore.gateway.Controller;

import com.fincore.gateway.Service.TokenSessionValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.util.Map;

/**
 * AuthController (login already implemented elsewhere).
 *
 * Provides:
 *  - POST /auth/logout           : logout using current Authorization Bearer token
 *  - POST /auth/logout/revoke    : admin-style revoke by providing username+jti in request body
 *
 * Notes:
 *  - logout(...) expects the request to be authenticated (Bearer token present).
 *  - We compute TTL from token expiresAt for informational/debugging only. Redis TTL is managed by TokenSessionValidator.
 */
@Slf4j
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final TokenSessionValidator validator;

    /**
     * Logout the current user (based on the JWT used to authenticate the request).
     * Steps:
     *  1) Get jti + sub (username) from the JWT
     *  2) Blacklist the jti and delete user's USR:<username> if it matches this jti
     */
    @PostMapping("/logout")
    public Mono<ResponseEntity<Map<String, Object>>> logout(JwtAuthenticationToken jwtAuth) {
        if (jwtAuth == null) {
            return Mono.just(ResponseEntity.badRequest().body(Map.of("error", "No authenticated token found")));
        }

        var jwt = jwtAuth.getToken();
        String jti = jwt.getId();
        String username = jwt.getSubject();

        if (jti == null || username == null) {
            return Mono.just(ResponseEntity.badRequest().body(Map.of("error", "Token missing jti or sub")));
        }

        log.info("Logout requested for user={} jti={}", username, jti);

        return validator.revokeTokenAndClearSessionIfMatches(username, jti)
                .then(Mono.fromSupplier(() -> ResponseEntity.noContent().build()));
    }

    /**
     * Admin-style revoke: provide JSON body { "username":"...", "jti":"..." }.
     * Authentication/authorization for an admin caller should be enforced by your security rules
     * (not included here). This endpoint simply blacklists provided jti and will not delete user key.
     */
    @PostMapping("/logout/revoke")
    public Mono<ResponseEntity<Map<String, Object>>> revokeByAdmin(@RequestBody RevokeRequest req) {
        if (req == null || req.getJti() == null) {
            return Mono.just(ResponseEntity.badRequest().body(Map.of("error", "Missing jti")));
        }

        log.info("Admin revoke requested for username={} jti={}", req.getUsername(), req.getJti());

        return validator.blacklistToken(req.getJti())
                .flatMap(ok -> {
                    if (Boolean.TRUE.equals(ok)) {
                        return Mono.just(ResponseEntity.noContent().build());
                    } else {
                        return Mono.just(ResponseEntity.internalServerError().body(Map.of("error", "Could not blacklist")));
                    }
                });
    }

    public static record RevokeRequest(String username, String jti) {}
}
