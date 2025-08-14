if (!(authentication instanceof BearerTokenAuthentication bta)) {
    return Mono.error(new BadCredentialsException("Unsupported authentication"));
}

Jwt jwt = (Jwt) bta.getPrincipal(); // decoded token
String username = jwt.getClaimAsString("sub"); // subject
String jti = jwt.getId(); // token ID

if (username == null || jti == null) {
    return Mono.error(new BadCredentialsException("Invalid token: missing sub or jti"));
}

String blKey = "BL:" + jti;
String userKey = "USR:" + username;

return redis.opsForValue().get(blKey)
    .defaultIfEmpty("")
    .flatMap(bl -> {
        if (!bl.isEmpty()) {
            return Mono.error(new BadCredentialsException("Token is blacklisted"));
        }
        return Mono.just(authentication);
    });