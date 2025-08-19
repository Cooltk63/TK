spring:
  application:
    name: api-gateway

  profiles:
    active: dev

  data:
    redis:
      host: localhost
      port: 6379
      database: 0
      # password: yourpassword  # uncomment if you use one

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

security:
  jwt:
    mode: hmac
    hmac-base64-secret:
    rsa-public:
    ttl-seconds: 900
    bypass-paths: /auth/login,/actuator/**

management:
  endpoints:
    web:
      exposure:
        include: health,info
  endpoint:
    health:
      show-details: always

logging:
  pattern:
    console: "%d{yyyy-MM-dd :: HH:mm:ss.SSS ||} %highlight(%-5level:: %file: | %line |){ERROR=bold red, WARN=yellow, INFO=white, DEBUG=green, TRACE=green} ::  %msg%n"