package com.fincore.gateway.Service;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.ReactiveStringRedisTemplate;
import org.springframework.security.authentication.AbstractAuthenticationToken;
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
 *   BL:jti             -> "1" (exists means revoked)   (TTL: until token expires)
 *   USR:<username>     -> current jti                  (TTL: optional)
 */
@Component
@RequiredArgsConstructor
public class TokenSessionValidator {

    private final ReactiveStringRedisTemplate redis;

    @Value("${redis.enabled:true}")
    private boolean redisEnabled;

    public Mono<Authentication> validateWithRedis(Authentication authentication) {
        if (!redisEnabled) return Mono.just(authentication); // short-circuit if Redis disabled

        if (!(authentication instanceof BearerTokenAuthentication bta)) {
            return Mono.error(new BadCredentialsException("Unsupported authentication"));
        }

        var principal = bta.getPrincipal();
        Jwt jwt =(Jwt) bta.getToken();
        String username = jwt.getClaimAsString("sub");   // subject
        String jti = jwt.getId();                        // unique token id

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
                        // User has a different active session -> reject
                        return Mono.error(new BadCredentialsException("Another session is active"));
                    }
                    // OK (let it continue)
                    return Mono.just(authentication);
                });
    }

    // Helper for /auth/login demo to register the session (single session policy)
    public Mono<Void> registerUserSession(String username, String jti, long ttlSeconds) {
        if (!redisEnabled) return Mono.empty();
        String userKey = "USR:" + username;
        return redis.opsForValue()
                .set(userKey, jti, Duration.ofSeconds(ttlSeconds))
                .then();
    }

    // Helper for /auth/logout demo to revoke the token
    public Mono<Void> revokeToken(String jti, long ttlSeconds) {
        if (!redisEnabled) return Mono.empty();
        String blKey = "BL:" + jti;
        return redis.opsForValue().set(blKey, "1", Duration.ofSeconds(ttlSeconds)).then();
    }
}

Still getting error on line ::
Unconvertible types; cannot cast 'org.springframework.security.oauth2.core.OAuth2AccessToken' to 'org.springframework.security.oauth2.jwt.Jwt'
Jwt jwt =(Jwt) bta.getToken();

Just provide me one stop solution really irritating
