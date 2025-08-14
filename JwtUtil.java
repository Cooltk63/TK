package com.fincore.gateway.JwtUtil;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.util.Date;
import java.util.UUID;

@Component
public class JwtUtil {

    @Value("${security.jwt.secret}")
    private String base64Secret;

    @Value("${security.jwt.expire-seconds:900}")
    private long expireSeconds;

    private SecretKey hmacKey;

    @PostConstruct
    void init() {
        byte[] keyBytes = java.util.Base64.getDecoder().decode(base64Secret);
        if (keyBytes.length < 32) { // 256 bits minimum for HS256
            throw new IllegalStateException("HS256 secret must be >= 32 bytes (base64-encoded).");
        }
        this.hmacKey = Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateToken(String username, String jti, String sessionId) {
        Instant now = Instant.now();
        Instant exp = now.plusSeconds(expireSeconds);
        return Jwts.builder()
                .setSubject(username)
                .setId(jti)
                .claim("sid", sessionId)  // optional
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(exp))
                .signWith(hmacKey, SignatureAlgorithm.HS256)
                .compact();
    }

    public long getTtlSeconds() {
        return expireSeconds;
    }

    public String newJti() {
        return UUID.randomUUID().toString();
    }}
