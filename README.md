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

  <groupId>com.example</groupId>
  <artifactId>secure-gateway</artifactId>
  <version>0.0.1-SNAPSHOT</version>
  <name>secure-gateway</name>
  <description>Spring Cloud Gateway with local JWT validation, Redis sessions & rate limiting</description>

  <properties>
    <java.version>17</java.version>
    <!-- Spring Cloud "Leyton" matches Boot 3.5.x -->
    <spring-cloud.version>2024.0.3</spring-cloud.version>
    <jjwt.version>0.12.5</jjwt.version>
  </properties>

  <dependencies>
    <!-- Core reactive web runtime used by Spring Cloud Gateway -->
    <dependency>
      <groupId>org.springframework.cloud</groupId>
      <artifactId>spring-cloud-starter-gateway-server-webflux</artifactId>
    </dependency>

    <!-- Reactive Redis (Lettuce) -->
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-data-redis-reactive</artifactId>
    </dependency>

    <!-- JWT (0.12.x API) -->
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

    <!-- Actuator + Prometheus registry -->
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-actuator</artifactId>
    </dependency>
    <dependency>
      <groupId>io.micrometer</groupId>
      <artifactId>micrometer-registry-prometheus</artifactId>
    </dependency>

    <!-- OPTIONAL: If you want to load service info from Kubernetes API (you can remove this). 
         For DNS-only discovery, you don't need it. -->
    <!--
    <dependency>
      <groupId>org.springframework.cloud</groupId>
      <artifactId>spring-cloud-starter-kubernetes-client</artifactId>
    </dependency>
    -->

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

package com.example.gateway.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import javax.crypto.SecretKey;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Parses & validates JWT locally (no network).
 * Supports:
 *  - HS256 via base64 secret (security.jwt.mode=hmac)
 *  - RS256 via PEM or base64 DER public key (security.jwt.mode=rsa)
 *
 * Also exposes a simple in-memory session map if Redis is disabled.
 */
@Component
public class JwtUtil {

    @Value("${security.jwt.mode:hmac}") // hmac | rsa
    private String mode;

    @Value("${security.jwt.secret:}") // base64 secret for HS256
    private String hmacSecret;

    @Value("${security.jwt.public-key:}") // PEM or base64 DER for RS256
    private String rsaPublicKey;

    @Value("${security.session.enforce:true}")
    private boolean enforceSession;

    @Value("${security.session.ttl-seconds:3600}")
    private long sessionTtlSeconds;

    @Value("${redis.enabled:true}")
    private boolean redisEnabled;

    /** In-memory fallback store when redis.enabled=false */
    private final Map<String, SessionEntry> localSessionMap = new ConcurrentHashMap<>();
    private final Map<String, Long> localBlacklist = new ConcurrentHashMap<>();

    private volatile SecretKey hsKey;
    private volatile PublicKey rsPublicKey;

    // ----- Key init -----

    private SecretKey getHsKey() {
        if (hsKey == null) {
            if (hmacSecret == null || hmacSecret.isBlank()) {
                throw new IllegalStateException("security.jwt.secret (base64) is required for HS256 mode");
            }
            byte[] secret = Decoders.BASE64.decode(hmacSecret);
            hsKey = Keys.hmacShaKeyFor(secret);
        }
        return hsKey;
    }

    private PublicKey getRsPublicKey() {
        if (rsPublicKey == null) {
            if (rsaPublicKey == null || rsaPublicKey.isBlank()) {
                throw new IllegalStateException("security.jwt.public-key is required for RS256 mode");
            }
            try {
                String cleaned = rsaPublicKey
                        .replace("-----BEGIN PUBLIC KEY-----", "")
                        .replace("-----END PUBLIC KEY-----", "")
                        .replaceAll("\\s", "");
                byte[] keyBytes;
                // If user pasted raw base64 without PEM header, cleaned==original base64
                keyBytes = Base64.getDecoder().decode(cleaned);
                X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
                KeyFactory kf = KeyFactory.getInstance("RSA");
                rsPublicKey = kf.generatePublic(spec);
            } catch (Exception e) {
                throw new IllegalStateException("Failed to parse RS256 public key. Provide PEM or base64 DER.", e);
            }
        }
        return rsPublicKey;
    }

    // ----- Token parsing -----

