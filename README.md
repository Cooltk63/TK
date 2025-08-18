package com.fincore.gateway.Config;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.jwt.NimbusReactiveJwtDecoder;
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoder;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.interfaces.RSAPublicKey;
import java.util.Base64;

/**
 * Creates a ReactiveJwtDecoder based on properties:
 *  security.jwt.mode = hmac | rsa
 *
 *  HMAC:
 *   - security.jwt.hmac-base64-secret = base64 of your HS256 secret bytes (>= 256-bit)
 *
 *  RSA:
 *   - security.jwt.rsa-public = PEM public key (-----BEGIN PUBLIC KEY----- ... -----END PUBLIC KEY-----)
 *
 * Switch environments by only changing properties. No code change.
 */
@Configuration
public class JwtDecoderConfig {

    @Value("${security.jwt.mode:hmac}")
    private String mode;

    @Value("${security.jwt.hmac-base64-secret:}")
    private String hmacBase64Secret;

    @Value("${security.jwt.rsa-public:}")
    private String rsaPublicPem;

    @PostConstruct
    void sanity() {
        if ("hmac".equalsIgnoreCase(mode)) {
            if (hmacBase64Secret == null || hmacBase64Secret.isBlank()) {
                throw new IllegalStateException("HMAC mode selected but security.jwt.hmac-base64-secret is empty");
            }
        } else if ("rsa".equalsIgnoreCase(mode)) {
            if (rsaPublicPem == null || rsaPublicPem.isBlank()) {
                throw new IllegalStateException("RSA mode selected but security.jwt.rsa-public is empty");
            }
        } else {
            throw new IllegalStateException("Unsupported security.jwt.mode: " + mode + " (use 'hmac' or 'rsa')");
        }
    }

    @Bean
    public ReactiveJwtDecoder jwtDecoder() {
        if ("hmac".equalsIgnoreCase(mode)) {
            // Decode base64 to raw bytes and create a SecretKey for HS256 verification
            byte[] secretBytes = Base64.getDecoder().decode(hmacBase64Secret);
            SecretKey key = new SecretKeySpec(secretBytes, "HmacSHA256");
            return NimbusReactiveJwtDecoder.withSecretKey(key).build();
        } else {
            // Parse RSA public key for RS256 verification
            RSAPublicKey publicKey = KeyUtils.parseRsaPublicKey(rsaPublicPem);
            return NimbusReactiveJwtDecoder.withPublicKey(publicKey).build();
        }
    }
}



XXX


package com.fincore.gateway.Config;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

/** Utility to parse a PEM RSA public key string into RSAPublicKey */
public class KeyUtils {

    public static RSAPublicKey parseRsaPublicKey(String pem) {
        try {
            String clean = pem
                    .replace("-----BEGIN PUBLIC KEY-----", "")
                    .replace("-----END PUBLIC KEY-----", "")
                    .replaceAll("\\s", "");
            byte[] decoded = Base64.getDecoder().decode(clean);
            X509EncodedKeySpec spec = new X509EncodedKeySpec(decoded);
            KeyFactory kf = KeyFactory.getInstance("RSA");
            return (RSAPublicKey) kf.generatePublic(spec);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new IllegalArgumentException("Invalid RSA public key", e);
        }
    }
}



XXX

package com.fincore.gateway.Config;


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

XXX
package com.fincore.gateway.Service;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.ReactiveStringRedisTemplate;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.time.Duration;

/**
 * Enforces:
 *  1) Token is NOT revoked:          Redis key  BL:<jti> == "1"   (exists => revoked)
 *  2) Single active session per user: Redis key  USR:<username> == current jti
 *
 * Notes:
 *  - Works only after the JWT is validated by Spring Security (signature/expiry).
 *  - Toggle with redis.enabled=true/false (per environment).
 */
@Component
@RequiredArgsConstructor
public class TokenSessionValidator {

    private final ReactiveStringRedisTemplate redis;

    @Value("${redis.enabled:true}")
    private boolean redisEnabled;

    public Mono<Authentication> validateWithRedis(Authentication authentication) {
        if (!redisEnabled) return Mono.just(authentication);

        if (!(authentication instanceof JwtAuthenticationToken jwtAuth)) {
            return Mono.error(new BadCredentialsException("Unsupported authentication type"));
        }

        Jwt jwt = jwtAuth.getToken();
        String username = jwt.getClaimAsString("sub"); // standard subject claim
        String jti = jwt.getId();                      // standard JWT ID claim

        if (username == null || jti == null) {
            return Mono.error(new BadCredentialsException("Missing required claims (sub/jti)"));
        }

        String blKey = "BL:" + jti;
        String userKey = "USR:" + username;

        return redis.opsForValue().get(blKey)
                .defaultIfEmpty("")
                .flatMap(bl -> {
                    if (!bl.isEmpty()) {
                        return Mono.error(new BadCredentialsException("Token revoked"));
                    }
                    return redis.opsForValue().get(userKey).defaultIfEmpty("");
                })
                .flatMap(currentJti -> {
                    if (!currentJti.isEmpty() && !currentJti.equals(jti)) {
                        return Mono.error(new BadCredentialsException("Another session is active"));
                    }
                    return Mono.just(authentication);
                });
    }

    /** Called on login: register user's active session jti with TTL == token lifetime */
    public Mono<Void> registerUserSession(String username, String jti, long ttlSeconds) {
        if (!redisEnabled) return Mono.empty();
        String userKey = "USR:" + username;
        return redis.opsForValue()
                .set(userKey, jti, Duration.ofSeconds(ttlSeconds))
                .then();
    }

    /** Called on logout: blacklist current token jti so it can’t be reused */
    public Mono<Void> revokeToken(String jti, long ttlSeconds) {
        if (!redisEnabled) return Mono.empty();
        String blKey = "BL:" + jti;
        return redis.opsForValue().set(blKey, "1", Duration.ofSeconds(ttlSeconds)).then();
    }
}

xxx
Application.properties
spring.application.name=api-gateway
server.port=8080

# ========== JWT MODE ==========
# hmac or rsa (pick per environment using profile files)
security.jwt.mode=hmac

# HS256: base64 secret (>= 256-bit). For dev, we override below.
security.jwt.hmac-base64-secret=
# RS256: PEM public key (BEGIN/END PUBLIC KEY). For prod, set via profile.
security.jwt.rsa-public=

# Token lifetime used by demo /auth/login (seconds)
security.jwt.ttl-seconds=900

# Paths that bypass auth (comma-separated). Adjust per env if needed.
security.jwt.bypass-paths=/auth/login,/actuator/**

# ========== Redis ==========
redis.enabled=true
spring.data.redis.host=localhost
spring.data.redis.port=6379
spring.data.redis.database=0
# spring.data.redis.password=   # if needed in non-dev

# Actuator basic
management.endpoints.web.exposure.include=health,info
management.endpoint.health.show-details=always

spring.profiles.active=dev
XXX

Application-dev.properties

# Local dev: use HS256 for easy testing
security.jwt.mode=hmac
# 32-byte (256-bit) secret, base64-encoded. Example only!
security.jwt.hmac-base64-secret=bWV0aGlvbnlsdGhyZW9ueWx0aHJlb255bGdsdXRhbWlueWxhbGFueWw=
#fake_base64_32byte_key_for_HM" (example)
security.jwt.bypass-paths=/auth/login,/actuator/**

# Local Redis
redis.enabled=true
spring.data.redis.host=localhost
spring.data.redis.port=6379
spring.data.redis.database=0
