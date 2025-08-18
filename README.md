SecurityConfig ::
package com.fincore.gateway.Config;

import com.fincore.gateway.Service.TokenSessionValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import reactor.core.publisher.Mono;

@Slf4j
@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    private final TokenSessionValidator tokenSessionValidator;

    public SecurityConfig(TokenSessionValidator tokenSessionValidator) {
        this.tokenSessionValidator = tokenSessionValidator;
    }

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        System.out.println("security web filter chain");
        return http
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .authorizeExchange(exchanges -> exchanges
                        .pathMatchers("/auth/login", "/auth/register").permitAll()
                        .anyExchange().authenticated()
                )
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(Customizer.withDefaults())
                )
                // 🔑 Must run AFTER JWT is decoded, but BEFORE controller
                .addFilterAfter(redisValidationFilter(), SecurityWebFiltersOrder.AUTHENTICATION)
                .build();
    }

    private WebFilter redisValidationFilter() {
        System.out.println("redisValidationFilter");
        return (exchange, chain) ->
                exchange.getPrincipal()
                        .cast(Authentication.class)
                        .flatMap(auth -> tokenSessionValidator.validateWithRedis(auth)
                                .flatMap(validAuth -> chain.filter(exchange))
                        )
                        .onErrorResume(e -> {
                            // ❌ If validation fails -> block request
                            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                            return exchange.getResponse().setComplete();
                        });
    }
}

AuthController ::
package com.fincore.gateway.Controller;


import com.fincore.gateway.JwtUtil.HmacJwtUtil;
import com.fincore.gateway.Service.TokenSessionValidator;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.Base64;
import java.util.Map;

/**
 * Test-only endpoints to exercise the gateway+redis locally.
 * - POST /auth/login  {username} -> returns HS256 token (sub + jti) and registers session in Redis
 * - POST /auth/logout            -> blacklists current token jti in Redis
 *
 * In PRODUCTION:
 * - Remove this controller.
 * - Your Login service should write to Redis: USR:<user>=<jti>; On logout write BL:<jti>=1.
 */
@Slf4j
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final TokenSessionValidator validator;

    @Value("${security.jwt.mode:hmac}")
    private String mode;

    @Value("${security.jwt.hmac-base64-secret:}")
    private String hmacSecret;

    @Value("${security.jwt.ttl-seconds:900}")
    private long ttlSeconds;

    @PostMapping("/login")
    public Mono<ResponseEntity<Map<String, Object>>> login(@RequestBody LoginReq req) {
        log.info("login req={}", req);
        if (!"hmac".equalsIgnoreCase(mode)) {
            return Mono.just(ResponseEntity.badRequest().body(Map.of(
                    "error", "This demo /auth/login issues HS256 tokens only. Switch to security.jwt.mode=hmac for local tests."
            )));
        }
        log.info("login accept={}", req);
        // Simple demo: accept any username
        if (hmacSecret == null || hmacSecret.isBlank()) {
            return Mono.just(ResponseEntity.internalServerError().body(Map.of("error", "Missing hmac secret")));
        }

        // Generate a HS256 JWT (sub + jti + exp)
        String token = HmacJwtUtil.generate(hmacSecret, req.getUsername(), ttlSeconds);
        log.info("Generated token={}", token);


        // Extract jti again so we can store it; JJWT returns it inside the token, but we avoid parsing here:
        // Small parse just to get jti back (safe because we just created it).
        var parser = io.jsonwebtoken.Jwts.parser().verifyWith(io.jsonwebtoken.security.Keys.hmacShaKeyFor(
                Base64.getDecoder().decode(hmacSecret))).build();
        var claimsJws = parser.parseSignedClaims(token);
        String jti = claimsJws.getPayload().getId();

        log.info("Jti from claims claimsJws.getPayload().getId(){}", jti);

        return validator.registerUserSession(req.getUsername(), jti/*, ttlSeconds*/)
                .thenReturn(ResponseEntity.ok(Map.of(
                        "accessToken", token,
                        "tokenType", "Bearer",
                        "expiresIn", ttlSeconds,
                        "sub", req.getUsername(),
                        "jti", jti
                )));
    }

    /*@PostMapping("/logout")
    public Mono<ResponseEntity<Void>> logout(JwtAuthenticationToken jwtAuth) {
        // Requires Authorization header: Bearer <token>
        String jti = jwtAuth.getToken().getId();
        long expEpoch = jwtAuth.getToken().getExpiresAt().toEpochMilli() / 1000;
        long nowEpoch = System.currentTimeMillis() / 1000;
        long ttl = Math.max(1, expEpoch - nowEpoch);
        return validator.revokeToken(jti, ttl)
                .thenReturn(ResponseEntity.noContent().build());
    }*/

    @Data
    public static class LoginReq {
        private String username;
        private String password;
    }
}

