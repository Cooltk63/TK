2025-08-14 :: 13:11:37.670 || ERROR:: CompositeLog.java: | 102 | ::  [9daa05f1-12]  500 Server Error for HTTP POST "/auth/login"
io.jsonwebtoken.io.DecodingException: Illegal base64 character: ' '
	at io.jsonwebtoken.io.Base64.ctoi(Base64.java:221)
	Suppressed: reactor.core.publisher.FluxOnAssembly$OnAssemblyException: 
Error has been observed at the following site(s):
	*__checkpoint ? org.springframework.web.cors.reactive.CorsWebFilter [DefaultWebFilterChain]
	*__checkpoint ? org.springframework.cloud.gateway.filter.WeightCalculatorWebFilter [DefaultWebFilterChain]
	*__checkpoint ? HTTP POST "/auth/login" [ExceptionHandlingWebHandler]
Original Stack Trace:
		at io.jsonwebtoken.io.Base64.ctoi(Base64.java:221)
		at io.jsonwebtoken.io.Base64.decodeFast(Base64.java:270)
		at io.jsonwebtoken.io.Base64Decoder.decode(Base64Decoder.java:36)
		at io.jsonwebtoken.io.Base64Decoder.decode(Base64Decoder.java:23)
		at io.jsonwebtoken.io.ExceptionPropagatingDecoder.decode(ExceptionPropagatingDecoder.java:36)
		at com.fincore.gateway.JwtUtil.JwtUtil.getHmacKey(JwtUtil.java:41)
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


  getting this error on  request ::

 URL :: http://localhost:8080/auth/login

 {
"username":"TUSHAR",
"password":"12345"
}


Response ::
{
    "timestamp": "2025-08-14T07:41:37.670+00:00",
    "path": "/auth/login",
    "status": 500,
    "error": "Internal Server Error",
    "requestId": "9daa05f1-12"
}
