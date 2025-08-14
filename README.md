<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>

  <parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>3.5.4</version>
    <relativePath/>
  </parent>

  <groupId>com.fincore.gateway</groupId>
  <artifactId>api-gateway</artifactId>
  <version>0.0.1-SNAPSHOT</version>
  <name>api-gateway</name>

  <properties>
    <java.version>17</java.version>
    <spring-cloud.version>2025.0.0</spring-cloud.version>
    <jjwt.version>0.11.5</jjwt.version>
  </properties>

  <dependencies>
    <!-- Gateway (WebFlux) -->
    <dependency>
      <groupId>org.springframework.cloud</groupId>
      <artifactId>spring-cloud-starter-gateway-server-webflux</artifactId>
    </dependency>

    <!-- WebFlux starter (for reactive) -->
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-webflux</artifactId>
    </dependency>

    <!-- Spring Data JPA (DB-backed session/token store) -->
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>

    <!-- Oracle JDBC runtime as you use Oracle in other services (replace if needed) -->
    <dependency>
      <groupId>com.oracle.database.jdbc</groupId>
      <artifactId>ojdbc11</artifactId>
      <scope>runtime</scope>
    </dependency>

    <!-- Redis (Lettuce client via Spring Boot) -->
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-data-redis</artifactId>
    </dependency>

    <!-- JJWT (0.11.5) - API + impl + jackson -->
    <dependency>
      <groupId>io.jsonwebtoken</groupId>
      <artifactId>jjwt-api</artifactId>
      <version>${jjwt.version}</version>
    </dependency>
    <dependency>
      <groupId>io.jsonwebtoken</groupId>
      <artifactId>jjwt-impl</artifactId>
      <version>${jjwt.version}</version>
      <scope>runtime</scope>
    </dependency>
    <dependency>
      <groupId>io.jsonwebtoken</groupId>
      <artifactId>jjwt-jackson</artifactId>
      <version>${jjwt.version}</version>
      <scope>runtime</scope>
    </dependency>

    <!-- Actuator -->
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-actuator</artifactId>
    </dependency>

    <!-- Lombok (optional but used in examples) -->
    <dependency>
      <groupId>org.projectlombok</groupId>
      <artifactId>lombok</artifactId>
      <optional>true</optional>
    </dependency>

    <!-- Testing -->
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-test</artifactId>
      <scope>test</scope>
    </dependency>
  </dependencies>

  <dependencyManagement>
    <dependencies>
      <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-dependencies</artifactId>
        <version>${spring-cloud.version}</version>
        <type>pom</type>
        <scope>import</scope>
      </dependency>
    </dependencies>
  </dependencyManagement>

  <build>
    <plugins>
      <plugin>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-maven-plugin</artifactId>
      </plugin>
    </plugins>
  </build>
</project>




xxxx

package com.fincore.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main entrypoint for the API Gateway application.
 */
@SpringBootApplication
public class ApiGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayApplication.class, args);
    }
}

xxx

package com.fincore.gateway.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.security.PublicKey;
import java.security.KeyFactory;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

/**
 * Utility to parse and validate JWT tokens locally.
 *
 * Supports:
 * - HS256 using a base64 secret (security.jwt.mode = hmac)
 * - RS256 using public key PEM/base64 (security.jwt.mode = rsa)
 *
 * Note: The Auth/Login service must issue tokens compatible with this utility.
 */
@Component
public class JwtUtil {

    @Value("${security.jwt.mode:hmac}") // "hmac" or "rsa"
    private String mode;

    // For HS256: base64-encoded secret
    @Value("${security.jwt.secret:}")
    private String hmacSecretBase64;

    // For RS256: PEM or base64 DER public key (optional)
    @Value("${security.jwt.public-key:}")
    private String rsaPublicKey;

    private volatile Key hmacKey;
    private volatile PublicKey rsaPubKey;

    private Key getHmacKey() {
        if (hmacKey == null) {
            if (hmacSecretBase64 == null || hmacSecretBase64.isBlank()) {
                throw new IllegalStateException("security.jwt.secret must be set for HS256 mode");
            }
            byte[] secretBytes = Decoders.BASE64.decode(hmacSecretBase64);
            hmacKey = Keys.hmacShaKeyFor(secretBytes);
        }
        return hmacKey;
    }

