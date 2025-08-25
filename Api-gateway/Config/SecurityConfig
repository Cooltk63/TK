package com.fincore.gateway.Config;

import com.fincore.gateway.Service.TokenSessionValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
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

    // Bypass paths List
    @Value("${security.jwt.bypass-paths}")
    private String[] bypassPaths;

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
//                        .pathMatchers("/auth/login", "/actuator/info","/actuator/health").permitAll()
                        .pathMatchers(bypassPaths).permitAll()
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
