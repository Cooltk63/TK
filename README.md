aplication.properties file ::
spring.application.name=ApiGateWay

spring.data.redis.host=localhost
spring.data.redis.port=6379
spring.data.redis.timeout=60000
spring.data.redis.client-type=lettuce


spring.datasource.driver-class-name=oracle.jdbc.OracleDriver
server.port=8080
spring.profiles.active=dev


security.jwt.public-key=uGp9YSsW41V4dIkoW7SHcoeyDnrUZ+amH+JuNESsQms=
security.session.ttl-seconds=3600
gateway.bypass.urls=/auth/**,/public/**

management.endpoints.web.exposure.include=health,info,prometheus
management.endpoint.health.show-details=never
management.endpoints.web.base-path=/management

# Logging
logging.level.org.springframework.cloud.gateway=INFO

spring.cloud.gateway.server.webflux.routes=product-service
spring.cloud.gateway.server.webflux.routes[0].uri=http://product-service:8080
spring.cloud.gateway.server.webflux.routes[0].predicates[0]=Path=/products/**
spring.cloud.gateway.server.webflux.routes[0].filters[0]=RequestRateLimiter=redis-rate-limiter.replenishRate=5,redis-rate-limiter.burstCapacity=10
spring.cloud.gateway.server.webflux.routes[0].filters[1]=RewritePath=/products/(?<segment>.*), /${segment}


Console error ::

Error starting ApplicationContext. To display the condition evaluation report re-run your application with 'debug' enabled.
2025-08-12T12:26:25.745+05:30 ERROR 24488 --- [ApiGateWay] [           main] o.s.boot.SpringApplication               : Application run failed

org.springframework.beans.factory.UnsatisfiedDependencyException: Error creating bean with name 'jwtAuthenticationFilter' defined in file [F:\Projects\Fincore\Backend\ApiGateWay\target\classes\com\fincore\ApiGateWay\Filters\JwtAuthenticationFilter.class]: Unsatisfied dependency expressed through constructor parameter 0: Error creating bean with name 'jwtUtil': Invocation of init method failed
	at org.springframework.beans.factory.support.ConstructorResolver.createArgumentArray(ConstructorResolver.java:804) ~[spring-beans-6.2.9.jar:6.2.9]
	at org.springframework.beans.factory.support.ConstructorResolver.autowireConstructor(ConstructorResolver.java:240) ~[spring-beans-6.2.9.jar:6.2.9]
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.autowireConstructor(AbstractAutowireCapableBeanFactory.java:1395) ~[spring-beans-6.2.9.jar:6.2.9]
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBeanInstance(AbstractAutowireCapableBeanFactory.java:1232) ~[spring-beans-6.2.9.jar:6.2.9]
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
	at com.fincore.ApiGateWay.ApiGateWayApplication.main(ApiGateWayApplication.java:10) ~[classes/:na]
Caused by: org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'jwtUtil': Invocation of init method failed
	at org.springframework.beans.factory.annotation.InitDestroyAnnotationBeanPostProcessor.postProcessBeforeInitialization(InitDestroyAnnotationBeanPostProcessor.java:222) ~[spring-beans-6.2.9.jar:6.2.9]
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.applyBeanPostProcessorsBeforeInitialization(AbstractAutowireCapableBeanFactory.java:429) ~[spring-beans-6.2.9.jar:6.2.9]
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.initializeBean(AbstractAutowireCapableBeanFactory.java:1818) ~[spring-beans-6.2.9.jar:6.2.9]
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.doCreateBean(AbstractAutowireCapableBeanFactory.java:607) ~[spring-beans-6.2.9.jar:6.2.9]
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBean(AbstractAutowireCapableBeanFactory.java:529) ~[spring-beans-6.2.9.jar:6.2.9]
	at org.springframework.beans.factory.support.AbstractBeanFactory.lambda$doGetBean$0(AbstractBeanFactory.java:339) ~[spring-beans-6.2.9.jar:6.2.9]
	at org.springframework.beans.factory.support.DefaultSingletonBeanRegistry.getSingleton(DefaultSingletonBeanRegistry.java:373) ~[spring-beans-6.2.9.jar:6.2.9]
	at org.springframework.beans.factory.support.AbstractBeanFactory.doGetBean(AbstractBeanFactory.java:337) ~[spring-beans-6.2.9.jar:6.2.9]
	at org.springframework.beans.factory.support.AbstractBeanFactory.getBean(AbstractBeanFactory.java:202) ~[spring-beans-6.2.9.jar:6.2.9]
	at org.springframework.beans.factory.support.DefaultListableBeanFactory.doResolveDependency(DefaultListableBeanFactory.java:1683) ~[spring-beans-6.2.9.jar:6.2.9]
	at org.springframework.beans.factory.support.DefaultListableBeanFactory.resolveDependency(DefaultListableBeanFactory.java:1628) ~[spring-beans-6.2.9.jar:6.2.9]
	at org.springframework.beans.factory.support.ConstructorResolver.resolveAutowiredArgument(ConstructorResolver.java:913) ~[spring-beans-6.2.9.jar:6.2.9]
	at org.springframework.beans.factory.support.ConstructorResolver.createArgumentArray(ConstructorResolver.java:791) ~[spring-beans-6.2.9.jar:6.2.9]
	... 21 common frames omitted
Caused by: java.security.spec.InvalidKeySpecException: java.security.InvalidKeyException: Unable to decode key
	at java.base/sun.security.rsa.RSAKeyFactory.engineGeneratePublic(RSAKeyFactory.java:241) ~[na:na]
	at java.base/java.security.KeyFactory.generatePublic(KeyFactory.java:351) ~[na:na]
	at com.fincore.ApiGateWay.Util.JwtUtil.init(JwtUtil.java:54) ~[classes/:na]
	at java.base/jdk.internal.reflect.DirectMethodHandleAccessor.invoke(DirectMethodHandleAccessor.java:103) ~[na:na]
	at java.base/java.lang.reflect.Method.invoke(Method.java:580) ~[na:na]
	at org.springframework.beans.factory.annotation.InitDestroyAnnotationBeanPostProcessor$LifecycleMethod.invoke(InitDestroyAnnotationBeanPostProcessor.java:457) ~[spring-beans-6.2.9.jar:6.2.9]
	at org.springframework.beans.factory.annotation.InitDestroyAnnotationBeanPostProcessor$LifecycleMetadata.invokeInitMethods(InitDestroyAnnotationBeanPostProcessor.java:401) ~[spring-beans-6.2.9.jar:6.2.9]
	at org.springframework.beans.factory.annotation.InitDestroyAnnotationBeanPostProcessor.postProcessBeforeInitialization(InitDestroyAnnotationBeanPostProcessor.java:219) ~[spring-beans-6.2.9.jar:6.2.9]
	... 33 common frames omitted
Caused by: java.security.InvalidKeyException: Unable to decode key
	at java.base/sun.security.x509.X509Key.decode(X509Key.java:376) ~[na:na]
	at java.base/sun.security.rsa.RSAPublicKeyImpl.<init>(RSAPublicKeyImpl.java:148) ~[na:na]
	at java.base/sun.security.rsa.RSAPublicKeyImpl.newKey(RSAPublicKeyImpl.java:80) ~[na:na]
	at java.base/sun.security.rsa.RSAKeyFactory.generatePublic(RSAKeyFactory.java:324) ~[na:na]
	at java.base/sun.security.rsa.RSAKeyFactory.engineGeneratePublic(RSAKeyFactory.java:237) ~[na:na]
	... 40 common frames omitted
Caused by: java.io.EOFException: not enough content
	at java.base/sun.security.util.DerValue.<init>(DerValue.java:429) ~[na:na]
	at java.base/sun.security.util.DerValue.<init>(DerValue.java:344) ~[na:na]
	at java.base/sun.security.x509.X509Key.decode(X509Key.java:374) ~[na:na]
	... 44 common frames omitted


Process finished with exit code 1
