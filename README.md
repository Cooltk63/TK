Console output :: 

2025-08-14 :: 14:54:42.712 || ERROR:: CompositeLog.java: | 102 | ::  [54af680c-1]  500 Server Error for HTTP POST "/auth/login"
io.jsonwebtoken.security.WeakKeyException: The specified key byte array is 224 bits which is not secure enough for any JWT HMAC-SHA algorithm.  The JWT JWA Specification (RFC 7518, Section 3.2) states that keys used with HMAC-SHA algorithms MUST have a size >= 256 bits (the key size must be greater than or equal to the hash output size).  Consider using the io.jsonwebtoken.security.Keys#secretKeyFor(SignatureAlgorithm) method to create a key guaranteed to be secure enough for your preferred HMAC-SHA algorithm.  See https://tools.ietf.org/html/rfc7518#section-3.2 for more information.
	at io.jsonwebtoken.security.Keys.hmacShaKeyFor(Keys.java:96)
	Suppressed: reactor.core.publisher.FluxOnAssembly$OnAssemblyException: 
Error has been observed at the following site(s):
	*__checkpoint ? org.springframework.web.cors.reactive.CorsWebFilter [DefaultWebFilterChain]
	*__checkpoint ? org.springframework.cloud.gateway.filter.WeightCalculatorWebFilter [DefaultWebFilterChain]
	*__checkpoint ? HTTP POST "/auth/login" [ExceptionHandlingWebHandler]
Original Stack Trace:
		at io.jsonwebtoken.security.Keys.hmacShaKeyFor(Keys.java:96)
		at com.fincore.gateway.JwtUtil.JwtUtil.getHmacKey(JwtUtil.java:42)
		at com.fincore.gateway.JwtUtil.JwtUtil.generateToken(JwtUtil.java:59)
		at com.fincore.gateway.Controller.AuthController.login(AuthController.java:38)
		at java.base/jdk.internal.reflect.DirectMethodHandleAccessor.invoke(DirectMethodHandleAccessor.java:103)
		at java.base/java.lang.reflect.Method.invoke(Method.java:580)
		at org.springframework.web.reactive.result.method.InvocableHandlerMethod.lambda$invoke$0(InvocableHandlerMethod.java:208)
		at reactor.core.publisher.MonoFlatMap$FlatMapMain.onNext(MonoFlatMap.java:132)
		at reactor.core.publisher.MonoZip$ZipCoordinator.signal(MonoZip.java:297)
		at reactor.core.publisher.MonoZip$ZipInner.onNext(MonoZip.java:478)
		at reactor.core.publisher.MonoPeekTerminal$MonoTerminalPeekSubscriber.onNext(MonoPeekTerminal.java:180)
		at reactor.core.publisher.FluxDefaultIfEmpty$DefaultIfEmptySubscriber.onNext(FluxDefaultIfEmpty.java:122)
		at reactor.core.publisher.FluxSwitchIfEmpty$SwitchIfEmptySubscriber.onNext(FluxSwitchIfEmpty.java:74)
		at reactor.core.publisher.FluxOnErrorResume$ResumeSubscriber.onNext(FluxOnErrorResume.java:79)
		at reactor.core.publisher.MonoFlatMap$FlatMapMain.onNext(MonoFlatMap.java:158)
		at reactor.core.publisher.FluxContextWrite$ContextWriteSubscriber.onNext(FluxContextWrite.java:107)
		at reactor.core.publisher.FluxMapFuseable$MapFuseableConditionalSubscriber.onNext(FluxMapFuseable.java:299)
		at reactor.core.publisher.FluxFilterFuseable$FilterFuseableConditionalSubscriber.onNext(FluxFilterFuseable.java:337)
		at reactor.core.publisher.Operators$BaseFluxToMonoOperator.completePossiblyEmpty(Operators.java:2096)
		at reactor.core.publisher.MonoCollect$CollectSubscriber.onComplete(MonoCollect.java:145)
		at reactor.core.publisher.FluxMap$MapSubscriber.onComplete(FluxMap.java:144)
		at reactor.core.publisher.FluxPeek$PeekSubscriber.onComplete(FluxPeek.java:260)
		at reactor.core.publisher.FluxMap$MapSubscriber.onComplete(FluxMap.java:144)
		at reactor.netty.channel.FluxReceive.onInboundComplete(FluxReceive.java:413)
		at reactor.netty.channel.ChannelOperations.onInboundComplete(ChannelOperations.java:455)
		at reactor.netty.http.server.HttpServerOperations.handleLastHttpContent(HttpServerOperations.java:903)
		at reactor.netty.http.server.HttpServerOperations.onInboundNext(HttpServerOperations.java:812)
		at reactor.netty.channel.ChannelOperationsHandler.channelRead(ChannelOperationsHandler.java:115)
		at io.netty.channel.AbstractChannelHandlerContext.invokeChannelRead(AbstractChannelHandlerContext.java:444)
		at io.netty.channel.AbstractChannelHandlerContext.invokeChannelRead(AbstractChannelHandlerContext.java:420)
		at io.netty.channel.AbstractChannelHandlerContext.fireChannelRead(AbstractChannelHandlerContext.java:412)
		at reactor.netty.http.server.HttpTrafficHandler.channelRead(HttpTrafficHandler.java:321)
		at io.netty.channel.AbstractChannelHandlerContext.invokeChannelRead(AbstractChannelHandlerContext.java:442)
		at io.netty.channel.AbstractChannelHandlerContext.invokeChannelRead(AbstractChannelHandlerContext.java:420)
		at io.netty.channel.AbstractChannelHandlerContext.fireChannelRead(AbstractChannelHandlerContext.java:412)
		at io.netty.channel.CombinedChannelDuplexHandler$DelegatingChannelHandlerContext.fireChannelRead(CombinedChannelDuplexHandler.java:436)
		at io.netty.handler.codec.ByteToMessageDecoder.fireChannelRead(ByteToMessageDecoder.java:346)
		at io.netty.handler.codec.ByteToMessageDecoder.channelRead(ByteToMessageDecoder.java:318)
		at io.netty.channel.CombinedChannelDuplexHandler.channelRead(CombinedChannelDuplexHandler.java:251)
		at io.netty.channel.AbstractChannelHandlerContext.invokeChannelRead(AbstractChannelHandlerContext.java:442)
		at io.netty.channel.AbstractChannelHandlerContext.invokeChannelRead(AbstractChannelHandlerContext.java:420)
		at io.netty.channel.AbstractChannelHandlerContext.fireChannelRead(AbstractChannelHandlerContext.java:412)
		at io.netty.channel.DefaultChannelPipeline$HeadContext.channelRead(DefaultChannelPipeline.java:1357)
		at io.netty.channel.AbstractChannelHandlerContext.invokeChannelRead(AbstractChannelHandlerContext.java:440)
		at io.netty.channel.AbstractChannelHandlerContext.invokeChannelRead(AbstractChannelHandlerContext.java:420)
		at io.netty.channel.DefaultChannelPipeline.fireChannelRead(DefaultChannelPipeline.java:868)
		at io.netty.channel.nio.AbstractNioByteChannel$NioByteUnsafe.read(AbstractNioByteChannel.java:166)
		at io.netty.channel.nio.NioEventLoop.processSelectedKey(NioEventLoop.java:796)
		at io.netty.channel.nio.NioEventLoop.processSelectedKeysOptimized(NioEventLoop.java:732)
		at io.netty.channel.nio.NioEventLoop.processSelectedKeys(NioEventLoop.java:658)
		at io.netty.channel.nio.NioEventLoop.run(NioEventLoop.java:562)
		at io.netty.util.concurrent.SingleThreadEventExecutor$4.run(SingleThreadEventExecutor.java:998)
		at io.netty.util.internal.ThreadExecutorMap$2.run(ThreadExecutorMap.java:74)
		at io.netty.util.concurrent.FastThreadLocalRunnable.run(FastThreadLocalRunnable.java:30)
		at java.base/java.lang.Thread.run(Thread.java:1570)

