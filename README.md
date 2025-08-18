@Bean
public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
    System.out.println("security web filter chain");
    return http
            .csrf(ServerHttpSecurity.CsrfSpec::disable)
            .authorizeExchange(exchanges -> exchanges
                    .pathMatchers("/auth/login", "/auth/register").permitAll()
                    .anyExchange().authenticated()
            )
            .oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()))
            // ✅ Run Redis validation right before authorization checks
            .addFilterAt(redisValidationFilter(), SecurityWebFiltersOrder.AUTHORIZATION)
            .build();
}



xxx

private WebFilter redisValidationFilter() {
    return (exchange, chain) -> {
        log.info("🚦 RedisValidationFilter invoked for path={}", exchange.getRequest().getPath());

        return exchange.getPrincipal()
                .cast(Authentication.class)
                .doOnNext(auth -> log.info("🔑 Principal = {}", auth))
                .flatMap(auth -> tokenSessionValidator.validateWithRedis(auth)
                        .doOnSuccess(validAuth -> log.info("✅ Redis validated for {}", validAuth.getName()))
                        .flatMap(validAuth -> chain.filter(exchange))
                )
                .switchIfEmpty(chain.filter(exchange))
                .onErrorResume(e -> {
                    log.error("❌ RedisValidationFilter error: {}", e.getMessage());
                    exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                    return exchange.getResponse().setComplete();
                });
    };
}
