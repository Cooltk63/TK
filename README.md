E:\Kubernates Yaml Files\Dev-Deployment\New Test>kubectl logs product-service-5c96b57f6b-fkgmp -n dev
WARNING: A terminally deprecated method in sun.misc.Unsafe has been called
WARNING: sun.misc.Unsafe::allocateMemory has been called by io.netty.util.internal.PlatformDependent0$2 (jar:nested:/app/app.jar/!BOOT-INF/lib/netty-common-4.1.123.Final.jar!/)
WARNING: Please consider reporting this to the maintainers of class io.netty.util.internal.PlatformDependent0$2
WARNING: sun.misc.Unsafe::allocateMemory will be removed in a future release

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/

 :: Spring Boot ::                (v3.5.4)

2025-08-21 :: 09:19:41.817 || INFO :: StartupInfoLogger.java: | 53 | ::  Starting FincoreProductApplication v0.0.1-SNAPSHOT using Java 24.0.2 with PID 1 (/app/app.jar started by circleci in /app)
2025-08-21 :: 09:19:41.825 || INFO :: SpringApplication.java: | 658 | ::  The following 1 profile is active: "dev"
2025-08-21 :: 09:19:42.663 || INFO :: RepositoryConfigurationDelegate.java: | 145 | ::  Bootstrapping Spring Data JPA repositories in DEFAULT mode.
2025-08-21 :: 09:19:42.691 || INFO :: RepositoryConfigurationDelegate.java: | 213 | ::  Finished Spring Data repository scanning in 14 ms. Found 0 JPA repository interfaces.
2025-08-21 :: 09:19:42.918 || INFO :: GenericScope.java: | 280 | ::  BeanFactory id=8068be97-36d8-3e5a-b369-c3d2a7afce1c
2025-08-21 :: 09:19:43.431 || INFO :: TomcatWebServer.java: | 111 | ::  Tomcat initialized with port 8081 (http)
2025-08-21 :: 09:19:43.445 || INFO :: DirectJDKLog.java: | 168 | ::  Starting service [Tomcat]
2025-08-21 :: 09:19:43.445 || INFO :: DirectJDKLog.java: | 168 | ::  Starting Servlet engine: [Apache Tomcat/10.1.43]
2025-08-21 :: 09:19:43.472 || INFO :: DirectJDKLog.java: | 168 | ::  Initializing Spring embedded WebApplicationContext
2025-08-21 :: 09:19:43.473 || INFO :: ServletWebServerApplicationContext.java: | 301 | ::  Root WebApplicationContext: initialization completed in 1565 ms
2025-08-21 :: 09:19:43.807 || INFO :: HikariDataSource.java: | 109 | ::  HikariPool-1 - Starting...
2025-08-21 :: 09:19:44.423 || INFO :: HikariPool.java: | 580 | ::  HikariPool-1 - Added connection oracle.jdbc.driver.T4CConnection@4b7ab7ab
2025-08-21 :: 09:19:44.426 || INFO :: HikariDataSource.java: | 122 | ::  HikariPool-1 - Start completed.
2025-08-21 :: 09:19:44.494 || INFO :: LogHelper.java: | 31 | ::  HHH000204: Processing PersistenceUnitInfo [name: default]
2025-08-21 :: 09:19:44.603 || INFO :: Version.java: | 44 | ::  HHH000412: Hibernate ORM core version 6.6.22.Final
2025-08-21 :: 09:19:44.656 || INFO :: RegionFactoryInitiator.java: | 50 | ::  HHH000026: Second-level cache disabled
2025-08-21 :: 09:19:45.122 || INFO :: SpringPersistenceUnitInfo.java: | 87 | ::  No LoadTimeWeaver setup: ignoring JPA class transformer
2025-08-21 :: 09:19:45.510 || INFO :: JdbcEnvironmentInitiator.java: | 163 | ::  HHH10001005: Database info:
        Database JDBC URL [Connecting through datasource 'HikariDataSource (HikariPool-1)']
        Database driver: undefined/unknown
        Database version: 19.28
        Autocommit mode: undefined/unknown
        Isolation level: undefined/unknown
        Minimum pool size: undefined/unknown
        Maximum pool size: undefined/unknown