application.properties ::
spring.application.name=api-gateway
server.port=8080

spring.profiles.active=dev

# Gateway - dynamic services (comma separated). Add your services here.
gateway.services=orders,products,users,inventory,payments,auth

# Default port of backend microservices (K8s service usually exposes same internal port)
gateway.service.default-port=8080

# Paths to bypass (no JWT)
gateway.bypass.urls=/auth/**,/public/**,/actuator/**

# JWT configuration (HS256 mode - base64 secret)
security.jwt.mode=hmac
# Small example secret (base64 of 32 bytes). Replace with your secure secret in prod.
security.jwt.secret=ZmFrZV9iYXNlNjRfc2VjcmV0XzMyX2J5dGVzIQ==

# Token TTL seconds (used by JwtUtil)
security.jwt.expire-seconds=900

# Redis toggle + connection (change per environment)
redis.enabled=true
spring.data.redis.host=localhost
spring.data.redis.port=6379
spring.data.redis.password=     \
# set in prod via env/secret

# CORS
gateway.cors.allowed-origins=http://localhost:3000
gateway.cors.allowed-methods=GET,POST,PUT,DELETE,OPTIONS
gateway.cors.allowed-headers=*

# ========== Logging ==========
# Console log pattern (Color-coded output)
logging.pattern.console=%d{yyyy-MM-dd :: HH:mm:ss.SSS ||} %highlight(%-5level:: %file: | %line |){ERROR=bold red, WARN=yellow, INFO=white, DEBUG=green, TRACE=green} ::  %msg%n

# ========== Actuators Config ==========
management.endpoint.health.show-details=always
management.endpoints.web.exposure.include=health,info


application-dev.properties ::
# For local dev you can disable redis if you don't run it:
redis.enabled=false

# If using hmac in dev - example secret base64 (DO NOT use in prod):
security.jwt.mode=hmac
security.jwt.secret=ZmFrZV9iYXNlNjRfc2VjcmV0XzMyX2J5dGVzIQ==
#base64 for fake_base64_secret_123456




how to resolve this issue please let me guide with 100 security for every environment
