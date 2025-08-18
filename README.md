package com.fincore.gateway.config;

import com.fincore.gateway.service.TokenSessionValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoder;
import org.springframework.security.web.server.SecurityWebFilterChain;

import java.util.Arrays;
import java.util.List;

/**
 * Spring Security for the Gateway.
 * - Reads bypass paths from properties (no code change per environment).
 * - Uses a ReactiveJwtDecoder bean (configured in JwtDecoderConfig) to validate JWT signature.
 * - After JWT is valid, runs TokenSessionValidator to enforce Redis rules:
 *   * single-session per user
 *   * token not blacklisted (revoked)
 */
@Configuration
@EnableWebFluxSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final TokenSessionValidator tokenSessionValidator;
    private final ReactiveJwtDecoder jwtDecoder; // provided by JwtDecoderConfig

    // Comma-separated list in properties, e.g. "/auth/**,/public/**,/actuator/**"
    @Value("${security.jwt.bypass-paths:/actuator/**}")
    private String bypassPathsRaw;

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        List<String> bypass = Arrays.stream(bypassPathsRaw.split(","))
                .map(String::trim)
                .filter(s -> !s.isBlank())
                .toList();

        return http
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .authorizeExchange(ex -> ex
                        .pathMatchers(bypass.toArray(String[]::new)).permitAll()
                        .anyExchange().authenticated()
                )
                .oauth2ResourceServer(oauth -> oauth
                        .jwt(jwt -> jwt.jwtDecoder(jwtDecoder)) // verify JWT
                )
                // After JWT is accepted, validate session rules with Redis:
                .authenticationManager((authentication) ->
                        tokenSessionValidator.validateWithRedis(authentication))
                .build();
    }
}

xxx

package com.fincore.gateway.config;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.jwt.NimbusReactiveJwtDecoder;
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoder;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.interfaces.RSAPublicKey;
import java.util.Base64;

/**
 * Creates a ReactiveJwtDecoder based on properties:
 *  security.jwt.mode = hmac | rsa
 *
 *  HMAC:
 *   - security.jwt.hmac-base64-secret = base64 of your HS256 secret bytes (>= 256-bit)
 *
 *  RSA:
 *   - security.jwt.rsa-public = PEM public key (-----BEGIN PUBLIC KEY----- ... -----END PUBLIC KEY-----)
 *
 * Switch environments by only changing properties. No code change.
 */
@Configuration
public class JwtDecoderConfig {

    @Value("${security.jwt.mode:hmac}")
    private String mode;

    @Value("${security.jwt.hmac-base64-secret:}")
    private String hmacBase64Secret;

    @Value("${security.jwt.rsa-public:}")
    private String rsaPublicPem;

    @PostConstruct
    void sanity() {
        if ("hmac".equalsIgnoreCase(mode)) {
            if (hmacBase64Secret == null || hmacBase64Secret.isBlank()) {
                throw new IllegalStateException("HMAC mode selected but security.jwt.hmac-base64-secret is empty");
            }
        } else if ("rsa".equalsIgnoreCase(mode)) {
            if (rsaPublicPem == null || rsaPublicPem.isBlank()) {
                throw new IllegalStateException("RSA mode selected but security.jwt.rsa-public is empty");
            }
        } else {
            throw new IllegalStateException("Unsupported security.jwt.mode: " + mode + " (use 'hmac' or 'rsa')");
        }
    }

    @Bean
    public ReactiveJwtDecoder jwtDecoder() {
        if ("hmac".equalsIgnoreCase(mode)) {
            // Decode base64 to raw bytes and create a SecretKey for HS256 verification
            byte[] secretBytes = Base64.getDecoder().decode(hmacBase64Secret);
            SecretKey key = new SecretKeySpec(secretBytes, "HmacSHA256");
            return NimbusReactiveJwtDecoder.withSecretKey(key).build();
        } else {
            // Parse RSA public key for RS256 verification
            RSAPublicKey publicKey = KeyUtils.parseRsaPublicKey(rsaPublicPem);
            return NimbusReactiveJwtDecoder.withPublicKey(publicKey).build();
        }
    }
}


xxx

package com.fincore.gateway.config;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

/** Utility to parse a PEM RSA public key string into RSAPublicKey */
public class KeyUtils {

