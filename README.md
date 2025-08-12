# Spring Cloud Gateway - Microservices Implementation

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
│   │   │       │   └── SecurityConfig.java
│   │   │       ├── filter/
│   │   │       │   ├── JwtValidationGatewayFilter.java
│   │   │       │   ├── RateLimitGatewayFilter.java
│   │   │       │   ├── SessionValidationGatewayFilter.java
│   │   │       │   └── RequestLoggingGatewayFilter.java
│   │   │       ├── service/
│   │   │       │   ├── JwtValidationService.java
│   │   │       │   ├── SessionManagementService.java
│   │   │       │   ├── RateLimitService.java
│   │   │       │   └── SecurityAuditService.java
│   │   │       ├── entity/
│   │   │       │   ├── UserSession.java
│   │   │       │   ├── RateLimit.java
│   │   │       │   └── SecurityAudit.java
│   │   │       ├── repository/
│   │   │       │   ├── UserSessionRepository.java
│   │   │       │   ├── RateLimitRepository.java
│   │   │       │   └── SecurityAuditRepository.java
│   │   │       ├── dto/
│   │   │       │   ├── JwtTokenData.java
│   │   │       │   └── SecurityContext.java
│   │   │       └── exception/
│   │   │           └── SecurityExceptionHandler.java
│   │   └── resources/
│   │       ├── application.properties
│   │       ├── application-dev.properties
│   │       ├── application-uat.properties
│   │       ├── application-prod.properties
│   │       └── bootstrap.properties
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
        <!-- Spring Cloud Gateway (WebFlux based) -->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-gateway</artifactId>
        </dependency>

        <!-- Spring Cloud Kubernetes -->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-kubernetes-client</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-kubernetes-client-discovery</artifactId>
        </dependency>

        <!-- Spring Data R2DBC for reactive database access -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-r2dbc</artifactId>
        </dependency>

        <!-- Oracle R2DBC Driver -->
        <dependency>
            <groupId>com.oracle.database.r2dbc</groupId>
            <artifactId>oracle-r2dbc</artifactId>
        </dependency>

        <!-- For non-reactive JPA operations (if needed) -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>

        <!-- Oracle JDBC Driver -->
        <dependency>
            <groupId>com.oracle.database.jdbc</groupId>
            <artifactId>ojdbc11</artifactId>
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

        <!-- Redis Reactive -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-redis-reactive</artifactId>
        </dependency>

        <!-- Actuator -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>

        <!-- Validation -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-validation</artifactId>
        </dependency>

        <!-- Micrometer for metrics -->
        <dependency>
            <groupId>io.micrometer</groupId>
            <artifactId>micrometer-registry-prometheus</artifactId>
        </dependency>

        <!-- Connection Pooling -->
        <dependency>
            <groupId>com.zaxxer</groupId>
            <artifactId>HikariCP</artifactId>
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

## 2. Application Properties Files

### bootstrap.properties
```properties
spring.application.name=api-gateway
spring.cloud.kubernetes.discovery.enabled=true
spring.cloud.kubernetes.discovery.all-namespaces=false
```

### application.properties
```properties
# Server Configuration
server.port=8080

# Logging Configuration
logging.level.root=INFO
logging.level.com.example.apigateway=DEBUG
logging.level.org.springframework.cloud.gateway=DEBUG
logging.pattern.console=%d{yyyy-MM-dd HH:mm:ss} - %msg%n

# Management Endpoints
management.endpoints.web.exposure.include=health,info,metrics,prometheus,gateway
management.endpoint.health.show-details=always
management.endpoint.gateway.enabled=true

# JWT Configuration
jwt.secret=mySecretKeyForJWTToken
jwt.expiration=3600000
jwt.issuer=api-gateway

# Rate Limiting
rate.limit.default.requests=100
rate.limit.default.window=60
rate.limit.auth.requests=10
rate.limit.auth.window=60

# Session Management
session.timeout=1800
session.max.concurrent=1

# Bypass URLs (No authentication required)
security.bypass.urls=/actuator/**,/health,/images/**,/static/**,/css/**,/js/**,/favicon.ico,/api/auth/login,/api/auth/register

# CORS Configuration
spring.cloud.gateway.globalcors.corsConfigurations.[/**].allowedOriginPatterns=*
spring.cloud.gateway.globalcors.corsConfigurations.[/**].allowedMethods=GET,POST,PUT,DELETE,OPTIONS
spring.cloud.gateway.globalcors.corsConfigurations.[/**].allowedHeaders=*
spring.cloud.gateway.globalcors.corsConfigurations.[/**].allowCredentials=true

# Gateway Configuration
spring.cloud.gateway.discovery.locator.enabled=true
spring.cloud.gateway.discovery.locator.lower-case-service-id=true
```

