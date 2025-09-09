Login Endpoint Controller  ::

 @PostMapping("/login")
    public Mono<ResponseEntity<Map<String, Object>>> login(@RequestBody Map<String, Object> userMap) {
        log.info("UserMap Received :: " + userMap);
        // Methods Validate the User Existed in DB & Return the user object
        Map<String, Object> userData = loginService.verifyUserCredentials(userMap);

        User user = (User) userData.get("user");
        log.info("User Data Received after verifyUserCredentials:: " + (user ==null ? null : user.toString()));

        log.info("User Data ID Received: " + user.getUserId());
//        log.info("User Data Name Received: " + user.getFirstName());

        log.info("login req={}", userData);
        if (!"hmac".equalsIgnoreCase(mode)) {
            return Mono.just(ResponseEntity.badRequest().body(Map.of(
                    "error", "This demo /auth/login issues HS256 tokens only. Switch to security.jwt.mode=hmac for local tests."
            )));
        }
        log.info("login accept={}", userData);
        // Simple demo: accept any username
        if (hmacSecret == null || hmacSecret.isBlank()) {
            return Mono.just(ResponseEntity.internalServerError().body(Map.of("error", "Missing hmac secret")));
        }
        Map<String,Object> UserData=new HashMap<>();
        UserData.put("userid", user.getUserId());
        UserData.put("userrole", "TESTROLE");
        UserData.put("Password", user.getPasswordHash());

        // Generate a HS256 JWT (sub + jti + exp)
        String token = HmacJwtUtil.generate(hmacSecret, user.getUserId(), ttlSeconds,UserData);
        log.info("Generated token={}", token);


        // Extract jti again so we can store it; JJWT returns it inside the token, but we avoid parsing here:
        // Small parse just to get jti back (safe because we just created it).
        var parser = io.jsonwebtoken.Jwts.parser().verifyWith(io.jsonwebtoken.security.Keys.hmacShaKeyFor(
                Base64.getDecoder().decode(hmacSecret))).build();
        var claimsJws = parser.parseSignedClaims(token);
        String jti = claimsJws.getPayload().getId();

        log.info("Jti from claims claimsJws.getPayload().getId(){}", jti);

        return validator.registerUserSession(user.getUserId(), jti)
                .thenReturn(ResponseEntity.ok(Map.of(
                        "accessToken", token,
                        "tokenType", "Bearer",
                        "expiresIn", ttlSeconds,
                        "sub", user.getUserId(),
                        "jti", jti
                )));
    }

    LoginServiceImpl Method ::
    public Map<String, Object> verifyUserCredentials(Map<String, Object> userCredentials) {

        String userId = userCredentials.get("username").toString();
        String pass = userCredentials.get("password").toString();

        log.info("UserId ::" + userId +"  Password ::"+pass);

        log.info("Finding the User Details for given UserID");
        User user = userRepository.findByUserId(userId);

        log.info("User Details Received from DB {}", user.getFirstName());

        LoginParam loginparam = loginParamRepository.getLoginParam();

        Map<String, Object> userInfo = new HashMap<>();
        userInfo.put("loginMethod", loginparam.getActiveLoginMode());
        if (user == null) {
            userInfo.put("message", "Invalid User");
            userInfo.put(VALID_CREDENTIALS, false);
            userInfo.put("userStatus", "INVALID");
            return userInfo;
        }
        int userFailedPasswordAttemptCount = user.getUserWrongPasswordCount();
        int permittedAttemptCount = loginparam.getWrongPasswordAttempts();

        LoginAttempt loginAttempt = new LoginAttempt();
        loginAttempt.setUserId(userId);
        loginAttempt.setLoginMethod(loginparam.getActiveLoginMode().equalsIgnoreCase("P") ? "PASS" : "SSO");

        if (user.getAccountStatus().equalsIgnoreCase("active")) {
            log.info("User Status is ::" + user.getAccountStatus());
            userInfo.put("userStatus", "ACTIVE");
            userInfo.put("user", user);
            if (loginparam.getActiveLoginMode().equalsIgnoreCase("P")) {
                log.info("Login Param Active Login Mode ::" + loginparam.getActiveLoginMode());
                passwordBasedLogin(userCredentials, userFailedPasswordAttemptCount, permittedAttemptCount, loginAttempt, userInfo, user, loginparam);
            } else {
                // SSO based login
                loginAttempt.setSuccess("Y");
                loginAttempt.setLoginMethod("SSO");
                userInfo.put("user", user);
                userInfo.put(VALID_CREDENTIALS, true);
            }
        } else {
            /*
            Request tampered and trying to login with  inactive / locked / verfication pending user
            */
            log.info("User Status is not active inside else block ::", user.getAccountStatus());
            loginAttempt.setSuccess("N");
            loginAttempt.setFailureReason("Trying to login with a " + user.getAccountStatus() + " User");
            userInfo.put("userStatus", user.getAccountStatus());
            userInfo.put(VALID_CREDENTIALS, false);

        }
        loginAttemptRepository.save(loginAttempt);
        if (Boolean.TRUE.equals(userInfo.get(VALID_CREDENTIALS))) {

            log.info("User Credentials Valid ::" + userInfo.get(VALID_CREDENTIALS));

            Map<String, Object> userRole = userRepository.getUserRole(userId);
            //log.info(userRole.get("role").toString());

            // Generate a HS256 JWT (sub + jti + exp)
            log.info("Before Generate JWT User Data ::{}", user.getUserId());
            Map<String,Object> UserData=new HashMap<>();
            UserData.put("userid", user.getUserId());
            UserData.put("userrole", userRole);
            UserData.put("Password", user.getPasswordHash());

            String accessToken = HmacJwtUtil.generate(hmacSecret, user.getUserId(), ttlSeconds,UserData);
            String refreshToken = HmacJwtUtil.generate(hmacSecret, user.getUserId(), ttlSeconds,UserData);

            RefreshToken previousRefreshToken = refreshTokenRepository.getLatestRefreshTokensByUserid(userId);
            if (previousRefreshToken != null) {
                previousRefreshToken.setSessionEndedAt(Timestamp.valueOf(LocalDateTime.now()));
                refreshTokenRepository.save(previousRefreshToken);
            }
            RefreshToken refreshTokenEntity = new RefreshToken();
            refreshTokenEntity.setUserid(userId);
            refreshTokenEntity.setRefreshToken(refreshToken);
            refreshTokenRepository.save(refreshTokenEntity);
            userInfo.put("accessToken", accessToken);
            userInfo.put("refreshToken", refreshToken);
        }
        return userInfo;
    }

