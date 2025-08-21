
E:\Kubernates Yaml Files\Dev-Deployment\New Test>kubectl apply -f namespace-dev.yaml
namespace/dev created

E:\Kubernates Yaml Files\Dev-Deployment\New Test>kubectl apply -f redis.yaml
deployment.apps/redis created
service/redis created

E:\Kubernates Yaml Files\Dev-Deployment\New Test>kubectl apply -f product-service.yaml
deployment.apps/product-service created
service/product-service created

E:\Kubernates Yaml Files\Dev-Deployment\New Test>kubectl apply -f fincore-service.yaml
deployment.apps/fincore-service created
service/fincore-service created

E:\Kubernates Yaml Files\Dev-Deployment\New Test>kubectl apply -f api-gateway.yaml
deployment.apps/api-gateway created
service/api-gateway created

E:\Kubernates Yaml Files\Dev-Deployment\New Test>kubectl get pods -n dev
NAME                               READY   STATUS    RESTARTS   AGE
api-gateway-d9c699469-lhn5m        1/1     Running   0          10s
fincore-service-54cdd58949-24djt   1/1     Running   0          19s
product-service-5c96b57f6b-r2wt7   1/1     Running   0          30s
redis-7c5ff8ffcf-7vphh             1/1     Running   0          38s

E:\Kubernates Yaml Files\Dev-Deployment\New Test>kubectl logs redis-7c5ff8ffcf-7vphh -n dev
8:C 21 Aug 2025 09:32:35.741 * oO0OoO0OoO0Oo Redis is starting oO0OoO0OoO0Oo
8:C 21 Aug 2025 09:32:35.741 * Redis version=7.4.5, bits=64, commit=00000000, modified=0, pid=8, just started
8:C 21 Aug 2025 09:32:35.741 * Configuration loaded
8:M 21 Aug 2025 09:32:35.741 * monotonic clock: POSIX clock_gettime
8:M 21 Aug 2025 09:32:35.742 * Running mode=standalone, port=6379.
8:M 21 Aug 2025 09:32:35.745 * Module 'RedisCompat' loaded from /opt/redis-stack/lib/rediscompat.so
8:M 21 Aug 2025 09:32:35.782 * <search> Redis version found by RedisSearch : 7.4.5 - oss
8:M 21 Aug 2025 09:32:35.782 * <search> RediSearch version 2.10.20 (Git=5c09b69)
8:M 21 Aug 2025 09:32:35.783 * <search> Low level api version 1 initialized successfully
8:M 21 Aug 2025 09:32:35.785 * <search> gc: ON, prefix min length: 2, min word length to stem: 4, prefix max expansions: 200, query timeout (ms): 500, timeout policy: return, cursor read size: 1000, cursor max idle (ms): 300000, max doctable size: 1000000, max number of search results:  10000,
8:M 21 Aug 2025 09:32:35.785 * <search> Initialized thread pools!
8:M 21 Aug 2025 09:32:35.787 * <search> Subscribe to config changes
8:M 21 Aug 2025 09:32:35.787 * <search> Enabled role change notification
8:M 21 Aug 2025 09:32:35.787 * Module 'search' loaded from /opt/redis-stack/lib/redisearch.so
8:M 21 Aug 2025 09:32:35.801 * <timeseries> RedisTimeSeries version 11206, git_sha=cdcbe34f8e87e15ea700b737634be6bac6b6700b
8:M 21 Aug 2025 09:32:35.802 * <timeseries> Redis version found by RedisTimeSeries : 7.4.5 - oss
8:M 21 Aug 2025 09:32:35.802 * <timeseries> loaded default CHUNK_SIZE_BYTES policy: 4096
8:M 21 Aug 2025 09:32:35.802 * <timeseries> loaded server DUPLICATE_POLICY: block
8:M 21 Aug 2025 09:32:35.802 * <timeseries> loaded default IGNORE_MAX_TIME_DIFF: 0
8:M 21 Aug 2025 09:32:35.802 * <timeseries> loaded default IGNORE_MAX_VAL_DIFF: 0.000000
8:M 21 Aug 2025 09:32:35.802 * <timeseries> Setting default series ENCODING to: compressed
8:M 21 Aug 2025 09:32:35.803 * <timeseries> Detected redis oss
8:M 21 Aug 2025 09:32:35.803 * Module 'timeseries' loaded from /opt/redis-stack/lib/redistimeseries.so
8:M 21 Aug 2025 09:32:35.809 * <ReJSON> Created new data type 'ReJSON-RL'
8:M 21 Aug 2025 09:32:35.809 # <ReJSON> Skip register defrag callbacks as defrag callbacks is not supported on the current Redis server.
8:M 21 Aug 2025 09:32:35.810 * <ReJSON> version: 20809 git sha: unknown branch: unknown
8:M 21 Aug 2025 09:32:35.810 * <ReJSON> Exported RedisJSON_V1 API
8:M 21 Aug 2025 09:32:35.810 * <ReJSON> Exported RedisJSON_V2 API
8:M 21 Aug 2025 09:32:35.810 * <ReJSON> Exported RedisJSON_V3 API
8:M 21 Aug 2025 09:32:35.810 * <ReJSON> Exported RedisJSON_V4 API
8:M 21 Aug 2025 09:32:35.810 * <ReJSON> Exported RedisJSON_V5 API
8:M 21 Aug 2025 09:32:35.810 * <ReJSON> Enabled diskless replication
8:M 21 Aug 2025 09:32:35.810 * <ReJSON> Initialized shared string cache, thread safe: false.
8:M 21 Aug 2025 09:32:35.811 * Module 'ReJSON' loaded from /opt/redis-stack/lib/rejson.so
8:M 21 Aug 2025 09:32:35.811 * <search> Acquired RedisJSON_V5 API
8:M 21 Aug 2025 09:32:35.816 * <bf> RedisBloom version 2.8.7 (Git=unknown)
8:M 21 Aug 2025 09:32:35.817 * Module 'bf' loaded from /opt/redis-stack/lib/redisbloom.so
8:M 21 Aug 2025 09:32:35.828 * <redisgears_2> Created new data type 'GearsType'
8:M 21 Aug 2025 09:32:35.831 * <redisgears_2> Detected redis oss
8:M 21 Aug 2025 09:32:35.834 # <redisgears_2> could not initialize RedisAI_InitError

