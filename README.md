@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    private final TokenSessionValidator tokenSessionValidator;

    public SecurityConfig(TokenSessionValidator tokenSessionValidator) {
        this.tokenSessionValidator = tokenSessionValidator;
    }

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        return http
            .csrf(ServerHttpSecurity.CsrfSpec::disable)
            .authorizeExchange(exchanges -> exchanges
                .pathMatchers("/auth/login", "/auth/register").permitAll()
                .anyExchange().authenticated()
            )
            .oauth2ResourceServer(oauth2 -> oauth2
                .jwt(Customizer.withDefaults())
            )
            // 🔑 Add custom filter after JWT validation
            .addFilterAt(redisValidationFilter(), SecurityWebFiltersOrder.AUTHENTICATION)
            .build();
    }

    private WebFilter redisValidationFilter() {
        return (exchange, chain) -> exchange.getPrincipal()
            .cast(Authentication.class)
            .flatMap(tokenSessionValidator::validateWithRedis)
            .then(chain.filter(exchange))
            .onErrorResume(e -> {
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                return exchange.getResponse().setComplete();
            });
    }
}