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