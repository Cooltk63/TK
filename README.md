# api-gateway.yaml
apiVersion: apps/v1
kind: Deployment
metadata:
  name: api-gateway
  namespace: dev
spec:
  replicas: 1
  selector:
    matchLabels:
      app: api-gateway
  template:
    metadata:
      labels:
        app: api-gateway
    spec:
      containers:
        - name: api-gateway
          image: api-gateway:latest
          imagePullPolicy: Never      # your docker built image
          ports:
            - containerPort: 8080
---
apiVersion: v1
kind: Service
metadata:
  name: api-gateway
  namespace: dev
spec:
  type: NodePort
  ports:
    - port: 8080
      targetPort: 8080
      nodePort: 30080
  selector:
    app: api-gateway




application.properties file of api gateway
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
spring.data.redis.host=redis
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
spring.cloud.gateway.server.webflux.routes[0].id=product-service
spring.cloud.gateway.server.webflux.routes[0].uri=http://product-service:8081
spring.cloud.gateway.server.webflux.routes[0].predicates[0]=Path=/Product/**

# Fincore service
spring.cloud.gateway.server.webflux.routes[1].id=fincore-service
spring.cloud.gateway.server.webflux.routes[1].uri=http://fincore-service:8089
spring.cloud.gateway.server.webflux.routes[1].predicates[0]=Path=/Fincore/**


application-dev properties file of api-gateway

# Local dev: use HS256 for easy testing
security.jwt.mode=hmac
# 32-byte (256-bit) secret, base64-encoded. Example only!
security.jwt.hmac-base64-secret=bWV0aGlvbnlsdGhyZW9ueWx0aHJlb255bGdsdXRhbWlueWxhbGFueWw=
#fake_base64_32byte_key_for_HM" (example)
security.jwt.bypass-paths=/auth/login,/actuator/**

# Local Redis
redis.enabled=true
spring.data.redis.host=redis
spring.data.redis.port=6379
spring.data.redis.database=0

docker file of api-gateway

# Use Red Hat UBI with OpenJDK 17 (if accessible internally)
FROM cimg/openjdk:24.0.2-node

# Set working directory inside container
WORKDIR /app

ARG JAR_FILE=target/*.jar

ENV SPRING_PROFILES_ACTIVE=dev
ENV SERVER_PORT=8080

# Copy JAR file into the container
COPY ${JAR_FILE} app.jar

EXPOSE 8080
# Run your Spring Boot app
CMD ["java", "-jar", "app.jar"]