HmacUtil Token Generation Method ::
   public static String generate(String base64Secret, String subject, long ttlSeconds, Map<String,Object> UserData) {
        byte[] secret = Base64.getDecoder().decode(base64Secret);
        SecretKey key = Keys.hmacShaKeyFor(secret);

        Instant now = Instant.now();
        Instant exp = now.plusSeconds(ttlSeconds);
        log.info("Generated exp in Seconds={}", exp);
        String jti = UUID.randomUUID().toString();
        log.info("Jti generated={}", jti);

        // Added the userData to JWT token
        return Jwts.builder().setClaims(UserData).setSubject(String.valueOf(subject))
                .id(jti)
                .issuedAt(Date.from(now))
                .expiration(Date.from(exp))
                .signWith(key)      // HS256
                .compact();
    }

    Security Config Filter ::
    
@Slf4j
@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    private final TokenSessionValidator tokenSessionValidator;
    // Bypass paths List
    @Value("${security.jwt.bypass-paths}")
    private String[] bypassPaths;


    public SecurityConfig(TokenSessionValidator tokenSessionValidator) {
        this.tokenSessionValidator = tokenSessionValidator;
    }

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        System.out.println("security web filter chain");
        return http
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .authorizeExchange(exchanges -> exchanges
                                .pathMatchers("/auth/login", "/actuator/info", "/actuator/health").permitAll()
                        //Dynamic Paths Passing
//                        .pathMatchers(bypassPaths).permitAll()
                                .anyExchange().authenticated()
                )
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()))
                //  Run Redis validation right before authorization checks
                .addFilterAt(redisValidationFilter(), SecurityWebFiltersOrder.AUTHORIZATION)
                .build();
    }

    private WebFilter redisValidationFilter() {
        return (exchange, chain) -> {
            log.info("RedisValidationFilter invoked for path={}", exchange.getRequest().getPath());

            return exchange.getPrincipal()
                    .cast(Authentication.class)
                    .doOnNext(auth -> log.info("Principal = {}", auth))
                    .flatMap(auth -> tokenSessionValidator.validateWithRedis(auth)
                            .doOnSuccess(validAuth -> log.info(" Redis validated for {}", validAuth.getName()))
                            .flatMap(validAuth -> chain.filter(exchange))
                    )
                    .switchIfEmpty(chain.filter(exchange))
                    .onErrorResume(e -> {
                        log.error("!! RedisValidationFilter error: {}", e.getMessage());
                        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                        return exchange.getResponse().setComplete();
                    });
        };
    }

}

