# Spring Cloud Gateway with Advanced Security Implementation

## Architecture Overview

```
┌─────────────────────────────────────────────────────────────────┐
│                    Spring Cloud Gateway                        │
│  ┌─────────────────┐ ┌─────────────────┐ ┌─────────────────┐   │
│  │   JWT Filter    │ │  Rate Limiter   │ │ Session Manager │   │
│  └─────────────────┘ └─────────────────┘ └─────────────────┘   │
└─────────────────────────────────────────────────────────────────┘
                                │
                                ▼
        ┌─────────────────────────────────────────────┐
        │              Redis Cache                    │
        │  ┌─────────────┐ ┌─────────────────────┐    │
        │  │   Sessions  │ │   Rate Limit Data   │    │
        │  └─────────────┘ └─────────────────────┘    │
        └─────────────────────────────────────────────┘
                                │
                                ▼
    ┌────────────┐  ┌────────────┐  ┌────────────┐
    │ Service A  │  │ Service B  │  │ Service C  │
    │ (Auth)     │  │ (Business) │  │ (Business) │
    └────────────┘  └────────────┘  └────────────┘
```

## Step 1: Project Structure

```
api-gateway/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/example/gateway/
│   │   │       ├── ApiGatewayApplication.java
│   │   │       ├── config/
│   │   │       │   ├── GatewayConfig.java
│   │   │       │   ├── SecurityConfig.java
│   │   │       │   ├── RedisConfig.java
│   │   │       │   └── RouteConfig.java
│   │   │       ├── filter/
│   │   │       │   ├── JwtAuthenticationFilter.java
│   │   │       │   ├── SessionValidationFilter.java
│   │   │       │   └── RequestLoggingFilter.java
│   │   │       ├── service/
│   │   │       │   ├── JwtService.java
│   │   │       │   ├── SessionService.java
│   │   │       │   └── RateLimitService.java
│   │   │       ├── model/
│   │   │       │   ├── UserSession.java
│   │   │       │   └── JwtTokenData.java
│   │   │       └── exception/
│   │   │           └── SecurityExceptionHandler.java
│   │   └── resources/
│   │       ├── application.yml
│   │       ├── application-dev.yml
│   │       ├── application-uat.yml
│   │       └── application-prod.yml
└── pom.xml
```

## Step 2: Maven Dependencies (pom.xml)

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 
         http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.example</groupId>
    <artifactId>api-gateway</artifactId>
    <version>1.0.0</version>
    <packaging>jar</packaging>

    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>3.1.5</version>
        <relativePath/>
    </parent>

    <properties>
        <java.version>17</java.version>
        <spring-cloud.version>2022.0.4</spring-cloud.version>
    </properties>

    <dependencies>
        <!-- Spring Cloud Gateway -->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-gateway</artifactId>
        </dependency>

        <!-- Spring Cloud Kubernetes -->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-kubernetes-client</artifactId>
        </dependency>

        <!-- Spring Cloud Kubernetes Discovery -->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-kubernetes-client-discovery</artifactId>
        </dependency>

        <!-- Spring Security -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-security</artifactId>
        </dependency>

        <!-- JWT -->
        <dependency>
            <groupId>io.jsonwebtoken</groupId>
            <artifactId>jjwt-api</artifactId>
            <version>0.11.5</version>
        </dependency>
        <dependency>
            <groupId>io.jsonwebtoken</groupId>
            <artifactId>jjwt-impl</artifactId>
            <version>0.11.5</version>
        </dependency>
        <dependency>
            <groupId>io.jsonwebtoken</groupId>
            <artifactId>jjwt-jackson</artifactId>
            <version>0.11.5</version>
        </dependency>

        <!-- Redis -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-redis-reactive</artifactId>
        </dependency>

        <!-- Resilience4j for Rate Limiting -->
        <dependency>
            <groupId>io.github.resilience4j</groupId>
            <artifactId>resilience4j-ratelimiter</artifactId>
        </dependency>
        <dependency>
            <groupId>io.github.resilience4j</groupId>
            <artifactId>resilience4j-reactor</artifactId>
        </dependency>

        <!-- Actuator for monitoring -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>

        <!-- WebFlux -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-webflux</artifactId>
        </dependency>

        <!-- Validation -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-validation</artifactId>
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
```

## Step 3: Main Application Class

```java
package com.example.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ApiGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayApplication.class, args);
    }
}
```

## Step 4: Configuration Files

### application.yml

```yaml
server:
  port: 8080

