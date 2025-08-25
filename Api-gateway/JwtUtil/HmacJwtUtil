package com.fincore.gateway.JwtUtil;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

/**
 * Minimal HS256 token generator for local tests (NOT for prod).
 */
@Slf4j
public final class HmacJwtUtil {

    private HmacJwtUtil() {
    }

    public static String generate(String base64Secret, String subject, long ttlSeconds) {
        byte[] secret = Base64.getDecoder().decode(base64Secret);
        SecretKey key = Keys.hmacShaKeyFor(secret);

        Instant now = Instant.now();
        Instant exp = now.plusSeconds(ttlSeconds);
        log.info("Generated exp in Seconds={}", exp);
        String jti = UUID.randomUUID().toString();
        log.info("Jti generated={}", jti);

        return Jwts.builder()
                .subject(subject)
                .id(jti)
                .issuedAt(Date.from(now))
                .expiration(Date.from(exp))
                .signWith(key)      // HS256
                .compact();
    }
}