    private PublicKey getRsaPublicKey() {
        if (rsaPubKey == null) {
            if (rsaPublicKey == null || rsaPublicKey.isBlank()) {
                throw new IllegalStateException("security.jwt.public-key must be set for RS256 mode");
            }
            try {
                String cleaned = rsaPublicKey
                        .replace("-----BEGIN PUBLIC KEY-----", "")
                        .replace("-----END PUBLIC KEY-----", "")
                        .replaceAll("\\s+", "");
                byte[] bytes = Base64.getDecoder().decode(cleaned);
                X509EncodedKeySpec spec = new X509EncodedKeySpec(bytes);
                KeyFactory kf = KeyFactory.getInstance("RSA");
                rsaPubKey = kf.generatePublic(spec);
            } catch (Exception ex) {
                throw new IllegalStateException("Failed to parse RSA public key", ex);
            }
        }
        return rsaPubKey;
    }

    /**
     * Parse JWT and return claims. Throws exception on invalid signature or expired token.
     */
    public Claims parseClaims(String token) {
        Jws<Claims> jws;
        if ("rsa".equalsIgnoreCase(mode)) {
            jws = Jwts.parserBuilder()
                    .setSigningKey(getRsaPublicKey())
                    .build()
                    .parseClaimsJws(token);
        } else {
            jws = Jwts.parserBuilder()
                    .setSigningKey(getHmacKey())
                    .build()
                    .parseClaimsJws(token);
        }
        return jws.getBody();
    }

    public boolean validate(String token) {
        try {
            Claims c = parseClaims(token);
            // parser will throw on expiry; this is just defensive
            return c.getExpiration() == null || c.getExpiration().getTime() > System.currentTimeMillis();
        } catch (Exception e) {
            return false;
        }
    }
}

xxx

package com.fincore.gateway.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

/**
 * DB entity to store active session info for a user.
 * This mirrors what your Login service stores.
 */
@Entity
@Table(name = "user_session")
@Getter @Setter @NoArgsConstructor
public class UserSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // username or user id used in the JWT "sub" claim
    @Column(nullable = false, unique = true)
    private String username;

    // server-side session id (a UUID), stored at login
    @Column(nullable = false)
    private String sessionId;

    // (optional) store current access token id or token fingerprint
    @Column(length = 2048)
    private String token;

    // expiry timestamp (seconds since epoch)
    private Instant expiresAt;
}


xxxx


package com.fincore.gateway.repository;

import com.fincore.gateway.entity.UserSession;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserSessionRepository extends JpaRepository<UserSession, Long> {
    Optional<UserSession> findByUsername(String username);
}


xxxc

package com.fincore.gateway.service;

import com.fincore.gateway.entity.UserSession;
import com.fincore.gateway.repository.UserSessionRepository;
import com.fincore.gateway.security.JwtUtil;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.ReactiveStringRedisTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;

/**
 * Centralized token/session validation:
 * - Validate signature & expiry via JwtUtil (local)
 * - Check Redis (fast) for token/session presence
 * - Fallback to DB (JPA) if Redis is disabled or missing record
 *
 * NOTE: JPA calls are blocking; we call them on boundedElastic scheduler.
 */
@Service
@RequiredArgsConstructor
public class TokenValidationService {

    private final JwtUtil jwtUtil;
    private final UserSessionRepository sessionRepository;
    private final ReactiveStringRedisTemplate redisTemplate;

    @Value("${redis.enabled:true}")
    private boolean redisEnabled;

    // Key prefixes (customize if needed)
    private static final String TOKEN_KEY_PREFIX = "auth:token:";    // token id or token text
    private static final String SESSION_KEY_PREFIX = "auth:session:"; // username -> sessionId

    @PostConstruct
    void init() {
        // nothing
    }

    /**
     * Validate token end-to-end:
     * 1) local signature + expiry check (fast)
     * 2) redis check: token key exists AND session mapping matches
     * 3) fallback to DB session record (blocking, executed on boundedElastic)
     */
    public Mono<Boolean> validateToken(String token) {
        if (!jwtUtil.validate(token)) {
            return Mono.just(false);
        }

        var claims = jwtUtil.parseClaims(token);
        String username = claims.getSubject();
        if (username == null) return Mono.just(false);

        // jti claim if present else fallback to token fingerprint (use token directly if you don't have jti)
        Object jtiObj = claims.get("jti");
        String tokenId = jtiObj != null ? String.valueOf(jtiObj) : token;

        if (redisEnabled) {
            // check both token key and session mapping in redis
            String tokenKey = TOKEN_KEY_PREFIX + tokenId;
            String sessionKey = SESSION_KEY_PREFIX + username;
            return redisTemplate.opsForValue().get(tokenKey)
                    .flatMap(v -> {
                        if (v == null) return Mono.just(false);
                        // check session mapping
                        return redisTemplate.opsForValue().get(sessionKey)
                                .map(sessVal -> sessVal != null && sessVal.equals(claims.get("sessionId")))
                                .defaultIfEmpty(false);
                    })
                    .defaultIfEmpty(false)
                    // if redis says false, fallback to DB lookup
                    .flatMap(ok -> ok ? Mono.just(true) : validateTokenFromDb(username, token));
        } else {
            // Redis disabled -> use DB
            return validateTokenFromDb(username, token);
        }
    }