    public Claims parseClaims(String token) throws JwtException {
        if ("rsa".equalsIgnoreCase(mode)) {
            return Jwts.parser().verifyWith(getRsPublicKey()).build()
                    .parseSignedClaims(token).getPayload();
        } else { // default hmac
            return Jwts.parser().verifyWith(getHsKey()).build()
                    .parseSignedClaims(token).getPayload();
        }
    }

    public boolean validateToken(String token) {
        try {
            Claims c = parseClaims(token);
            Date exp = c.getExpiration();
            return exp != null && exp.toInstant().isAfter(Instant.now());
        } catch (Exception ex) {
            return false;
        }
    }

    public String getSubject(String token) {
        return parseClaims(token).getSubject();
    }

    public String getSessionId(String token) {
        Object sid = parseClaims(token).get("sessionId");
        return sid == null ? null : String.valueOf(sid);
    }

    @SuppressWarnings("unchecked")
    public List<String> getScopes(String token) {
        Object sc = parseClaims(token).get("scope");
        if (sc == null) return List.of();
        if (sc instanceof String s) return Arrays.asList(s.split("\\s+|,"));
        if (sc instanceof Collection<?> col) {
            List<String> out = new ArrayList<>();
            col.forEach(o -> out.add(String.valueOf(o)));
            return out;
        }
        return List.of(String.valueOf(sc));
    }

    // ----- In-memory fallback (used when redis.enabled=false) -----

    public Mono<Boolean> inMemoryIsBlacklisted(String token) {
        if (redisEnabled) return Mono.just(false);
        Long until = localBlacklist.get(token);
        if (until == null) return Mono.just(false);
        if (until < System.currentTimeMillis()) {
            localBlacklist.remove(token);
            return Mono.just(false);
        }
        return Mono.just(true);
    }

    public Mono<Void> inMemoryBlacklist(String token, long ttlSeconds) {
        if (redisEnabled) return Mono.empty();
        localBlacklist.put(token, System.currentTimeMillis() + ttlSeconds * 1000);
        return Mono.empty();
    }

    public Mono<Boolean> inMemoryIsSessionActive(String subject, String sessionId) {
        if (redisEnabled || !enforceSession) return Mono.just(true);
        if (subject == null || sessionId == null) return Mono.just(false);
        SessionEntry entry = localSessionMap.get(subject);
        if (entry == null) return Mono.just(false);
        // expire
        if (entry.expiresAt < System.currentTimeMillis()) {
            localSessionMap.remove(subject);
            return Mono.just(false);
        }
        return Mono.just(Objects.equals(entry.sessionId, sessionId));
    }

    public Mono<Void> inMemoryStoreSession(String subject, String sessionId) {
        if (redisEnabled || !enforceSession) return Mono.empty();
        localSessionMap.put(subject,
                new SessionEntry(sessionId, System.currentTimeMillis() + sessionTtlSeconds * 1000));
        return Mono.empty();
    }

    private record SessionEntry(String sessionId, long expiresAt) {}
}


xxx

package com.example.gateway.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.ReactiveStringRedisTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.Duration;

/**
 * Blacklists tokens (e.g., after logout) using Redis if enabled,
 * otherwise delegates to JwtUtil's in-memory fallback.
 */
@Service
public class TokenBlacklistService {

    private final ReactiveStringRedisTemplate redis;
    private final JwtUtil jwtUtil;

    @Value("${redis.enabled:true}")
    private boolean redisEnabled;

    public TokenBlacklistService(ReactiveStringRedisTemplate redis, JwtUtil jwtUtil) {
        this.redis = redis;
        this.jwtUtil = jwtUtil;
    }

    public Mono<Void> blacklist(String token, long ttlSeconds) {
        if (!redisEnabled) return jwtUtil.inMemoryBlacklist(token, ttlSeconds);
        String key = "bl:" + token;
        return redis.opsForValue().set(key, "1", Duration.ofSeconds(ttlSeconds)).then();
    }

    public Mono<Boolean> isBlacklisted(String token) {
        if (!redisEnabled) return jwtUtil.inMemoryIsBlacklisted(token);
        return redis.opsForValue().get("bl:" + token).map(v -> true).defaultIfEmpty(false);
    }
}

xxx. 

package com.example.gateway.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.ReactiveStringRedisTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.Duration;

/**
 * Enforces "single active session per user".
 * Stores subject -> sessionId in Redis (or in-memory fallback through JwtUtil).
 */