2025-08-21 :: 09:19:45.976 || INFO :: JtaPlatformInitiator.java: | 59 | ::  HHH000489: No JTA platform available (set 'hibernate.transaction.jta.platform' to enable JTA platform integration)
2025-08-21 :: 09:19:45.983 || INFO :: AbstractEntityManagerFactoryBean.java: | 447 | ::  Initialized JPA EntityManagerFactory for persistence unit 'default'
2025-08-21 :: 09:19:46.451 || WARN :: JpaBaseConfiguration.java: | 258 | ::  spring.jpa.open-in-view is enabled by default. Therefore, database queries may be performed during view rendering. Explicitly configure spring.jpa.open-in-view to disable this warning
2025-08-21 :: 09:19:47.023 || WARN :: AbstractApplicationContext.java: | 635 | ::  Exception encountered during context initialization - cancelling refresh attempt: org.springframework.beans.factory.UnsatisfiedDependencyException: Error creating bean with name 'compositeDiscoveryClient' defined in class path resource [org/springframework/cloud/client/discovery/composite/CompositeDiscoveryClientAutoConfiguration.class]: Unsatisfied dependency expressed through method 'compositeDiscoveryClient' parameter 0: Error creating bean with name 'kubernetesDiscoveryClient' defined in class path resource [org/springframework/cloud/kubernetes/discovery/KubernetesDiscoveryClientBlockingAutoConfiguration.class]: Failed to instantiate [org.springframework.cloud.kubernetes.discovery.KubernetesDiscoveryClient]: Factory method 'kubernetesDiscoveryClient' threw exception with message: 'spring.cloud.kubernetes.discovery.discovery-server-url' must be specified and be a valid URL.
2025-08-21 :: 09:19:47.025 || INFO :: AbstractEntityManagerFactoryBean.java: | 660 | ::  Closing JPA EntityManagerFactory for persistence unit 'default'
2025-08-21 :: 09:19:47.028 || INFO :: HikariDataSource.java: | 349 | ::  HikariPool-1 - Shutdown initiated...
2025-08-21 :: 09:19:47.084 || INFO :: HikariDataSource.java: | 351 | ::  HikariPool-1 - Shutdown completed.
2025-08-21 :: 09:19:47.086 || INFO :: DirectJDKLog.java: | 168 | ::  Stopping service [Tomcat]
2025-08-21 :: 09:19:47.102 || INFO :: ConditionEvaluationReportLogger.java: | 82 | ::

Error starting ApplicationContext. To display the condition evaluation report re-run your application with 'debug' enabled.
2025-08-21 :: 09:19:47.126 || ERROR:: SpringApplication.java: | 857 | ::  Application run failed
org.springframework.beans.factory.UnsatisfiedDependencyException: Error creating bean with name 'compositeDiscoveryClient' defined in class path resource [org/springframework/cloud/client/discovery/composite/CompositeDiscoveryClientAutoConfiguration.class]: Unsatisfied dependency expressed through method 'compositeDiscoveryClient' parameter 0: Error creating bean with name 'kubernetesDiscoveryClient' defined in class path resource [org/springframework/cloud/kubernetes/discovery/KubernetesDiscoveryClientBlockingAutoConfiguration.class]: Failed to instantiate [org.springframework.cloud.kubernetes.discovery.KubernetesDiscoveryClient]: Factory method 'kubernetesDiscoveryClient' threw exception with message: 'spring.cloud.kubernetes.discovery.discovery-server-url' must be specified and be a valid URL.
        at org.springframework.beans.factory.support.ConstructorResolver.createArgumentArray(ConstructorResolver.java:804)
        at org.springframework.beans.factory.support.ConstructorResolver.instantiateUsingFactoryMethod(ConstructorResolver.java:546)
        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.instantiateUsingFactoryMethod(AbstractAutowireCapableBeanFactory.java:1375)
        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBeanInstance(AbstractAutowireCapableBeanFactory.java:1205)
        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.doCreateBean(AbstractAutowireCapableBeanFactory.java:569)
        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBean(AbstractAutowireCapableBeanFactory.java:529)
        at org.springframework.beans.factory.support.AbstractBeanFactory.lambda$doGetBean$0(AbstractBeanFactory.java:339)
        at org.springframework.beans.factory.support.DefaultSingletonBeanRegistry.getSingleton(DefaultSingletonBeanRegistry.java:373)
        at org.springframework.beans.factory.support.AbstractBeanFactory.doGetBean(AbstractBeanFactory.java:337)
        at org.springframework.beans.factory.support.AbstractBeanFactory.getBean(AbstractBeanFactory.java:202)
        at org.springframework.beans.factory.support.DefaultListableBeanFactory.instantiateSingleton(DefaultListableBeanFactory.java:1222)
        at org.springframework.beans.factory.support.DefaultListableBeanFactory.preInstantiateSingleton(DefaultListableBeanFactory.java:1188)
        at org.springframework.beans.factory.support.DefaultListableBeanFactory.preInstantiateSingletons(DefaultListableBeanFactory.java:1123)
        at org.springframework.context.support.AbstractApplicationContext.finishBeanFactoryInitialization(AbstractApplicationContext.java:987)
        at org.springframework.context.support.AbstractApplicationContext.refresh(AbstractApplicationContext.java:627)
        at org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext.refresh(ServletWebServerApplicationContext.java:146)
        at org.springframework.boot.SpringApplication.refresh(SpringApplication.java:752)
        at org.springframework.boot.SpringApplication.refreshContext(SpringApplication.java:439)
        at org.springframework.boot.SpringApplication.run(SpringApplication.java:318)
        at org.springframework.boot.SpringApplication.run(SpringApplication.java:1361)
        at org.springframework.boot.SpringApplication.run(SpringApplication.java:1350)
        at com.example.Product.FincoreProductApplication.main(FincoreProductApplication.java:11)
        at java.base/jdk.internal.reflect.DirectMethodHandleAccessor.invoke(DirectMethodHandleAccessor.java:104)
        at java.base/java.lang.reflect.Method.invoke(Method.java:565)
        at org.springframework.boot.loader.launch.Launcher.launch(Launcher.java:102)
        at org.springframework.boot.loader.launch.Launcher.launch(Launcher.java:64)
        at org.springframework.boot.loader.launch.JarLauncher.main(JarLauncher.java:40)
