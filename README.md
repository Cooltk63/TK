package com.fincore.gateway.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.time.Instant;
import java.util.Date;

/**
 * JWT helper:
 * - supports HS256 (HMAC) tokens (configured via security.jwt.mode=hmac)
 * - expects the HMAC secret as BASE64 (security.jwt.secret)
 *
 * This class provides:
 * - generateToken(...) used in the small AuthController (only for dev/test)
 * - parseClaims(...) + validate(...) used by the gateway filter
 *
 * Production: prefer RS256 with private key on Auth service and public key on gateway.
 */
@Component
public class JwtUtil {

    @Value("${security.jwt.mode:hmac}")
    private String mode;

    // Base64-encoded secret for HS256 mode. (example provided in dev props)
    @Value("${security.jwt.secret:}")
    private String base64Secret;

    @Value("${security.jwt.expire-seconds:900}") // default 15 minutes
    private long tokenValiditySeconds;

    private Key getHmacKey() {
        if (base64Secret == null || base64Secret.isBlank()) {
            throw new IllegalStateException("security.jwt.secret must be configured for HS256 mode");
        }
        byte[] keyBytes = Decoders.BASE64.decode(base64Secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    /**
     * Generate a JWT (HS256) with subject, jti and sessionId claims.
     * This method is included to help you test the gateway end-to-end.
     */
    public String generateToken(String username, String jti, String sessionId) {
        Instant now = Instant.now();
        Date iat = Date.from(now);
        Date exp = Date.from(now.plusSeconds(tokenValiditySeconds));
        return Jwts.builder()
                .setSubject(username)
                .setId(jti)
                .claim("sessionId", sessionId)
                .setIssuedAt(iat)
                .setExpiration(exp)
                .signWith(getHmacKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * Parse and return claims. If invalid signature or expired, parser throws.
     */
    public Claims parseClaims(String token) {
        JwtParserBuilder builder = Jwts.parserBuilder();
        if ("hmac".equalsIgnoreCase(mode)) {
            builder = builder.setSigningKey(getHmacKey());
        } else {
            throw new UnsupportedOperationException("Only hmac mode implemented in this sample");
        }
        Jws<Claims> jws = builder.build().parseClaimsJws(token);
        return jws.getBody();
    }

    /**
     * Quick boolean validation wrapper (signature + expiry).
     */
    public boolean validate(String token) {
        try {
            Claims c = parseClaims(token);
            return c.getExpiration() == null || c.getExpiration().after(new Date());
        } catch (JwtException | IllegalArgumentException ex) {
            return false;
        }
    }

    public long getTokenValiditySeconds() {
        return tokenValiditySeconds;
    }
}



xxxx

package com.fincore.gateway.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.ReactiveStringRedisTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.Duration;

/**
 * Minimal Redis-backed token/session store.
 *
 * Key design:
 *  - auth:token:{jti} -> sessionId   (set at login, TTL = token expiry)
 *  - auth:session:{username} -> sessionId (set at login, TTL = token expiry)
 *
 * For validation:
 *  - we require auth:token:{jti} to exist and equal stored sessionId
 *  - and auth:session:{username} to equal the sessionId (ensures single-session)
 *
 * NOTE: This service uses ReactiveStringRedisTemplate so it is fully non-blocking.
 */
@Service
public class RedisTokenService {

    private final ReactiveStringRedisTemplate redis;
    private final boolean redisEnabled;

    public RedisTokenService(ReactiveStringRedisTemplate redis,
                             @Value("${redis.enabled:true}") boolean redisEnabled) {
        this.redis = redis;
        this.redisEnabled = redisEnabled;
    }

    public boolean isRedisEnabled() {
        return redisEnabled;
    }

    private String tokenKey(String jti) {
        return "auth:token:" + jti;
    }

    private String sessionKey(String username) {
        return "auth:session:" + username;
    }

    /**
     * Store token and session mappings in Redis with TTL (seconds).
     * Auth service should call this after successful login.
     */
    public Mono<Void> storeToken(String jti, String username, String sessionId, long ttlSeconds) {
        if (!redisEnabled) return Mono.empty();
        Duration ttl = Duration.ofSeconds(ttlSeconds);
        return redis.opsForValue().set(tokenKey(jti), sessionId, ttl)
                .then(redis.opsForValue().set(sessionKey(username), sessionId, ttl))
                .then();
    }

    /**
     * Remove token and session mapping (logout).
     */
    public Mono<Void> removeToken(String jti, String username) {
        if (!redisEnabled) return Mono.empty();
        return redis.opsForValue().delete(tokenKey(jti))
                .then(redis.opsForValue().delete(sessionKey(username)))
                .then();
    }

    /**
     * Validate token by checking Redis keys.
     * Returns Mono<Boolean> true if token is present and session matches.
     */
    public Mono<Boolean> validateToken(String jti, String username, String sessionId) {
        if (!redisEnabled) {
            // If redis disabled, this service will return empty and caller should decide fallback behavior.
            return Mono.just(false);
        }
        String tokenK = tokenKey(jti);
        String sessionK = sessionKey(username);

        return redis.opsForValue().get(tokenK)
                .flatMap(storedSessionId -> {
                    if (storedSessionId == null) return Mono.just(false);
                    // token entry exists - now check session mapping
                    if (!storedSessionId.equals(sessionId)) return Mono.just(false);
                    return redis.opsForValue().get(sessionK)
                            .map(sessVal -> sessVal != null && sessVal.equals(sessionId))
                            .defaultIfEmpty(false);
                })
                .defaultIfEmpty(false);
    }
}

xxx

package com.fincore.gateway.filter;

import com.fincore.gateway.security.JwtUtil;
import com.fincore.gateway.service.RedisTokenService;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Global JWT filter:
 * - Bypasses whitelist (e.g. /auth/**)
 * - Parses JWT locally
 * - Checks Redis for active session/token (fast)
 * - Forwards request with X-User-Id & X-Session-Id headers
 */
@Component
@Slf4j
public class JwtAuthenticationFilter implements GlobalFilter, Ordered {

    private final JwtUtil jwtUtil;
    private final RedisTokenService redisTokenService;
    private final List<String> bypassList;

    public JwtAuthenticationFilter(JwtUtil jwtUtil,
                                   RedisTokenService redisTokenService,
                                   @Value("${gateway.bypass.urls:/auth/**,/public/**}") String bypassUrls) {
        this.jwtUtil = jwtUtil;
        this.redisTokenService = redisTokenService;
        this.bypassList = Arrays.stream(bypassUrls.split(","))
                .map(String::trim).collect(Collectors.toList());
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String path = exchange.getRequest().getURI().getPath();
        log.debug("Incoming: {} {}", exchange.getRequest().getMethod(), path);

        // Check bypass prefixes (/auth/** etc.)
        for (String pattern : bypassList) {
            String p = pattern.trim();
            if (p.endsWith("/**")) p = p.substring(0, p.length() - 3);
            if (path.startsWith(p)) {
                log.debug("Bypassing auth for path {}", path);
                return chain.filter(exchange);
            }
        }

        // Allow preflight
        if ("OPTIONS".equalsIgnoreCase(exchange.getRequest().getMethodValue())) {
            return chain.filter(exchange);
        }

        String authHeader = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            log.warn("Missing or invalid Authorization header for {}", path);
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }
        String token = authHeader.substring(7);

        // Validate signature & expiry locally first
        if (!jwtUtil.validate(token)) {
            log.warn("JWT failed local validation for {}", path);
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }

        // Parse claims
        Claims claims = jwtUtil.parseClaims(token);
        String username = claims.getSubject();
        Object jtiObj = claims.getId(); // jti
        Object sessionObj = claims.get("sessionId");
        String jti = jtiObj != null ? jtiObj.toString() : null;
        String sessionId = sessionObj == null ? null : sessionObj.toString();

        if (username == null || jti == null || sessionId == null) {
            log.warn("Token missing required claims (sub/jti/sessionId)");
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }

        // Validate Redis presence (fast). If Redis disabled or missing entry -> reject (no DB fallback in this minimal project)
        return redisTokenService.validateToken(jti, username, sessionId)
                .flatMap(valid -> {
                    if (!Boolean.TRUE.equals(valid)) {
                        log.warn("Token not found / revoked in Redis for user={}", username);
                        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                        return exchange.getResponse().setComplete();
                    }
                    // token ok -> set forwarded headers and continue
                    ServerWebExchange mutated = exchange.mutate()
                            .request(exchange.getRequest().mutate()
                                    .header("X-User-Id", username)
                                    .header("X-Session-Id", sessionId)
                                    .build())
                            .build();
                    return chain.filter(mutated);
                });
    }

    @Override
    public int getOrder() {
        return -1;
    }
}


xxx
package com.fincore.gateway.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * Create routes dynamically from a comma-separated property:
 *   gateway.services=orders,products,users
 *
 * For each service "svc" we create:
 *   /svc/**  -> http://svc:8080/**
 * with a rewrite to remove the "/svc" prefix.
 *
 * Keep this simple: add/remove services via properties only.
 */
@Slf4j
@Configuration
public class GatewayRoutesConfig {

    @Value("${gateway.services:}")
    private String gatewayServicesCsv;

    @Value("${gateway.service.default-port:8080}")
    private int defaultPort;

    @Bean
    public RouteLocator customRoutes(RouteLocatorBuilder builder) {
        var routes = builder.routes();

        if (gatewayServicesCsv == null || gatewayServicesCsv.isBlank()) {
            log.warn("No gateway.services configured, no dynamic routes registered");
            return routes.build();
        }

        // Build a mutable list of service names
        String[] parts = gatewayServicesCsv.split(",");
        List<String> services = new ArrayList<>();
        for (String p : parts) {
            if (p != null && !p.isBlank()) services.add(p.trim());
        }

        for (String svc : services) {
            String target = String.format("http://%s:%d", svc, defaultPort);
            log.info("Registering route '{}' -> {}", svc, target);

            routes.route(svc, r -> r
                    .path("/" + svc + "/**")
                    .filters(f -> f.rewritePath("/" + svc + "/(?<segment>.*)", "/${segment}"))
                    .uri(target)
            );
        }

        return routes.build();
    }
}


xxxx


package com.fincore.gateway.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

/**
 * Simple CORS using properties. Keeps gateway accessible from front-end.
 */
@Configuration
public class CorsConfig {

    @Value("${gateway.cors.allowed-origins:*}")
    private String allowedOrigins;

    @Value("${gateway.cors.allowed-methods:GET,POST,PUT,DELETE,OPTIONS}")
    private String allowedMethods;

    @Value("${gateway.cors.allowed-headers:*}")
    private String allowedHeaders;

    @Bean
    public CorsWebFilter corsWebFilter() {
        var config = new CorsConfiguration();
        for (String o : allowedOrigins.split(",")) config.addAllowedOrigin(o.trim());
        for (String m : allowedMethods.split(",")) config.addAllowedMethod(HttpMethod.valueOf(m.trim()));
        for (String h : allowedHeaders.split(",")) config.addAllowedHeader(h.trim());
        config.setAllowCredentials(true);
        var source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return new CorsWebFilter(source);
    }
}


xxx


package com.fincore.gateway.controller;

import com.fincore.gateway.security.JwtUtil;
import com.fincore.gateway.service.RedisTokenService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.UUID;

/**
 * Very small auth endpoints to help local testing:
 *  - POST /auth/login  -> returns access token (JWT) and stores token/session in Redis
 *  - POST /auth/logout -> removes token/session from Redis
 *
 * NOTE: This is a test stub. Replace with your real Login service in production.
 */
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final JwtUtil jwtUtil;
    private final RedisTokenService redisTokenService;

    @PostMapping("/login")
    public Mono<ResponseEntity<LoginResponse>> login(@RequestBody LoginRequest req) {
        // In real app: verify username/password against DB.
        // Here we accept any username/password for demo.
        String username = req.getUsername();
        String sessionId = UUID.randomUUID().toString();
        String jti = UUID.randomUUID().toString();

        // generate token using JwtUtil (HS256)
        String token = jwtUtil.generateToken(username, jti, sessionId);

        // store in redis with TTL = token validity
        long ttl = jwtUtil.getTokenValiditySeconds();
        return redisTokenService.storeToken(jti, username, sessionId, ttl)
                .thenReturn(ResponseEntity.ok(new LoginResponse(token, jti, sessionId, ttl)));
    }

    @PostMapping("/logout")
    public Mono<ResponseEntity<Void>> logout(@RequestBody LogoutRequest req) {
        return redisTokenService.removeToken(req.getJti(), req.getUsername())
                .thenReturn(ResponseEntity.noContent().build());
    }

    @Data static class LoginRequest {
        private String username;
        private String password;
    }
    @Data static class LoginResponse {
        private final String token;
        private final String jti;
        private final String sessionId;
        private final long ttlSeconds;
    }
    @Data static class LogoutRequest {
        private String username;
        private String jti;
    }
}


xxxx


# Base config
spring.application.name=api-gateway
server.port=8080

# Logging
logging.level.root=INFO
logging.level.com.fincore.gateway=DEBUG

# Gateway - dynamic services (comma separated). Add your 15+ services here.
gateway.services=orders,products,users,inventory,payments,auth

# Default port of backend microservices (K8s service usually exposes same internal port)
gateway.service.default-port=8080

# Paths to bypass (no JWT)
gateway.bypass.urls=/auth/**,/public/**,/actuator/**

# JWT configuration (HS256 mode - base64 secret)
security.jwt.mode=hmac
# Small example secret (base64 of 32 bytes). Replace with your secure secret in prod.
security.jwt.secret=ZmFrZV9iYXNlNjRfc2VjcmV0XzMyX2J5dGVzIQ==

# Token TTL seconds (used by JwtUtil)
security.jwt.expire-seconds=900

# Redis toggle + connection (change per environment)
redis.enabled=true
spring.data.redis.host=localhost
spring.data.redis.port=6379
spring.data.redis.password=     # set in prod via env/secret

# CORS
gateway.cors.allowed-origins=http://localhost:3000
gateway.cors.allowed-methods=GET,POST,PUT,DELETE,OPTIONS
gateway.cors.allowed-headers=*



xxxxc



version: '3.8'
services:
  redis:
    image: redis:7-alpine
    container_name: redis
    command: ["redis-server", "/usr/local/etc/redis/redis.conf"]
    volumes:
      - ./redis.conf:/usr/local/etc/redis/redis.conf:ro
    ports:
      - "6379:6379"
    restart: unless-stopped