Token Session Validator MEthod ::

@Component
public class TokenSessionValidator {

    private static final Logger log = LoggerFactory.getLogger(TokenSessionValidator.class);
    // Redis key prefixes
    private static final String USER_PREFIX = "USR:";
    private static final String BLACKLIST_PREFIX = "BL:";
    // Expiry = same as JWT expiry (example: 15 minutes, should match your JwtService config)
    private static final Duration TOKEN_TTL = Duration.ofMinutes(15);
    private final ReactiveStringRedisTemplate redisTemplate;

    public TokenSessionValidator(ReactiveStringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * Called during login to register a new session for the user.
     * 1. Blacklists old JTI if exists
     * 2. Stores new JTI in Redis with expiry
     */
    public Mono<Void> registerUserSession(String username, String newJti) {
        log.info("Getting Username & newjti ::", username, newJti);
        String userKey = USER_PREFIX + username;

        log.info("Inside registerUserSession method " + userKey);

        return redisTemplate.opsForValue().get(userKey)
                .flatMap(oldJti -> {
                    if (oldJti != null) {
                        log.info(" Found old session for user={} -> blacklisting oldJti={}", username, oldJti);
                        return blacklistToken(oldJti);
                    }
                    return Mono.empty();
                })
                .then(redisTemplate.opsForValue()
                        .set(userKey, newJti, TOKEN_TTL)
                        .doOnSuccess(v -> log.info(" Registered new session in Redis for user={} jti={}", username, newJti))
                        .then()
                );
    }

    /**
     * Blacklist a token (on logout or replacement).
     */
    public Mono<Boolean> blacklistToken(String jti) {
        String key = BLACKLIST_PREFIX + jti;
        return redisTemplate.opsForValue().set(key, "true", TOKEN_TTL)
                .doOnSuccess(v -> log.info(" Blacklisted token jti={}", jti));
    }

    /**
     * Clear session for a given user (e.g., on logout).
     */
    public Mono<Boolean> clearUserSession(String username) {
        return redisTemplate.delete(USER_PREFIX + username)
                .map(deleted -> {
                    if (deleted > 0) {
                        log.info(" Cleared session for user={}", username);
                        return true;
                    }
                    return false;
                });
    }

    /**
     * Validate token against Redis.
     * - Reject if blacklisted
     * - Reject if not equal to the latest session JTI
     */
    public Mono<Authentication> validateWithRedis(Authentication authentication) {
        if (!(authentication instanceof JwtAuthenticationToken jwtAuth)) {
            log.warn("Skipping validation: not a JwtAuthenticationToken -> {}", authentication);
            return Mono.error(new BadCredentialsException("Invalid authentication type"));
        }

        Jwt jwt = jwtAuth.getToken();
        String username = jwt.getSubject();
        String jti = jwt.getId();

        if (jti == null) {
            log.error("Token missing JTI claim -> rejecting token for user={}", username);
            return Mono.error(new BadCredentialsException("Missing token ID (jti)"));
        }

        log.info(" Validating token for user={} with jti={}", username, jti);

        return redisTemplate.hasKey(BLACKLIST_PREFIX + jti)
                .flatMap(isBlacklisted -> {
                    if (Boolean.TRUE.equals(isBlacklisted)) {
                        log.warn(" Token is blacklisted -> jti={}, user={}", jti, username);
                        return Mono.error(new BadCredentialsException("Token revoked"));
                    }

                    return redisTemplate.opsForValue().get(USER_PREFIX + username)
                            .flatMap(currentJti -> {
                                log.info(" Redis stored JTI for user={} is {}", username, currentJti);

                                if (currentJti == null) {
                                    log.warn(" No active session found in Redis for user={} -> rejecting", username);
                                    return Mono.error(new BadCredentialsException("No active session"));
                                }

                                if (!currentJti.equals(jti)) {
                                    log.warn(" Token mismatch for user={} -> expected={}, got={}",
                                            username, currentJti, jti);
                                    return Mono.error(new BadCredentialsException("Another session is active"));
                                }

                                log.info(" Token validation success for user={} with jti={}", username, jti);
                                return Mono.just(authentication);
                            });
                });
    }
}

I need the logout endpoint and its all the code for above code logic please provide me tested and working logout code it.
