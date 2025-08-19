Properties file

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


Comppose-Yaml file
version: "3.9"
services:
  redis:
    image: redis/redis-stack-server
    command: ["redis-server", "--appendonly", "yes"]
    networks:
      - appnet
    ports:
      - "6379:6379"

  api-gateway:
    image: api-gateway:latest
    depends_on:
      - redis
      - product-service
      - fincore-service
    environment:
      - SPRING_PROFILES_ACTIVE=dev
      - SPRING_DATA_REDIS_HOST=redis
    networks:
      - appnet
    ports:
      - "8080:8080"      # only gateway is exposed to host

  product-service:
    image: product-service:latest
    networks:
      - appnet
    environment:
      - SPRING_PROFILES_ACTIVE=dev
      - GATEWAY_BASE_URL=http://api-gateway:8080
    # do NOT publish ports for this service

  fincore-service:
    image: fincore-service:latest
    networks:
      - appnet
    environment:
      - SPRING_PROFILES_ACTIVE=dev
      - GATEWAY_BASE_URL=http://api-gateway:8080

networks:
  appnet:
    driver: bridge

docker images & network details as per below
C:\Users\v1012297>docker images
REPOSITORY                 TAG           IMAGE ID       CREATED          SIZE
api-gateway                latest        b412084079a3   36 minutes ago   3.6GB
fincore-service            latest        eeba607b5bd9   53 minutes ago   3.53GB
product-service            latest        7f44ddd751f9   2 hours ago      3.6GB
cimg/openjdk               24.0.2-node   7de6cbbe2cbc   3 weeks ago      3.43GB
redis/redis-stack-server   latest        3751e8743b31   6 weeks ago      824MB

C:\Users\v1012297>docker network ls
NETWORK ID     NAME             DRIVER    SCOPE
e92f9da361f2   bridge           bridge    local
ae8374b507be   host             host      local
eff958cf0962   my-app-network   bridge    local
4cc6f9f437be   none             null      local


C:\Users\v1012297>docker run -d --network my-app-network  --name product-service -p 8081:8081 product-service
631980baeb1984679ed612d36469f16cf8794db0d96c8429d6c5dd641a79f3f1

C:\Users\v1012297>docker run -d --network my-app-network  --name fincore-service -p 8089:8089 fincore-service
7b3901c467d73c52b98835c88d5967dc1a07833ad25e4a6d61b64e19b331d822

C:\Users\v1012297>docker run -d --network my-app-network  --name api-gateway -p 8080:8080 api-gateway
1cd6e626fdf4feb3344f09d34dd3f3e06de09d7471bbdcd1e78d7d7859eb1a85

C:\Users\v1012297>docker run -d --name redis -p 6379:6379 redis/redis-stack-server
605cd0f9c4c1a42cbde9d3daaa1f496258956da049281788801bcc316b189738

C:\Users\v1012297>docker ps
CONTAINER ID   IMAGE                      COMMAND               CREATED          STATUS          PORTS                                         NAMES
1cd6e626fdf4   api-gateway                "java -jar app.jar"   8 seconds ago    Up 8 seconds    0.0.0.0:8080->8080/tcp, [::]:8080->8080/tcp   api-gateway
7b3901c467d7   fincore-service            "java -jar app.jar"   13 seconds ago   Up 13 seconds   0.0.0.0:8089->8089/tcp, [::]:8089->8089/tcp   fincore-service
631980baeb19   product-service            "java -jar app.jar"   17 seconds ago   Up 17 seconds   0.0.0.0:8081->8081/tcp, [::]:8081->8081/tcp   product-service
98458fc7b1dc   redis/redis-stack-server   "/entrypoint.sh"      13 minutes ago   Up 13 minutes   0.0.0.0:6379->6379/tcp, [::]:6379->6379/tcp   redis

Kindly give me the proper withput fail use composeyaml file for both dev & prod enviroment without fail 