    /**
     * Blocking DB lookup performed on boundedElastic scheduler:
     * Ensures DB session exists and matches provided token/sessionId.
     */
    private Mono<Boolean> validateTokenFromDb(String username, String token) {
        return Mono.fromCallable(() -> {
            UserSession us = sessionRepository.findByUsername(username).orElse(null);
            if (us == null) return false;
            // compare either token text or sessionId depending on your design
            if (us.getToken() != null && !us.getToken().isBlank()) {
                return token.equals(us.getToken());
            } else {
                // fallback: compare sessionId claim
                // parse again and check claim sessionId
                var cl = jwtUtil.parseClaims(token);
                Object sid = cl.get("sessionId");
                return sid != null && sid.equals(us.getSessionId());
            }
        }).subscribeOn(Schedulers.boundedElastic());
    }

    /**
     * Helper used by auth service: store token & session in Redis with TTL.
     * (Auth service should call this after successful login; not gateway)
     */
    public Mono<Void> storeTokenInRedis(String tokenId, String username, String sessionId, long ttlSeconds) {
        if (!redisEnabled) return Mono.empty();
        String tokenKey = TOKEN_KEY_PREFIX + tokenId;
        String sessionKey = SESSION_KEY_PREFIX + username;
        return redisTemplate.opsForValue().set(tokenKey, "1", Duration.ofSeconds(ttlSeconds))
                .then(redisTemplate.opsForValue().set(sessionKey, sessionId, Duration.ofSeconds(ttlSeconds)))
                .then();
    }
}


xxxcc

package com.fincore.gateway.filter;