### application-dev.properties
```properties
# Database Configuration - Dev (Regular JDBC for JPA)
spring.datasource.url=jdbc:oracle:thin:@localhost:1521/XEPDB1
spring.datasource.username=dev_gateway
spring.datasource.password=dev_password
spring.datasource.driver-class-name=oracle.jdbc.OracleDriver

# R2DBC Configuration - Dev (for reactive operations)
spring.r2dbc.url=r2dbc:oracle://localhost:1521/XEPDB1
spring.r2dbc.username=dev_gateway
spring.r2dbc.password=dev_password

# Connection Pool
spring.datasource.hikari.maximum-pool-size=20
spring.datasource.hikari.minimum-idle=5
spring.datasource.hikari.connection-timeout=30000

# JPA Configuration
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.OracleDialect
spring.jpa.properties.hibernate.format_sql=true

# Redis Configuration - Dev
spring.redis.host=localhost
spring.redis.port=6379
spring.redis.database=0
spring.redis.timeout=2000ms

# Service URLs for Local Development
service.auth.url=http://localhost:8081
service.user.url=http://localhost:8082
service.order.url=http://localhost:8083
service.product.url=http://localhost:8084

# Gateway Routes - Dev
spring.cloud.gateway.routes[0].id=auth-service
spring.cloud.gateway.routes[0].uri=${service.auth.url}
spring.cloud.gateway.routes[0].predicates[0]=Path=/api/auth/**
spring.cloud.gateway.routes[0].filters[0]=StripPrefix=2

spring.cloud.gateway.routes[1].id=user-service
spring.cloud.gateway.routes[1].uri=${service.user.url}
spring.cloud.gateway.routes[1].predicates[0]=Path=/api/users/**
spring.cloud.gateway.routes[1].filters[0]=StripPrefix=2

spring.cloud.gateway.routes[2].id=order-service
spring.cloud.gateway.routes[2].uri=${service.order.url}
spring.cloud.gateway.routes[2].predicates[0]=Path=/api/orders/**
spring.cloud.gateway.routes[2].filters[0]=StripPrefix=2

spring.cloud.gateway.routes[3].id=product-service
spring.cloud.gateway.routes[3].uri=${service.product.url}
spring.cloud.gateway.routes[3].predicates[0]=Path=/api/products/**
spring.cloud.gateway.routes[3].filters[0]=StripPrefix=2

# Debug Settings
logging.level.org.springframework.security=DEBUG
logging.level.org.hibernate.SQL=DEBUG
logging.level.reactor.netty=DEBUG
```