    public static RSAPublicKey parseRsaPublicKey(String pem) {
        try {
            String clean = pem
                    .replace("-----BEGIN PUBLIC KEY-----", "")
                    .replace("-----END PUBLIC KEY-----", "")
                    .replaceAll("\\s", "");
            byte[] decoded = Base64.getDecoder().decode(clean);
            X509EncodedKeySpec spec = new X509EncodedKeySpec(decoded);
            KeyFactory kf = KeyFactory.getInstance("RSA");
            return (RSAPublicKey) kf.generatePublic(spec);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new IllegalArgumentException("Invalid RSA public key", e);
        }
    }
}

xxx

package com.fincore.gateway.config;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

/** Utility to parse a PEM RSA public key string into RSAPublicKey */
public class KeyUtils {

    public static RSAPublicKey parseRsaPublicKey(String pem) {
        try {
            String clean = pem
                    .replace("-----BEGIN PUBLIC KEY-----", "")
                    .replace("-----END PUBLIC KEY-----", "")
                    .replaceAll("\\s", "");
            byte[] decoded = Base64.getDecoder().decode(clean);
            X509EncodedKeySpec spec = new X509EncodedKeySpec(decoded);
            KeyFactory kf = KeyFactory.getInstance("RSA");
            return (RSAPublicKey) kf.generatePublic(spec);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new IllegalArgumentException("Invalid RSA public key", e);
        }
    }
}

xxx


package com.fincore.gateway.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.ReactiveStringRedisTemplate;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.time.Duration;

/**
 * Enforces:
 *  1) Token is NOT revoked:          Redis key  BL:<jti> == "1"   (exists => revoked)
 *  2) Single active session per user: Redis key  USR:<username> == current jti
 *
 * Notes:
 *  - Works only after the JWT is validated by Spring Security (signature/expiry).
 *  - Toggle with redis.enabled=true/false (per environment).
 */
@Component
@RequiredArgsConstructor
public class TokenSessionValidator {

    private final ReactiveStringRedisTemplate redis;

    @Value("${redis.enabled:true}")
    private boolean redisEnabled;

    public Mono<Authentication> validateWithRedis(Authentication authentication) {
        if (!redisEnabled) return Mono.just(authentication);

        if (!(authentication instanceof JwtAuthenticationToken jwtAuth)) {
            return Mono.error(new BadCredentialsException("Unsupported authentication type"));
        }

        Jwt jwt = jwtAuth.getToken();
        String username = jwt.getClaimAsString("sub"); // standard subject claim
        String jti = jwt.getId();                      // standard JWT ID claim

        if (username == null || jti == null) {
            return Mono.error(new BadCredentialsException("Missing required claims (sub/jti)"));
        }

        String blKey = "BL:" + jti;
        String userKey = "USR:" + username;

        return redis.opsForValue().get(blKey)
                .defaultIfEmpty("")
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

    /** Called on login: register user's active session jti with TTL == token lifetime */
    public Mono<Void> registerUserSession(String username, String jti, long ttlSeconds) {
        if (!redisEnabled) return Mono.empty();
        String userKey = "USR:" + username;
        return redis.opsForValue()
                .set(userKey, jti, Duration.ofSeconds(ttlSeconds))
                .then();
    }

    /** Called on logout: blacklist current token jti so it can’t be reused */
    public Mono<Void> revokeToken(String jti, long ttlSeconds) {
        if (!redisEnabled) return Mono.empty();
        String blKey = "BL:" + jti;
        return redis.opsForValue().set(blKey, "1", Duration.ofSeconds(ttlSeconds)).then();
    }
}

xxx

package com.fincore.gateway.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

/** Minimal HS256 token generator for local tests (NOT for prod). */
public final class HmacJwtUtil {

    private HmacJwtUtil() {}

    public static String generate(String base64Secret, String subject, long ttlSeconds) {
        byte[] secret = Base64.getDecoder().decode(base64Secret);
        SecretKey key = Keys.hmacShaKeyFor(secret);

        Instant now = Instant.now();
        Instant exp = now.plusSeconds(ttlSeconds);
        String jti = UUID.randomUUID().toString();

        return Jwts.builder()
                .subject(subject)
                .id(jti)
                .issuedAt(Date.from(now))
                .expiration(Date.from(exp))
                .signWith(key)      // HS256
                .compact();
    }
}

xxx

package com.fincore.gateway.controller;

import com.fincore.gateway.service.TokenSessionValidator;
import com.fincore.gateway.util.HmacJwtUtil;
import lombok.Data;
import lombok.RequiredArgsConstructor;
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
        if (!"hmac".equalsIgnoreCase(mode)) {
            return Mono.just(ResponseEntity.badRequest().body(Map.of(
                    "error", "This demo /auth/login issues HS256 tokens only. Switch to security.jwt.mode=hmac for local tests."
            )));
        }

