package com.fincore.ApiGateWay.Util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.ReactiveStringRedisTemplate;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import javax.crypto.SecretKey;
import java.time.Duration;
import java.util.Base64;

@Component
public class JwtUtil {

    @Value("${security.jwt.secret}")
    private String secretKeyStr; // HMAC secret in Base64

    @Value("${security.session.ttl-seconds:3600}")
    private long sessionTtlSeconds;

    private final ReactiveStringRedisTemplate redisTemplate;
    private SecretKey secretKey;

    public JwtUtil(ReactiveStringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @PostConstruct
    public void init() {
        byte[] keyBytes = Base64.getDecoder().decode(secretKeyStr);
        this.secretKey = Keys.hmacShaKeyFor(keyBytes);
    }

    public boolean validateToken(String token) {
        try {
            Claims claims = getAllClaims(token);
            return claims.getExpiration().after(new java.util.Date());
        } catch (Exception e) {
            return false;
        }
    }

    public String getSubject(String token) {
        return getAllClaims(token).getSubject();
    }

    public String getSessionId(String token) {
        Object sessionId = getAllClaims(token).get("sessionId");
        return sessionId != null ? sessionId.toString() : null;
    }

    public Mono<Boolean> isSessionActiveMono(String subject, String sessionId) {
        if (subject == null || sessionId == null) return Mono.just(false);
        String redisKey = "session:" + subject;
        return redisTemplate.opsForValue().get(redisKey)
                .map(storedSessionId -> storedSessionId.equals(sessionId))
                .defaultIfEmpty(false);
    }

    public Mono<Void> storeSession(String subject, String sessionId) {
        String redisKey = "session:" + subject;
        return redisTemplate.opsForValue()
                .set(redisKey, sessionId, Duration.ofSeconds(sessionTtlSeconds))
                .then();
    }

    private Claims getAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}