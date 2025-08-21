application.yaml files as per below ::
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


application-dev.yaml files as per below ::

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

redis: enabled:true;
XXXXXXXXXXXXXXXX
Docker File :: as per below

# java
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



Now As per production grade changes we need to separate the hmac-base64-secret to environment varible and other required like redis password and guide me as per my above application.yaml files also use the spring profiles active 

alos had one more requirement how will i deploy the same microservices but on different enviornment all the envornment has same setup only currently dev enviornemnt keep as it is cause its on local desktop so need to keep the secrets in different yaml file as environment file

Right now I required production grade everthing here for testing as per my docker files , yaml file 