import com.fincore.gateway.service.TokenValidationService;
import com.fincore.gateway.security.JwtUtil;
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
 * Global JWT filter that:
 * - Bypasses configured whitelist (e.g., /auth/**, /public/**)
 * - Validates JWT locally and checks Redis/DB via TokenValidationService
 * - Injects X-User-Id and X-Session-Id headers for downstream services
 */
@Component
@Slf4j
public class JwtAuthenticationFilter implements GlobalFilter, Ordered {

    private final JwtUtil jwtUtil;
    private final TokenValidationService tokenService;
    private final List<String> bypassList;

    public JwtAuthenticationFilter(JwtUtil jwtUtil,
                                   TokenValidationService tokenService,
                                   @Value("${gateway.bypass.urls:/auth/**,/public/**,/actuator/**}") String bypassUrls) {
        this.jwtUtil = jwtUtil;
        this.tokenService = tokenService;
        this.bypassList = Arrays.stream(bypassUrls.split(","))
                .map(String::trim).collect(Collectors.toList());
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String path = exchange.getRequest().getURI().getPath();
        log.debug("JwtAuthenticationFilter: incoming request path={}", path);

        // allow bypass paths (simple prefix match for /**)
        for (String pattern : bypassList) {
            String p = pattern.trim();
            if (p.endsWith("/**")) p = p.substring(0, p.length() - 3);
            if (path.startsWith(p)) {
                log.debug("Bypassing JWT check for path '{}'", path);
                return chain.filter(exchange);
            }
        }

        // Allow OPTIONS (CORS preflight)
        if ("OPTIONS".equalsIgnoreCase(exchange.getRequest().getMethodValue())) {
            return chain.filter(exchange);
        }

        String auth = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        if (auth == null || !auth.startsWith("Bearer ")) {
            log.warn("Missing Authorization header for request {}", path);
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }

        String token = auth.substring(7);

        // Validate locally & check redis/db
        return tokenService.validateToken(token)
                .flatMap(valid -> {
                    if (!Boolean.TRUE.equals(valid)) {
                        log.warn("Token is invalid/revoked for request {}", path);
                        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                        return exchange.getResponse().setComplete();
                    }
                    // token valid: parse claims and set headers for downstream
                    Claims claims = jwtUtil.parseClaims(token);
                    String subject = claims.getSubject();
                    Object sid = claims.get("sessionId");
                    ServerWebExchange mutated = exchange.mutate()
                            .request(exchange.getRequest().mutate()
                                    .header("X-User-Id", subject == null ? "" : subject)
                                    .header("X-Session-Id", sid == null ? "" : String.valueOf(sid))
                                    .build())
                            .build();
                    log.debug("Token validated for user={}, path={}", subject, path);
                    return chain.filter(mutated);
                });
    }

    @Override
    public int getOrder() {
        // Run early
        return -1;
    }
}


xxxc

package com.fincore.gateway.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * Build routes from a comma-separated list defined in application.properties:
 *
 *    gateway.services=orders,products,users,...
 *
 * For each service "X" a route is created:
 *   path /X/** --> forward to http://X:8080 (K8s DNS)
 * and rewrite the path to remove the "/X" prefix before forwarding.
 *
 * This makes adding/removing services a config change only.
 */
@Slf4j
@Configuration
public class GatewayRoutesConfig {

    // comma-separated list of service names (no spaces recommended)
    @Value("${gateway.services:}")
    private String gatewayServicesCsv;

    @Value("${gateway.service.default-port:8080}")
    private int defaultPort;

    @Bean
    public RouteLocator customRoutes(RouteLocatorBuilder builder) {
        var routes = builder.routes();

        if (gatewayServicesCsv == null || gatewayServicesCsv.isBlank()) {
            log.warn("No gateway.services configured - gateway will have no dynamic routes");
            return routes.build();
        }

        List<String> services = List.of(gatewayServicesCsv.split(","));
        for (String s : services) {
            String svc = s.trim();
            if (svc.isEmpty()) continue;
            String target = "http://" + svc + ":" + defaultPort;

            log.info("Registering route for service '{}' -> {}", svc, target);

            routes.route(svc, r -> r
                    .path("/" + svc + "/**")
                    .filters(f -> f
                            .rewritePath("/" + svc + "/(?<segment>.*)", "/${segment}")
                    )
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
 * Simple property-driven CORS configuration for the Gateway.
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


xxxc

# Base properties (common)
spring.application.name=api-gateway
server.port=8080

# Logging
logging.level.org.springframework=INFO
logging.level.com.fincore.gateway=DEBUG

# JWT mode: "hmac" or "rsa"
security.jwt.mode=hmac

# For HS256 use base64 secret (example placeholder - replace!)
# NOTE: If using hmac and you already used security.jwt.public-key earlier, set this to the base64 secret instead.
security.jwt.secret=REPLACE_WITH_BASE64_SECRET_BASE64_LENGTH_32+

# If using RSA, put PEM public key here (security.jwt.mode=rsa)
security.jwt.public-key=

# Gateway routes: comma-separated service names
gateway.services=orders,products,users,inventory,payments,auth

# Default port for services (when constructing http://<service>:<port>)
gateway.service.default-port=8080

# Bypass paths (no auth)
gateway.bypass.urls=/auth/**,/public/**,/actuator/**

# Toggle Redis usage
redis.enabled=true

# Redis connection (dev defaults)
spring.data.redis.host=localhost
spring.data.redis.port=6379
spring.data.redis.password=   # set in production

# DB (JPA) -- configure to your Oracle DB used by your login service
spring.datasource.url=jdbc:oracle:thin:@//HOST:PORT/SERVICE
spring.datasource.username=your_db_user
spring.datasource.password=your_db_password
spring.jpa.hibernate.ddl-auto=none
spring.jpa.show-sql=false


xxxc

spring.profiles.active=dev

# For local dev you can disable redis if you don't run it:
redis.enabled=false

# If using hmac in dev - example secret base64 (DO NOT use in prod):
security.jwt.mode=hmac
security.jwt.secret=ZmFrZV9iYXNlNjRfc2VjcmV0XzEyMzQ1Ng==  # base64 for "fake_base64_secret_123456"


xxxc
spring.profiles.active=uat

# UAT: redis usually enabled
redis.enabled=true
spring.data.redis.host=uat-redis-host
spring.data.redis.port=6379
spring.data.redis.password=uatRedisPassword

# JWT: likely RSA in UAT
security.jwt.mode=rsa
security.jwt.public-key=-----BEGIN PUBLIC KEY-----\n...paste PEM here...\n-----END PUBLIC KEY-----


xxx

spring.profiles.active=prod

redis.enabled=true
spring.data.redis.host=redis
spring.data.redis.port=6379
# Use K8s Secrets or environment variables to inject password
spring.data.redis.password=${REDIS_PASSWORD:}

security.jwt.mode=rsa
security.jwt.public-key=${JWT_PUBLIC_KEY_PEM:}


xxxc

version: '3.8'
services:
  redis:
    image: redis:7-alpine
    container_name: redis
    command: ["redis-server", "/usr/local/etc/redis/redis.conf"]
    volumes:
      - ./redis-data:/data
      - ./redis.conf:/usr/local/etc/redis/redis.conf:ro
    ports:
      - "6379:6379"
    restart: unless-stopped


