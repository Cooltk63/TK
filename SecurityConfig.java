package com.fincore.gateway.Config;

import com.fincore.gateway.Service.TokenSessionValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    @Value("${gateway.bypass.urls}")
    private String bypassCsv;

    @Value("${gateway.cors.allowed-origins:*}")
    private String allowedOrigins;

    @Value("${gateway.cors.allowed-methods:*}")
    private String allowedMethods;

    @Value("${gateway.cors.allowed-headers:*}")
    private String allowedHeaders;

    private final TokenSessionValidator tokenSessionValidator; // Redis-backed validator

    // ========= JWT: validate HS256 using a base64-encoded secret =========
    @Bean
    ReactiveJwtDecoder jwtDecoder(@Value("${security.jwt.secret}") String base64Secret) {
        byte[] key = java.util.Base64.getDecoder().decode(base64Secret);
        return NimbusReactiveJwtDecoder.withSecretKey(new javax.crypto.spec.SecretKeySpec(key, "HmacSHA256"))
                .build();
    }

    // ========= The ONLY filter chain =========
    @Bean
    SecurityWebFilterChain springSecurity(ServerHttpSecurity http, ReactiveJwtDecoder decoder) {

        // Build permitAll matchers from CSV
        var permitMatchers = Arrays.stream(bypassCsv.split(","))
                .map(String::trim)
                .filter(s -> !s.isBlank())
                .toArray(String[]::new);

        http
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .cors(cors -> cors.configurationSource(exchange -> {
                    var c = new CorsConfiguration();
                    c.setAllowedOrigins(splitCsv(allowedOrigins));
                    c.setAllowedMethods(splitCsv(allowedMethods));
                    c.setAllowedHeaders(splitCsv(allowedHeaders));
                    c.setAllowCredentials(true);
                    c.setMaxAge(Duration.ofHours(1));
                    return c;
                }))

                // Authorize: bypass some URLs, everything else requires JWT
                .authorizeExchange(reg -> reg
                        .pathMatchers(permitMatchers).permitAll()
                        .anyExchange().authenticated()
                )

                // Enable JWT resource server (Bearer token)
                .oauth2ResourceServer(oauth -> oauth
                        .jwt(jwt -> jwt
                                .jwtDecoder(decoder)
                                .jwtAuthenticationConverter(jwtAuthConverter(tokenSessionValidator))
                        ));

        return http.build();
    }

    private static List<String> splitCsv(String csv) {
        if (csv == null || csv.isBlank()) return List.of("*");
        return Arrays.stream(csv.split(",")).map(String::trim).toList();
    }

    @Bean
    public Converter<Jwt, ? extends Mono<? extends AbstractAuthenticationToken>> jwtAuthConverter(TokenSessionValidator tokenSessionValidator) {
        return jwt -> {
            UsernamePasswordAuthenticationToken auth =
                    new UsernamePasswordAuthenticationToken(jwt.getSubject(), jwt, AuthorityUtils.NO_AUTHORITIES);
            return tokenSessionValidator.validateWithRedis(auth).thenReturn(auth);
        };
    }

}

