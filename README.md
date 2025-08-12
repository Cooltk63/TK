XXXXX FILE: src/main/java/com/example/gateway/ApiGatewayApplication.java XXXXX
package com.example.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApiGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayApplication.class, args);
    }
}



xxx

XXXXX FILE: src/main/java/com/example/gateway/util/JwtUtil.java XXXXX
package com.example.gateway.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.ReactiveStringRedisTemplate;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.time.Duration;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtUtil {

    @Value("${security.jwt.public-key}")
    private String publicKeyStr;

    @Value("${security.session.ttl-seconds:3600}")
    private long sessionTtlSeconds;

    private final ReactiveStringRedisTemplate redisTemplate;
    private PublicKey publicKey;

    public JwtUtil(ReactiveStringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @PostConstruct
    public void init() throws Exception {
        byte[] keyBytes = Base64.getDecoder().decode(publicKeyStr);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        this.publicKey = keyFactory.generatePublic(keySpec);
    }

    public boolean validateToken(String token) {
        try {
            Claims claims = getAllClaims(token);
            return claims.getExpiration().after(new Date());
        } catch (Exception e) {
            return false;
        }
    }

    public String getSubject(String token) {
        return getAllClaims(token).getSubject();
    }

    public String getSessionId(String token) {
        Object sessionId = getAllClaims(token).get("sessionId");
        return sessionId != null ? sessionId.toString() : null;
    }

    public Mono<Boolean> isSessionActiveMono(String subject, String sessionId) {
        if (subject == null || sessionId == null) return Mono.just(false);
        String redisKey = "session:" + subject;
        return redisTemplate.opsForValue().get(redisKey)
                .map(stored -> stored.equals(sessionId))
                .defaultIfEmpty(false);
    }

    public Mono<Void> storeSession(String subject, String sessionId) {
        String redisKey = "session:" + subject;
        return redisTemplate.opsForValue()
                .set(redisKey, sessionId, Duration.ofSeconds(sessionTtlSeconds))
                .then();
    }

    private Claims getAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(publicKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}


xxx

XXXXX FILE: src/main/java/com/example/gateway/service/TokenBlacklistService.java XXXXX
package com.example.gateway.service;

import org.springframework.data.redis.core.ReactiveStringRedisTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Service
public class TokenBlacklistService {

    private final ReactiveStringRedisTemplate redisTemplate;

    public TokenBlacklistService(ReactiveStringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public Mono<Void> blacklistToken(String token, long expirySeconds) {
        String key = "blacklist:" + token;
        return redisTemplate.opsForValue()
                .set(key, "true", Duration.ofSeconds(expirySeconds))
                .then();
    }

    public Mono<Boolean> isBlacklistedMono(String token) {
        String key = "blacklist:" + token;
        return redisTemplate.hasKey(key);
    }
}

xxx

XXXXX FILE: src/main/java/com/example/gateway/filter/JwtAuthenticationFilter.java XXXXX
package com.example.gateway.filter;

import com.example.gateway.service.TokenBlacklistService;
import com.example.gateway.util.JwtUtil;
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

        // Allow public endpoints
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

        String subject = jwtUtil.getSubject(token);
        String sessionId = jwtUtil.getSessionId(token);

        return blacklistService.isBlacklistedMono(token)
                .flatMap(isBlacklisted -> {
                    if (isBlacklisted) {
                        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                        return exchange.getResponse().setComplete();
                    }
                    return jwtUtil.isSessionActiveMono(subject, sessionId)
                            .flatMap(active -> {
                                if (!active) {
                                    exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                                    return exchange.getResponse().setComplete();
                                }
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


xxx


XXXXX FILE: src/main/resources/application.properties XXXXX
spring.application.name=api-gateway
server.port=8080

# Gateway Routes
spring.cloud.gateway.routes[0].id=product-service
spring.cloud.gateway.routes[0].uri=http://product-service:8080
spring.cloud.gateway.routes[0].predicates[0]=Path=/products/**
spring.cloud.gateway.routes[0].filters[0]=RequestRateLimiter=redis-rate-limiter.replenishRate=5,redis-rate-limiter.burstCapacity=10
spring.cloud.gateway.routes[0].filters[1]=RewritePath=/products/(?<segment>.*), /${segment}

# Redis Config
spring.data.redis.host=localhost
spring.data.redis.port=6379

# Security
security.jwt.public-key=MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8A...
security.session.ttl-seconds=3600
gateway.bypass.urls=/auth/**,/public/**

# Logging
logging.level.org.springframework.cloud.gateway=INFO

xxx

XXXXX FILE: pom.xml XXXXX
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 
         https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <groupId>com.example</groupId>
    <artifactId>gateway</artifactId>
    <version>1.0.0</version>
    <properties>
        <java.version>17</java.version>
        <spring.boot.version>3.5.4</spring.boot.version>
        <spring.cloud.version>2024.0.3</spring.cloud.version>
    </properties>
    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-webflux</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-gateway</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-redis-reactive</artifactId>
        </dependency>
        <dependency>
            <groupId>io.jsonwebtoken</groupId>
            <artifactId>jjwt-api</artifactId>
            <version>0.11.5</version>
        </dependency>
        <dependency>
            <groupId>io.jsonwebtoken</groupId>
            <artifactId>jjwt-impl</artifactId>
            <version>0.11.5</version>
            <scope>runtime</scope>
        </dependency>
        <dependency>
            <groupId>io.jsonwebtoken</groupId>
            <artifactId>jjwt-jackson</artifactId>
            <version>0.11.5</version>
            <scope>runtime</scope>
        </dependency>
    </dependencies>
    <dependencyManagement>
        <dependencies>
            <dependency>
                <groupId>org.springframework.cloud</groupId>
                <artifactId>spring-cloud-dependencies</artifactId>
                <version>${spring.cloud.version}</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
        </dependencies>
    </dependencyManagement>
</project>