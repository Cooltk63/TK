package com.example.gateway.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import reactor.core.publisher.Mono;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    private final TokenSessionValidator tokenSessionValidator;

    public SecurityConfig(TokenSessionValidator tokenSessionValidator) {
        this.tokenSessionValidator = tokenSessionValidator;
    }

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        return http
            .csrf(ServerHttpSecurity.CsrfSpec::disable)
            .authorizeExchange(exchanges -> exchanges
                .pathMatchers("/auth/login", "/auth/register").permitAll()
                .anyExchange().authenticated()
            )
            .oauth2ResourceServer(oauth2 -> oauth2
                .jwt(Customizer.withDefaults())
            )
            // 🔑 Must run AFTER JWT is decoded, but BEFORE controller
            .addFilterAfter(redisValidationFilter(), SecurityWebFiltersOrder.AUTHENTICATION)
            .build();
    }

    private WebFilter redisValidationFilter() {
        return (exchange, chain) -> 
            exchange.getPrincipal()
                .cast(Authentication.class)
                .flatMap(auth -> tokenSessionValidator.validateWithRedis(auth)
                    .flatMap(validAuth -> chain.filter(exchange))
                )
                .onErrorResume(e -> {
                    // ❌ If validation fails -> block request
                    exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                    return exchange.getResponse().setComplete();
                });
    }
}