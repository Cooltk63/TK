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
 * security.jwt.mode = hmac | rsa
 * <p>
 * HMAC:
 * - security.jwt.hmac-base64-secret = base64 of your HS256 secret bytes (>= 256-bit)
 * <p>
 * RSA:
 * - security.jwt.rsa-public = PEM public key (-----BEGIN PUBLIC KEY----- ... -----END PUBLIC KEY-----)
 * <p>
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

