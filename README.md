After adding your provided pom.xml 

Getting below Error ::
2025-08-12T15:03:11.043+05:30  INFO 55648 --- [ApiGateWay] [           main] o.h.e.t.j.p.i.JtaPlatformInitiator       : HHH000489: No JTA platform available (set 'hibernate.transaction.jta.platform' to enable JTA platform integration)
2025-08-12T15:03:11.051+05:30  INFO 55648 --- [ApiGateWay] [           main] j.LocalContainerEntityManagerFactoryBean : Initialized JPA EntityManagerFactory for persistence unit 'default'
2025-08-12T15:03:12.051+05:30  WARN 55648 --- [ApiGateWay] [           main] GatewayClassPathWarningAutoConfiguration : 

**********************************************************

The artifact spring-cloud-gateway-server is deprecated. It will be removed in the next major release. Please add spring-cloud-gateway-server-webflux dependency.

**********************************************************


2025-08-12T15:03:12.575+05:30  WARN 55648 --- [ApiGateWay] [           main] onfigReactiveWebServerApplicationContext : Exception encountered during context initialization - cancelling refresh attempt: org.springframework.beans.factory.UnsatisfiedDependencyException: Error creating bean with name 'nettyWriteResponseFilter' defined in class path resource [org/springframework/cloud/gateway/config/GatewayAutoConfiguration$NettyConfiguration.class]: Unsatisfied dependency expressed through method 'nettyWriteResponseFilter' parameter 0: Error creating bean with name 'gatewayProperties': Could not bind properties to 'GatewayProperties' : prefix=spring.cloud.gateway.server.webflux, ignoreInvalidFields=false, ignoreUnknownFields=true
2025-08-12T15:03:12.590+05:30  INFO 55648 --- [ApiGateWay] [           main] j.LocalContainerEntityManagerFactoryBean : Closing JPA EntityManagerFactory for persistence unit 'default'
2025-08-12T15:03:12.592+05:30  INFO 55648 --- [ApiGateWay] [           main] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Shutdown initiated...
2025-08-12T15:03:12.637+05:30  INFO 55648 --- [ApiGateWay] [           main] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Shutdown completed.
2025-08-12T15:03:17.712+05:30 ERROR 55648 --- [ApiGateWay] [alhost:4318/...] i.o.exporter.internal.http.HttpExporter  : Failed to export metrics. The request could not be executed. Full error message: Failed to connect to localhost/[0:0:0:0:0:0:0:1]:4318