ProtectedController ::
package com.fincore.gateway.Controller;

import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.Map;

/** Protected endpoint to verify auth+redis policies easily. */
@RestController
@RequestMapping("/secure")
public class ProtectedEchoController {

    @GetMapping("/hello")
    public Mono<Map<String, Object>> hello(JwtAuthenticationToken auth) {
        return Mono.just(Map.of(
                "message", "Hello " + auth.getName(),
                "sub", auth.getToken().getSubject(),
                "jti", auth.getToken().getId()
        ));
    }
}

TokenSessionValidator ::
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

    private final ReactiveStringRedisTemplate redisTemplate;

    // Redis key prefixes
    private static final String USER_PREFIX = "USR:";
    private static final String BLACKLIST_PREFIX = "BL:";

    // Expiry = same as JWT expiry (example: 15 minutes, should match your JwtService config)
    private static final Duration TOKEN_TTL = Duration.ofMinutes(15);

    public TokenSessionValidator(ReactiveStringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * ✅ Called during login to register a new session for the user.
     * 1. Blacklists old JTI if exists
     * 2. Stores new JTI in Redis with expiry
     */
    public Mono<Void> registerUserSession(String username, String newJti) {
        String userKey = USER_PREFIX + username;

        log.info("Inside registerUserSession method "+userKey);

        return redisTemplate.opsForValue().get(userKey)
                .flatMap(oldJti -> {
                    if (oldJti != null) {
                        log.info("⚠️ Found old session for user={} -> blacklisting oldJti={}", username, oldJti);
                        return blacklistToken(oldJti);
                    }
                    return Mono.empty();
                })
                .then(redisTemplate.opsForValue()
                        .set(userKey, newJti, TOKEN_TTL)
                        .doOnSuccess(v -> log.info("✅ Registered new session in Redis for user={} jti={}", username, newJti))
                        .then()
                );
    }

    /**
     * ❌ Blacklist a token (on logout or replacement).
     */
    public Mono<Boolean> blacklistToken(String jti) {
        String key = BLACKLIST_PREFIX + jti;
        return redisTemplate.opsForValue().set(key, "true", TOKEN_TTL)
                .doOnSuccess(v -> log.info("🚫 Blacklisted token jti={}", jti));
    }

    /**
     * 🗑️ Clear session for a given user (e.g., on logout).
     */
    public Mono<Boolean> clearUserSession(String username) {
        return redisTemplate.delete(USER_PREFIX + username)
                .map(deleted -> {
                    if (deleted > 0) {
                        log.info("🗑️ Cleared session for user={}", username);
                        return true;
                    }
                    return false;
                });
    }

    /**
     * 🔑 Validate token against Redis.
     * - Reject if blacklisted
     * - Reject if not equal to the latest session JTI
     */
    public Mono<Authentication> validateWithRedis(Authentication authentication) {
        if (!(authentication instanceof JwtAuthenticationToken jwtAuth)) {
            log.warn("❌ Skipping validation: not a JwtAuthenticationToken -> {}", authentication);
            return Mono.error(new BadCredentialsException("Invalid authentication type"));
        }

        Jwt jwt = jwtAuth.getToken();
        String username = jwt.getSubject();
        String jti = jwt.getId();

        if (jti == null) {
            log.error("❌ Token missing JTI claim -> rejecting token for user={}", username);
            return Mono.error(new BadCredentialsException("Missing token ID (jti)"));
        }

        log.info("🔑 Validating token for user={} with jti={}", username, jti);

        return redisTemplate.hasKey(BLACKLIST_PREFIX + jti)
                .flatMap(isBlacklisted -> {
                    if (Boolean.TRUE.equals(isBlacklisted)) {
                        log.warn("❌ Token is blacklisted -> jti={}, user={}", jti, username);
                        return Mono.error(new BadCredentialsException("Token revoked"));
                    }

                    return redisTemplate.opsForValue().get(USER_PREFIX + username)
                            .flatMap(currentJti -> {
                                log.info("📌 Redis stored JTI for user={} is {}", username, currentJti);

                                if (currentJti == null) {
                                    log.warn("⚠️ No active session found in Redis for user={} -> rejecting", username);
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





