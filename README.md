Standard Commons Logging discovery in action with spring-jcl: please remove commons-logging.jar from classpath in order to avoid potential conflicts
SLF4J(W): Class path contains multiple SLF4J providers.
SLF4J(W): Found provider [ch.qos.logback.classic.spi.LogbackServiceProvider@3f102e87]
SLF4J(W): Found provider [org.apache.logging.slf4j.SLF4JServiceProvider@27abe2cd]
SLF4J(W): See https://www.slf4j.org/codes.html#multiple_bindings for an explanation.
SLF4J(I): Actual provider is of type [ch.qos.logback.classic.spi.LogbackServiceProvider@3f102e87]
Logging system failed to initialize using configuration from 'classpath:application-log4j2.properties'
java.lang.IllegalStateException: Could not initialize Logback logging from classpath:application-log4j2.properties
	at org.springframework.boot.logging.logback.LogbackLoggingSystem.lambda$loadConfiguration$1(LogbackLoggingSystem.java:257)
	at org.springframework.boot.logging.logback.LogbackLoggingSystem.withLoggingSuppressed(LogbackLoggingSystem.java:472)
	at org.springframework.boot.logging.logback.LogbackLoggingSystem.loadConfiguration(LogbackLoggingSystem.java:248)
	at org.springframework.boot.logging.AbstractLoggingSystem.initializeWithSpecificConfig(AbstractLoggingSystem.java:67)
	at org.springframework.boot.logging.AbstractLoggingSystem.initialize(AbstractLoggingSystem.java:58)
	at org.springframework.boot.logging.logback.LogbackLoggingSystem.initialize(LogbackLoggingSystem.java:193)
	at org.springframework.boot.context.logging.LoggingApplicationListener.initializeSystem(LoggingApplicationListener.java:335)
	at org.springframework.boot.context.logging.LoggingApplicationListener.initialize(LoggingApplicationListener.java:298)
	at org.springframework.boot.context.logging.LoggingApplicationListener.onApplicationEnvironmentPreparedEvent(LoggingApplicationListener.java:246)
	at org.springframework.boot.context.logging.LoggingApplicationListener.onApplicationEvent(LoggingApplicationListener.java:223)
	at org.springframework.context.event.SimpleApplicationEventMulticaster.doInvokeListener(SimpleApplicationEventMulticaster.java:185)
	at org.springframework.context.event.SimpleApplicationEventMulticaster.invokeListener(SimpleApplicationEventMulticaster.java:178)
	at org.springframework.context.event.SimpleApplicationEventMulticaster.multicastEvent(SimpleApplicationEventMulticaster.java:156)
	at org.springframework.context.event.SimpleApplicationEventMulticaster.multicastEvent(SimpleApplicationEventMulticaster.java:138)
	at org.springframework.boot.context.event.EventPublishingRunListener.multicastInitialEvent(EventPublishingRunListener.java:136)
	at org.springframework.boot.context.event.EventPublishingRunListener.environmentPrepared(EventPublishingRunListener.java:81)
	at org.springframework.boot.SpringApplicationRunListeners.lambda$environmentPrepared$2(SpringApplicationRunListeners.java:64)
	at java.base/java.lang.Iterable.forEach(Iterable.java:75)
	at org.springframework.boot.SpringApplicationRunListeners.doWithListeners(SpringApplicationRunListeners.java:118)
	at org.springframework.boot.SpringApplicationRunListeners.doWithListeners(SpringApplicationRunListeners.java:112)
	at org.springframework.boot.SpringApplicationRunListeners.environmentPrepared(SpringApplicationRunListeners.java:63)
	at org.springframework.boot.SpringApplication.prepareEnvironment(SpringApplication.java:370)
	at org.springframework.boot.SpringApplication.run(SpringApplication.java:330)
	at org.springframework.boot.SpringApplication.run(SpringApplication.java:1363)
	at org.springframework.boot.SpringApplication.run(SpringApplication.java:1352)
	at com.crs.externalApiService.externalApiService.main(externalApiService.java:11)
Caused by: java.lang.IllegalArgumentException: Unsupported file extension in 'file:/F:/Projects/CRS%20Projects/CRS_Revamp/Backend/externalApiService/target/classes/application-log4j2.properties'. Only .xml is supported
	at org.springframework.boot.logging.logback.LogbackLoggingSystem.configureByResourceUrl(LogbackLoggingSystem.java:295)
	at org.springframework.boot.logging.logback.LogbackLoggingSystem.lambda$loadConfiguration$1(LogbackLoggingSystem.java:254)
	... 25 more

Process finished with exit code 1