java.net.ConnectException: Failed to connect to localhost/[0:0:0:0:0:0:0:1]:4318
	at okhttp3.internal.connection.RealConnection.connectSocket(RealConnection.kt:297) ~[okhttp-4.12.0.jar:na]
	at okhttp3.internal.connection.RealConnection.connect(RealConnection.kt:207) ~[okhttp-4.12.0.jar:na]
	at okhttp3.internal.connection.ExchangeFinder.findConnection(ExchangeFinder.kt:226) ~[okhttp-4.12.0.jar:na]
	at okhttp3.internal.connection.ExchangeFinder.findHealthyConnection(ExchangeFinder.kt:106) ~[okhttp-4.12.0.jar:na]
	at okhttp3.internal.connection.ExchangeFinder.find(ExchangeFinder.kt:74) ~[okhttp-4.12.0.jar:na]
	at okhttp3.internal.connection.RealCall.initExchange$okhttp(RealCall.kt:255) ~[okhttp-4.12.0.jar:na]
	at okhttp3.internal.connection.ConnectInterceptor.intercept(ConnectInterceptor.kt:32) ~[okhttp-4.12.0.jar:na]
	at okhttp3.internal.http.RealInterceptorChain.proceed(RealInterceptorChain.kt:109) ~[okhttp-4.12.0.jar:na]
	at okhttp3.internal.cache.CacheInterceptor.intercept(CacheInterceptor.kt:95) ~[okhttp-4.12.0.jar:na]
	at okhttp3.internal.http.RealInterceptorChain.proceed(RealInterceptorChain.kt:109) ~[okhttp-4.12.0.jar:na]
	at okhttp3.internal.http.BridgeInterceptor.intercept(BridgeInterceptor.kt:83) ~[okhttp-4.12.0.jar:na]
	at okhttp3.internal.http.RealInterceptorChain.proceed(RealInterceptorChain.kt:109) ~[okhttp-4.12.0.jar:na]
	at okhttp3.internal.http.RetryAndFollowUpInterceptor.intercept(RetryAndFollowUpInterceptor.kt:76) ~[okhttp-4.12.0.jar:na]
	at okhttp3.internal.http.RealInterceptorChain.proceed(RealInterceptorChain.kt:109) ~[okhttp-4.12.0.jar:na]
	at io.opentelemetry.exporter.sender.okhttp.internal.RetryInterceptor.intercept(RetryInterceptor.java:96) ~[opentelemetry-exporter-sender-okhttp-1.49.0.jar:1.49.0]
	at okhttp3.internal.http.RealInterceptorChain.proceed(RealInterceptorChain.kt:109) ~[okhttp-4.12.0.jar:na]
	at okhttp3.internal.connection.RealCall.getResponseWithInterceptorChain$okhttp(RealCall.kt:201) ~[okhttp-4.12.0.jar:na]
	at okhttp3.internal.connection.RealCall$AsyncCall.run(RealCall.kt:517) ~[okhttp-4.12.0.jar:na]
	at java.base/java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1144) ~[na:na]
	at java.base/java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:642) ~[na:na]
	at java.base/java.lang.Thread.run(Thread.java:1570) ~[na:na]
	Suppressed: java.net.ConnectException: Failed to connect to localhost/127.0.0.1:4318
		... 21 common frames omitted
	Caused by: java.net.ConnectException: Connection refused: getsockopt
		at java.base/sun.nio.ch.Net.pollConnect(Native Method)
		at java.base/sun.nio.ch.Net.pollConnectNow(Net.java:682)
		at java.base/sun.nio.ch.NioSocketImpl.timedFinishConnect(NioSocketImpl.java:542)
		at java.base/sun.nio.ch.NioSocketImpl.connect(NioSocketImpl.java:592)
		at java.base/java.net.SocksSocketImpl.connect(SocksSocketImpl.java:327)
		at java.base/java.net.Socket.connect(Socket.java:752)
		at okhttp3.internal.platform.Platform.connectSocket(Platform.kt:128)
		at okhttp3.internal.connection.RealConnection.connectSocket(RealConnection.kt:295)
		... 20 common frames omitted
Caused by: java.net.ConnectException: Connection refused: getsockopt
	at java.base/sun.nio.ch.Net.pollConnect(Native Method) ~[na:na]
	at java.base/sun.nio.ch.Net.pollConnectNow(Net.java:682) ~[na:na]
	at java.base/sun.nio.ch.NioSocketImpl.timedFinishConnect(NioSocketImpl.java:542) ~[na:na]
	at java.base/sun.nio.ch.NioSocketImpl.connect(NioSocketImpl.java:592) ~[na:na]
	at java.base/java.net.SocksSocketImpl.connect(SocksSocketImpl.java:327) ~[na:na]
	at java.base/java.net.Socket.connect(Socket.java:752) ~[na:na]
	at okhttp3.internal.platform.Platform.connectSocket(Platform.kt:128) ~[okhttp-4.12.0.jar:na]
	at okhttp3.internal.connection.RealConnection.connectSocket(RealConnection.kt:295) ~[okhttp-4.12.0.jar:na]
	... 20 common frames omitted

2025-08-12T15:03:17.732+05:30  INFO 55648 --- [ApiGateWay] [           main] .s.b.a.l.ConditionEvaluationReportLogger : 

Error starting ApplicationContext. To display the condition evaluation report re-run your application with 'debug' enabled.
2025-08-12T15:03:17.751+05:30 ERROR 55648 --- [ApiGateWay] [           main] o.s.b.d.LoggingFailureAnalysisReporter   : 

***************************
APPLICATION FAILED TO START
***************************

Description:

Failed to bind properties under 'spring.cloud.gateway.server.webflux.routes' to java.util.List<org.springframework.cloud.gateway.route.RouteDefinition>:

    Property: spring.cloud.gateway.server.webflux.routes
    Value: "product-service"
    Origin: class path resource [application.properties] - 25:44
    Reason: failed to convert java.lang.String to @jakarta.validation.constraints.NotNull @jakarta.validation.Valid org.springframework.cloud.gateway.route.RouteDefinition (caused by jakarta.validation.ValidationException: Unable to parse RouteDefinition text 'product-service', must be of the form name=value)

Action:

Update your application's configuration


Process finished with exit code 1


Kindly provide the solution for above issue.