### application-uat.properties
```properties
# Database Configuration - UAT
spring.datasource.url=jdbc:oracle:thin:@uat-oracle-db:1521/UATDB
spring.datasource.username=uat_gateway
spring.datasource.password=${UAT_DB_PASSWORD}
spring.datasource.driver-class-name=oracle.jdbc.OracleDriver

# R2DBC Configuration - UAT
spring.r2dbc.url=r2dbc:oracle://uat-oracle-db:1521/UATDB
spring.r2dbc.username=uat_gateway
spring.r2dbc.password=${UAT_DB_PASSWORD}

# Connection Pool
spring.datasource.hikari.maximum-pool-size=50
spring.datasource.hikari.minimum-idle=10
spring.datasource.hikari.connection-timeout=30000

# JPA Configuration
spring.jpa.hibernate.ddl-auto=validate
spring.jpa.show-sql=false

# Redis Configuration - UAT
spring.redis.host=uat-redis
spring.redis.port=6379
spring.redis.database=0
spring.redis.password=${UAT_REDIS_PASSWORD}

# Gateway Routes - UAT (Kubernetes Service Discovery)
spring.cloud.gateway.routes[0].id=auth-service
spring.cloud.gateway.routes[0].uri=lb://auth-service
spring.cloud.gateway.routes[0].predicates[0]=Path=/api/auth/**
spring.cloud.gateway.routes[0].filters[0]=StripPrefix=2

spring.cloud.gateway.routes[1].id=user-service
spring.cloud.gateway.routes[1].uri=lb://user-service
spring.cloud.gateway.routes[1].predicates[0]=Path=/api/users/**
spring.cloud.gateway.routes[1].filters[0]=StripPrefix=2

spring.cloud.gateway.routes[2].id=order-service
spring.cloud.gateway.routes[2].uri=lb://order-service
spring.cloud.gateway.routes[2].predicates[0]=Path=/api/orders/**
spring.cloud.gateway.routes[2].filters[0]=StripPrefix=2

spring.cloud.gateway.routes[3].id=product-service
spring.cloud.gateway.routes[3].uri=lb://product-service
spring.cloud.gateway.routes[3].predicates[0]=Path=/api/products/**
spring.cloud.gateway.routes[3].filters[0]=StripPrefix=2

# Rate Limiting - UAT
rate.limit.default.requests=200
rate.limit.auth.requests=20

# Security
jwt.secret=${UAT_JWT_SECRET}
```

### application-prod.properties
```properties
# Database Configuration - Production
spring.datasource.url=jdbc:oracle:thin:@prod-oracle-cluster:1521/PRODDB
spring.datasource.username=prod_gateway
spring.datasource.password=${PROD_DB_PASSWORD}
spring.datasource.driver-class-name=oracle.jdbc.OracleDriver

# R2DBC Configuration - Production
spring.r2dbc.url=r2dbc:oracle://prod-oracle-cluster:1521/PRODDB
spring.r2dbc.username=prod_gateway
spring.r2dbc.password=${PROD_DB_PASSWORD}

# Connection Pool - Production
spring.datasource.hikari.maximum-pool-size=100
spring.datasource.hikari.minimum-idle=20
spring.datasource.hikari.connection-timeout=30000
spring.datasource.hikari.idle-timeout=600000
spring.datasource.hikari.max-lifetime=1800000

# JPA Configuration - Production
spring.jpa.hibernate.ddl-auto=none
spring.jpa.show-sql=false

# Redis Configuration - Production
spring.redis.host=prod-redis-cluster
spring.redis.port=6379
spring.redis.database=0
spring.redis.password=${PROD_REDIS_PASSWORD}

# Gateway Routes - Production
spring.cloud.gateway.routes[0].id=auth-service
spring.cloud.gateway.routes[0].uri=lb://auth-service
spring.cloud.gateway.routes[0].predicates[0]=Path=/api/auth/**
spring.cloud.gateway.routes[0].filters[0]=StripPrefix=2

spring.cloud.gateway.routes[1].id=user-service
spring.cloud.gateway.routes[1].uri=lb://user-service
spring.cloud.gateway.routes[1].predicates[0]=Path=/api/users/**
spring.cloud.gateway.routes[1].filters[0]=StripPrefix=2

spring.cloud.gateway.routes[2].id=order-service
spring.cloud.gateway.routes[2].uri=lb://order-service
spring.cloud.gateway.routes[2].predicates[0]=Path=/api/orders/**
spring.cloud.gateway.routes[2].filters[0]=StripPrefix=2

spring.cloud.gateway.routes[3].id=product-service
spring.cloud.gateway.routes[3].uri=lb://product-service
spring.cloud.gateway.routes[3].predicates[0]=Path=/api/products/**
spring.cloud.gateway.routes[3].filters[0]=StripPrefix=2

# Rate Limiting - Production
rate.limit.default.requests=1000
rate.limit.auth.requests=50

# Security - Production
jwt.secret=${PROD_JWT_SECRET}
jwt.expiration=1800000
session.timeout=1200

# Logging - Production
logging.level.root=WARN
logging.level.com.example.apigateway=INFO
logging.file.name=/app/logs/gateway.log
```

