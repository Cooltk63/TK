package com.fincore.gateway.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.ReactiveStringRedisTemplate;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.BearerTokenAuthentication;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.time.Duration;

/**
 * Enforces:
 * 1) Token is NOT revoked (blacklist).
 * 2) Only a single active session per user (jti must match the user's current session).
 *
 * Redis keys used:
 *   BL:<jti>        -> "1" (exists means revoked)   (TTL: until token expires)
 *   USR:<username>  -> current jti                  (TTL: optional)
 */
@Component
@RequiredArgsConstructor
public class TokenSessionValidator {

    private final ReactiveStringRedisTemplate redis;

    @Value("${redis.enabled:true}")
    private boolean redisEnabled;

    public Mono<Authentication> validateWithRedis(Authentication authentication) {
        if (!redisEnabled) {
            return Mono.just(authentication); // Skip check if Redis disabled
        }

        if (!(authentication instanceof BearerTokenAuthentication bta)) {
            return Mono.error(new BadCredentialsException("Unsupported authentication"));
        }

        // Get decoded JWT (claims available here)
        Jwt jwt = (Jwt) bta.getPrincipal();
        String username = jwt.getClaimAsString("sub"); // subject claim
        String jti = jwt.getId(); // unique token id (jti claim)

        if (username == null || jti == null) {
            return Mono.error(new BadCredentialsException("Missing sub/jti claims"));
        }

        String blKey = "BL:" + jti;
        String userKey = "USR:" + username;

        return redis.opsForValue().get(blKey)
                .defaultIfEmpty("") // empty means not blacklisted
                .flatMap(bl -> {
                    if (!bl.isEmpty()) {
                        return Mono.error(new BadCredentialsException("Token revoked"));
                    }
                    return redis.opsForValue().get(userKey).defaultIfEmpty("");
                })
                .flatMap(currentJti -> {
                    if (!currentJti.isEmpty() && !currentJti.equals(jti)) {
                        return Mono.error(new BadCredentialsException("Another session is active"));
                    }
                    return Mono.just(authentication);
                });
    }

    // Helper for /auth/login to register session
    public Mono<Void> registerUserSession(String username, String jti, long ttlSeconds) {
        if (!redisEnabled) return Mono.empty();
        String userKey = "USR:" + username;
        return redis.opsForValue()
                .set(userKey, jti, Duration.ofSeconds(ttlSeconds))
                .then();
    }

    // Helper for /auth/logout to revoke token
    public Mono<Void> revokeToken(String jti, long ttlSeconds) {
        if (!redisEnabled) return Mono.empty();
        String blKey = "BL:" + jti;
        return redis.opsForValue()
                .set(blKey, "1", Duration.ofSeconds(ttlSeconds))
                .then();
    }
}
