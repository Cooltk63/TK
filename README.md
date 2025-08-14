Previously you have given me the 

package com.fincore.gateway.Controller;


import com.fincore.gateway.JwtUtil.JwtUtil;
import com.fincore.gateway.Service.RedisTokenService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.UUID;

/**
 * Very small auth endpoints to help local testing:
 *  - POST /auth/login  -> returns access token (JWT) and stores token/session in Redis
 *  - POST /auth/logout -> removes token/session from Redis
 *
 * NOTE: This is a test stub. Replace with your real Login service in production.
 */
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final JwtUtil jwtUtil;
    private final RedisTokenService redisTokenService;

    @PostMapping("/login")
    public Mono<ResponseEntity<LoginResponse>> login(@RequestBody LoginRequest req) {
        // In real app: verify username/password against DB.
        // Here we accept any username/password for demo.
        String username = req.getUsername();
        String sessionId = UUID.randomUUID().toString();
        String jti = UUID.randomUUID().toString();

        // generate token using JwtUtil (HS256)
        String token = jwtUtil.generateToken(username, jti, sessionId);

        // store in redis with TTL = token validity
        long ttl = jwtUtil.getTokenValiditySeconds();
        return redisTokenService.storeToken(jti, username, sessionId, ttl)
                .thenReturn(ResponseEntity.ok(new LoginResponse(token, jti, sessionId, ttl)));
    }

    @PostMapping("/logout")
    public Mono<ResponseEntity<Void>> logout(@RequestBody LogoutRequest req) {
        return redisTokenService.removeToken(req.getJti(), req.getUsername())
                .thenReturn(ResponseEntity.noContent().build());
    }

    @Data static class LoginRequest {
        private String username;
        private String password;
    }
    @Data static class LoginResponse {
        private final String token;
        private final String jti;
        private final String sessionId;
        private final long ttlSeconds;
    }
    @Data static class LogoutRequest {
        private String username;
        private String jti;
    }
}



And Another Controller ::
package com.fincore.gateway.Controller;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/redis-test")
public class RedisTestController {

    private final StringRedisTemplate redisTemplate;

    public RedisTestController(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @GetMapping("/set")
    public String setKey(@RequestParam String key, @RequestParam String value) {
        redisTemplate.opsForValue().set(key, value);
        return "Saved " + key + "=" + value;
    }

    @GetMapping("/get")
    public String getKey(@RequestParam String key) {
        return redisTemplate.opsForValue().get(key);
    }
}

