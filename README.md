package com.example.gateway.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.ReactiveStringRedisTemplate;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class TokenSessionValidator {

    private static final Logger log = LoggerFactory.getLogger(TokenSessionValidator.class);

    private final ReactiveStringRedisTemplate redisTemplate;

    public TokenSessionValidator(ReactiveStringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * Validate an authenticated JWT against Redis session storage.
     * Ensures:
     *  1. Token is not blacklisted
     *  2. Token JTI matches the latest active session for this user
     */
    public Mono<Authentication> validateWithRedis(Authentication authentication) {
        if (!(authentication instanceof JwtAuthenticationToken jwtAuth)) {
            log.warn("❌ Skipping validation: authentication is not a JwtAuthenticationToken -> {}", authentication);
            return Mono.error(new BadCredentialsException("Invalid authentication type"));
        }

        Jwt jwt = jwtAuth.getToken();
        String username = jwt.getSubject();
        String jti = jwt.getId(); // unique token ID (must be set when generating token)

        if (jti == null) {
            log.error("❌ Token has no JTI claim (id) -> rejecting token for user={}", username);
            return Mono.error(new BadCredentialsException("Missing token ID (jti)"));
        }

        log.info("🔑 Validating token for user={} with jti={}", username, jti);

        // Step 1: Check if token is blacklisted
        return redisTemplate.hasKey("BL:" + jti)
            .flatMap(isBlacklisted -> {
                if (Boolean.TRUE.equals(isBlacklisted)) {
                    log.warn("❌ Token is blacklisted -> jti={}, user={}", jti, username);
                    return Mono.error(new BadCredentialsException("Token revoked"));
                }

                // Step 2: Fetch current session JTI for user
                return redisTemplate.opsForValue().get("USR:" + username)
                    .flatMap(currentJti -> {
                        log.info("📌 Redis stored JTI for user={} is {}", username, currentJti);

                        if (currentJti == null) {
                            log.warn("⚠️ No active session found in Redis for user={} -> rejecting token", username);
                            return Mono.error(new BadCredentialsException("No active session"));
                        }

                        if (!currentJti.equals(jti)) {
                            log.warn("❌ Token mismatch for user={} -> expected={}, got={}", 
                                username, currentJti, jti);
                            return Mono.error(new BadCredentialsException("Another session is active"));
                        }

                        log.info("✅ Token validation success for user={} with jti={}", username, jti);
                        return Mono.just(authentication);
                    });
            });
    }
}