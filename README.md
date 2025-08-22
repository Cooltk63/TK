 public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        System.out.println("security web filter chain");
        return http
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .authorizeExchange(exchanges -> exchanges
                        .pathMatchers("/auth/login", "/actuator/info","/actuator/health").permitAll()
                        .anyExchange().authenticated()
                )
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()))
                //  Run Redis validation right before authorization checks
                .addFilterAt(redisValidationFilter(), SecurityWebFiltersOrder.AUTHORIZATION)
                .build();
    }


properties from .yaml file
  bypass-paths: /auth/login,/actuator/**

  i need to pass this paths to .pathMatchers("/auth/login", "/actuator/info","/actuator/health").permitAll() dynamically insted of hardcoding how will I pass this yaml files paths to .pathMatchers()
    
