// File: JwtAuthMicroserviceApplication.java
package com.example.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JwtAuthMicroserviceApplication {
    public static void main(String[] args) {
        SpringApplication.run(JwtAuthMicroserviceApplication.class, args);
    }
}


// File: controller/AuthController.java
package com.example.auth.controller;

import com.example.auth.service.AuthService;
import com.example.auth.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> request) {
        String userId = request.get("userId");
        return authService.login(userId);
    }
}


// File: service/AuthService.java
package com.example.auth.service;

import org.springframework.http.ResponseEntity;

public interface AuthService {
    ResponseEntity<?> login(String userId);
}


// File: dao/AuthDao.java
package com.example.auth.dao;

import java.util.Map;

public interface AuthDao {
    Map<String, Object> findUserById(String userId);
}


// File: dao/impl/AuthDaoImpl.java
package com.example.auth.dao.impl;

import com.example.auth.dao.AuthDao;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class AuthDaoImpl implements AuthDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Map<String, Object> findUserById(String userId) {
        Query query = entityManager.createNativeQuery("SELECT * FROM ba_user WHERE user_id = :userId", "UserMapping");
        query.setParameter("userId", userId);
        try {
            Object[] row = (Object[]) query.getSingleResult();
            Map<String, Object> userData = new HashMap<>();
            userData.put("user_id", row[0]);
            userData.put("user_role", row[1]);
            // Add more fields if needed
            return userData;
        } catch (Exception e) {
            return null;
        }
    }
}


// File: service/impl/AuthServiceImpl.java
package com.example.auth.service.impl;

import com.example.auth.dao.AuthDao;
import com.example.auth.service.AuthService;
import com.example.auth.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AuthDao authDao;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public ResponseEntity<?> login(String userId) {
        Map<String, Object> user = authDao.findUserById(userId);
        if (user != null) {
            String token = jwtUtil.generateToken(user);
            return ResponseEntity.ok().body(Map.of("token", token));
        } else {
            return ResponseEntity.status(401).body("Invalid user ID");
        }
    }
}


// File: util/JwtUtil.java
package com.example.auth.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private long expiration;

    public String generateToken(Map<String, Object> userData) {
        return Jwts.builder()
                .setClaims(userData)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }
}


// File: config/SecurityConfig.java
package com.example.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/auth/login").permitAll()
                .anyRequest().authenticated());

        return http.build();
    }
}


// File: resources/application.properties
server.port=8080
spring.datasource.url=jdbc:mysql://localhost:3306/your_database
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update

jwt.secret=your_jwt_secret_key
jwt.expiration=3600000


// File: resources/logback-spring.xml
<configuration>
    <property name="CONSOLE_LOG_PATTERN"
              value="%clr(%d{yyyy-MM-dd HH:mm:ss}){faint} %clr(%5p) %clr([%thread]){faint} %clr(%logger{36}){cyan} - %msg%n"/>

    <appender name="CONSOLE" class="ch.qos.logback.core.ConsoleAppender">
        <encoder>
            <pattern>${CONSOLE_LOG_PATTERN}</pattern>
        </encoder>
    </appender>

    <root level="INFO">
        <appender-ref ref="CONSOLE"/>
    </root>
</configuration>
