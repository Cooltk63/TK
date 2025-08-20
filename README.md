Radies Docker console log :
2025-08-20 12:17:27.435 | 8:signal-handler (1755672447) Received SIGTERM scheduling shutdown...
2025-08-20 12:17:27.435 | 8:signal-handler (1755672447) Received SIGTERM scheduling shutdown...
2025-08-20 12:17:27.725 | 8:C 20 Aug 2025 06:47:27.724 * oO0OoO0OoO0Oo Redis is starting oO0OoO0OoO0Oo
2025-08-20 12:17:27.725 | 8:C 20 Aug 2025 06:47:27.724 * Redis version=7.4.5, bits=64, commit=00000000, modified=0, pid=8, just started
2025-08-20 12:17:27.725 | 8:C 20 Aug 2025 06:47:27.724 * Configuration loaded
2025-08-20 12:17:27.725 | 8:M 20 Aug 2025 06:47:27.725 * monotonic clock: POSIX clock_gettime
2025-08-20 12:17:27.726 | 8:M 20 Aug 2025 06:47:27.725 * Running mode=standalone, port=6379.
2025-08-20 12:17:27.726 | 8:M 20 Aug 2025 06:47:27.726 * Module 'RedisCompat' loaded from /opt/redis-stack/lib/rediscompat.so
2025-08-20 12:17:27.727 | 8:M 20 Aug 2025 06:47:27.727 * <search> Redis version found by RedisSearch : 7.4.5 - oss
2025-08-20 12:17:27.727 | 8:M 20 Aug 2025 06:47:27.727 * <search> RediSearch version 2.10.20 (Git=5c09b69)
2025-08-20 12:17:27.728 | 8:M 20 Aug 2025 06:47:27.727 * <search> Low level api version 1 initialized successfully
2025-08-20 12:17:27.728 | 8:M 20 Aug 2025 06:47:27.728 * <search> gc: ON, prefix min length: 2, min word length to stem: 4, prefix max expansions: 200, query timeout (ms): 500, timeout policy: return, cursor read size: 1000, cursor max idle (ms): 300000, max doctable size: 1000000, max number of search results:  10000, 
2025-08-20 12:17:27.728 | 8:M 20 Aug 2025 06:47:27.728 * <search> Initialized thread pools!
2025-08-20 12:17:27.728 | 8:M 20 Aug 2025 06:47:27.728 * <search> Subscribe to config changes
2025-08-20 12:17:27.728 | 8:M 20 Aug 2025 06:47:27.728 * <search> Enabled role change notification
2025-08-20 12:17:27.728 | 8:M 20 Aug 2025 06:47:27.728 * Module 'search' loaded from /opt/redis-stack/lib/redisearch.so
2025-08-20 12:17:27.730 | 8:M 20 Aug 2025 06:47:27.730 * <timeseries> RedisTimeSeries version 11206, git_sha=cdcbe34f8e87e15ea700b737634be6bac6b6700b
2025-08-20 12:17:27.730 | 8:M 20 Aug 2025 06:47:27.730 * <timeseries> Redis version found by RedisTimeSeries : 7.4.5 - oss
2025-08-20 12:17:27.730 | 8:M 20 Aug 2025 06:47:27.730 * <timeseries> loaded default CHUNK_SIZE_BYTES policy: 4096
2025-08-20 12:17:27.730 | 8:M 20 Aug 2025 06:47:27.730 * <timeseries> loaded server DUPLICATE_POLICY: block
2025-08-20 12:17:27.730 | 8:M 20 Aug 2025 06:47:27.730 * <timeseries> loaded default IGNORE_MAX_TIME_DIFF: 0
2025-08-20 12:17:27.730 | 8:M 20 Aug 2025 06:47:27.730 * <timeseries> loaded default IGNORE_MAX_VAL_DIFF: 0.000000
2025-08-20 12:17:27.730 | 8:M 20 Aug 2025 06:47:27.730 * <timeseries> Setting default series ENCODING to: compressed
2025-08-20 12:17:27.730 | 8:M 20 Aug 2025 06:47:27.730 * <timeseries> Detected redis oss
2025-08-20 12:17:27.730 | 8:M 20 Aug 2025 06:47:27.730 * Module 'timeseries' loaded from /opt/redis-stack/lib/redistimeseries.so
2025-08-20 12:17:27.731 | 8:M 20 Aug 2025 06:47:27.730 * <ReJSON> Created new data type 'ReJSON-RL'
2025-08-20 12:17:27.731 | 8:M 20 Aug 2025 06:47:27.731 # <ReJSON> Skip register defrag callbacks as defrag callbacks is not supported on the current Redis server.
2025-08-20 12:17:27.731 | 8:M 20 Aug 2025 06:47:27.731 * <ReJSON> version: 20809 git sha: unknown branch: unknown
2025-08-20 12:17:27.731 | 8:M 20 Aug 2025 06:47:27.731 * <ReJSON> Exported RedisJSON_V1 API
2025-08-20 12:17:27.731 | 8:M 20 Aug 2025 06:47:27.731 * <ReJSON> Exported RedisJSON_V2 API
2025-08-20 12:17:27.731 | 8:M 20 Aug 2025 06:47:27.731 * <ReJSON> Exported RedisJSON_V3 API
2025-08-20 12:17:27.731 | 8:M 20 Aug 2025 06:47:27.731 * <ReJSON> Exported RedisJSON_V4 API
2025-08-20 12:17:27.731 | 8:M 20 Aug 2025 06:47:27.731 * <ReJSON> Exported RedisJSON_V5 API
2025-08-20 12:17:27.731 | 8:M 20 Aug 2025 06:47:27.731 * <ReJSON> Enabled diskless replication
2025-08-20 12:17:27.731 | 8:M 20 Aug 2025 06:47:27.731 * <ReJSON> Initialized shared string cache, thread safe: false.
2025-08-20 12:17:27.731 | 8:M 20 Aug 2025 06:47:27.731 * Module 'ReJSON' loaded from /opt/redis-stack/lib/rejson.so
2025-08-20 12:17:27.731 | 8:M 20 Aug 2025 06:47:27.731 * <search> Acquired RedisJSON_V5 API
2025-08-20 12:17:27.731 | 8:M 20 Aug 2025 06:47:27.731 * <bf> RedisBloom version 2.8.7 (Git=unknown)
2025-08-20 12:17:27.731 | 8:M 20 Aug 2025 06:47:27.731 * Module 'bf' loaded from /opt/redis-stack/lib/redisbloom.so
2025-08-20 12:17:27.732 | 8:M 20 Aug 2025 06:47:27.732 * <redisgears_2> Created new data type 'GearsType'
2025-08-20 12:17:27.733 | 8:M 20 Aug 2025 06:47:27.732 * <redisgears_2> Detected redis oss
2025-08-20 12:17:27.733 | 8:M 20 Aug 2025 06:47:27.732 # <redisgears_2> could not initialize RedisAI_InitError
2025-08-20 12:17:27.733 | 
2025-08-20 12:17:27.733 | 8:M 20 Aug 2025 06:47:27.732 * <redisgears_2> Failed loading RedisAI API.
2025-08-20 12:17:27.733 | 8:M 20 Aug 2025 06:47:27.733 * <redisgears_2> RedisGears v2.0.20, sha='9b737886bf825fe29ddc2f8da81f73cbe0b4e858', build_type='release', built_for='Linux-ubuntu22.04.x86_64', redis_version:'7.4.5', enterprise:'false'.
2025-08-20 12:17:27.734 | 8:M 20 Aug 2025 06:47:27.734 * <redisgears_2> Registered backend: js.
2025-08-20 12:17:27.734 | 8:M 20 Aug 2025 06:47:27.734 * Module 'redisgears_2' loaded from /opt/redis-stack/lib/redisgears.so
2025-08-20 12:17:27.735 | 8:M 20 Aug 2025 06:47:27.735 * Server initialized
2025-08-20 12:17:27.736 | 8:M 20 Aug 2025 06:47:27.735 * <search> Loading event starts
2025-08-20 12:17:27.736 | 8:M 20 Aug 2025 06:47:27.735 * <redisgears_2> Got a loading start event, clear the entire functions data.
2025-08-20 12:17:27.736 | 8:M 20 Aug 2025 06:47:27.736 * Loading RDB produced by version 7.4.5
2025-08-20 12:17:27.736 | 8:M 20 Aug 2025 06:47:27.736 * RDB age 64249 seconds
2025-08-20 12:17:27.736 | 8:M 20 Aug 2025 06:47:27.736 * RDB memory usage when created 1.33 Mb
2025-08-20 12:17:27.736 | 8:M 20 Aug 2025 06:47:27.736 * Done loading RDB, keys loaded: 0, keys expired: 0.
2025-08-20 12:17:27.736 | 8:M 20 Aug 2025 06:47:27.736 * <search> Loading event ends


