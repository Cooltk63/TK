Getting error on api-gateway logs as per below:
2025-08-21 :: 12:22:42.556 || INFO :: TokenSessionValidator.java: | 98 | ::  Validating token for user=TUSHAR with jti=c78558a9-2a1c-41eb-a119-cc45ecd13fcb

2025-08-21 :: 12:22:42.559 || INFO :: TokenSessionValidator.java: | 109 | :: 📌 Redis stored JTI for user=TUSHAR is c78558a9-2a1c-41eb-a119-cc45ecd13fcb

2025-08-21 :: 12:22:42.559 || INFO :: TokenSessionValidator.java: | 122 | ::  Token validation success for user=TUSHAR with jti=c78558a9-2a1c-41eb-a119-cc45ecd13fcb

2025-08-21 :: 12:22:42.560 || INFO :: SecurityConfig.java: | 52 | ::  Redis validated for TUSHAR

2025-08-21 :: 12:22:44.568 || ERROR:: SecurityConfig.java: | 57 | :: !! RedisValidationFilter error: Failed to resolve 'product-service' [A(1)]

application.yaml files as per below ::
spring:
  application:
    name: api-gateway

  profiles:
    active: dev

  main:
    web-application-type: reactive

  cloud:
    gateway:
      routes:
        - id: product-service
          uri: http://product-service:8081
          predicates:
            - Path=/Product/**
        - id: fincore-service
          uri: http://fincore-service:8089
          predicates:
            - Path=/Fincore/**
    kubernetes:
      discovery:
        enabled: false

server:
  port: 8080

# ========== JWT MODE ==========
security:
  jwt:
    mode: hmac
    hmac-base64-secret: ""   # set in application-dev.yml
    rsa-public: ""           # for prod, if using RSA
    ttl-seconds: 900
    bypass-paths: /auth/login,/actuator/**

# ========== Redis ==========
redis:
  enabled: true
  data:
    redis:
      host: redis
      port: 6379
      database: 0
      # password: ""  # if needed

# ========== Actuator ==========
management:
  endpoints:
    web:
      exposure:
        include: health,info
  endpoint:
    health:
      show-details: always

# ========== Logging ==========
logging:
  pattern:
    console: "%d{yyyy-MM-dd :: HH:mm:ss.SSS ||} %highlight(%-5level:: %file: | %line |){ERROR=bold red, WARN=yellow, INFO=white, DEBUG=green, TRACE=green} :: %msg%n"


application-dev.yaml files as per below ::

security:
  jwt:
    mode: hmac
    # example 32-byte (256-bit) base64-encoded secret
    hmac-base64-secret: bWV0aGlvbnlsdGhyZW9ueWx0aHJlb255bGdsdXRhbWlueWxhbGFueWw=
    bypass-paths: /auth/login,/actuator/**

spring:
  data:
    redis:
      host: redis
      port: 6379
      database: 0

redis: enabled:true;

SecurityConfig.java file as per below ::
package com.fincore.gateway.Config;

import com.fincore.gateway.Service.TokenSessionValidator;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.server.WebFilter;

@Slf4j
@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    private final TokenSessionValidator tokenSessionValidator;


    public SecurityConfig(TokenSessionValidator tokenSessionValidator) {
        this.tokenSessionValidator = tokenSessionValidator;
    }

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        System.out.println("security web filter chain");
        return http
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .authorizeExchange(exchanges -> exchanges
                        .pathMatchers("/auth/login", "/actuator/info","/actuator/health").permitAll()
                        .anyExchange().authenticated()
                )
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()))
                //  Run Redis validation right before authorization checks
                .addFilterAt(redisValidationFilter(), SecurityWebFiltersOrder.AUTHORIZATION)
                .build();
    }

    private WebFilter redisValidationFilter() {
        return (exchange, chain) -> {
            log.info("RedisValidationFilter invoked for path={}", exchange.getRequest().getPath());

            return exchange.getPrincipal()
                    .cast(Authentication.class)
                    .doOnNext(auth -> log.info("Principal = {}", auth))
                    .flatMap(auth -> tokenSessionValidator.validateWithRedis(auth)
                            .doOnSuccess(validAuth -> log.info(" Redis validated for {}", validAuth.getName()))
                            .flatMap(validAuth -> chain.filter(exchange))
                    )
                    .switchIfEmpty(chain.filter(exchange))
                    .onErrorResume(e -> {
                        log.error("!! RedisValidationFilter error: {}", e.getMessage());
                        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                        return exchange.getResponse().setComplete();
                    });
        };
    }

}

Trying to access the mapping from product-Service 
http://localhost:30080/Product/getProductTwo

Getting the Resposnse as unauthorized 401 :: as Ia m alredygenearting the latest JWT token using the "http://localhost:30080/auth/login" using generated JWT tpken for accesing the prodct mapping in Autozization headers why I am getting still this error is there something from with mapping or yaml files or missing anything which causes the console error 
