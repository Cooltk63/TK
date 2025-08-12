package com.fincore.ApiGateWay.Util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.ReactiveStringRedisTemplate;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.time.Duration;
import java.util.Base64;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.ReactiveStringRedisTemplate;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.time.Duration;
import java.util.Base64;

@Component
public class JwtUtil {

    @Value("${security.jwt.public-key}")
    private String publicKeyStr;

    @Value("${security.session.ttl-seconds:3600}")
    private long sessionTtlSeconds;

    private final ReactiveStringRedisTemplate redisTemplate;
    private PublicKey publicKey;

    public JwtUtil(ReactiveStringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @PostConstruct
    public void init() throws Exception {
        byte[] keyBytes = Base64.getDecoder().decode(publicKeyStr);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("HmacSHA256");
        this.publicKey = keyFactory.generatePublic(keySpec);
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
                .setSigningKey(publicKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}


This is Jwtutil class

I have recently key generated using ::
public class Main {
    public static void main(String[] args) throws NoSuchAlgorithmException {

        KeyGenerator keygen =KeyGenerator.getInstance("HmacSHA256");
        keygen.init(256);

        SecretKey secretKey = keygen.generateKey();

        String Base64= java.util.Base64.getEncoder().encodeToString((secretKey).getEncoded());

        System.out.println("KeyGen Is ::" +Base64);
    }
}

help me where should i have to changes