Caused by: org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'kubernetesDiscoveryClient' defined in class path resource [org/springframework/cloud/kubernetes/discovery/KubernetesDiscoveryClientBlockingAutoConfiguration.class]: Failed to instantiate [org.springframework.cloud.kubernetes.discovery.KubernetesDiscoveryClient]: Factory method 'kubernetesDiscoveryClient' threw exception with message: 'spring.cloud.kubernetes.discovery.discovery-server-url' must be specified and be a valid URL.
        at org.springframework.beans.factory.support.ConstructorResolver.instantiate(ConstructorResolver.java:657)
        at org.springframework.beans.factory.support.ConstructorResolver.instantiateUsingFactoryMethod(ConstructorResolver.java:645)
        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.instantiateUsingFactoryMethod(AbstractAutowireCapableBeanFactory.java:1375)
        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBeanInstance(AbstractAutowireCapableBeanFactory.java:1205)
        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.doCreateBean(AbstractAutowireCapableBeanFactory.java:569)
        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBean(AbstractAutowireCapableBeanFactory.java:529)
        at org.springframework.beans.factory.support.AbstractBeanFactory.lambda$doGetBean$0(AbstractBeanFactory.java:339)
        at org.springframework.beans.factory.support.DefaultSingletonBeanRegistry.getSingleton(DefaultSingletonBeanRegistry.java:373)
        at org.springframework.beans.factory.support.AbstractBeanFactory.doGetBean(AbstractBeanFactory.java:337)
        at org.springframework.beans.factory.support.AbstractBeanFactory.getBean(AbstractBeanFactory.java:202)
        at org.springframework.beans.factory.config.DependencyDescriptor.resolveCandidate(DependencyDescriptor.java:254)
        at org.springframework.beans.factory.support.DefaultListableBeanFactory.addCandidateEntry(DefaultListableBeanFactory.java:1996)
        at org.springframework.beans.factory.support.DefaultListableBeanFactory.findAutowireCandidates(DefaultListableBeanFactory.java:1960)
        at org.springframework.beans.factory.support.DefaultListableBeanFactory.resolveMultipleBeanCollection(DefaultListableBeanFactory.java:1850)
        at org.springframework.beans.factory.support.DefaultListableBeanFactory.resolveMultipleBeans(DefaultListableBeanFactory.java:1818)
        at org.springframework.beans.factory.support.DefaultListableBeanFactory.doResolveDependency(DefaultListableBeanFactory.java:1694)
        at org.springframework.beans.factory.support.DefaultListableBeanFactory.resolveDependency(DefaultListableBeanFactory.java:1628)
        at org.springframework.beans.factory.support.ConstructorResolver.resolveAutowiredArgument(ConstructorResolver.java:913)
        at org.springframework.beans.factory.support.ConstructorResolver.createArgumentArray(ConstructorResolver.java:791)
        ... 26 common frames omitted
Caused by: org.springframework.beans.BeanInstantiationException: Failed to instantiate [org.springframework.cloud.kubernetes.discovery.KubernetesDiscoveryClient]: Factory method 'kubernetesDiscoveryClient' threw exception with message: 'spring.cloud.kubernetes.discovery.discovery-server-url' must be specified and be a valid URL.
        at org.springframework.beans.factory.support.SimpleInstantiationStrategy.lambda$instantiate$0(SimpleInstantiationStrategy.java:200)
        at org.springframework.beans.factory.support.SimpleInstantiationStrategy.instantiateWithFactoryMethod(SimpleInstantiationStrategy.java:89)
        at org.springframework.beans.factory.support.SimpleInstantiationStrategy.instantiate(SimpleInstantiationStrategy.java:169)
        at org.springframework.beans.factory.support.ConstructorResolver.instantiate(ConstructorResolver.java:653)
        ... 44 common frames omitted
Caused by: org.springframework.cloud.kubernetes.discovery.DiscoveryServerUrlInvalidException: 'spring.cloud.kubernetes.discovery.discovery-server-url' must be specified and be a valid URL.
        at org.springframework.cloud.kubernetes.discovery.KubernetesDiscoveryClient.<init>(KubernetesDiscoveryClient.java:58)
        at org.springframework.cloud.kubernetes.discovery.KubernetesDiscoveryClientBlockingAutoConfiguration.kubernetesDiscoveryClient(KubernetesDiscoveryClientBlockingAutoConfiguration.java:51)
        at java.base/jdk.internal.reflect.DirectMethodHandleAccessor.invoke(DirectMethodHandleAccessor.java:104)
        at java.base/java.lang.reflect.Method.invoke(Method.java:565)
        at org.springframework.beans.factory.support.SimpleInstantiationStrategy.lambda$instantiate$0(SimpleInstantiationStrategy.java:172)
        ... 47 common frames omitted

        This is the error logs for product service
