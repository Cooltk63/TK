@PostMapping("/logout")
public Mono<ResponseEntity<Map<String, Object>>> logout(JwtAuthenticationToken jwtAuth) {
    if (jwtAuth == null) {
        return Mono.just(ResponseEntity.badRequest().body(Map.of("error", "No authenticated token found")));
    }

    var jwt = jwtAuth.getToken();
    String username = jwt.getSubject();
    String jti = jwt.getId();

    log.info("Logout requested for user={} jti={}", username, jti);

    // Blacklist token + clear session
    return validator.revokeTokenAndClearSessionIfMatches(username, jti)
            .then(Mono.just(ResponseEntity.ok(Map.of(
                    "message", "User logged out successfully",
                    "user", username,
                    "jti", jti
            ))));
}