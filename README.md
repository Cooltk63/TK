package com.fincore.gateway.Controller;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.ReactiveStringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.Map;

/**
 * Logout endpoint using existing TokenSessionValidator logic.
 *
 * Behavior:
 *  1) Blacklists (revokes) the JTI of the incoming token (always).
 *  2) Reads Redis key USR:<username> and if its value equals the JTI being logged out,
 *     then clears the user session (deletes USR:<username>).
 *  3) If Redis contains a different JTI (user already logged-in elsewhere), we do NOT clear the session.
 *
 * Notes:
 *  - Uses only existing public methods from TokenSessionValidator: blacklistToken(...) and clearUserSession(...).
 *  - Uses ReactiveStringRedisTemplate to read the current session JTI to avoid modifying TokenSessionValidator.
 *  - JWT must be presented in Authorization: Bearer <token> header (JwtAuthenticationToken is injected).
 */
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class LogoutController {

    private static final Logger log = LoggerFactory.getLogger(LogoutController.class);

    private final com.fincore.gateway.Service.TokenSessionValidator validator;
    private final ReactiveStringRedisTemplate redisTemplate;

    private static final String USER_PREFIX = "USR:";

    /**
     * POST /auth/logout
     * - Requires Authorization: Bearer <token>
     * - Revokes the token and conditionally clears the user session if it matches the token's jti.
     */
    @PostMapping("/logout")
    public Mono<ResponseEntity<Map<String, Object>>> logout(JwtAuthenticationToken jwtAuth) {
        if (jwtAuth == null) {
            return Mono.just(ResponseEntity.badRequest()
                    .body(Map.of("error", "No authenticated token provided")));
        }

        var jwt = jwtAuth.getToken();
        String username = jwt.getSubject();
        String jti = jwt.getId();

        if (username == null || jti == null) {
            return Mono.just(ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", "Token missing subject or jti")));
        }

        log.info("Logout requested for user={} jti={}", username, jti);

        String userKey = USER_PREFIX + username;

        // 1) blacklist the given jti
        return validator.blacklistToken(jti)
                // 2) then check current stored JTI for user
                .then(redisTemplate.opsForValue().get(userKey).defaultIfEmpty(""))
                .flatMap(currentJti -> {
                    if (jti.equals(currentJti)) {
                        // 3) Only clear session if current stored JTI equals this jti
                        log.info("Current Redis JTI matches logout jti -> clearing session for user={}", username);
                        return validator.clearUserSession(username)
                                .map(cleared -> Map.<String, Object>of(
                                        "message", "User logged out (revoked token and cleared session)",
                                        "user", username,
                                        "jti", jti
                                ));
                    } else {
                        // Someone else already replaced the session; keep that session intact.
                        log.info("Redis JTI does not match logout jti (currentJti={}) -> not clearing session for user={}",
                                currentJti, username);
                        return Mono.just(Map.<String, Object>of(
                                "message", "Token revoked. Active session remains (another token is active)",
                                "user", username,
                                "jti", jti,
                                "currentJti", currentJti == null ? "" : currentJti
                        ));
                    }
                })
                .map(body -> ResponseEntity.ok(body))
                .onErrorResume(e -> {
                    log.error("Logout failed for user={} jti={}, reason={}", username, jti, e.toString());
                    return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                            .body(Map.of("error", "Logout failed: " + e.getMessage())));
                });
    }
}


xxx