8:M 21 Aug 2025 09:32:35.834 * <redisgears_2> Failed loading RedisAI API.
8:M 21 Aug 2025 09:32:35.834 * <redisgears_2> RedisGears v2.0.20, sha='9b737886bf825fe29ddc2f8da81f73cbe0b4e858', build_type='release', built_for='Linux-ubuntu22.04.x86_64', redis_version:'7.4.5', enterprise:'false'.
8:M 21 Aug 2025 09:32:35.866 * <redisgears_2> Registered backend: js.
8:M 21 Aug 2025 09:32:35.875 * Module 'redisgears_2' loaded from /opt/redis-stack/lib/redisgears.so
8:M 21 Aug 2025 09:32:35.875 * Server initialized
8:M 21 Aug 2025 09:32:35.879 * Ready to accept connections tcp

E:\Kubernates Yaml Files\Dev-Deployment\New Test>kubectl -n dev port-forward svc/api-gateway 30080:8080
Forwarding from 127.0.0.1:30080 -> 8080
Forwarding from [::1]:30080 -> 8080
Handling connection for 30080
Handling connection for 30080
E0821 15:09:19.479791    9856 portforward.go:424] "Unhandled Error" err=<
        an error occurred forwarding 30080 -> 8080: error forwarding port 8080 to pod 0b5b0e33067391dca9effb97b7548c1c1d6473fd3f1df0e8d67366b3a7637877, uid : exit status 1: 2025/08/21 09:39:19 socat[19497] E connect(5, AF=2 127.0.0.1:8080, 16): Connection refused
 >
E0821 15:09:19.479254    9856 portforward.go:424] "Unhandled Error" err=<
        an error occurred forwarding 30080 -> 8080: error forwarding port 8080 to pod 0b5b0e33067391dca9effb97b7548c1c1d6473fd3f1df0e8d67366b3a7637877, uid : exit status 1: 2025/08/21 09:39:19 socat[19498] E connect(5, AF=2 127.0.0.1:8080, 16): Connection refused
 >
error: lost connection to pod

Still not able to access any endpoint from localhost machine(From Postman )
http://localhost:30080/auth/login  Response  :: Could not get response
http://localhost:8080/auth/login


