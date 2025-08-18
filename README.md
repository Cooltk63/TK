@PostMapping("/logout")
public Mono<ResponseEntity<String>> logout(@AuthenticationPrincipal Jwt jwt) {
    String jti = jwt.getId();
    String username = jwt.getSubject();

    return redisTemplate.opsForValue()
        .set("BL:" + jti, "true", Duration.ofSeconds(TOKEN_TTL_SECONDS))
        .then(redisTemplate.delete("USR:" + username))
        .thenReturn(ResponseEntity.ok("User " + username + " logged out successfully"));
}