        // Simple demo: accept any username
        if (hmacSecret == null || hmacSecret.isBlank()) {
            return Mono.just(ResponseEntity.internalServerError().body(Map.of("error", "Missing hmac secret")));
        }

        // Generate a HS256 JWT (sub + jti + exp)
        String token = HmacJwtUtil.generate(hmacSecret, req.getUsername(), ttlSeconds);

        // Extract jti again so we can store it; JJWT returns it inside the token, but we avoid parsing here:
        // Small parse just to get jti back (safe because we just created it).
        var parser = io.jsonwebtoken.Jwts.parser().verifyWith(io.jsonwebtoken.security.Keys.hmacShaKeyFor(
                Base64.getDecoder().decode(hmacSecret))).build();
        var claimsJws = parser.parseSignedClaims(token);
        String jti = claimsJws.getPayload().getId();

        return validator.registerUserSession(req.getUsername(), jti, ttlSeconds)
                .thenReturn(ResponseEntity.ok(Map.of(
                        "accessToken", token,
                        "tokenType", "Bearer",
                        "expiresIn", ttlSeconds,
                        "sub", req.getUsername(),
                        "jti", jti
                )));
    }

    @PostMapping("/logout")
    public Mono<ResponseEntity<Void>> logout(JwtAuthenticationToken jwtAuth) {
        // Requires Authorization header: Bearer <token>
        String jti = jwtAuth.getToken().getId();
        long expEpoch = jwtAuth.getToken().getExpiresAt().toEpochMilli() / 1000;
        long nowEpoch = System.currentTimeMillis() / 1000;
        long ttl = Math.max(1, expEpoch - nowEpoch);
        return validator.revokeToken(jti, ttl)
                .thenReturn(ResponseEntity.noContent().build());
    }

    @Data
    public static class LoginReq {
        private String username;
        private String password;
    }
}


xxx

package com.fincore.gateway.controller;

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

xxx

spring.application.name=api-gateway
server.port=8080

# ========== JWT MODE ==========
# hmac or rsa (pick per environment using profile files)
security.jwt.mode=hmac

# HS256: base64 secret (>= 256-bit). For dev, we override below.
security.jwt.hmac-base64-secret=
# RS256: PEM public key (BEGIN/END PUBLIC KEY). For prod, set via profile.
security.jwt.rsa-public=

# Token lifetime used by demo /auth/login (seconds)
security.jwt.ttl-seconds=900

# Paths that bypass auth (comma-separated). Adjust per env if needed.
security.jwt.bypass-paths=/auth/login,/actuator/**

# ========== Redis ==========
redis.enabled=true
spring.data.redis.host=localhost
spring.data.redis.port=6379
spring.data.redis.database=0
# spring.data.redis.password=   # if needed in non-dev

# Actuator basic
management.endpoints.web.exposure.include=health,info
management.endpoint.health.show-details=always

xxx

spring.profiles.active=dev

# Local dev: use HS256 for easy testing
security.jwt.mode=hmac
# 32-byte (256-bit) secret, base64-encoded. Example only!
security.jwt.hmac-base64-secret=ZmFrZV9iYXNlNjRfMzJieXRlX2tleV9mb3JfSE0=  # "fake_base64_32byte_key_for_HM" (example)
security.jwt.bypass-paths=/auth/login,/actuator/**

# Local Redis
redis.enabled=true
spring.data.redis.host=localhost
spring.data.redis.port=6379
spring.data.redis.database=0

xxx

spring.profiles.active=qa

# Example: switch to RSA in QA (paste your PEM public key)
security.jwt.mode=rsa
security.jwt.rsa-public=-----BEGIN PUBLIC KEY-----\nMIIBIjANBgkqhkiG9w0BAQ...snip...\n-----END PUBLIC KEY-----

security.jwt.bypass-paths=/auth/login,/actuator/**

redis.enabled=true
spring.data.redis.host=qa-redis.internal.company
spring.data.redis.port=6379
spring.data.redis.database=0

xxx

spring.profiles.active=prod

# Production: RSA strongly recommended. Inject via env/secret manager.
security.jwt.mode=rsa
security.jwt.rsa-public=${JWT_RSA_PUBLIC}

# Minimal bypass in prod (typically only health)
security.jwt.bypass-paths=/actuator/**

redis.enabled=true
spring.data.redis.host=prod-redis.internal.company
spring.data.redis.port=6379
spring.data.redis.database=0
# spring.data.redis.password=${REDIS_PASSWORD}

xxx

docker run --rm -p 6379:6379 --name redis redis:7

xxx