below is API-gateway pod log 
2025-08-21 :: 09:42:39.777 || INFO :: ReflectorRunnable.java: | 87 | ::  class io.kubernetes.client.openapi.models.V1Endpoints#Start listing and watching...
2025-08-21 :: 09:42:39.779 || ERROR:: ReflectorRunnable.java: | 285 | ::  class io.kubernetes.client.openapi.models.V1Endpoints#Reflector loop failed unexpectedly
io.kubernetes.client.openapi.ApiException: class V1Status {
    apiVersion: v1
    code: 403
    details: class V1StatusDetails {
        causes: null
        group: null
        kind: endpoints
        name: null
        retryAfterSeconds: null
        uid: null
    }
    kind: Status
    message: endpoints is forbidden: User "system:serviceaccount:dev:default" cannot list resource "endpoints" in API group "" in the namespace "dev"
    metadata: class V1ListMeta {
        _continue: null
        remainingItemCount: null
        resourceVersion: null
        selfLink: null
    }
    reason: Forbidden
    status: Failure
}
        at io.kubernetes.client.util.generic.KubernetesApiResponse.lambda$throwsApiException$0(KubernetesApiResponse.java:64)
        at io.kubernetes.client.util.generic.KubernetesApiResponse.onFailure(KubernetesApiResponse.java:78)
        at io.kubernetes.client.util.generic.KubernetesApiResponse.throwsApiException(KubernetesApiResponse.java:62)
        at io.kubernetes.client.informer.SharedInformerFactory$2.list(SharedInformerFactory.java:318)
        at io.kubernetes.client.informer.cache.ReflectorRunnable.run(ReflectorRunnable.java:91)
        at java.base/java.util.concurrent.Executors$RunnableAdapter.call(Executors.java:545)
        at java.base/java.util.concurrent.FutureTask.runAndReset(FutureTask.java:369)
        at java.base/java.util.concurrent.ScheduledThreadPoolExecutor$ScheduledFutureTask.run(ScheduledThreadPoolExecutor.java:310)
        at java.base/java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1095)
        at java.base/java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:619)
        at java.base/java.lang.Thread.run(Thread.java:1447)
2025-08-21 :: 09:42:39.835 || INFO :: ReflectorRunnable.java: | 87 | ::  class io.kubernetes.client.openapi.models.V1Service#Start listing and watching...
2025-08-21 :: 09:42:39.842 || ERROR:: ReflectorRunnable.java: | 285 | ::  class io.kubernetes.client.openapi.models.V1Service#Reflector loop failed unexpectedly
io.kubernetes.client.openapi.ApiException: class V1Status {
    apiVersion: v1
    code: 403
    details: class V1StatusDetails {
        causes: null
        group: null
        kind: services
        name: null
        retryAfterSeconds: null
        uid: null
    }
    kind: Status
    message: services is forbidden: User "system:serviceaccount:dev:default" cannot list resource "services" in API group "" in the namespace "dev"
    metadata: class V1ListMeta {
        _continue: null
        remainingItemCount: null
        resourceVersion: null
        selfLink: null
    }
    reason: Forbidden
    status: Failure
}
        at io.kubernetes.client.util.generic.KubernetesApiResponse.lambda$throwsApiException$0(KubernetesApiResponse.java:64)
        at io.kubernetes.client.util.generic.KubernetesApiResponse.onFailure(KubernetesApiResponse.java:78)
        at io.kubernetes.client.util.generic.KubernetesApiResponse.throwsApiException(KubernetesApiResponse.java:62)
        at io.kubernetes.client.informer.SharedInformerFactory$2.list(SharedInformerFactory.java:318)
        at io.kubernetes.client.informer.cache.ReflectorRunnable.run(ReflectorRunnable.java:91)
        at java.base/java.util.concurrent.Executors$RunnableAdapter.call(Executors.java:545)
        at java.base/java.util.concurrent.FutureTask.runAndReset(FutureTask.java:369)
        at java.base/java.util.concurrent.ScheduledThreadPoolExecutor$ScheduledFutureTask.run(ScheduledThreadPoolExecutor.java:310)
        at java.base/java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1095)
        at java.base/java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:619)
        at java.base/java.lang.Thread.run(Thread.java:1447)

