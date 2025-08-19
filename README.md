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


Then create the same to same yaml file for the application as it wont fixing in application.poroperties
