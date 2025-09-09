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
import java.util.List;

@Slf4j
@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    private final TokenSessionValidator tokenSessionValidator;
    // Bypass paths List
    @Value("${security.jwt.bypass-paths}")
    private List<String> bypassPaths;
    
    
    public SecurityConfig(TokenSessionValidator tokenSessionValidator) {
        this.tokenSessionValidator = tokenSessionValidator;
    }

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        System.out.println("security web filter chain");
        return http
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .authorizeExchange(exchanges -> exchanges
                                .pathMatchers("/auth/login","/auth/logout", "/actuator/info", "/actuator/health").permitAll()
                        //Dynamic Paths Passing
//                        .pathMatchers(bypassPaths).permitAll()
                                .anyExchange().authenticated()
                )
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()))
                //  Run Redis validation right before authorization checks
                .addFilterAt(redisValidationFilter(), SecurityWebFiltersOrder.AUTHORIZATION)
                .build();
    }

    In Above line of code Dynamic Paths Passing not working or getting error I wanted to pass the allowed paths from .yaml files which is properties files how to use the dynamic way to pass the .pathMatchers to the above line of code 
