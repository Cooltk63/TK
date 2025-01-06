  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/

 :: Spring Boot ::                (v3.3.0)

2025-01-06T17:53:28.656+05:30  INFO 8800 --- [renderService] [  restartedMain] com.crs.renderService.renderServiceAPI   : Starting renderServiceAPI using Java 22.0.1 with PID 8800 (F:\Projects\CRS Projects\CRS ReWork\backend\renderService\target\classes started by V1012297 in F:\Projects\CRS Projects\CRS ReWork\backend\renderService)
2025-01-06T17:53:28.660+05:30  INFO 8800 --- [renderService] [  restartedMain] com.crs.renderService.renderServiceAPI   : No active profile set, falling back to 1 default profile: "default"
2025-01-06T17:53:28.723+05:30  INFO 8800 --- [renderService] [  restartedMain] o.s.b.devtools.restart.ChangeableUrls    : The Class-Path manifest attribute in C:\Users\v1012297\.m2\repository\com\oracle\database\jdbc\ojdbc11\21.9.0.0\ojdbc11-21.9.0.0.jar referenced one or more files that do not exist: file:/C:/Users/v1012297/.m2/repository/com/oracle/database/jdbc/ojdbc11/21.9.0.0/oraclepki.jar   
2025-01-06T17:53:28.723+05:30  INFO 8800 --- [renderService] [  restartedMain] .e.DevToolsPropertyDefaultsPostProcessor : Devtools property defaults active! Set 'spring.devtools.add-properties' to 'false' to disable
2025-01-06T17:53:28.724+05:30  INFO 8800 --- [renderService] [  restartedMain] .e.DevToolsPropertyDefaultsPostProcessor : For additional web related logging consider setting the 'logging.level.web' property to 'DEBUG'
2025-01-06T17:53:30.703+05:30  INFO 8800 --- [renderService] [  restartedMain] .s.d.r.c.RepositoryConfigurationDelegate : Bootstrapping Spring Data JPA repositories in DEFAULT mode.
2025-01-06T17:53:31.017+05:30  INFO 8800 --- [renderService] [  restartedMain] .s.d.r.c.RepositoryConfigurationDelegate : Finished Spring Data repository scanning in 297 ms. Found 9 JPA repository interfaces.
2025-01-06T17:53:32.686+05:30  INFO 8800 --- [renderService] [  restartedMain] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat initialized with port 8082 (http)
2025-01-06T17:53:32.707+05:30  INFO 8800 --- [renderService] [  restartedMain] o.apache.catalina.core.StandardService   : Starting service [Tomcat]
2025-01-06T17:53:32.708+05:30  INFO 8800 --- [renderService] [  restartedMain] o.apache.catalina.core.StandardEngine    : Starting Servlet engine: [Apache Tomcat/10.1.24]
2025-01-06T17:53:32.804+05:30  INFO 8800 --- [renderService] [  restartedMain] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring embedded WebApplicationContext
2025-01-06T17:53:32.804+05:30  INFO 8800 --- [renderService] [  restartedMain] w.s.c.ServletWebServerApplicationContext : Root WebApplicationContext: initialization completed in 4079 ms
2025-01-06T17:53:33.571+05:30  INFO 8800 --- [renderService] [  restartedMain] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Starting...
2025-01-06T17:53:34.138+05:30  INFO 8800 --- [renderService] [  restartedMain] com.zaxxer.hikari.pool.HikariPool        : HikariPool-1 - Added connection oracle.jdbc.driver.T4CConnection@542d835d
2025-01-06T17:53:34.141+05:30  INFO 8800 --- [renderService] [  restartedMain] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Start completed.
2025-01-06T17:53:34.217+05:30  INFO 8800 --- [renderService] [  restartedMain] o.hibernate.jpa.internal.util.LogHelper  : HHH000204: Processing PersistenceUnitInfo [name: default]
2025-01-06T17:53:34.316+05:30  INFO 8800 --- [renderService] [  restartedMain] org.hibernate.Version                    : HHH000412: Hibernate ORM core version 6.5.2.Final
2025-01-06T17:53:34.376+05:30  INFO 8800 --- [renderService] [  restartedMain] o.h.c.internal.RegionFactoryInitiator    : HHH000026: Second-level cache disabled
2025-01-06T17:53:34.723+05:30  INFO 8800 --- [renderService] [  restartedMain] o.s.o.j.p.SpringPersistenceUnitInfo      : No LoadTimeWeaver setup: ignoring JPA class transformer
2025-01-06T17:53:35.090+05:30 ERROR 8800 --- [renderService] [  restartedMain] j.LocalContainerEntityManagerFactoryBean : Failed to initialize JPA EntityManagerFactory: Property 'com.crs.renderService.models.CRSInduDvlpInc.branchNumber' belongs to an '@IdClass' but has no matching property in entity class 'com.crs.renderService.models.CRSInduDvlpInc' (every property of the '@IdClass' must have a corresponding persistent property in the '@Entity' class)
2025-01-06T17:53:35.092+05:30  WARN 8800 --- [renderService] [  restartedMain] ConfigServletWebServerApplicationContext : Exception encountered during context initialization - cancelling refresh attempt: org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'entityManagerFactory' defined in class path resource [org/springframework/boot/autoconfigure/orm/jpa/HibernateJpaConfiguration.class]: Property 'com.crs.renderService.models.CRSInduDvlpInc.branchNumber' belongs to an '@IdClass' but has no matching property in entity class 'com.crs.renderService.models.CRSInduDvlpInc' (every property of the '@IdClass' must have a corresponding persistent property in the '@Entity' class)
2025-01-06T17:53:35.095+05:30  INFO 8800 --- [renderService] [  restartedMain] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Shutdown initiated...
2025-01-06T17:53:35.207+05:30  INFO 8800 --- [renderService] [  restartedMain] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Shutdown completed.
2025-01-06T17:53:35.210+05:30  INFO 8800 --- [renderService] [  restartedMain] o.apache.catalina.core.StandardService   : Stopping service [Tomcat]
2025-01-06T17:53:35.233+05:30  INFO 8800 --- [renderService] [  restartedMain] .s.b.a.l.ConditionEvaluationReportLogger : 