@Service
public class SessionService {

    private final ReactiveStringRedisTemplate redis;
    private final JwtUtil jwtUtil;

    @Value("${redis.enabled:true}")
    private boolean redisEnabled;

    @Value("${security.session.enforce:true}")
    private boolean enforceSession;

    @Value("${security.session.ttl-seconds:3600}")
    private long ttl;

    public SessionService(ReactiveStringRedisTemplate redis, JwtUtil jwtUtil) {
        this.redis = redis;
        this.jwtUtil = jwtUtil;
    }

    public Mono<Boolean> isActive(String subject, String sessionId) {
        if (!enforceSession) return Mono.just(true);
        if (!redisEnabled) return jwtUtil.inMemoryIsSessionActive(subject, sessionId);
        String key = "session:" + subject;
        return redis.opsForValue().get(key)
                .map(stored -> stored.equals(sessionId))
                .defaultIfEmpty(false);
    }

    public Mono<Void> store(String subject, String sessionId) {
        if (!enforceSession) return Mono.empty();
        if (!redisEnabled) return jwtUtil.inMemoryStoreSession(subject, sessionId);
        String key = "session:" + subject;
        return redis.opsForValue().set(key, sessionId, Duration.ofSeconds(ttl)).then();
    }
}

xxxc

package com.example.gateway.security;

import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Global auth filter:
 *  - Bypasses configurable paths (e.g., /auth/**, /public/**, /actuator/**)
 *  - Allows CORS preflight (OPTIONS)
 *  - Validates JWT locally (HS256 or RS256)
 *  - Enforces "single session" + blacklist
 *  - Injects X-User-Id, X-Session-Id, X-Scopes for downstream services
 */
@Component
public class JwtAuthenticationFilter implements GlobalFilter, Ordered {

    private final JwtUtil jwt;
    private final SessionService sessions;
    private final TokenBlacklistService blacklist;

    private final List<String> bypassList;

    @Value("${security.session.enforce:true}")
    private boolean enforceSession;

    @Value("${security.debug-errors:false}")
    private boolean debugErrors;

    public JwtAuthenticationFilter(
            JwtUtil jwt,
            SessionService sessions,
            TokenBlacklistService blacklist,
            @Value("${gateway.bypass.urls:/auth/**,/public/**,/actuator/**}") String bypassUrls) {
        this.jwt = jwt;
        this.sessions = sessions;
        this.blacklist = blacklist;
        this.bypassList = Arrays.stream(bypassUrls.split(","))
                .map(String::trim).collect(Collectors.toList());
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        var req = exchange.getRequest();

        // Permit CORS pre-flight
        if (req.getMethod() == HttpMethod.OPTIONS) {
            return chain.filter(exchange);
        }

        String path = req.getURI().getPath();
        if (isBypassed(path)) {
            return chain.filter(exchange);
        }

        String auth = req.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        if (auth == null || !auth.startsWith("Bearer ")) {
            return reject(exchange, "Missing Bearer token");
        }
        String token = auth.substring(7);

        if (!jwt.validateToken(token)) {
            return reject(exchange, "Invalid or expired token");
        }

        return blacklist.isBlacklisted(token)
                .flatMap(black -> {
                    if (black) return reject(exchange, "Token revoked");
                    // Check session constraint
                    String sub = jwt.getSubject(token);
                    String sid = jwt.getSessionId(token);
                    Mono<Boolean> ok = enforceSession ? sessions.isActive(sub, sid) : Mono.just(true);
                    return ok.flatMap(active -> {
                        if (!active) return reject(exchange, "Session no longer active");
                        Claims claims = jwt.parseClaims(token);
                        var mut = req.mutate()
                                .header("X-User-Id", sub == null ? "" : sub)
                                .header("X-Session-Id", sid == null ? "" : sid)
                                .header("X-Scopes", String.join(",", jwt.getScopes(token)))
                                .header("X-JWT-Exp", claims.getExpiration() == null ? "" :
                                        String.valueOf(claims.getExpiration().getTime()))
                                .build();
                        return chain.filter(exchange.mutate().request(mut).build());
                    });
                });
    }

    private boolean isBypassed(String path) {
        for (String pattern : bypassList) {
            String p = pattern.trim();
            if (p.endsWith("/**")) p = p.substring(0, p.length() - 3);
            if (path.startsWith(p)) return true;
        }
        return false;
    }

    private Mono<Void> reject(ServerWebExchange exchange, String msg) {
        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        if (debugErrors) {
            exchange.getResponse().getHeaders().add("WWW-Authenticate", "Bearer error=\"" + msg + "\"");
        }
        return exchange.getResponse().setComplete();
    }

    @Override
    public int getOrder() {
        // run very early, but after some internal filters; -1 is fine
        return -1;
    }
}

xxxx

package com.example.gateway.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

/**
 * Simple CORS config controlled by properties.
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
        CorsConfiguration config = new CorsConfiguration();
        for (String o : allowedOrigins.split(",")) config.addAllowedOrigin(o.trim());
        for (String m : allowedMethods.split(",")) config.addAllowedMethod(HttpMethod.valueOf(m.trim()));
        for (String h : allowedHeaders.split(",")) config.addAllowedHeader(h.trim());
        config.setAllowCredentials(true);
        config.addExposedHeader("X-Request-Id");

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return new CorsWebFilter(source);
    }
}

xxx

package com.example.gateway.config;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * Adds common security headers to every response.
 */
@Component
public class SecurityHeadersFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        return chain.filter(exchange).then(Mono.fromRunnable(() -> {
            HttpHeaders h = exchange.getResponse().getHeaders();
            h.addIfAbsent("X-Content-Type-Options", "nosniff");
            h.addIfAbsent("X-Frame-Options", "DENY");
            h.addIfAbsent("X-XSS-Protection", "0");
            h.addIfAbsent("Referrer-Policy", "no-referrer");
            h.addIfAbsent("Content-Security-Policy", "default-src 'none'; frame-ancestors 'none'; base-uri 'none';");
        }));
    }

    @Override
    public int getOrder() { return 1000; } // run late (after route execution)
}

