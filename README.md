# Spring Cloud Gateway - Tailored for Your JWT System

Based on your existing login service, I'll create a Spring Cloud Gateway that integrates seamlessly with your JWT implementation and adds enterprise-grade security features.

## Project Structure

```
api-gateway/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/example/apigateway/
│   │   │       ├── ApiGatewayApplication.java
│   │   │       ├── config/
│   │   │       │   ├── GatewayConfig.java
│   │   │       │   ├── DatabaseConfig.java
│   │   │       │   ├── RedisConfig.java
│   │   │       │   └── CorsConfig.java
│   │   │       ├── filter/
│   │   │       │   ├── JwtValidationFilter.java
│   │   │       │   ├── SessionValidationFilter.java
│   │   │       │   ├── RateLimitFilter.java
│   │   │       │   ├── SecurityAuditFilter.java
│   │   │       │   └── RequestLoggingFilter.java
│   │   │       ├── service/
│   │   │       │   ├── JwtValidationService.java
│   │   │       │   ├── SessionManagementService.java
│   │   │       │   ├── RateLimitService.java
│   │   │       │   ├── SecurityThreatService.java
│   │   │       │   └── AuditService.java
│   │   │       ├── entity/
│   │   │       │   ├── GatewaySession.java
│   │   │       │   ├── RateLimitRecord.java
│   │   │       │   ├── SecurityEvent.java
│   │   │       │   └── ThreatDetection.java
│   │   │       ├── repository/
│   │   │       │   ├── GatewaySessionRepository.java
│   │   │       │   ├── RateLimitRepository.java
│   │   │       │   ├── SecurityEventRepository.java
│   │   │       │   └── ThreatDetectionRepository.java
│   │   │       ├── dto/
│   │   │       │   ├── JwtTokenInfo.java
│   │   │       │   ├── SecurityContext.java
│   │   │       │   └── ThreatAnalysis.java
│   │   │       └── exception/
│   │   │           └── SecurityExceptionHandler.java
│   │   └── resources/
│   │       ├── application.properties
│   │       ├── application-dev.properties
│   │       ├── application-uat.properties
│   │       └── application-prod.properties
└── pom.xml
```

## 1. Maven Dependencies (pom.xml)

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

        <!-- Spring Cloud Kubernetes for Service Discovery -->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-kubernetes-client</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-kubernetes-client-discovery</artifactId>
        </dependency>

        <!-- Spring Data JPA -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>

        <!-- Oracle Database -->
        <dependency>
            <groupId>com.oracle.database.jdbc</groupId>
            <artifactId>ojdbc11</artifactId>
        </dependency>

        <!-- JWT (Same version as your login service) -->
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

        <!-- Redis for Reactive Operations -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-redis-reactive</artifactId>
        </dependency>

        <!-- Actuator for Health Checks -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>

        <!-- Validation -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-validation</artifactId>
        </dependency>

        <!-- Connection Pooling -->
        <dependency>
            <groupId>com.zaxxer</groupId>
            <artifactId>HikariCP</artifactId>
        </dependency>

        <!-- Micrometer for Metrics -->
        <dependency>
            <groupId>io.micrometer</groupId>
            <artifactId>micrometer-registry-prometheus</artifactId>
        </dependency>

        <!-- Test Dependencies -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>

        <dependency>
            <groupId>io.projectreactor</groupId>
            <artifactId>reactor-test</artifactId>
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
```

## 2. Application Properties

### application.properties
```properties
spring.application.name=api-gateway
server.port=8080

# Logging
logging.level.root=INFO
logging.level.com.example.apigateway=DEBUG
logging.level.org.springframework.cloud.gateway=INFO

# Management endpoints
management.endpoints.web.exposure.include=health,info,metrics,prometheus,gateway
management.endpoint.health.show-details=always
management.endpoint.gateway.enabled=true

# JWT Configuration (Match your login service)
jwt.secret=thisIsTheMostSecretKeytcs123springframeworkspringframeworkspringframework
jwt.access.expiration=900000
jwt.refresh.expiration=86400000