Error starting ApplicationContext. To display the condition evaluation report re-run your application with 'debug' enabled.
2025-01-06T17:53:35.285+05:30 ERROR 8800 --- [renderService] [  restartedMain] o.s.boot.SpringApplication               : Application run failed

org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'entityManagerFactory' defined in class path resource [org/springframework/boot/autoconfigure/orm/jpa/HibernateJpaConfiguration.class]: Property 'com.crs.renderService.models.CRSInduDvlpInc.branchNumber' belongs to an '@IdClass' but has no matching property in entity class 'com.crs.renderService.models.CRSInduDvlpInc' (every property of the '@IdClass' must have a corresponding persistent property in the '@Entity' class)
        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.initializeBean(AbstractAutowireCapableBeanFactory.java:1788) ~[spring-beans-6.1.8.jar:6.1.8]
        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.doCreateBean(AbstractAutowireCapableBeanFactory.java:600) ~[spring-beans-6.1.8.jar:6.1.8]
        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBean(AbstractAutowireCapableBeanFactory.java:522) ~[spring-beans-6.1.8.jar:6.1.8]
        at org.springframework.beans.factory.support.AbstractBeanFactory.lambda$doGetBean$0(AbstractBeanFactory.java:337) ~[spring-beans-6.1.8.jar:6.1.8]
        at org.springframework.beans.factory.support.DefaultSingletonBeanRegistry.getSingleton(DefaultSingletonBeanRegistry.java:234) ~[spring-beans-6.1.8.jar:6.1.8]
        at org.springframework.beans.factory.support.AbstractBeanFactory.doGetBean(AbstractBeanFactory.java:335) ~[spring-beans-6.1.8.jar:6.1.8]
        at org.springframework.beans.factory.support.AbstractBeanFactory.getBean(AbstractBeanFactory.java:205) ~[spring-beans-6.1.8.jar:6.1.8]
        at org.springframework.context.support.AbstractApplicationContext.finishBeanFactoryInitialization(AbstractApplicationContext.java:952) ~[spring-context-6.1.8.jar:6.1.8]
        at org.springframework.context.support.AbstractApplicationContext.refresh(AbstractApplicationContext.java:624) ~[spring-context-6.1.8.jar:6.1.8]
        at org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext.refresh(ServletWebServerApplicationContext.java:146) ~[spring-boot-3.3.0.jar:3.3.0]
        at org.springframework.boot.SpringApplication.refresh(SpringApplication.java:754) ~[spring-boot-3.3.0.jar:3.3.0]
        at org.springframework.boot.SpringApplication.refreshContext(SpringApplication.java:456) ~[spring-boot-3.3.0.jar:3.3.0]
        at org.springframework.boot.SpringApplication.run(SpringApplication.java:335) ~[spring-boot-3.3.0.jar:3.3.0]
        at org.springframework.boot.SpringApplication.run(SpringApplication.java:1363) ~[spring-boot-3.3.0.jar:3.3.0]
        at org.springframework.boot.SpringApplication.run(SpringApplication.java:1352) ~[spring-boot-3.3.0.jar:3.3.0]
        at com.crs.renderService.renderServiceAPI.main(renderServiceAPI.java:10) ~[classes/:na]
        at java.base/jdk.internal.reflect.DirectMethodHandleAccessor.invoke(DirectMethodHandleAccessor.java:103) ~[na:na]
        at java.base/java.lang.reflect.Method.invoke(Method.java:580) ~[na:na]
        at org.springframework.boot.devtools.restart.RestartLauncher.run(RestartLauncher.java:50) ~[spring-boot-devtools-3.3.0.jar:3.3.0]
