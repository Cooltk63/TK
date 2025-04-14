// File: JwtAuthMicroserviceApplication.java package com.example.auth;

import org.springframework.boot.SpringApplication; import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication public class JwtAuthMicroserviceApplication { public static void main(String[] args) { SpringApplication.run(JwtAuthMicroserviceApplication.class, args); } }

// File: entity/UserEntity.java package com.example.auth.entity;

import jakarta.persistence.*;

@Entity @Table(name = "ba_user") public class UserEntity {

@Id
@Column(name = "user_id")
private String userId;

@Column(name = "user_role")
private String userRole;

// Add other columns if necessary

public String getUserId() {
    return userId;
}

public void setUserId(String userId) {
    this.userId = userId;
}

public String getUserRole() {
    return userRole;
}

public void setUserRole(String userRole) {
    this.userRole = userRole;
}

}

// File: repository/UserRepository.java package com.example.auth.repository;

import com.example.auth.entity.UserEntity; import org.springframework.data.jpa.repository.JpaRepository; import org.springframework.stereotype.Repository;

@Repository public interface UserRepository extends JpaRepository<UserEntity, String> { UserEntity findByUserId(String userId); }

// File: controller/AuthController.java package com.example.auth.controller;

import com.example.auth.service.AuthService; import org.springframework.beans.factory.annotation.Autowired; import org.springframework.http.ResponseEntity; import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController @RequestMapping("/api/auth") public class AuthController {

@Autowired
private AuthService authService;

@PostMapping("/login")
public ResponseEntity<?> login(@RequestBody Map<String, String> request) {
    String userId = request.get("userId");
    return authService.login(userId);
}

}

// File: service/AuthService.java package com.example.auth.service;

import org.springframework.http.ResponseEntity;

public interface AuthService { ResponseEntity<?> login(String userId); }

// File: service/impl/AuthServiceImpl.java package com.example.auth.service.impl;

import com.example.auth.entity.UserEntity; import com.example.auth.repository.UserRepository; import com.example.auth.service.AuthService; import com.example.auth.util.JwtUtil; import org.springframework.beans.factory.annotation.Autowired; import org.springframework.http.ResponseEntity; import org.springframework.stereotype.Service;

import java.util.Map;

@Service public class AuthServiceImpl implements AuthService {

@Autowired
private UserRepository userRepository;

@Autowired
private JwtUtil jwtUtil;

@Override
public ResponseEntity<?> login(String userId) {
    UserEntity user = userRepository.findByUserId(userId);
    if (user != null) {
        Map<String, Object> claims = Map.of(
            "user_id", user.getUserId(),
            "user_role", user.getUserRole()
        );
        String token = jwtUtil.generateToken(claims);
        return ResponseEntity.ok().body(Map.of("token", token));
    } else {
        return ResponseEntity.status(401).body("Invalid user ID");
    }
}

}

// File: util/JwtUtil.java package com.example.auth.util;

import io.jsonwebtoken.Jwts; import io.jsonwebtoken.SignatureAlgorithm; import org.springframework.beans.factory.annotation.Value; import org.springframework.stereotype.Component;

import java.util.Date; import java.util.Map;

@Component public class JwtUtil {

@Value("${jwt.secret}")
private String secret;

@Value("${jwt.expiration}")
private long expiration;

public String generateToken(Map<String, Object> claims) {
    return Jwts.builder()
            .setClaims(claims)
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + expiration))
            .signWith(SignatureAlgorithm.HS256, secret)
            .compact();
}

}

// File: config/SecurityConfig.java package com.example.auth.config;

import org.springframework.context.annotation.Bean; import org.springframework.context.annotation.Configuration; import org.springframework.security.config.annotation.web.builders.HttpSecurity; import org.springframework.security.web.SecurityFilterChain;

@Configuration public class SecurityConfig {

@Bean
public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.csrf(csrf -> csrf.disable())
        .authorizeHttpRequests(auth -> auth
            .requestMatchers("/api/auth/login").permitAll()
            .anyRequest().authenticated());

    return http.build();
}

}

// File: resources/application.properties server.port=8080 spring.datasource.url=jdbc:mysql://localhost:3306/your_database spring.datasource.username=your_username spring.datasource.password=your_password spring.jpa.hibernate.ddl-auto=update spring.jpa.show-sql=true

jwt.secret=your_jwt_secret_key jwt.expiration=3600000

// File: resources/logback-spring.xml <configuration> <property name="CONSOLE_LOG_PATTERN"
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