Api gateway docker container logs  ::
2025-08-20 12:18:33.086 | 2025-08-20 :: 06:48:33.086 || INFO :: SecurityConfig.java: | 46 | ::  RedisValidationFilter invoked for path=/auth/login
2025-08-20 12:18:33.089 | 2025-08-20 :: 06:48:33.089 || INFO :: AuthController.java: | 52 | ::  login req=AuthController.LoginReq(username=TUSHAR, password=12345)
2025-08-20 12:18:33.089 | 2025-08-20 :: 06:48:33.089 || INFO :: AuthController.java: | 58 | ::  login accept=AuthController.LoginReq(username=TUSHAR, password=12345)
2025-08-20 12:18:33.089 | 2025-08-20 :: 06:48:33.089 || INFO :: HmacJwtUtil.java: | 28 | ::  Generated exp in Seconds=2025-08-20T07:03:33.089718600Z
2025-08-20 12:18:33.090 | 2025-08-20 :: 06:48:33.089 || INFO :: HmacJwtUtil.java: | 30 | ::  Jti generated=3e38108e-22dd-4e5f-a91d-5bfe18fe28c0
2025-08-20 12:18:33.091 | 2025-08-20 :: 06:48:33.090 || INFO :: AuthController.java: | 66 | ::  Generated token=eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJUVVNIQVIiLCJqdGkiOiIzZTM4MTA4ZS0yMmRkLTRlNWYtYTkxZC01YmZlMThmZTI4YzAiLCJpYXQiOjE3NTU2NzI1MTMsImV4cCI6MTc1NTY3MzQxM30.HFOJfL2gAw6VCebJ0lVRrcYKKqzXQeaS_P6BqK6aCqA
2025-08-20 12:18:33.092 | 2025-08-20 :: 06:48:33.092 || INFO :: AuthController.java: | 76 | ::  Jti from claims claimsJws.getPayload().getId()3e38108e-22dd-4e5f-a91d-5bfe18fe28c0
2025-08-20 12:18:33.093 | 2025-08-20 :: 06:48:33.092 || INFO :: TokenSessionValidator.java: | 38 | ::  Inside registerUserSession method USR:TUSHAR
2025-08-20 12:18:33.098 | 2025-08-20 :: 06:48:33.097 || ERROR:: SecurityConfig.java: | 57 | ::  !! RedisValidationFilter error: Unable to connect to Redis

XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX

I have already running the redis in local like from ide I run api-gateway and radis in docker I can able to generate the jwt token and radis jti 

below is my api-gateway Application properties main and dev properties.

1: Application properties ::

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


2: Application-dev properties ::

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



Could you guide me whats wrong here why is canot to redis as I am clrely see in logs "2025-08-20 12:18:33.098 | 2025-08-20 :: 06:48:33.097 || ERROR:: SecurityConfig.java: | 57 | ::  !! RedisValidationFilter error: Unable to connect to Redis"  why I am getting this error and how does I resolved this error please guide me with proper solution 