Caused by: org.hibernate.AnnotationException: Property 'com.crs.renderService.models.CRSInduDvlpInc.branchNumber' belongs to an '@IdClass' but has no matching property in entity class 'com.crs.renderService.models.CRSInduDvlpInc' (every property of the '@IdClass' must have a corresponding persistent property in the '@Entity' class)
        at org.hibernate.boot.model.internal.EmbeddableBinder.processIdClassElements(EmbeddableBinder.java:587) ~[hibernate-core-6.5.2.Final.jar:6.5.2.Final]
        at org.hibernate.boot.model.internal.EmbeddableBinder.fillEmbeddable(EmbeddableBinder.java:370) ~[hibernate-core-6.5.2.Final.jar:6.5.2.Final]
        at org.hibernate.boot.model.internal.EntityBinder.bindIdClass(EntityBinder.java:673) ~[hibernate-core-6.5.2.Final.jar:6.5.2.Final]
        at org.hibernate.boot.model.internal.EntityBinder.mapAsIdClass(EntityBinder.java:496) ~[hibernate-core-6.5.2.Final.jar:6.5.2.Final]
        at org.hibernate.boot.model.internal.EntityBinder.handleIdClass(EntityBinder.java:442) ~[hibernate-core-6.5.2.Final.jar:6.5.2.Final]
        at org.hibernate.boot.model.internal.EntityBinder.handleIdentifier(EntityBinder.java:408) ~[hibernate-core-6.5.2.Final.jar:6.5.2.Final]
        at org.hibernate.boot.model.internal.EntityBinder.bindEntityClass(EntityBinder.java:248) ~[hibernate-core-6.5.2.Final.jar:6.5.2.Final]
        at org.hibernate.boot.model.internal.AnnotationBinder.bindClass(AnnotationBinder.java:399) ~[hibernate-core-6.5.2.Final.jar:6.5.2.Final]
        at org.hibernate.boot.model.source.internal.annotations.AnnotationMetadataSourceProcessorImpl.processEntityHierarchies(AnnotationMetadataSourceProcessorImpl.java:259) ~[hibernate-core-6.5.2.Final.jar:6.5.2.Final]
        at org.hibernate.boot.model.process.spi.MetadataBuildingProcess$1.processEntityHierarchies(MetadataBuildingProcess.java:281) ~[hibernate-core-6.5.2.Final.jar:6.5.2.Final]
        at org.hibernate.boot.model.process.spi.MetadataBuildingProcess.complete(MetadataBuildingProcess.java:324) ~[hibernate-core-6.5.2.Final.jar:6.5.2.Final]
        at org.hibernate.jpa.boot.internal.EntityManagerFactoryBuilderImpl.metadata(EntityManagerFactoryBuilderImpl.java:1431) ~[hibernate-core-6.5.2.Final.jar:6.5.2.Final]
        at org.hibernate.jpa.boot.internal.EntityManagerFactoryBuilderImpl.build(EntityManagerFactoryBuilderImpl.java:1502) ~[hibernate-core-6.5.2.Final.jar:6.5.2.Final]
        at org.springframework.orm.jpa.vendor.SpringHibernateJpaPersistenceProvider.createContainerEntityManagerFactory(SpringHibernateJpaPersistenceProvider.java:75) ~[spring-orm-6.1.8.jar:6.1.8]    
        at org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean.createNativeEntityManagerFactory(LocalContainerEntityManagerFactoryBean.java:390) ~[spring-orm-6.1.8.jar:6.1.8]
        at org.springframework.orm.jpa.AbstractEntityManagerFactoryBean.buildNativeEntityManagerFactory(AbstractEntityManagerFactoryBean.java:409) ~[spring-orm-6.1.8.jar:6.1.8]
        at org.springframework.orm.jpa.AbstractEntityManagerFactoryBean.afterPropertiesSet(AbstractEntityManagerFactoryBean.java:396) ~[spring-orm-6.1.8.jar:6.1.8]
        at org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean.afterPropertiesSet(LocalContainerEntityManagerFactoryBean.java:366) ~[spring-orm-6.1.8.jar:6.1.8]
        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.invokeInitMethods(AbstractAutowireCapableBeanFactory.java:1835) ~[spring-beans-6.1.8.jar:6.1.8]
        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.initializeBean(AbstractAutowireCapableBeanFactory.java:1784) ~[spring-beans-6.1.8.jar:6.1.8]
        ... 18 common frames omitted
