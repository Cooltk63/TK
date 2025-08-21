spring:
  profiles: prod

# Keep empty values, will be injected via K8s secrets
security:
  jwt:
    hmac-base64-secret: ${JWT_SECRET}

spring:
  data:
    redis:
      host: ${REDIS_HOST}
      port: ${REDIS_PORT}
      database: ${REDIS_DB}
      password: ${REDIS_PASSWORD}


xxx

apiVersion: v1
kind: Secret
metadata:
  name: api-gateway-secrets
  namespace: prod
type: Opaque
stringData:
  JWT_SECRET: "super-secure-prod-jwt-secret"
  REDIS_PASSWORD: "super-strong-password"

xx

apiVersion: apps/v1
kind: Deployment
metadata:
  name: api-gateway
  namespace: prod
spec:
  replicas: 2
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


xx

plain

spring:
  application:
    name: api-gateway

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
  port: ${SERVER_PORT:8080}

# ========== JWT MODE ==========
security:
  jwt:
    mode: hmac
    hmac-base64-secret: ${JWT_SECRET:}   # injected via env
    rsa-public: ${RSA_PUBLIC:}
    ttl-seconds: 900
    bypass-paths: /auth/login,/actuator/**

# ========== Redis ==========
spring:
  data:
    redis:
      host: ${REDIS_HOST:redis}
      port: ${REDIS_PORT:6379}
      database: ${REDIS_DB:0}
      password: ${REDIS_PASSWORD:}

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

