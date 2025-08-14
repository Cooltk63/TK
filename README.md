# App
spring.application.name=api-gateway
server.port=8080
spring.profiles.active=dev

# ====== ROUTING (dynamic) ======
# Comma-separated list of logical service names you want to expose via gateway:
gateway.services=orders,products,users
# Default port for local dev targets:
gateway.service.default-port=8081
# (In Kubernetes, you’ll use http://<svc-name>:<port> automatically — see route config)

# ====== SECURITY (JWT) ======
# We'll validate HS256 tokens (symmetric secret) locally at the gateway
security.jwt.mode=hmac
# >= 32 bytes base64-encoded secret (don’t commit real prod secret):
security.jwt.secret=ZjZjY2YxN2Q4M2U5M2YxY2I5M2Y2ZGE0ZmQzY2U2YmE1NTYzNzY3ODkwMDEyMw==

# Optional: token validity used by the demo /auth/login (seconds)
security.jwt.expire-seconds=900

# ====== BYPASS vs PROTECTED ======
# Paths here will NOT require JWT (comma-separated, ant-style patterns):
gateway.bypass.urls=/auth/**,/public/**,/actuator/**

# ====== CORS ======
gateway.cors.allowed-origins=http://localhost:3000
gateway.cors.allowed-methods=GET,POST,PUT,DELETE,OPTIONS
gateway.cors.allowed-headers=*

# ====== REDIS ======
redis.enabled=true
spring.data.redis.host=localhost
spring.data.redis.port=6379
spring.data.redis.password=
# If you don’t run Redis locally:
# redis.enabled=false

# Actuator
management.endpoint.health.show-details=always
management.endpoints.web.exposure.include=health,info


xxxxc

package com.fincore.gateway.security;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    @Value("${gateway.bypass.urls}")
    private String bypassCsv;

    @Value("${gateway.cors.allowed-origins:*}")
    private String allowedOrigins;

    @Value("${gateway.cors.allowed-methods:*}")
    private String allowedMethods;

    @Value("${gateway.cors.allowed-headers:*}")
    private String allowedHeaders;

    private final TokenSessionValidator tokenSessionValidator; // Redis-backed validator

    // ========= JWT: validate HS256 using a base64-encoded secret =========
    @Bean
    ReactiveJwtDecoder jwtDecoder(@Value("${security.jwt.secret}") String base64Secret) {
        byte[] key = java.util.Base64.getDecoder().decode(base64Secret);
        return NimbusReactiveJwtDecoder.withSecretKey(new javax.crypto.spec.SecretKeySpec(key, "HmacSHA256"))
                .build();
    }

    // ========= The ONLY filter chain =========
    @Bean
    SecurityWebFilterChain springSecurity(ServerHttpSecurity http, ReactiveJwtDecoder decoder) {

        // Build permitAll matchers from CSV
        var permitMatchers = Arrays.stream(bypassCsv.split(","))
                .map(String::trim)
                .filter(s -> !s.isBlank())
                .toArray(String[]::new);

        http
            .csrf(ServerHttpSecurity.CsrfSpec::disable)
            .cors(cors -> cors.configurationSource(exchange -> {
                var c = new CorsConfiguration();
                c.setAllowedOrigins(splitCsv(allowedOrigins));
                c.setAllowedMethods(splitCsv(allowedMethods));
                c.setAllowedHeaders(splitCsv(allowedHeaders));
                c.setAllowCredentials(true);
                c.setMaxAge(Duration.ofHours(1));
                return c;
            }))

            // Authorize: bypass some URLs, everything else requires JWT
            .authorizeExchange(reg -> reg
                .pathMatchers(permitMatchers).permitAll()
                .anyExchange().authenticated()
            )

            // Enable JWT resource server (Bearer token)
            .oauth2ResourceServer(oauth -> oauth
                .jwt(jwt -> jwt.jwtDecoder(decoder))
                // After Spring validates signature/exp, we do our Redis check:
                .authenticationManager(auth -> tokenSessionValidator.validateWithRedis(auth))
            );

        return http.build();
    }

    private static List<String> splitCsv(String csv) {
        if (csv == null || csv.isBlank()) return List.of("*");
        return Arrays.stream(csv.split(",")).map(String::trim).toList();
    }
}


xxxx

package com.fincore.gateway.security;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.ReactiveStringRedisTemplate;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
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
        var jwt = bta.getToken();
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

xxxc

package com.fincore.gateway.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.util.Date;
import java.util.UUID;

@Component
public class JwtUtil {

    @Value("${security.jwt.secret}")
    private String base64Secret;

    @Value("${security.jwt.expire-seconds:900}")
    private long expireSeconds;

    private SecretKey hmacKey;

    @PostConstruct
    void init() {
        byte[] keyBytes = java.util.Base64.getDecoder().decode(base64Secret);
        if (keyBytes.length < 32) { // 256 bits minimum for HS256
            throw new IllegalStateException("HS256 secret must be >= 32 bytes (base64-encoded).");
        }
        this.hmacKey = Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateToken(String username, String jti, String sessionId) {
        Instant now = Instant.now();
        Instant exp = now.plusSeconds(expireSeconds);
        return Jwts.builder()
                .setSubject(username)
                .setId(jti)
                .claim("sid", sessionId)  // optional
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(exp))
                .signWith(hmacKey, SignatureAlgorithm.HS256)
                .compact();
    }

    public long getTtlSeconds() {
        return expireSeconds;
    }

    public String newJti() {
        return UUID.randomUUID().toString();
    }}

xxx

package com.fincore.gateway.controller;

import com.fincore.gateway.jwt.JwtUtil;
import com.fincore.gateway.security.TokenSessionValidator;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final JwtUtil jwtUtil;
    private final TokenSessionValidator sessionValidator;

    @PostMapping("/login")
    public Mono<ResponseEntity<LoginResponse>> login(@RequestBody LoginRequest req) {
        // In real life: validate username/password. Here we accept anything for demo.
        String username = req.getUsername();
        String jti = jwtUtil.newJti();
        String sessionId = UUID.randomUUID().toString();

        String token = jwtUtil.generateToken(username, jti, sessionId);

        // Register "single active session" in Redis (USR:<username> -> jti)
        long ttl = jwtUtil.getTtlSeconds();
        return sessionValidator.registerUserSession(username, jti, ttl)
                .thenReturn(ResponseEntity.ok(new LoginResponse(token, jti, sessionId, ttl)));
    }

    @PostMapping("/logout")
    public Mono<ResponseEntity<Void>> logout(@RequestBody LogoutRequest req) {
        long ttl = jwtUtil.getTtlSeconds();
        return sessionValidator.revokeToken(req.getJti(), ttl)
                .thenReturn(ResponseEntity.noContent().build());
    }

    @Data public static class LoginRequest { private String username; private String password; }
    @Data public static class LogoutRequest { private String jti; }
    @Data public static class LoginResponse {
        private final String token; private final String jti; private final String sessionId; private final long ttlSeconds;
    }
}



xxx

package com.fincore.gateway.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Minimal dynamic routes:
 * - For each name in gateway.services, we expose /{name}/**.
 * - In local dev, send to http://localhost:<default-port>
 * - In Kubernetes, set the URI to http://{name}:{port} (Cluster DNS)
 */
@Configuration
public class RoutesConfig {

    @Value("${gateway.services}")
    private String servicesCsv;

    @Value("${gateway.service.default-port:8081}")
    private int defaultPort;

    @Value("${spring.profiles.active:dev}")
    private String profile;

    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        var rs = builder.routes();
        for (String raw : servicesCsv.split(",")) {
            String svc = raw.trim();
            if (svc.isEmpty()) continue;

            String uri;
            if ("dev".equalsIgnoreCase(profile)) {
                // local dev -> change port per service if you want
                uri = "http://localhost:" + defaultPort;
            } else {
                // k8s -> service DNS name + port
                uri = "http://" + svc + ":" + defaultPort;
            }

            rs.route(svc, r -> r
                .path("/" + svc + "/**")
                .filters(f -> f.rewritePath("/" + svc + "/(?<segment>.*)", "/${segment}"))
                .uri(uri)
            );
        }
        return rs.build();
    }
}

