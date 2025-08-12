Console Output ::

Error starting ApplicationContext. To display the condition evaluation report re-run your application with 'debug' enabled.
2025-08-12T13:32:33.266+05:30 ERROR 54076 --- [ApiGateWay] [           main] o.s.b.d.LoggingFailureAnalysisReporter   : 

***************************
APPLICATION FAILED TO START
***************************

Description:

Failed to bind properties under 'spring.cloud.gateway.server.webflux.routes' to java.util.List<org.springframework.cloud.gateway.route.RouteDefinition>:

    Property: spring.cloud.gateway.server.webflux.routes
    Value: "product-service"
    Origin: class path resource [application.properties] - 25:44
    Reason: failed to convert java.lang.String to @jakarta.validation.constraints.NotNull @jakarta.validation.Valid org.springframework.cloud.gateway.route.RouteDefinition (caused by jakarta.validation.ValidationException: Unable to parse RouteDefinition text 'product-service', must be of the form name=value)

Action:

Update your application's configuration


Process finished with exit code 1




Application.properties file ::

spring.application.name=ApiGateWay

spring.data.redis.host=localhost
spring.data.redis.port=6379
spring.data.redis.timeout=60000
spring.data.redis.client-type=lettuce


spring.datasource.driver-class-name=oracle.jdbc.OracleDriver
server.port=8080
spring.profiles.active=dev


security.jwt.public-key=uGp9YSsW41V4dIkoW7SHcoeyDnrUZ+amH+JuNESsQms=
security.session.ttl-seconds=3600
gateway.bypass.urls=/auth/**,/public/**

management.endpoints.web.exposure.include=health,info,prometheus
management.endpoint.health.show-details=never
management.endpoints.web.base-path=/management

# Logging
logging.level.org.springframework.cloud.gateway=INFO

spring.cloud.gateway.server.webflux.routes=product-service
spring.cloud.gateway.server.webflux.routes[0].uri=http://product-service:8080
spring.cloud.gateway.server.webflux.routes[0].predicates[0]=Path=/products/**
spring.cloud.gateway.server.webflux.routes[0].filters[0]=RequestRateLimiter=redis-rate-limiter.replenishRate=5,redis-rate-limiter.burstCapacity=10
spring.cloud.gateway.server.webflux.routes[0].filters[1]=RewritePath=/products/(?<segment>.*), /${segment}

gateway.cors.allowed-origins=http://localhost:8080
gateway.cors.allowed-methods=GET,POST,UPDATE,DELETE
gateway.cors.allowed-headers=*
