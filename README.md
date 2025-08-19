spring.application.name=api-gateway
server.port=8080

# ========== JWT MODE ==========
# hmac or rsa (pick per environment using profile files)
security.jwt.mode=hmac

# HS256: base64 secret (>= 256-bit). For dev, we override below.
security.jwt.hmac-base64-secret=
# RS256: PEM public key (BEGIN/END PUBLIC KEY). For prod, set via profile.
security.jwt.rsa-public=

# Token lifetime used by demo /auth/login (seconds)
security.jwt.ttl-seconds=900

# Paths that bypass auth (comma-separated). Adjust per env if needed.
security.jwt.bypass-paths=/auth/login,/actuator/**

# ========== Redis ==========
redis.enabled=true
spring.data.redis.host=localhost
spring.data.redis.port=6379
spring.data.redis.database=0
# spring.data.redis.password=   # if needed in non-dev

# Actuator basic
management.endpoints.web.exposure.include=health,info
management.endpoint.health.show-details=always

# ========== Logging ==========
# Console log pattern (Color-coded output)
logging.pattern.console=%d{yyyy-MM-dd :: HH:mm:ss.SSS ||} %highlight(%-5level:: %file: | %line |){ERROR=bold red, WARN=yellow, INFO=white, DEBUG=green, TRACE=green} ::  %msg%n


spring.profiles.active=dev


# Product service
spring.cloud.gateway.routes=product-service
spring.cloud.gateway.routes[0].uri=http://product-service:8081
spring.cloud.gateway.routes[0].predicates[0]=Path=/Product/**

# Fincore service
spring.cloud.gateway.routes[1].id=fincore-service
spring.cloud.gateway.routes[1].uri=http://fincore-service:8089
spring.cloud.gateway.routes[1].predicates[0]=Path=/Fincore/**


Error Snap as per below ::

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/

 :: Spring Boot ::                (v3.5.4)

2025-08-19 :: 14:22:14.227 || INFO :: StartupInfoLogger.java: | 53 | ::  Starting ApiGatewayApplication using Java 22.0.1 with PID 30572 (F:\Projects\Fincore\Backend\api-gateway\target\classes started by V1012297 in F:\Projects\Fincore\Backend\api-gateway)
2025-08-19 :: 14:22:14.231 || INFO :: SpringApplication.java: | 658 | ::  The following 1 profile is active: "dev"
2025-08-19 :: 14:22:16.234 || INFO :: RepositoryConfigurationDelegate.java: | 294 | ::  Multiple Spring Data modules found, entering strict repository configuration mode
2025-08-19 :: 14:22:16.238 || INFO :: RepositoryConfigurationDelegate.java: | 145 | ::  Bootstrapping Spring Data Redis repositories in DEFAULT mode.
2025-08-19 :: 14:22:16.277 || INFO :: RepositoryConfigurationDelegate.java: | 213 | ::  Finished Spring Data repository scanning in 18 ms. Found 0 Redis repository interfaces.
2025-08-19 :: 14:22:16.663 || INFO :: GenericScope.java: | 280 | ::  BeanFactory id=80888d7a-debc-30ce-a39f-2bbbf667e39c
security web filter chain
2025-08-19 :: 14:22:18.240 || WARN :: AbstractApplicationContext.java: | 635 | ::  Exception encountered during context initialization - cancelling refresh attempt: org.springframework.beans.factory.UnsatisfiedDependencyException: Error creating bean with name 'nettyWriteResponseFilter' defined in class path resource [org/springframework/cloud/gateway/config/GatewayAutoConfiguration$NettyConfiguration.class]: Unsatisfied dependency expressed through method 'nettyWriteResponseFilter' parameter 0: Error creating bean with name 'gatewayProperties': Could not bind properties to 'GatewayProperties' : prefix=spring.cloud.gateway.server.webflux, ignoreInvalidFields=false, ignoreUnknownFields=true
2025-08-19 :: 14:22:18.260 || WARN :: GatewayServerWebfluxPropertiesMigrationListener.java: | 162 | ::  
The use of configuration keys that have been renamed was found in the environment:

Property source 'Config resource 'class path resource [application.properties]' via location 'optional:classpath:/'':
	Key: spring.cloud.gateway.routes
		Line: 39
		Replacement: spring.cloud.gateway.server.webflux.routes
	Key: spring.cloud.gateway.routes[0].uri
		Line: 40
		Replacement: spring.cloud.gateway.server.webflux.routes[0].uri
	Key: spring.cloud.gateway.routes[0].predicates[0]
		Line: 41
		Replacement: spring.cloud.gateway.server.webflux.routes[0].predicates[0]
	Key: spring.cloud.gateway.routes[1].id
		Line: 44
		Replacement: spring.cloud.gateway.server.webflux.routes[1].id
	Key: spring.cloud.gateway.routes[1].uri
		Line: 45
		Replacement: spring.cloud.gateway.server.webflux.routes[1].uri
	Key: spring.cloud.gateway.routes[1].predicates[0]
		Line: 46
		Replacement: spring.cloud.gateway.server.webflux.routes[1].predicates[0]


Each configuration key has been temporarily mapped to its replacement for your convenience. To silence this warning, please update your configuration to use the new keys.

2025-08-19 :: 14:22:18.261 || WARN :: PropertiesMigrationListener.java: | 95 | ::  
The use of configuration keys that have been renamed was found in the environment:

Property source 'Config resource 'class path resource [application.properties]' via location 'optional:classpath:/'':
	Key: spring.cloud.gateway.routes
		Line: 39
		Replacement: spring.cloud.gateway.server.webflux.routes


Each configuration key has been temporarily mapped to its replacement for your convenience. To silence this warning, please update your configuration to use the new keys.

2025-08-19 :: 14:22:18.273 || INFO :: ConditionEvaluationReportLogger.java: | 82 | ::  

Error starting ApplicationContext. To display the condition evaluation report re-run your application with 'debug' enabled.
2025-08-19 :: 14:22:18.290 || ERROR:: LoggingFailureAnalysisReporter.java: | 40 | ::  

***************************
APPLICATION FAILED TO START
***************************

Description:

Failed to bind properties under 'spring.cloud.gateway.server.webflux.routes' to java.util.List<org.springframework.cloud.gateway.route.RouteDefinition>:

    Property: spring.cloud.gateway.server.webflux.routes
    Value: "product-service"
    Origin: class path resource [application.properties] - 39:29
    Reason: failed to convert java.lang.String to @jakarta.validation.constraints.NotNull @jakarta.validation.Valid org.springframework.cloud.gateway.route.RouteDefinition (caused by jakarta.validation.ValidationException: Unable to parse RouteDefinition text 'product-service', must be of the form name=value)

Action:

Update your application's configuration


Process finished with exit code 1
