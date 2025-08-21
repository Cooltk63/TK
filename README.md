2025-08-21 :: 10:43:11.594 || INFO :: ReflectorRunnable.java: | 87 | :: class io.kubernetes.client.openapi.models.V1Service#Start listing and watching...

2025-08-21 :: 10:43:11.601 || ERROR:: ReflectorRunnable.java: | 285 | :: class io.kubernetes.client.openapi.models.V1Service#Reflector loop failed unexpectedly

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
