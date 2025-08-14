package com.fincore.gateway.Service;

import io.jsonwebtoken.Jwt;
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


Now I have useed the latest 0.12.6 version for jjwt token 

below is my pom.xml let me know if any dependency is missing or required to add inside this to resolve this prevoisuly povided error on  " String username = jwt.getClaimAsString("sub");   // subject
        String jti = jwt.getId();                        // unique token id"



        pom.xml ::
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

  <groupId>com.fincore</groupId>
  <artifactId>api-gateway</artifactId>
  <version>0.0.1-SNAPSHOT</version>
  <name>api-gateway</name>

  <properties>
    <java.version>17</java.version>
    <spring-cloud.version>2025.0.0</spring-cloud.version>
    <jjwt.version>0.12.6</jjwt.version>
  </properties>

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

  <dependencies>
    <!-- Spring Cloud Gateway (WebFlux server) -->
    <dependency>
      <groupId>org.springframework.cloud</groupId>
      <artifactId>spring-cloud-starter-gateway-server-webflux</artifactId>
    </dependency>

    <!-- WebFlux starter (for reactive) -->
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-webflux</artifactId>
    </dependency>

    <!-- Reactive Redis (Lettuce) -->
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-data-redis-reactive</artifactId>
    </dependency>

    <!-- JWT  -->
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

    <!-- Kubernetes discovery (optional in dev) -->
    <dependency>
      <groupId>org.springframework.cloud</groupId>
      <artifactId>spring-cloud-starter-kubernetes-client</artifactId>
      <optional>true</optional>
    </dependency>

    <!-- Actuator -->
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-actuator</artifactId>
    </dependency>

    <!-- resilience4j optional -->
    <dependency>
      <groupId>io.github.resilience4j</groupId>
      <artifactId>resilience4j-spring-boot3</artifactId>
      <version>2.2.0</version>
    </dependency>

    <!-- Micrometer Prometheus (optional) -->
    <dependency>
      <groupId>io.micrometer</groupId>
      <artifactId>micrometer-registry-prometheus</artifactId>
    </dependency>

    <!-- https://mvnrepository.com/artifact/org.projectlombok/lombok -->
    <dependency>
      <groupId>org.projectlombok</groupId>
      <artifactId>lombok</artifactId>
      <version>1.18.38</version>
    </dependency>

    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-oauth2-resource-server</artifactId>
    </dependency>
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-security</artifactId>
    </dependency>

    <!-- https://mvnrepository.com/artifact/org.springframework.security/spring-security-oauth2-resource-server -->
    <dependency>
      <groupId>org.springframework.security</groupId>
      <artifactId>spring-security-oauth2-resource-server</artifactId>
      <version>6.5.2</version>
    </dependency>

  </dependencies>

  <build>
    <plugins>
      <plugin>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-maven-plugin</artifactId>
      </plugin>
    </plugins>
  </build>
</project>

        
