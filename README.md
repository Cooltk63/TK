package com.fincore.gateway.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.ReactiveStringRedisTemplate;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Component
public class TokenSessionValidator {

    private static final Logger log = LoggerFactory.getLogger(TokenSessionValidator.class);
    // Redis key prefixes
    private static final String USER_PREFIX = "USR:";
    private static final String BLACKLIST_PREFIX = "BL:";
    // Expiry = same as JWT expiry (example: 15 minutes, should match your JwtService config)
    private static final Duration TOKEN_TTL = Duration.ofMinutes(15);
    private final ReactiveStringRedisTemplate redisTemplate;

    public TokenSessionValidator(ReactiveStringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * Called during login to register a new session for the user.
     * 1. Blacklists old JTI if exists
     * 2. Stores new JTI in Redis with expiry
     */
    public Mono<Void> registerUserSession(String username, String newJti) {
        log.info("Getting Username & newjti ::", username, newJti);
        String userKey = USER_PREFIX + username;

        log.info("Inside registerUserSession method " + userKey);

        return redisTemplate.opsForValue().get(userKey)
                .flatMap(oldJti -> {
                    if (oldJti != null) {
                        log.info(" Found old session for user={} -> blacklisting oldJti={}", username, oldJti);
                        return blacklistToken(oldJti);
                    }
                    return Mono.empty();
                })
                .then(redisTemplate.opsForValue()
                        .set(userKey, newJti, TOKEN_TTL)
                        .doOnSuccess(v -> log.info(" Registered new session in Redis for user={} jti={}", username, newJti))
                        .then()
                );
    }

    /**
     * Blacklist a token (on logout or replacement).
     */
    public Mono<Boolean> blacklistToken(String jti) {
        String key = BLACKLIST_PREFIX + jti;
        return redisTemplate.opsForValue().set(key, "true", TOKEN_TTL)
                .doOnSuccess(v -> log.info(" Blacklisted token jti={}", jti));
    }

    /**
     * Clear session for a given user (e.g., on logout).
     */
    public Mono<Boolean> clearUserSession(String username) {
        return redisTemplate.delete(USER_PREFIX + username)
                .map(deleted -> {
                    if (deleted > 0) {
                        log.info(" Cleared session for user={}", username);
                        return true;
                    }
                    return false;
                });
    }

    /**
     * Validate token against Redis.
     * - Reject if blacklisted
     * - Reject if not equal to the latest session JTI
     */
    public Mono<Authentication> validateWithRedis(Authentication authentication) {
        if (!(authentication instanceof JwtAuthenticationToken jwtAuth)) {
            log.warn("Skipping validation: not a JwtAuthenticationToken -> {}", authentication);
            return Mono.error(new BadCredentialsException("Invalid authentication type"));
        }

        Jwt jwt = jwtAuth.getToken();
        String username = jwt.getSubject();
        String jti = jwt.getId();

        if (jti == null) {
            log.error("Token missing JTI claim -> rejecting token for user={}", username);
            return Mono.error(new BadCredentialsException("Missing token ID (jti)"));
        }

        log.info(" Validating token for user={} with jti={}", username, jti);

        return redisTemplate.hasKey(BLACKLIST_PREFIX + jti)
                .flatMap(isBlacklisted -> {
                    if (Boolean.TRUE.equals(isBlacklisted)) {
                        log.warn(" Token is blacklisted -> jti={}, user={}", jti, username);
                        return Mono.error(new BadCredentialsException("Token revoked"));
                    }

                    return redisTemplate.opsForValue().get(USER_PREFIX + username)
                            .flatMap(currentJti -> {
                                log.info(" Redis stored JTI for user={} is {}", username, currentJti);

                                if (currentJti == null) {
                                    log.warn(" No active session found in Redis for user={} -> rejecting", username);
                                    return Mono.error(new BadCredentialsException("No active session"));
                                }

                                if (!currentJti.equals(jti)) {
                                    log.warn(" Token mismatch for user={} -> expected={}, got={}",
                                            username, currentJti, jti);
                                    return Mono.error(new BadCredentialsException("Another session is active"));
                                }

                                log.info(" Token validation success for user={} with jti={}", username, jti);
                                return Mono.just(authentication);
                            });
                });
    }
}



This is my tokenSession Validator Class there is no such method "revokeTokenAndClearSessionIfMatches" so guide me properly and read the class again and use existing code and business logic do not use or need the change in isndie the code.