spring:
  application:
    name: api-gateway
  profiles:
    active: ${SPRING_PROFILES_ACTIVE:dev}
  
  cloud:
    gateway:
      globalcors:
        corsConfigurations:
          '[/**]':
            allowedOriginPatterns: "*"
            allowedMethods: "*"
            allowedHeaders: "*"
            allowCredentials: true
      default-filters:
        - DedupeResponseHeader=Access-Control-Allow-Credentials Access-Control-Allow-Origin
      discovery:
        locator:
          enabled: true
          lower-case-service-id: true

  redis:
    host: ${REDIS_HOST:localhost}
    port: ${REDIS_PORT:6379}
    password: ${REDIS_PASSWORD:}
    database: 0
    timeout: 2000ms
    lettuce:
      pool:
        max-active: 8
        max-wait: -1ms
        max-idle: 8
        min-idle: 0

management:
  endpoints:
    web:
      exposure:
        include: health,info,metrics,gateway
  endpoint:
    health:
      show-details: always

logging:
  level:
    com.example.gateway: DEBUG
    org.springframework.cloud.gateway: DEBUG

# JWT Configuration
jwt:
  secret: ${JWT_SECRET:mySecretKey}
  expiration: ${JWT_EXPIRATION:3600000}
  refresh-expiration: ${JWT_REFRESH_EXPIRATION:604800000}

# Rate Limiting Configuration
rate-limit:
  default:
    requests-per-second: 10
    burst-capacity: 20
  auth:
    requests-per-second: 5
    burst-capacity: 10

# Session Configuration
session:
  timeout: ${SESSION_TIMEOUT:1800}
  max-concurrent: ${MAX_CONCURRENT_SESSIONS:1}
