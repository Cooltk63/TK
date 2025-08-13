
reactor.core.Exceptions$ErrorCallbackNotImplemented: java.lang.UnsupportedOperationException
Caused by: java.lang.UnsupportedOperationException: null
	at java.base/java.util.Collections$UnmodifiableCollection.add(Collections.java:1092) ~[na:na]
	at com.fincore.gateway.config.GatewayRoutesConfig.lambda$customRoutes$6(GatewayRoutesConfig.java:54) ~[classes/:na]
	at reactor.core.publisher.LambdaSubscriber.onNext(LambdaSubscriber.java:160) ~[reactor-core-3.7.8.jar:3.7.8]
	at reactor.core.publisher.FluxMapFuseable$MapFuseableSubscriber.onNext(FluxMapFuseable.java:129) ~[reactor-core-3.7.8.jar:3.7.8]
	at reactor.core.publisher.FluxIterable$IterableSubscription.fastPath(FluxIterable.java:402) ~[reactor-core-3.7.8.jar:3.7.8]
	at reactor.core.publisher.FluxIterable$IterableSubscription.request(FluxIterable.java:291) ~[reactor-core-3.7.8.jar:3.7.8]
	at reactor.core.publisher.FluxMapFuseable$MapFuseableSubscriber.request(FluxMapFuseable.java:171) ~[reactor-core-3.7.8.jar:3.7.8]
	at reactor.core.publisher.LambdaSubscriber.onSubscribe(LambdaSubscriber.java:119) ~[reactor-core-3.7.8.jar:3.7.8]
	at reactor.core.publisher.FluxMapFuseable$MapFuseableSubscriber.onSubscribe(FluxMapFuseable.java:96) ~[reactor-core-3.7.8.jar:3.7.8]
	at reactor.core.publisher.FluxIterable.subscribe(FluxIterable.java:201) ~[reactor-core-3.7.8.jar:3.7.8]
	at reactor.core.publisher.FluxIterable.subscribe(FluxIterable.java:83) ~[reactor-core-3.7.8.jar:3.7.8]
	at reactor.core.publisher.Flux.subscribe(Flux.java:8891) ~[reactor-core-3.7.8.jar:3.7.8]
	at reactor.core.publisher.Flux.subscribeWith(Flux.java:9012) ~[reactor-core-3.7.8.jar:3.7.8]
	at reactor.core.publisher.Flux.subscribe(Flux.java:8856) ~[reactor-core-3.7.8.jar:3.7.8]
	at reactor.core.publisher.Flux.subscribe(Flux.java:8780) ~[reactor-core-3.7.8.jar:3.7.8]
	at reactor.core.publisher.Flux.subscribe(Flux.java:8723) ~[reactor-core-3.7.8.jar:3.7.8]
	at com.fincore.gateway.config.GatewayRoutesConfig.customRoutes(GatewayRoutesConfig.java:53) ~[classes/:na]
	at com.fincore.gateway.config.GatewayRoutesConfig$$SpringCGLIB$$0.CGLIB$customRoutes$0(<generated>) ~[classes/:na]
	at com.fincore.gateway.config.GatewayRoutesConfig$$SpringCGLIB$$FastClass$$1.invoke(<generated>) ~[classes/:na]
	at org.springframework.cglib.proxy.MethodProxy.invokeSuper(MethodProxy.java:258) ~[spring-core-6.2.9.jar:6.2.9]
	at org.springframework.context.annotation.ConfigurationClassEnhancer$BeanMethodInterceptor.intercept(ConfigurationClassEnhancer.java:400) ~[spring-context-6.2.9.jar:6.2.9]
	at com.fincore.gateway.config.GatewayRoutesConfig$$SpringCGLIB$$0.customRoutes(<generated>) ~[classes/:na]
	at java.base/jdk.internal.reflect.DirectMethodHandleAccessor.invoke(DirectMethodHandleAccessor.java:103) ~[na:na]
	at java.base/java.lang.reflect.Method.invoke(Method.java:580) ~[na:na]
	at org.springframework.beans.factory.support.SimpleInstantiationStrategy.lambda$instantiate$0(SimpleInstantiationStrategy.java:172) ~[spring-beans-6.2.9.jar:6.2.9]
	at org.springframework.beans.factory.support.SimpleInstantiationStrategy.instantiateWithFactoryMethod(SimpleInstantiationStrategy.java:89) ~[spring-beans-6.2.9.jar:6.2.9]
	at org.springframework.beans.factory.support.SimpleInstantiationStrategy.instantiate(SimpleInstantiationStrategy.java:169) ~[spring-beans-6.2.9.jar:6.2.9]
	at org.springframework.beans.factory.support.ConstructorResolver.instantiate(ConstructorResolver.java:653) ~[spring-beans-6.2.9.jar:6.2.9]
	at org.springframework.beans.factory.support.ConstructorResolver.instantiateUsingFactoryMethod(ConstructorResolver.java:645) ~[spring-beans-6.2.9.jar:6.2.9]
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.instantiateUsingFactoryMethod(AbstractAutowireCapableBeanFactory.java:1375) ~[spring-beans-6.2.9.jar:6.2.9]
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBeanInstance(AbstractAutowireCapableBeanFactory.java:1205) ~[spring-beans-6.2.9.jar:6.2.9]
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.doCreateBean(AbstractAutowireCapableBeanFactory.java:569) ~[spring-beans-6.2.9.jar:6.2.9]
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBean(AbstractAutowireCapableBeanFactory.java:529) ~[spring-beans-6.2.9.jar:6.2.9]
	at org.springframework.beans.factory.support.AbstractBeanFactory.lambda$doGetBean$0(AbstractBeanFactory.java:339) ~[spring-beans-6.2.9.jar:6.2.9]
	at org.springframework.beans.factory.support.DefaultSingletonBeanRegistry.getSingleton(DefaultSingletonBeanRegistry.java:373) ~[spring-beans-6.2.9.jar:6.2.9]
	at org.springframework.beans.factory.support.AbstractBeanFactory.doGetBean(AbstractBeanFactory.java:337) ~[spring-beans-6.2.9.jar:6.2.9]
	at org.springframework.beans.factory.support.AbstractBeanFactory.getBean(AbstractBeanFactory.java:202) ~[spring-beans-6.2.9.jar:6.2.9]
	at org.springframework.beans.factory.support.DefaultListableBeanFactory.instantiateSingleton(DefaultListableBeanFactory.java:1222) ~[spring-beans-6.2.9.jar:6.2.9]
	at org.springframework.beans.factory.support.DefaultListableBeanFactory.preInstantiateSingleton(DefaultListableBeanFactory.java:1188) ~[spring-beans-6.2.9.jar:6.2.9]
	at org.springframework.beans.factory.support.DefaultListableBeanFactory.preInstantiateSingletons(DefaultListableBeanFactory.java:1123) ~[spring-beans-6.2.9.jar:6.2.9]
	at org.springframework.context.support.AbstractApplicationContext.finishBeanFactoryInitialization(AbstractApplicationContext.java:987) ~[spring-context-6.2.9.jar:6.2.9]
	at org.springframework.context.support.AbstractApplicationContext.refresh(AbstractApplicationContext.java:627) ~[spring-context-6.2.9.jar:6.2.9]
	at org.springframework.boot.web.reactive.context.ReactiveWebServerApplicationContext.refresh(ReactiveWebServerApplicationContext.java:66) ~[spring-boot-3.5.4.jar:3.5.4]
	at org.springframework.boot.SpringApplication.refresh(SpringApplication.java:752) ~[spring-boot-3.5.4.jar:3.5.4]
	at org.springframework.boot.SpringApplication.refreshContext(SpringApplication.java:439) ~[spring-boot-3.5.4.jar:3.5.4]
	at org.springframework.boot.SpringApplication.run(SpringApplication.java:318) ~[spring-boot-3.5.4.jar:3.5.4]
	at org.springframework.boot.SpringApplication.run(SpringApplication.java:1361) ~[spring-boot-3.5.4.jar:3.5.4]
	at org.springframework.boot.SpringApplication.run(SpringApplication.java:1350) ~[spring-boot-3.5.4.jar:3.5.4]
	at com.fincore.gateway.ApiGatewayApplication.main(ApiGatewayApplication.java:12) ~[classes/:na]

 After running project everytime I am getting this error in cconsole I dont understand how this apiGateway gonna work I have added the loggers in jwt filter not single log in getting printted but when I call the pulic api where is bypasses by jwt Is returning output but no log printed in jwt auth filer used "Sl4j Annotation for logger  also provde above error solution




 I dont understand anything what does this class file do

 package com.fincore.gateway.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Defines routes. You point to services by DNS names when on Kubernetes (e.g., http://service-a:8080).
 * For local dev, point to localhost:port.
 *
 * We also add a sample route to httpbin to test quickly.
 */
@Configuration
public class GatewayRoutesConfig {

    @Value("${routes.service-a.uri:http://localhost:8090}")
    private String serviceAUri;

    @Value("${routes.httpbin.uri:https://httpbin.org}")
    private String httpbinUri;

    @Value("${ratelimit.enabled:true}")
    private boolean rateLimitEnabled;

    @Bean
    public RouteLocator customRoutes(RouteLocatorBuilder builder, OptionalRateLimiterProvider rlProvider) {
        var routes = builder.routes()

                // Example: /service-a/** -> http://service-a:8080/**
                .route("service-a", r -> r
                        .path("/service-a/**")
                        .filters(f -> {
                            if (rateLimitEnabled && rlProvider.redisRateLimiter() != null) {
                                f.requestRateLimiter(c -> c.setRateLimiter(rlProvider.redisRateLimiter()));
                            }
                            f.rewritePath("/service-a/(?<segment>.*)", "/${segment}");
                            return f;
                        })
                        .uri(serviceAUri))

                // Quick test route that requires auth (except you can add /public/** bypass):
                .route("httpbin", r -> r
                        .path("/httpbin/**")
                        .filters(f -> f.rewritePath("/httpbin/(?<segment>.*)", "/${segment}"))
                        .uri(httpbinUri))

                .build();

        // Make sure the original request URL is preserved for diagnostics
        routes.getRoutes().subscribe(rd ->
                rd.getFilters().add((exchange, chain) -> {
                    exchange.getAttributes().put(ServerWebExchangeUtils.GATEWAY_REQUEST_URL_ATTR, exchange.getRequest().getURI());
                    return chain.filter(exchange);
                })
        );
        return routes;
    }
}

