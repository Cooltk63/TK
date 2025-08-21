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
spring:
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


xxx

security:
  jwt:
    mode: hmac
    # example 32-byte (256-bit) base64-encoded secret
    hmac-base64-secret: bWV0aGlvbnlsdGhyZW9ueWx0aHJlb255bGdsdXRhbWlueWxhbGFueWw=
    bypass-paths: /auth/login,/actuator/**

spring:
  data:
    redis:
      host: redis
      port: 6379
      database: 0