```

### application-dev.yml

```yaml
spring:
  cloud:
    gateway:
      routes:
        - id: auth-service
          uri: ${AUTH_SERVICE_URL:http://localhost:8081}
          predicates:
            - Path=/api/auth/**
          filters:
            - StripPrefix=2

        - id: user-service
          uri: ${USER_SERVICE_URL:http://localhost:8082}
          predicates:
            - Path=/api/users/**
          filters:
            - StripPrefix=2

        - id: order-service
          uri: ${ORDER_SERVICE_URL:http://localhost:8083}
          predicates:
            - Path=/api/orders/**
          filters:
            - StripPrefix=2

logging:
  level:
    root: INFO
    com.example.gateway: DEBUG
```

### application-uat.yml

```yaml
spring:
  cloud:
    gateway:
      routes:
        - id: auth-service
          uri: lb://auth-service
          predicates:
            - Path=/api/auth/**
          filters:
            - StripPrefix=2

        - id: user-service
          uri: lb://user-service
          predicates:
            - Path=/api/users/**
          filters:
            - StripPrefix=2

        - id: order-service
          uri: lb://order-service
          predicates:
            - Path=/api/orders/**
          filters:
            - StripPrefix=2

logging:
  level:
    root: WARN
    com.example.gateway: INFO
```

### application-prod.yml

```yaml
spring:
  cloud:
    gateway:
      routes:
        - id: auth-service
          uri: lb://auth-service
          predicates:
            - Path=/api/auth/**
          filters:
            - StripPrefix=2

        - id: user-service
          uri: lb://user-service
          predicates:
            - Path=/api/users/**
          filters:
            - StripPrefix=2

        - id: order-service
          uri: lb://order-service
          predicates:
            - Path=/api/orders/**
          filters:
            - StripPrefix=2

logging:
  level:
    root: ERROR
    com.example.gateway: WARN

# Production security settings
jwt:
  expiration: 1800000 # 30 minutes
  refresh-expiration: 86400000 # 24 hours

rate-limit:
  default:
    requests-per-second: 100
    burst-capacity: 200

session:
  timeout: 900 # 15 minutes
```

## Step 5: Model Classes

### UserSession.java

```java
package com.example.gateway.model;

import java.time.LocalDateTime;

public class UserSession {
    private String sessionId;
    private String userId;
    private String username;
    private String ipAddress;
    private String userAgent;
    private LocalDateTime createdAt;
    private LocalDateTime lastAccessedAt;
    private boolean active;
    private String refreshToken;

    // Constructors
    public UserSession() {}

    public UserSession(String sessionId, String userId, String username, 
                      String ipAddress, String userAgent) {
        this.sessionId = sessionId;
        this.userId = userId;
        this.username = username;
        this.ipAddress = ipAddress;
        this.userAgent = userAgent;
        this.createdAt = LocalDateTime.now();
        this.lastAccessedAt = LocalDateTime.now();
        this.active = true;
    }

    // Getters and Setters
    public String getSessionId() { return sessionId; }
    public void setSessionId(String sessionId) { this.sessionId = sessionId; }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getIpAddress() { return ipAddress; }
    public void setIpAddress(String ipAddress) { this.ipAddress = ipAddress; }

    public String getUserAgent() { return userAgent; }
    public void setUserAgent(String userAgent) { this.userAgent = userAgent; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getLastAccessedAt() { return lastAccessedAt; }
    public void setLastAccessedAt(LocalDateTime lastAccessedAt) { 
        this.lastAccessedAt = lastAccessedAt; 
    }

    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }

    public String getRefreshToken() { return refreshToken; }
    public void setRefreshToken(String refreshToken) { this.refreshToken = refreshToken; }
}
```

### JwtTokenData.java

```java
package com.example.gateway.model;

import java.time.LocalDateTime;
import java.util.List;

public class JwtTokenData {
    private String userId;
    private String username;
    private String email;
    private List<String> roles;
    private String sessionId;
    private LocalDateTime issuedAt;
    private LocalDateTime expiresAt;

    // Constructors
    public JwtTokenData() {}

    public JwtTokenData(String userId, String username, String email, 
                       List<String> roles, String sessionId) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.roles = roles;
        this.sessionId = sessionId;
        this.issuedAt = LocalDateTime.now();
    }

    // Getters and Setters
    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public List<String> getRoles() { return roles; }
    public void setRoles(List<String> roles) { this.roles = roles; }

    public String getSessionId() { return sessionId; }
    public void setSessionId(String sessionId) { this.sessionId = sessionId; }

    public LocalDateTime getIssuedAt() { return issuedAt; }
    public void setIssuedAt(LocalDateTime issuedAt) { this.issuedAt = issuedAt; }

    public LocalDateTime getExpiresAt() { return expiresAt; }
    public void setExpiresAt(LocalDateTime expiresAt) { this.expiresAt = expiresAt; }
}
```

## Step 6: Configuration Classes

### RedisConfig.java

```java
package com.example.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@Configuration
public class RedisConfig {

    @Bean
    public ReactiveRedisTemplate<String, Object> reactiveRedisTemplate(
            ReactiveRedisConnectionFactory connectionFactory) {
        
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        
        Jackson2JsonRedisSerializer<Object> serializer = 
            new Jackson2JsonRedisSerializer<>(Object.class);
        serializer.setObjectMapper(objectMapper);

        RedisSerializationContext.RedisSerializationContextBuilder<String, Object> builder =
                RedisSerializationContext.newSerializationContext(new StringRedisSerializer());

        RedisSerializationContext<String, Object> context = 
            builder.value(serializer).hashValue(serializer).build();

        return new ReactiveRedisTemplate<>(connectionFactory, context);
    }
}
```

### SecurityConfig.java

```java
package com.example.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsConfigurationSource;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        return http
            .csrf(csrf -> csrf.disable())
            .cors(cors -> cors.configurationSource(corsConfigurationSource()))
            .authorizeExchange(exchanges -> exchanges
                .pathMatchers("/actuator/**", "/api/auth/login", "/api/auth/register").permitAll()
                .anyExchange().authenticated()
            )
            .oauth2ResourceServer(oauth2 -> oauth2.jwt())
            .build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOriginPatterns(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("*"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setAllowCredentials(true);
        
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
```

## Step 7: Service Classes

### JwtService.java

```java
package com.example.gateway.service;

import com.example.gateway.model.JwtTokenData;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import javax.crypto.SecretKey;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Service
public class JwtService {

    private final SecretKey secretKey;
    private final long jwtExpiration;
    private final long refreshExpiration;

    public JwtService(@Value("${jwt.secret}") String secret,
                     @Value("${jwt.expiration}") long jwtExpiration,
                     @Value("${jwt.refresh-expiration}") long refreshExpiration) {
        this.secretKey = Keys.hmacShaKeyFor(secret.getBytes());
        this.jwtExpiration = jwtExpiration;
        this.refreshExpiration = refreshExpiration;
    }

    public Mono<String> generateToken(JwtTokenData tokenData) {
        return Mono.fromCallable(() -> {
            Date now = new Date();
            Date expiry = new Date(now.getTime() + jwtExpiration);
            
            tokenData.setExpiresAt(LocalDateTime.ofInstant(expiry.toInstant(), ZoneId.systemDefault()));

            return Jwts.builder()
                    .setSubject(tokenData.getUserId())
                    .claim("username", tokenData.getUsername())
                    .claim("email", tokenData.getEmail())
                    .claim("roles", tokenData.getRoles())
                    .claim("sessionId", tokenData.getSessionId())
                    .setIssuedAt(now)
                    .setExpiration(expiry)
                    .signWith(secretKey, SignatureAlgorithm.HS512)
                    .compact();
        });
    }

    public Mono<String> generateRefreshToken(String userId, String sessionId) {
        return Mono.fromCallable(() -> {
            Date now = new Date();
       