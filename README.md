spring.application.name=api-gateway
server.port=8080
spring.profiles.active=dev

# Redis (dev)
spring.data.redis.host=localhost
spring.data.redis.port=6379

# bypass comma separated
gateway.bypass.urls=/auth/login,/auth/refresh,/public/**,/actuator/health

# CORS allowed origins (comma separated)
gateway.cors.allowed-origins=http://localhost:3000

# JWT
jwt.public-key=classpath:keys/public.pem

# route example (use k8s dns in uat/prod)
product.service.url=http://product-service:8080


###

package com.example.gateway.filter;

import com.example.gateway.service.TokenBlacklistService;
import com.example.gateway.util.JwtUtil;
import io.jsonwebtoken.Claims;
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

@Component
public class JwtAuthenticationFilter implements GlobalFilter, Ordered {

    private final JwtUtil jwtUtil;
    private final TokenBlacklistService blacklistService;
    private final List<String> bypassList;

    public JwtAuthenticationFilter(JwtUtil jwtUtil,
                                   TokenBlacklistService blacklistService,
                                   @Value("${gateway.bypass.urls}") String bypassUrls) {
        this.jwtUtil = jwtUtil;
        this.blacklistService = blacklistService;
        this.bypassList = Arrays.stream(bypassUrls.split(","))
                                .map(String::trim).collect(Collectors.toList());
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String path = exchange.getRequest().getURI().getPath();

        // allow if matches any bypass prefix or wildcard (simple startsWith for /**)
        for (String pattern : bypassList) {
            String p = pattern.trim();
            if (p.endsWith("/**")) p = p.substring(0, p.length() - 3);
            if (path.startsWith(p)) {
                return chain.filter(exchange);
            }
        }

        String authHeader = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }

        String token = authHeader.substring(7);
        if (!jwtUtil.validateToken(token)) {
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }

        // single-session + blacklist
        String subject = jwtUtil.getSubject(token);
        String sessionId = jwtUtil.getSessionId(token);
        return blacklistService.isBlacklistedMono(token)
                .flatMap(isBlacklisted -> {
                    if (Boolean.TRUE.equals(isBlacklisted)) {
                        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                        return exchange.getResponse().setComplete();
                    }
                    // check session mapping in Redis
                    return jwtUtil.isSessionActiveMono(subject, sessionId)
                            .flatMap(active -> {
                                if (!Boolean.TRUE.equals(active)) {
                                    exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                                    return exchange.getResponse().setComplete();
                                }
                                // set forwarded headers for downstream services (defense in depth)
                                exchange.getRequest().mutate()
                                          .header("X-User-Id", subject)
                                          .header("X-Session-Id", sessionId)
                                          .build();
                                return chain.filter(exchange);
                            });
                });
    }

    @Override
    public int getOrder() {
        return -1;
    }
}


xxxx

@Configuration
public class RateLimiterConfig {
    @Bean
    public KeyResolver userKeyResolver() {
        return exchange -> {
            String auth = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
            if (auth != null && auth.startsWith("Bearer ")) {
                try {
                    String token = auth.substring(7);
                    String user = jwtUtil.getSubject(token); // inject JwtUtil here
                    return Mono.just(user);
                } catch (Exception ignore) { }
            }
            String ip = exchange.getRequest().getRemoteAddress() != null
                    ? exchange.getRequest().getRemoteAddress().getAddress().getHostAddress()
                    : "unknown";
            return Mono.just(ip);
        };
    }
}

xxxx

@Bean
public RouteLocator routes(RouteLocatorBuilder rlb, ReactiveRedisRateLimiter rateLimiter) {
    return rlb.routes()
      .route("product-service", r -> r.path("/products/**")
        .filters(f -> f.stripPrefix(1)
                       .filter(jwtFilter)                 // or use GlobalFilter only
                       .requestRateLimiter(config -> config.setRateLimiter(rateLimiter))
                       .circuitBreaker(cb -> cb.setName("productCB")
                                               .setFallbackUri("forward:/fallback/product"))
                       .retry(retry -> retry.setRetries(2).setStatuses(HttpStatus.INTERNAL_SERVER_ERROR)))
        .uri(productServiceUri))
      .build();
}

xxxx

spring.codec.max-in-memory-size=2MB
spring.cloud.gateway.httpclient.connect-timeout=5000
spring.cloud.gateway.httpclient.response-timeout=15s


xxxx

@Component
public class SecurityHeadersFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        exchange.getResponse().getHeaders().add("Strict-Transport-Security","max-age=31536000; includeSubDomains");
        exchange.getResponse().getHeaders().add("X-Content-Type-Options","nosniff");
        exchange.getResponse().getHeaders().add("X-Frame-Options","DENY");
        exchange.getResponse().getHeaders().add("Referrer-Policy","no-referrer");
        exchange.getResponse().getHeaders().add("X-XSS-Protection","1; mode=block");
        exchange.getResponse().getHeaders().add("Content-Security-Policy","default-src 'self'");
        return chain.filter(exchange);
    }
    @Override public int getOrder() { return 0; }
}


xxx

@Configuration
public class CorsConfig {
    @Value("${gateway.cors.allowed-origins}")
    private String allowedOrigins;

    @Bean
    public CorsWebFilter corsWebFilter() {
        CorsConfiguration config = new CorsConfiguration();
        Arrays.stream(allowedOrigins.split(",")).forEach(config::addAllowedOrigin);
        config.addAllowedMethod("*");
        config.addAllowedHeader("*");
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return new CorsWebFilter(source);
    }
}


xxxx

management.endpoints.web.exposure.include=health,info,prometheus
management.endpoint.health.show-details=never
management.endpoints.web.base-path=/management


xxxx

<dependency>
  <groupId>io.micrometer</groupId>
  <artifactId>micrometer-registry-prometheus</artifactId>
</dependency>
<dependency>
  <groupId>io.opentelemetry</groupId>
  <artifactId>opentelemetry-api</artifactId>
</dependency>

xxx

package com.example.gateway.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.time.Instant;
import java.util.Base64;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtil {

    @Value("${jwt.public.key}")
    private String publicKeyString;

    @Value("${jwt.private.key}")
    private String privateKeyString;

    @Value("${jwt.expiration-ms}")
    private long jwtExpirationMs;

    private PublicKey publicKey;
    private PrivateKey privateKey;

    @PostConstruct
    public void initKeys() throws Exception {
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");

        byte[] publicBytes = Base64.getDecoder().decode(publicKeyString);
        X509EncodedKeySpec publicSpec = new X509EncodedKeySpec(publicBytes);
        this.publicKey = keyFactory.generatePublic(publicSpec);

        byte[] privateBytes = Base64.getDecoder().decode(privateKeyString);
        PKCS8EncodedKeySpec privateSpec = new PKCS8EncodedKeySpec(privateBytes);
        this.privateKey = keyFactory.generatePrivate(privateSpec);
    }

    // Generate token with claims
    public String generateToken(String username, Map<String, Object> extraClaims) {
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(username)
                .setIssuedAt(Date.from(Instant.now()))
                .setExpiration(Date.from(Instant.now().plusMillis(jwtExpirationMs)))
                .signWith(privateKey, SignatureAlgorithm.RS256)
                .compact();
    }

    // Validate token
    public boolean validateToken(String token, String username) {
        final String tokenUsername = extractUsername(token);
        return (tokenUsername.equals(username) && !isTokenExpired(token));
    }

    // Extract username
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // Extract claim generically
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = parseToken(token);
        return claimsResolver.apply(claims);
    }

    // Parse and verify token
    private Claims parseToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(publicKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // Check expiration
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
}