@Bean
public Converter<Jwt, ? extends Mono<? extends AbstractAuthenticationToken>> jwtAuthConverter(TokenSessionValidator tokenSessionValidator) {
    return jwt -> {
        UsernamePasswordAuthenticationToken auth =
            new UsernamePasswordAuthenticationToken(jwt.getSubject(), jwt, AuthorityUtils.NO_AUTHORITIES);
        return tokenSessionValidator.validateWithRedis(auth).thenReturn(auth);
    };
}


.oauth2ResourceServer(oauth -> oauth
    .jwt(jwt -> jwt
        .jwtDecoder(decoder)
        .jwtAuthenticationConverter(jwtAuthConverter(tokenSessionValidator))
    )
)