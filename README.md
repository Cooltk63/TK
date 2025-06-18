// --- User.java (Entity) --- package com.example.auditapp.entity;

import jakarta.persistence.; import java.util.;

@Entity public class User { @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;

private String username;
private String email;

@ElementCollection(fetch = FetchType.EAGER)
@Enumerated(EnumType.STRING)
private Set<Permission> permissions = new HashSet<>();

// Getters and Setters
public Long getId() { return id; }
public void setId(Long id) { this.id = id; }

public String getUsername() { return username; }
public void setUsername(String username) { this.username = username; }

public String getEmail() { return email; }
public void setEmail(String email) { this.email = email; }

public Set<Permission> getPermissions() { return permissions; }
public void setPermissions(Set<Permission> permissions) { this.permissions = permissions; }

}

// --- Permission.java (Enum) --- package com.example.auditapp.entity;

public enum Permission { INTERNAL_AUDIT, EXTERNAL_AUDIT, PERSONAL_AUDIT }

// --- UserRepository.java --- package com.example.auditapp.repository;

import com.example.auditapp.entity.User; import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> { }

// --- UserService.java (Interface) --- package com.example.auditapp.service;

import com.example.auditapp.entity.Permission; import com.example.auditapp.entity.User;

import java.util.*;

public interface UserService { List<User> getAllUsers(); List<Permission> getAllPermissions(); User assignPermissions(Long userId, Set<Permission> permissions); User getUserById(Long userId); User removePermissions(Long userId, Set<Permission> permissions); User createUser(String username, String email); }

// --- UserServiceImpl.java --- package com.example.auditapp.service.impl;

import com.example.auditapp.entity.Permission; import com.example.auditapp.entity.User; import com.example.auditapp.repository.UserRepository; import com.example.auditapp.service.UserService; import org.springframework.beans.factory.annotation.Autowired; import org.springframework.stereotype.Service;

import java.util.*;

@Service public class UserServiceImpl implements UserService {

@Autowired
private UserRepository userRepository;

@Override
public List<User> getAllUsers() {
    return userRepository.findAll();
}

@Override
public List<Permission> getAllPermissions() {
    return Arrays.asList(Permission.values());
}

@Override
public User assignPermissions(Long userId, Set<Permission> permissions) {
    User user = userRepository.findById(userId).orElseThrow();
    user.setPermissions(permissions);
    return userRepository.save(user);
}

@Override
public User removePermissions(Long userId, Set<Permission> permissions) {
    User user = userRepository.findById(userId).orElseThrow();
    user.getPermissions().removeAll(permissions);
    return userRepository.save(user);
}

@Override
public User getUserById(Long userId) {
    return userRepository.findById(userId).orElseThrow();
}

@Override
public User createUser(String username, String email) {
    User user = new User();
    user.setUsername(username);
    user.setEmail(email);
    return userRepository.save(user);
}

}

// --- UserController.java --- package com.example.auditapp.controller;

import com.example.auditapp.entity.Permission; import com.example.auditapp.entity.User; import com.example.auditapp.service.UserService; import org.springframework.beans.factory.annotation.Autowired; import org.springframework.http.; import org.springframework.web.bind.annotation.;

import java.util.*;

@RestController @RequestMapping("/api") public class UserController {

@Autowired
private UserService userService;

@GetMapping("/users")
public ResponseEntity<List<User>> getAllUsers() {
    return ResponseEntity.ok(userService.getAllUsers());
}

@GetMapping("/permissions")
public ResponseEntity<List<Permission>> getAllPermissions() {
    return ResponseEntity.ok(userService.getAllPermissions());
}

@PostMapping("/users")
public ResponseEntity<User> createUser(@RequestBody Map<String, String> req) {
    return ResponseEntity.ok(userService.createUser(req.get("username"), req.get("email")));
}

@PostMapping("/users/{userId}/permissions")
public ResponseEntity<User> assignPermissions(@PathVariable Long userId, @RequestBody Map<String, List<Permission>> body) {
    return ResponseEntity.ok(userService.assignPermissions(userId, new HashSet<>(body.get("permissions"))));
}

@GetMapping("/users/{userId}")
public ResponseEntity<User> getUserById(@PathVariable Long userId) {
    return ResponseEntity.ok(userService.getUserById(userId));
}

@DeleteMapping("/users/{userId}/permissions")
public ResponseEntity<User> removePermissions(@PathVariable Long userId, @RequestBody Map<String, List<Permission>> body) {
    return ResponseEntity.ok(userService.removePermissions(userId, new HashSet<>(body.get("permissions"))));
}

}

// --- application.properties --- spring.datasource.url=jdbc:mysql://localhost:3306/auditdb spring.datasource.username=root spring.datasource.password=root spring.jpa.hibernate.ddl-auto=update spring.jpa.show-sql=true

