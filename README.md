E:\Kubernates Yaml Files\Prod-Grade>kubectl logs api-gateway-8ddc8bc88-srf6m -n prod
06:02:33.070 [main] ERROR org.springframework.boot.SpringApplication -- Application run failed
org.springframework.boot.context.config.InvalidConfigDataPropertyException: Property 'spring.profiles' imported from location 'class path resource [application-prod.yaml]' is invalid and should be replaced with 'spring.config.activate.on-profile' [origin: class path resource [application-prod.yaml] from app.jar - 2:13]
        at org.springframework.boot.context.config.InvalidConfigDataPropertyException.lambda$throwIfPropertyFound$0(InvalidConfigDataPropertyException.java:113)
        at java.base/java.util.LinkedHashMap.forEach(LinkedHashMap.java:987)
        at java.base/java.util.Collections$UnmodifiableMap.forEach(Collections.java:1708)
        at org.springframework.boot.context.config.InvalidConfigDataPropertyException.throwIfPropertyFound(InvalidConfigDataPropertyException.java:109)
        
       getting this error while trying to run the api-gateway using the prod environment using below configurations 

       DockerFile ::
FROM cimg/openjdk:24.0.2-node
# Set working directory inside container
WORKDIR /app
ARG JAR_FILE=target/*.jar
# Copy JAR file into the container
COPY ${JAR_FILE} app.jar
EXPOSE 8080
# Run your Spring Boot app
CMD ["java", "-jar", "app.jar"]

application.yml ::
spring:
  application:
    name: api-gateway

  profiles:
    active: dev

  main:
    web-application-type: reactive

  cloud:
    gateway:
      routes:
        - id: product-service
          uri: http://product-service:8081
          predicates:
            - Path=/Product/**
        - id: fincore-service
          uri: http://fincore-service:8089
          predicates:
            - Path=/Fincore/**
    kubernetes:
      discovery:
        enabled: false

server:
  port: 8080

# ========== JWT MODE ==========
security:
  jwt:
    mode: hmac
    hmac-base64-secret: ""   # set in application-dev.yml
    rsa-public: ""           # for prod, if using RSA
    ttl-seconds: 900
    bypass-paths: /auth/login,/actuator/**

# ========== Redis ==========
redis:
  enabled: true
  data:
    redis:
      host: redis
      port: 6379
      database: 0
      # password: ""  # if needed

# ========== Actuator ==========
management:
  endpoints:
    web:
      exposure:
        include: health,info
  endpoint:
    health:
      show-details: always

# ========== Logging ==========
logging:
  pattern:
    console: "%d{yyyy-MM-dd :: HH:mm:ss.SSS ||} %highlight(%-5level:: %file: | %line |){ERROR=bold red, WARN=yellow, INFO=white, DEBUG=green, TRACE=green} :: %msg%n"

application-prod.yml ::
spring:
  profiles: prod
  data:
    redis:
          host: ${REDIS_HOST}
          port: ${REDIS_PORT}
          database: ${REDIS_DB}
          password: ${REDIS_PASSWORD}
# Keep empty values, will be injected via K8s secrets
security:
  jwt:
    hmac-base64-secret: ${JWT_SECRET}

Deployment yaml file for api-gateway
apiVersion: apps/v1
kind: Deployment
metadata:
  name: api-gateway
  namespace: prod
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
          imagePullPolicy: IfNotPresent
          ports:
            - containerPort: 8080
          envFrom:
            - configMapRef:
                name: api-gateway-config
            - secretRef:
                name: api-gateway-secrets
          env:
            - name: SPRING_PROFILES_ACTIVE
              value: prod
---
apiVersion: v1
kind: Service
metadata:
  name: api-gateway
  namespace: prod
spec:
  type: NodePort
  ports:
    - port: 8080
      targetPort: 8080
      nodePort: 30080
  selector:
    app: api-gateway
XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
    Prod-Config.Yaml ::
    
    apiVersion: v1
kind: ConfigMap
metadata:
  name: api-gateway-config
  namespace: prod
data:
  REDIS_HOST: "redis"
  REDIS_PORT: "6379"
  REDIS_DB: "0"
XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
prod-secrets.yaml ::
apiVersion: v1
kind: Secret
metadata:
  name: api-gateway-secrets
  namespace: prod
type: Opaque
stringData:
  JWT_SECRET: bWV0aGlvbnlsdGhyZW9ueWx0aHJlb255bGdsdXRhbWlueWxhbGFueWw=
  REDIS_PASSWORD: Cooltcs@@RedisPassHere