# Security Configuration
security.single.session.enabled=true
security.rate.limit.enabled=true
security.threat.detection.enabled=true
security.audit.enabled=true

# Rate Limiting
rate.limit.requests.per.minute=60
rate.limit.requests.per.hour=1000
rate.limit.auth.requests.per.minute=10
rate.limit.admin.requests.per.minute=30

# Session Management
session.max.concurrent.per.user=1
session.timeout.minutes=30
session.cleanup.interval.minutes=5

# Threat Detection
threat.detection.max.failed.attempts=5
threat.detection.lockout.duration.minutes=15
threat.detection.suspicious.activity.threshold=10

# Bypass URLs (no authentication required)
security.bypass.patterns=/actuator/**,/health,/login-service/login,/login-service/check-user,/login-service/update-password,/static/**,/images/**,/css/**,/js/**,/favicon.ico

# Service Discovery
spring.cloud.gateway.discovery.locator.enabled=true
spring.cloud.gateway.discovery.locator.lower-case-service-id=true

# CORS
cors.allowed.origins=*
cors.allowed.methods=GET,POST,PUT,DELETE,OPTIONS
cors.allowed.headers=*
cors.allow.credentials=true
```

### application-dev.properties
```properties
# Database Configuration - Dev
spring.datasource.url=jdbc:oracle:thin:@localhost:1521/XEPDB1
spring.datasource.username=gateway_dev
spring.datasource.password=dev_password
spring.datasource.driver-class-name=oracle.jdbc.OracleDriver

# Connection Pool
spring.datasource.hikari.maximum-pool-size=10
spring.datasource.hikari.minimum-idle=2
spring.datasource.hikari.connection-timeout=20000

# JPA Configuration
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.OracleDialect

# Redis Configuration - Dev
spring.redis.host=localhost
spring.redis.port=6379
spring.redis.database=1
spring.redis.timeout=2000ms

# Service URLs (Local Development)
services.login-service.url=http://localhost:8081
services.user-service.url=http://localhost:8082
services.order-service.url=http://localhost:8083

# Gateway Routes Configuration
spring.cloud.gateway.routes[0].id=login-service
spring.cloud.gateway.routes[0].uri=${services.login-service.url}
spring.cloud.gateway.routes[0].predicates[0]=Path=/login-service/**
spring.cloud.gateway.routes[0].filters[0]=StripPrefix=1

spring.cloud.gateway.routes[1].id=user-service
spring.cloud.gateway.routes[1].uri=${services.user-service.url}
spring.cloud.gateway.routes[1].predicates[0]=Path=/user-service/**
spring.cloud.gateway.routes[1].filters[0]=StripPrefix=1

spring.cloud.gateway.routes[2].id=order-service
spring.cloud.gateway.routes[2].uri=${services.order-service.url}
spring.cloud.gateway.routes[2].predicates[0]=Path=/order-service/**
spring.cloud.gateway.routes[2].filters[0]=StripPrefix=1

# Debugging
logging.level.org.springframework.security=DEBUG
logging.level.com.example.apigateway=DEBUG
```

### application-uat.properties
```properties
# Database Configuration - UAT
spring.datasource.url=jdbc:oracle:thin:@uat-oracle-db:1521/UATDB
spring.datasource.username=gateway_uat
spring.datasource.password=${UAT_DB_PASSWORD}

# Redis Configuration - UAT
spring.redis.host=uat-redis-cluster
spring.redis.port=6379
spring.redis.password=${UAT_REDIS_PASSWORD}

# Service URLs (Kubernetes Service Discovery)
spring.cloud.gateway.routes[0].id=login-service
spring.cloud.gateway.routes[0].uri=lb://login-service
spring.cloud.gateway.routes[0].predicates[0]=Path=/login-service/**
spring.cloud.gateway.routes[0].filters[0]=StripPrefix=1

spring.cloud.gateway.routes[1].id=user-service
spring.cloud.gateway.routes[1].uri=lb://user-service
spring.cloud.gateway.routes[1].predicates[0]=Path=/user-service/**
spring.cloud.gateway.routes[1].filters[0]=StripPrefix=1

spring.cloud.gateway.routes[2].id=order-service
spring.cloud.gateway.routes[2].uri=lb://order-service
spring.cloud.gateway.routes[2].predicates[0]=Path=/order-service/**
spring.cloud.gateway.routes[2].filters[0]=StripPrefix=1

# Rate Limiting - UAT
rate.limit.requests.per.minute=120
rate.limit.requests.per.hour=2000

# Logging
logging.level.root=WARN
logging.level.com.example.apigateway=INFO
```

### application-prod.properties
```properties
# Database Configuration - Production
spring.datasource.url=jdbc:oracle:thin:@prod-oracle-cluster:1521/PRODDB
spring.datasource.username=gateway_prod
spring.datasource.password=${PROD_DB_PASSWORD}

# Connection Pool - Production
spring.datasource.hikari.maximum-pool-size=50
spring.datasource.hikari.minimum-idle=10
spring.datasource.hikari.connection-timeout=30000

# JPA Configuration - Production
spring.jpa.hibernate.ddl-auto=none
spring.jpa.show-sql=false

# Redis Configuration - Production
spring.redis.host=prod-redis-cluster
spring.redis.port=6379
spring.redis.password=${PROD_REDIS_PASSWORD}

# Service URLs - Production
spring.cloud.gateway.routes[0].id=login-service
spring.cloud.gateway.routes[0].uri=lb://login-service
spring.cloud.gateway.routes[0].predicates[0]=Path=/login-service/**
spring.cloud.gateway.routes[0].filters[0]=StripPrefix=1

spring.cloud.gateway.routes[1].id=user-service
spring.cloud.gateway.routes[1].uri=lb://user-service
spring.cloud.gateway.routes[1].predicates[0]=Path=/user-service/**
spring.cloud.gateway.routes[1].filters[0]=StripPrefix=1

spring.cloud.gateway.routes[2].id=order-service
spring.cloud.gateway.routes[2].uri=lb://order-service
spring.cloud.gateway.routes[2].predicates[0]=Path=/order-service/**
spring.cloud.gateway.routes[2].filters[0]=StripPrefix=1

# Rate Limiting - Production
rate.limit.requests.per.minute=300
rate.limit.requests.per.hour=10000

# Security - Production
jwt.secret=${PROD_JWT_SECRET}
session.timeout.minutes=15
threat.detection.max.failed.attempts=3

# Logging - Production
logging.level.root=ERROR
logging.level.com.example.apigateway=WARN
```

## 3. Main Application Class

```java
package com.example.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableDiscoveryClient
@EnableJpaRepositories
@EnableScheduling
public class ApiGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayApplication.class, args);
    }
}
```

## 4. JWT Validation Service (Compatible with your JWT structure)

### JwtValidationService.java
```java
package com.example.apigateway.service;

import com.example.apigateway.dto.JwtTokenInfo;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class JwtValidationService {

    private final SecretKey secretKey;
    private final long accessTokenExpiration;
    private final long refreshTokenExpiration;

    public JwtValidationService(
            @Value("${jwt.secret}") String secret,
            @Value("${jwt.access.expiration}") long accessTokenExpiration,
            @Value("${jwt.refresh.expiration}") long refreshTokenExpiration) {
        this.secretKey = Keys.hmacShaKeyFor(secret.getBytes());
        this.accessTokenExpiration = accessTokenExpiration;
        this.refreshTokenExpiration = refreshTokenExpiration;
    }

    public Mono<JwtTokenInfo> validateAccessToken(String token) {
        return Mono.fromCallable(() -> {
            try {
                Claims claims = Jwts.parser()
                        .setSigningKey(secretKey)
                        .build()
                        .parseClaimsJws(token)
                        .getBody();

                // Extract claims matching your JWT structure
                JwtTokenInfo tokenInfo = new JwtTokenInfo();
                tokenInfo.setUserId(Integer.parseInt(claims.getSubject()));
                tokenInfo.setFirstName(claims.get("firstName", String.class));
                tokenInfo.setMiddleName(claims.get("middleName", String.class));
                tokenInfo.setLastName(claims.get("lastName", String.class));
                tokenInfo.setEmail(claims.get("email", String.class));
                tokenInfo.setPhoneNumber(claims.get("phoneNumber", String.class));
                tokenInfo.setRole(claims.get("role", String.class));
                tokenInfo.setRoleName(claims.get("roleName", String.class));
                tokenInfo.setIssuedAt(claims.getIssuedAt());
                tokenInfo.setExpiresAt(claims.getExpiration());

                return tokenInfo;
            } catch (JwtException | IllegalArgumentException e) {
                throw new RuntimeException("Invalid JWT token: " + e.getMessage(), e);
            }
        });
    }

    public Mono<Boolean> isTokenExpired(String token) {
        return Mono.fromCallable(() -> {
            try {
                Claims claims = Jwts.parser()
                        .setSigningKey(secretKey)
                        .build()
                        .parseClaimsJws(token)
                        .getBody();
                
                return claims.getExpiration().before(new Date());
            } catch (JwtException e) {
                return true; // Consider invalid tokens as expired
            }
        });
    }

    public Mono<Integer> extractUserId(String token) {
        return Mono.fromCallable(() -> {
            try {
                Claims claims = Jwts.parser()
                        .setSigningKey(secretKey)
                        .build()
                        .parseClaimsJws(token)
                        .getBody();
                
                return Integer.parseInt(claims.getSubject());
            } catch (JwtException | NumberFormatException e) {
                throw new RuntimeException("Cannot extract user ID from token", e);
            }
        });
    }

    public Mono<String> extractRole(String token) {
        return Mono.fromCallable(() -> {
            try {
                Claims claims = Jwts.parser()
                        .setSigningKey(secretKey)
                        .build()
                        .parseClaimsJws(token)
                        .getBody();
                
                return claims.get("role", String.class);
            } catch (JwtException e) {
                throw new RuntimeException("Cannot extract role from token", e);
            }
        });
    }
}
```

## 5. Session Management Service (Integrated with your RefreshToken system)

### SessionManagementService.java
```java
package com.example.apigateway.service;

import com.example.apigateway.entity.GatewaySession;
import com.example.apigateway.repository.GatewaySessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class SessionManagementService {

    private final GatewaySessionRepository sessionRepository;
    private final ReactiveRedisTemplate<String, Object> redisTemplate;
    private final int maxConcurrentSessions;
    private final int sessionTimeoutMinutes;

    private static final String REDIS_SESSION_PREFIX = "gateway:session:";
    private static final String REDIS_USER_SESSIONS_PREFIX = "gateway:user:sessions:";

    @Autowired
    public SessionManagementService(
            GatewaySessionRepository sessionRepository,
            ReactiveRedisTemplate<String, Object> redisTemplate,
            @Value("${session.max.concurrent.per.user}") int maxConcurrentSessions,
            @Value("${session.timeout.minutes}") int sessionTimeoutMinutes) {
        this.sessionRepository = sessionRepository;
        this.redisTemplate = redisTemplate;
        this.maxConcurrentSessions = maxConcurrentSessions;
        this.sessionTimeoutMinutes = sessionTimeoutMinutes;
    }

    public Mono<Boolean> validateSession(Integer userId, String refreshToken, String clientIP, String userAgent) {
        String sessionKey = REDIS_SESSION_PREFIX + userId;
        
        return redisTemplate.opsForValue()
                .get(sessionKey)
                .cast(GatewaySession.class)
                .flatMap(cachedSession -> {
                    // Validate session details
                    if (cachedSession.getRefreshToken