This is application.yaml file of Api-gateway for Prod Environment
Filename: application.yaml

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
# ========== DB Details ==========
  datasource:
    url: jdbc:oracle:thin:@10.191.216.58:1522:crsprod
    username: fnsonli
    password: Password#1234
server:
  port: 8080
# ========== JWT MODE ==========
security:
  jwt:
    mode: hmac
    hmac-base64-secret: ""
    # for prod, if using RSA
    rsa-public: ""
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

    XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX

This is application-prod.yaml file of Api-gateway for Prod Environment
Filename: application-prod.yaml

spring:
  config:
    activate:
      on-profile: prod
  data:
  # ========== Redis Config ==========
    redis:
      host: ${REDIS_HOST}
      port: ${REDIS_PORT}
      database: ${REDIS_DB}
      password: ${REDIS_PASSWORD}
  # ========== DB Details ==========
  datasource:
    url: jdbc:oracle:thin:@${DB_HOST}:${DB_PORT}:${DB_SID}
    username: ${DB_USER}
    password: ${DB_PASS}
# ========== JWT Secrets ==========
security:
  jwt:
    hmac-base64-secret: ${JWT_SECRET}
    XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
# prod-secrets.yaml

apiVersion: v1
kind: Secret
metadata:
  name: api-gateway-secrets
  namespace: prod
type: Opaque
stringData:
  JWT_SECRET: bWV0aGlvbnlsdGhyZW9ueWx0aHJlb255bGdsdXRhbWlueWxhbGFueWw=
  REDIS_PASSWORD: Cooltcs@@RedisPassHere
  DB_PASS: Password#1234
    XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
  
# redis.yaml
apiVersion: apps/v1
kind: Deployment
metadata:
  name: redis
  namespace: prod
spec:
  replicas: 1
  selector:
    matchLabels:
      app: redis
  template:
    metadata:
      labels:
        app: redis
    spec:
      containers:
        - name: redis
          image: redis/redis-stack-server:latest
          imagePullPolicy: Never
          ports:
            - containerPort: 6379
---
apiVersion: v1
kind: Service
metadata:
  name: redis
  namespace: prod
spec:
  type: ClusterIP
  ports:
    - port: 6379
      targetPort: 6379
  selector:
    app: redis
	
    XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
	# prod-api-gateway.yaml
	
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
    XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
	# prod-config.yaml
	
	apiVersion: v1
kind: ConfigMap
metadata:
 name: api-gateway-config
 namespace: prod
data:
 DB_HOST: "10.191.216.58"
 DB_PORT: "1522"
 DB_SID: "crsprod"
 DB_USER: "fnsonli"
 REDIS_HOST: "redis"
 REDIS_PORT: "6379"
 REDIS_DB: "0"
 
    XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
 I also had the other service fincore-service & product-service which is separate microservices as per previosyly discussed we use both microservices only used or exposed internally and communicate or call each other internally also each microservices has the its own config of DB
 Now my requiremnts are 
 1: Implement the Ingress & Egress for the Kubernates
 2: Need Detailed & proper commented yaml files
 3: needed the application highly secured & dynamic 
 4: I had more then 4+ other different types or DB ,Oracle, hadooop or more Etc.
 5: Need option for scaling or upgrade required the more microservices to be added as its tightly secured. 
 6: need the Understanding for how my FE Vite-js gonna work with microservices what config we have to do betwen FE & BE Microservices
 7: How does flow Work or have to config. I have  Domain name Eg. TEAMFLOW.com there is LB name which is VMware Avi Load Balancer then what should I do at my end as tanzu team only provide us namespace & we have to do all the config where should I start and end where I am completly unwere for the concept of the config or say completely new and beginer guide me how to do config & complete setup.
 8: Suggest me what else we can add here to make enterprise grade security and more robust and easy to main or any suggestion might be helpful for security prospective.
 9: next phase -2 we will implement the logging mechanism as we need the FE & BE logs.
 
