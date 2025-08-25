package com.fincore.gateway.Controller;


import com.fincore.gateway.JwtUtil.HmacJwtUtil;
import com.fincore.gateway.Service.TokenSessionValidator;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Base64;
import java.util.Map;

/**
 * Test-only endpoints to exercise the gateway+redis locally.
 * - POST /auth/login  {username} -> returns HS256 token (sub + jti) and registers session in Redis
 * - POST /auth/logout            -> blacklists current token jti in Redis
 * <p>
 * In PRODUCTION:
 * - Remove this controller.
 * - Your Login service should write to Redis: USR:<user>=<jti>; On logout write BL:<jti>=1.
 */
@Slf4j
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final TokenSessionValidator validator;

    @Value("${security.jwt.mode:hmac}")
    private String mode;

    @Value("${security.jwt.hmac-base64-secret:}")
    private String hmacSecret;

    @Value("${security.jwt.ttl-seconds:900}")
    private long ttlSeconds;

    @PostMapping("/login")
    public Mono<ResponseEntity<Map<String, Object>>> login(@RequestBody LoginReq req) {
        log.info("login req={}", req);
        if (!"hmac".equalsIgnoreCase(mode)) {
            return Mono.just(ResponseEntity.badRequest().body(Map.of(
                    "error", "This demo /auth/login issues HS256 tokens only. Switch to security.jwt.mode=hmac for local tests."
            )));
        }
        log.info("login accept={}", req);
        // Simple demo: accept any username
        if (hmacSecret == null || hmacSecret.isBlank()) {
            return Mono.just(ResponseEntity.internalServerError().body(Map.of("error", "Missing hmac secret")));
        }

        // Generate a HS256 JWT (sub + jti + exp)
        String token = HmacJwtUtil.generate(hmacSecret, req.getUsername(), ttlSeconds);
        log.info("Generated token={}", token);


        // Extract jti again so we can store it; JJWT returns it inside the token, but we avoid parsing here:
        // Small parse just to get jti back (safe because we just created it).
        var parser = io.jsonwebtoken.Jwts.parser().verifyWith(io.jsonwebtoken.security.Keys.hmacShaKeyFor(
                Base64.getDecoder().decode(hmacSecret))).build();
        var claimsJws = parser.parseSignedClaims(token);
        String jti = claimsJws.getPayload().getId();

        log.info("Jti from claims claimsJws.getPayload().getId(){}", jti);

        return validator.registerUserSession(req.getUsername(), jti)
                .thenReturn(ResponseEntity.ok(Map.of(
                        "accessToken", token,
                        "tokenType", "Bearer",
                        "expiresIn", ttlSeconds,
                        "sub", req.getUsername(),
                        "jti", jti
                )));
    }

   /* @PostMapping("/logout")
    public Mono<ResponseEntity<String>> logout(@AuthenticationPrincipal Jwt jwt) {
        String jti = jwt.getId();
        String username = jwt.getSubject();

        return redisTemplate.opsForValue()
                .set("BL:" + jti, "true", Duration.ofSeconds(TOKEN_TTL_SECONDS))
                .then(redisTemplate.delete("USR:" + username))
                .thenReturn(ResponseEntity.ok("User " + username + " logged out successfully"));
    }*/

    @Data
    public static class LoginReq {
        private String username;
        private String password;
    }
}

