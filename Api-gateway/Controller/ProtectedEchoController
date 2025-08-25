package com.fincore.gateway.Controller;

import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.Map;

/**
 * Protected endpoint to verify auth+redis policies easily.
 */
@RestController
@RequestMapping("/secure")
public class ProtectedEchoController {

    @GetMapping("/hello")
    public Mono<Map<String, Object>> hello(JwtAuthenticationToken auth) {
        return Mono.just(Map.of(
                "message", "Hello " + auth.getName(),
                "sub", auth.getToken().getSubject(),
                "jti", auth.getToken().getId()
        ));
    }
}