xxx

package com.example.gateway.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.factory.rewrite.RewritePathGatewayFilterFactory;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Defines routes. You point to services by DNS names when on Kubernetes (e.g., http://service-a:8080).
 * For local dev, point to localhost:port.
 *
 * We also add a sample route to httpbin to test quickly.
 */
@Configuration
public class GatewayRoutesConfig {

    @Value("${routes.service-a.uri:http://localhost:8090}")
    private String serviceAUri;

    @Value("${routes.httpbin.uri:https://httpbin.org}")
    private String httpbinUri;

    @Value("${ratelimit.enabled:true}")
    private boolean rateLimitEnabled;

    @Bean
    public RouteLocator customRoutes(RouteLocatorBuilder builder, OptionalRateLimiterProvider rlProvider) {
        var routes = builder.routes()

            // Example: /service-a/** -> http://service-a:8080/**
            .route("service-a", r -> r
                .path("/service-a/**")
                .filters(f -> {
                    if (rateLimitEnabled && rlProvider.redisRateLimiter() != null) {
                        f.requestRateLimiter(c -> c.setRateLimiter(rlProvider.redisRateLimiter()));
                    }
                    f.rewritePath("/service-a/(?<segment>.*)", "/${segment}");
                    return f;
                })
                .uri(serviceAUri))

            // Quick test route that requires auth (except you can add /public/** bypass):
            .route("httpbin", r -> r
                .path("/httpbin/**")
                .filters(f -> f.rewritePath("/httpbin/(?<segment>.*)", "/${segment}"))
                .uri(httpbinUri))

            .build();

        // Make sure the original request URL is preserved for diagnostics
        routes.getRoutes().subscribe(rd ->
            rd.getFilters().add((exchange, chain) -> {
                exchange.getAttributes().put(ServerWebExchangeUtils.GATEWAY_REQUEST_URL_ATTR, exchange.getRequest().getURI());
                return chain.filter(exchange);
            })
        );
        return routes;
    }
}
xxxc
package com.example.gateway.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.ratelimit.RedisRateLimiter;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.ReactiveStringRedisTemplate;

/**
 * Provides RedisRateLimiter only when redis & ratelimit are enabled.
 */
@Configuration
public class OptionalRateLimiterProvider {

    private final RedisRateLimiter redisRateLimiter;

    public OptionalRateLimiterProvider(
            ReactiveStringRedisTemplate redisTemplate,
            @Value("${redis.enabled:true}") boolean redisEnabled,
            @Value("${ratelimit.enabled:true}") boolean rlEnabled,
            @Value("${ratelimit.replenishRate:5}") int replenishRate,
            @Value("${ratelimit.burstCapacity:10}") int burstCapacity
    ) {
        if (redisEnabled && rlEnabled) {
            this.redisRateLimiter = new RedisRateLimiter(replenishRate, burstCapacity);
        } else {
            this.redisRateLimiter = null;
        }
    }

    public RedisRateLimiter redisRateLimiter() {
        return redisRateLimiter;
    }
}

xxx

package com.example.gateway.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Public ping endpoint (bypassed by auth filter via /public/**).
 */
@RestController
public class PublicController {

    @GetMapping("/public/ping")
    public String ping() {
        return "pong";
    }
}

xxx

package com.example.gateway.api;

import com.example.gateway.security.TokenBlacklistService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

/**
 * Minimal logout endpoint in the gateway to revoke the current token.
 * NOTE: This is OPTIONAL. If your Auth service already handles logout,
 * you can skip this controller. The gateway blacklist is helpful to immediately
 * block the token at the edge.
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final TokenBlacklistService blacklist;

    @Value("${security.token.ttl-seconds:3600}")
    private long tokenTtl;

    public AuthController(TokenBlacklistService blacklist) {
        this.blacklist = blacklist;
    }

    @PostMapping("/logout")
    public Mono<String> logout(@RequestHeader(HttpHeaders.AUTHORIZATION) String auth) {
        if (auth == null || !auth.startsWith("Bearer ")) {
            return Mono.just("Missing Bearer token");
        }
        String token = auth.substring(7);
        return blacklist.blacklist(token, tokenTtl).thenReturn("Logged out");
    }
}


xxx

spring.application.name=secure-gateway
server.port=8080

# -- Actuator --
management.endpoints.web.exposure.include=health,info,prometheus
management.endpoint.health.show-details=never
management.endpoints.web.base-path=/actuator

# -- Logging --
logging.level.org.springframework.cloud.gateway=INFO
logging.level.com.example.gateway=INFO

# -- CORS (comma-separated) --
gateway.cors.allowed-origins=http://localhost:3000
gateway.cors.allowed-methods=GET,POST,PUT,DELETE,OPTIONS
gateway.cors.allowed-headers=*

# -- Auth filter bypass list --
gateway.bypass.urls=/public/**,/auth/**,/actuator/**

# -- JWT mode: hmac or rsa --
security.jwt.mode=hmac

# For HS256: base64 secret (EXAMPLE ONLY; replace for real use!)
# To generate: java -jar jjwt-tool OR any secure generator; must be >=256-bit
security.jwt.secret=REPLACE_WITH_BASE64_SECRET

# For RS256: paste PEM or base64 DER public key (only used when security.jwt.mode=rsa)
security.jwt.public-key=

# Token/session controls
security.token.ttl-seconds=3600
security.session.enforce=true
security.session.ttl-seconds=3600
security.debug-errors=false

# -- Redis toggle & config --
redis.enabled=false
spring.data.redis.host=localhost
spring.data.redis.port=6379
spring.data.redis.timeout=60000

# -- Rate limiting --
ratelimit.enabled=false
ratelimit.replenishRate=5
ratelimit.burstCapacity=10

# -- Example routes (override per env) --
routes.service-a.uri=http://localhost:8090
routes.httpbin.uri=https://httpbin.org

xxxc

spring.profiles.active=dev

# Local HS256 key for dev (example 256-bit key)
security.jwt.mode=hmac
security.jwt.secret=KfFqH8o+JH+2m7h7Wb0m0zQj1tH6bHnq0N7r8kJ3kQk=  # <-- REPLACE

# Turn on Redis locally if you have it
redis.enabled=false
# ratelimit.enabled=true  # enable only if Redis is on


xxx

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.*;

public class TokenGenerator {
    public static void main(String[] args) {
        String secret = "uGp9YSsW41V4dIkoW7SHcoeyDnrUZ+amH+JuNESsQms="; // from properties

        String jwt = Jwts.builder()
                .setSubject("tushar.khade")
                .claim("roles", Arrays.asList("ROLE_USER"))
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 3600_000)) // 1 hour
                .signWith(SignatureAlgorithm.HS256, Base64.getDecoder().decode(secret))
                .compact();

        System.out.println(jwt);
    }
}