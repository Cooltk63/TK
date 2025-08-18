package com.fincore.gateway.config;

import com.fincore.gateway.Service.TokenSessionValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.server.WebFilter;
import org.springframework.security.core.Authentication;
import reactor.core.publisher.Mono;

/**
 * Security configuration for the API Gateway.
 *
 * Features:
 *  - Uses JWT authentication with Spring Security's built-in resource server support.
 *  - Protects all routes except /auth/login and /auth/register.
 *  - Adds a custom WebFilter to enforce Redis-backed token/session validation:
 *      -> Prevents usage of revoked tokens.
 *      -> Enforces single active session per user (latest token only).
 */
@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    private final TokenSessionValidator tokenSessionValidator;

    // Inject our custom Redis validator
    public SecurityConfig(TokenSessionValidator tokenSessionValidator) {
        this.tokenSessionValidator = tokenSessionValidator;
    }

    /**
     * Defines the security filter chain for the gateway.
     * - Disables CSRF (not needed for token-based APIs).
     * - Permits login and register endpoints.
     * - Requires authentication for all other endpoints.
     * - Enables JWT decoding and validation.
     * - Adds Redis validation filter after authentication step.
     */
    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        return http
            // Disable CSRF (since this is an API using JWT, not cookies/sessions)
            .csrf(ServerHttpSecurity.CsrfSpec::disable)

            // Authorization rules
            .authorizeExchange(exchanges -> exchanges
                .pathMatchers("/auth/login", "/auth/register").permitAll() // allow login/register
                .anyExchange().authenticated() // everything else requires authentication
            )

            // Use Spring Security’s OAuth2 Resource Server support with JWT
            .oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()))

            // Add our custom Redis validator AFTER authentication step
            .addFilterAt(redisValidationFilter(), SecurityWebFiltersOrder.AUTHENTICATION)

            .build();
    }

    /**
     * Custom WebFilter to enforce Redis-based validation.
     * - Called AFTER JWT is successfully validated by Spring Security.
     * - Delegates to TokenSessionValidator to check:
     *      1. If token is blacklisted (revoked).
     *      2. If the token's JTI matches the currently active session for the user.
     * - If validation fails -> returns 401 Unauthorized.
     */
    private WebFilter redisValidationFilter() {
        return (exchange, chain) -> exchange.getPrincipal()
            .cast(Authentication.class)
            .flatMap(tokenSessionValidator::validateWithRedis) // validate token in Redis
            .then(chain.filter(exchange)) // continue request if valid
            .onErrorResume(e -> {
                // If validation fails -> return 401
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                return exchange.getResponse().setComplete();
            });
    }
}