## 3. Main Application Class

```java
package com.example.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableDiscoveryClient
@EnableJpaRepositories
public class ApiGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayApplication.class, args);
    }
}
```

## 4. Entity Classes (Same as before)

### UserSession.java
```java
package com.example.apigateway.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "USER_SESSIONS")
public class UserSession {
    
    @Id
    @Column(name = "SESSION_ID", length = 255)
    private String sessionId;
    
    @Column(name = "USER_ID", nullable = false)
    private String userId;
    
    @Column(name = "USERNAME", nullable = false)
    private String username;
    
    @Column(name = "IP_ADDRESS")
    private String ipAddress;
    
    @Column(name = "USER_AGENT", length = 500)
    private String userAgent;
    
    @Column(name = "CREATED_AT", nullable = false)
    private LocalDateTime createdAt;
    
    @Column(name = "LAST_ACCESSED_AT")
    private LocalDateTime lastAccessedAt;
    
    @Column(name = "IS_ACTIVE", nullable = false)
    private Boolean isActive = true;
    
    @Column(name = "EXPIRES_AT")
    private LocalDateTime expiresAt;

    // Constructors
    public UserSession() {}

    public UserSession(String sessionId, String userId, String username, String ipAddress, String userAgent) {
        this.sessionId = sessionId;
        this.userId = userId;
        this.username = username;
        this.ipAddress = ipAddress;
        this.userAgent = userAgent;
        this.createdAt = LocalDateTime.now();
        this.lastAccessedAt = LocalDateTime.now();
        this.isActive = true;
        this.expiresAt = LocalDateTime.now().plusSeconds(1800); // 30 minutes
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
    public void setLastAccessedAt(LocalDateTime lastAccessedAt) { this.lastAccessedAt = lastAccessedAt; }
    
    public Boolean getIsActive() { return isActive; }
    public void setIsActive(Boolean isActive) { this.isActive = isActive; }
    
    public LocalDateTime getExpiresAt() { return expiresAt; }
    public void setExpiresAt(LocalDateTime expiresAt) { this.expiresAt = expiresAt; }
}
```

## 5. Service Classes (Reactive)

### JwtValidationService.java
```java
package com.example.apigateway.service;

import com.example.apigateway.dto.JwtTokenData;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import javax.crypto.SecretKey;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Service
public class JwtValidationService {

    private final SecretKey secretKey;
    private final String issuer;

    public JwtValidationService(@Value("${jwt.secret}") String secret,
                               @Value("${jwt.issuer}") String issuer) {
        this.secretKey = Keys.hmacShaKeyFor(secret.getBytes());
        this.issuer = issuer;
    }

    public Mono<JwtTokenData> validateToken(String token) {
        return Mono.fromCallable(() -> {
            try {
                Claims claims = Jwts.parserBuilder()
                        .setSigningKey(secretKey)
                        .requireIssuer(issuer)
                        .build()
                        .parseClaimsJws(token)
                        .getBody();

                JwtTokenData tokenData = new JwtTokenData();
                tokenData.setUserId(claims.getSubject());
                tokenData.setUsername(claims.get("username", String.class));
                tokenData.setEmail(claims.get("email", String.class));
                tokenData.setRoles((List<String>) claims.get("roles"));
                tokenData.setSessionId(claims.get("sessionId", String.class));
                tokenData.setIssuedAt(LocalDateTime.ofInstant(
                    claims.getIssuedAt().toInstant(), ZoneId.systemDefault()));
                tokenData.setExpiresAt(LocalDateTime.ofInstant(
                    claims.getExpiration().toInstant(), ZoneI