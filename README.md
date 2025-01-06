2025-01-06T16:01:18.265+05:30  INFO 15344 --- [renderService] [  restartedMain] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Start completed.
2025-01-06T16:01:18.390+05:30  INFO 15344 --- [renderService] [  restartedMain] o.hibernate.jpa.internal.util.LogHelper  : HHH000204: Processing PersistenceUnitInfo [name: default]
2025-01-06T16:01:18.576+05:30  INFO 15344 --- [renderService] [  restartedMain] org.hibernate.Version                    : HHH000412: Hibernate ORM core version 6.5.2.Final
2025-01-06T16:01:18.664+05:30  INFO 15344 --- [renderService] [  restartedMain] o.h.c.internal.RegionFactoryInitiator    : HHH000026: Second-level cache disabled
2025-01-06T16:01:19.337+05:30  INFO 15344 --- [renderService] [  restartedMain] o.s.o.j.p.SpringPersistenceUnitInfo      : No LoadTimeWeaver setup: ignoring JPA class transformer
2025-01-06T16:01:20.518+05:30  WARN 15344 --- [renderService] [  restartedMain] org.hibernate.mapping.RootClass          : HHH000038: Composite-id class does not override equals(): com.crs.renderService.models.CrsEduIntSubcdEwsId
2025-01-06T16:01:20.521+05:30  WARN 15344 --- [renderService] [  restartedMain] org.hibernate.mapping.RootClass          : HHH000039: Composite-id class does not override hashCode(): com.crs.renderService.models.CrsEduIntSubcdEwsId
2025-01-06T16:01:20.524+05:30  WARN 15344 --- [renderService] [  restartedMain] org.hibernate.mapping.RootClass          : HHH000038: Composite-id class does not override equals(): com.crs.renderService.models.CRSHousDvlpIncExtended
2025-01-06T16:01:20.533+05:30  WARN 15344 --- [renderService] [  restartedMain] org.hibernate.mapping.RootClass          : HHH000039: Composite-id class does not override hashCode(): com.crs.renderService.models.CRSHousDvlpIncExtended
2025-01-06T16:01:20.536+05:30  WARN 15344 --- [renderService] [  restartedMain] org.hibernate.mapping.RootClass          : HHH000038: Composite-id class does not override equals(): com.crs.renderService.models.CrsAddIntSubventionId
2025-01-06T16:01:20.550+05:30  WARN 15344 --- [renderService] [  restartedMain] org.hibernate.mapping.RootClass          : HHH000039: Composite-id class does not override hashCode(): com.crs.renderService.models.CrsAddIntSubventionId
2025-01-06T16:01:20.555+05:30  WARN 15344 --- [renderService] [  restartedMain] org.hibernate.mapping.RootClass          : HHH000038: Composite-id class does not override equals(): com.crs.renderService.models.CrsWoshgId
2025-01-06T16:01:20.559+05:30  WARN 15344 --- [renderService] [  restartedMain] org.hibernate.mapping.RootClass          : HHH000039: Composite-id class does not override hashCode(): com.crs.renderService.models.CrsWoshgId
2025-01-06T16:01:20.586+05:30  WARN 15344 --- [renderService] [  restartedMain] org.hibernate.mapping.RootClass          : HHH000038: Composite-id class does not override equals(): com.crs.renderService.models.CRSAgrDvlpIncExtended
2025-01-06T16:01:20.591+05:30  WARN 15344 --- [renderService] [  restartedMain] org.hibernate.mapping.RootClass          : HHH000039: Composite-id class does not override hashCode(): com.crs.renderService.models.CRSAgrDvlpIncExtended
2025-01-06T16:01:20.596+05:30  WARN 15344 --- [renderService] [  restartedMain] org.hibernate.mapping.RootClass          : HHH000038: Composite-id class does not override equals(): com.crs.renderService.models.CRSInduDvlpIncExtended
2025-01-06T16:01:20.603+05:30  WARN 15344 --- [renderService] [  restartedMain] org.hibernate.mapping.RootClass          : HHH000039: Composite-id class does not override hashCode(): com.crs.renderService.models.CRSInduDvlpIncExtended
2025-01-06T16:01:20.607+05:30  WARN 15344 --- [renderService] [  restartedMain] org.hibernate.mapping.RootClass          : HHH000038: Composite-id class does not override equals(): com.crs.renderService.models.CRSInfraDvlpIncExtended
2025-01-06T16:01:20.609+05:30  WARN 15344 --- [renderService] [  restartedMain] org.hibernate.mapping.RootClass          : HHH000039: Composite-id class does not override hashCode(): com.crs.renderService.models.CRSInfraDvlpIncExtended
2025-01-06T16:01:20.611+05:30  WARN 15344 --- [renderService] [  restartedMain] org.hibernate.mapping.RootClass          : HHH000038: Composite-id class does not override equals(): com.crs.renderService.models.CrsStCreditId
2025-01-06T16:01:20.612+05:30  WARN 15344 --- [renderService] [  restartedMain] org.hibernate.mapping.RootClass          : HHH000039: Composite-id class does not override hashCode(): com.crs.renderService.models.CrsStCreditId
2025-01-06T16:01:22.749+05:30  INFO 15344 --- [renderService] [  restartedMain] o.h.e.t.j.p.i.JtaPlatformInitiator       : HHH000489: No JTA platform available (set 'hibernate.transaction.jta.platform' to enable JTA platform integration)
2025-01-06T16:01:22.755+05:30  INFO 15344 --- [renderService] [  restartedMain] j.LocalContainerEntityManagerFactoryBean : Initialized JPA EntityManagerFactory for persistence unit 'default'
2025-01-06T16:01:23.589+05:30  INFO 15344 --- [renderService] [  restartedMain] o.s.d.j.r.query.QueryEnhancerFactory     : Hibernate is in classpath; If applicable, HQL parser will be used.
2025-01-06T16:01:24.092+05:30  WARN 15344 --- [renderService] [  restartedMain] ConfigServletWebServerApplicationContext : Exception encountered during context initialization - cancelling refresh attempt: org.springframework.beans.factory.UnsatisfiedDependencyException: Error creating bean with name 'renderController': Unsatisfied dependency expressed through field 'renderService': Error creating bean with name 'RenderService': Unsatisfied dependency expressed through field 'serviceInvoker': Error creating bean with name 'serviceInvoker': Unsatisfied dependency expressed through field 'rw10Service': Error creating bean with name 'RW10Service': Unsatisfied dependency expressed through field 'crsInduDvIncRepository': Error creating bean with name 'CRSInduDvIncRepository' defined in com.crs.renderService.repositories.CRSInduDvIncRepository defined in @EnableJpaRepositories declared on JpaRepositoriesRegistrar.EnableJpaRepositoriesConfiguration: Could not create query for public abstract com.crs.renderService.models.CRSInduDvlpInc com.crs.renderService.repositories.CRSInduDvIncRepository.findByDateAndBranchNumberAndCrsInduDvlpId(java.util.Date,java.lang.String,java.lang.String); Reason: Failed to create query for method public abstract com.crs.renderService.models.CRSInduDvlpInc com.crs.renderService.repositories.CRSInduDvIncRepository.findByDateAndBranchNumberAndCrsInduDvlpId(java.util.Date,java.lang.String,java.lang.String); No property 'crsInduDvlpId' found for type 'CRSInduDvlpInc'
2025-01-06T16:01:24.094+05:30  INFO 15344 --- [renderService] [  restartedMain] j.LocalContainerEntityManagerFactoryBean : Closing JPA EntityManagerFactory for persistence unit 'default'
2025-01-06T16:01:24.103+05:30  INFO 15344 --- [renderService] [  restartedMain] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Shutdown initiated...
2025-01-06T16:01:24.177+05:30  INFO 15344 --- [renderService] [  restartedMain] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Shutdown completed.
2025-01-06T16:01:24.185+05:30  INFO 15344 --- [renderService] [  restartedMain] o.apache.catalina.core.StandardService   : Stopping service [Tomcat]
2025-01-06T16:01:24.220+05:30  INFO 15344 --- [renderService] [  restartedMain] .s.b.a.l.ConditionEvaluationReportLogger : 

Error starting ApplicationContext. To display the condition evaluation report re-run your application with 'debug' enabled.
2025-01-06T16:01:24.306+05:30 ERROR 15344 --- [renderService] [  restartedMain] o.s.boot.SpringApplication               : Application run failed

org.springframework.beans.factory.UnsatisfiedDependencyException: Error creating bean with name 'renderController': Unsatisfied dependency expressed through field 'renderService': Error creating bean with name 'RenderService': Unsatisfied dependency expressed through field 'serviceInvoker': Error creating bean with name 'serviceInvoker': Unsatisfied dependency expressed through field 'rw10Service': Error creating bean with name 'RW10Service': Unsatisfied dependency expressed through field 'crsInduDvIncRepository': Error creating bean with name 'CRSInduDvIncRepository' defined in com.crs.renderService.repositories.CRSInduDvIncRepository defined in @EnableJpaRepositories declared on JpaRepositoriesRegistrar.EnableJpaRepositoriesConfiguration: Could not create 
query for public abstract com.crs.renderService.models.CRSInduDvlpInc com.crs.renderService.repositories.CRSInduDvIncRepository.findByDateAndBranchNumberAndCrsInduDvlpId(java.util.Date,java.lang.String,java.lang.String); Reason: Failed to create query for method public abstract com.crs.renderService.models.CRSInduDvlpInc com.crs.renderService.repositories.CRSInduDvIncRepository.findByDateAndBranchNumberAndCrsInduDvlpId(java.util.Date,java.lang.String,java.lang.String); No property 'crsInduDvlpId' found for type 'CRSInduDvlpInc'
        at org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor$AutowiredFieldElement.resolveFieldValue(AutowiredAnnotationBeanPostProcessor.java:787) ~[spring-beans-6.1.8.jar:6.1.8]
        at org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor$AutowiredFieldElement.inject(AutowiredAnnotationBeanPostProcessor.java:767) ~[spring-beans-6.1.8.jar:6.1.8]
        at org.springframework.beans.factory.annotation.InjectionMetadata.inject(InjectionMetadata.java:145) ~[spring-beans-6.1.8.jar:6.1.8]
        at org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor.postProcessProperties(AutowiredAnnotationBeanPostProcessor.java:508) ~[spring-beans-6.1.8.jar:6.1.8]
        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.populateBean(AbstractAutowireCapableBeanFactory.java:1421) ~[spring-beans-6.1.8.jar:6.1.8]
        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.doCreateBean(AbstractAutowireCapableBeanFactory.java:599) ~[spring-beans-6.1.8.jar:6.1.8]
        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBean(AbstractAutowireCapableBeanFactory.java:522) ~[spring-beans-6.1.8.jar:6.1.8]
        at org.springframework.beans.factory.support.AbstractBeanFactory.lambda$doGetBean$0(AbstractBeanFactory.java:337) ~[spring-beans-6.1.8.jar:6.1.8]
        at org.springframework.beans.factory.support.DefaultSingletonBeanRegistry.getSingleton(DefaultSingletonBeanRegistry.java:234) ~[spring-beans-6.1.8.jar:6.1.8]
        at org.springframework.beans.factory.support.AbstractBeanFactory.doGetBean(AbstractBeanFactory.java:335) ~[spring-beans-6.1.8.jar:6.1.8]
        at org.springframework.beans.factory.support.AbstractBeanFactory.getBean(AbstractBeanFactory.java:200) ~[spring-beans-6.1.8.jar:6.1.8]
        at org.springframework.beans.factory.support.DefaultListableBeanFactory.preInstantiateSingletons(DefaultListableBeanFactory.java:975) ~[spring-beans-6.1.8.jar:6.1.8]
        at org.springframework.context.support.AbstractApplicationContext.finishBeanFactoryInitialization(AbstractApplicationContext.java:962) ~[spring-context-6.1.8.jar:6.1.8]
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
Caused by: org.springframework.beans.factory.UnsatisfiedDependencyException: Error creating bean with name 'RenderService': Unsatisfied dependency expressed through field 'serviceInvoker': Error creating bean with name 'serviceInvoker': Unsatisfied dependency expressed through field 'rw10Service': Error creating bean with name 'RW10Service': Unsatisfied dependency expressed through field 'crsInduDvIncRepository': Error creating bean with name 'CRSInduDvIncRepository' defined in com.crs.renderService.repositories.CRSInduDvIncRepository defined in @EnableJpaRepositories declared on JpaRepositoriesRegistrar.EnableJpaRepositoriesConfiguration: Could not create query for public abstract com.crs.renderService.models.CRSInduDvlpInc com.crs.renderService.repositories.CRSInduDvIncRepository.findByDateAndBranchNumberAndCrsInduDvlpId(java.util.Date,java.lang.String,java.lang.String); Reason: Failed to create query for method public abstract com.crs.renderService.models.CRSInduDvlpInc com.crs.renderService.repositories.CRSInduDvIncRepository.findByDateAndBranchNumberAndCrsInduDvlpId(java.util.Date,java.lang.String,java.lang.String); No property 'crsInduDvlpId' found for type 'CRSInduDvlpInc'
        at org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor$AutowiredFieldElement.resolveFieldValue(AutowiredAnnotationBeanPostProcessor.java:787) ~[spring-beans-6.1.8.jar:6.1.8]
        at org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor$AutowiredFieldElement.inject(AutowiredAnnotationBeanPostProcessor.java:767) ~[spring-beans-6.1.8.jar:6.1.8]
        at org.springframework.beans.factory.annotation.InjectionMetadata.inject(InjectionMetadata.java:145) ~[spring-beans-6.1.8.jar:6.1.8]
        at org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor.postProcessProperties(AutowiredAnnotationBeanPostProcessor.java:508) ~[spring-beans-6.1.8.jar:6.1.8]
        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.populateBean(AbstractAutowireCapableBeanFactory.java:1421) ~[spring-beans-6.1.8.jar:6.1.8]
        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.doCreateBean(AbstractAutowireCapableBeanFactory.java:599) ~[spring-beans-6.1.8.jar:6.1.8]
        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBean(AbstractAutowireCapableBeanFactory.java:522) ~[spring-beans-6.1.8.jar:6.1.8]
        at org.springframework.beans.factory.support.AbstractBeanFactory.lambda$doGetBean$0(AbstractBeanFactory.java:337) ~[spring-beans-6.1.8.jar:6.1.8]
        at org.springframework.beans.factory.support.DefaultSingletonBeanRegistry.getSingleton(DefaultSingletonBeanRegistry.java:234) ~[spring-beans-6.1.8.jar:6.1.8]
        at org.springframework.beans.factory.support.AbstractBeanFactory.doGetBean(AbstractBeanFactory.java:335) ~[spring-beans-6.1.8.jar:6.1.8]
        at org.springframework.beans.factory.support.AbstractBeanFactory.getBean(AbstractBeanFactory.java:200) ~[spring-beans-6.1.8.jar:6.1.8]
        at org.springframework.beans.factory.config.DependencyDescriptor.resolveCandidate(DependencyDescriptor.java:254) ~[spring-beans-6.1.8.jar:6.1.8]
        at org.springframework.beans.factory.support.DefaultListableBeanFactory.doResolveDependency(DefaultListableBeanFactory.java:1443) ~[spring-beans-6.1.8.jar:6.1.8]
        at org.springframework.beans.factory.support.DefaultListableBeanFactory.resolveDependency(DefaultListableBeanFactory.java:1353) ~[spring-beans-6.1.8.jar:6.1.8]
        at org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor$AutowiredFieldElement.resolveFieldValue(AutowiredAnnotationBeanPostProcessor.java:784) ~[spring-beans-6.1.8.jar:6.1.8]
        ... 23 common frames omitted
Caused by: org.springframework.beans.factory.UnsatisfiedDependencyException: Error creating bean with name 'serviceInvoker': Unsatisfied dependency expressed through field 'rw10Service': Error creating bean with name 'RW10Service': Unsatisfied dependency expressed through field 'crsInduDvIncRepository': Error creating bean with name 'CRSInduDvIncRepository' defined in com.crs.renderService.repositories.CRSInduDvIncRepository defined in @EnableJpaRepositories declared on JpaRepositoriesRegistrar.EnableJpaRepositoriesConfiguration: Could not create query for public abstract com.crs.renderService.models.CRSInduDvlpInc com.crs.renderService.repositories.CRSInduDvIncRepository.findByDateAndBranchNumberAndCrsInduDvlpId(java.util.Date,java.lang.String,java.lang.String); Reason: Failed to create query for method public abstract com.crs.renderService.models.CRSInduDvlpInc com.crs.renderService.repositories.CRSInduDvIncRepository.findByDateAndBranchNumberAndCrsInduDvlpId(java.util.Date,java.lang.String,java.lang.String); No property 'crsInduDvlpId' found for type 'CRSInduDvlpInc'
        at org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor$AutowiredFieldElement.resolveFieldValue(AutowiredAnnotationBeanPostProcessor.java:787) ~[spring-beans-6.1.8.jar:6.1.8]
        at org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor$AutowiredFieldElement.inject(AutowiredAnnotationBeanPostProcessor.java:767) ~[spring-beans-6.1.8.jar:6.1.8]
        at org.springframework.beans.factory.annotation.InjectionMetadata.inject(InjectionMetadata.java:145) ~[spring-beans-6.1.8.jar:6.1.8]
        at org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor.postProcessProperties(AutowiredAnnotationBeanPostProcessor.java:508) ~[spring-beans-6.1.8.jar:6.1.8]
        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.populateBean(AbstractAutowireCapableBeanFactory.java:1421) ~[spring-beans-6.1.8.jar:6.1.8]
        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.doCreateBean(AbstractAutowireCapableBeanFactory.java:599) ~[spring-beans-6.1.8.jar:6.1.8]
        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBean(AbstractAutowireCapableBeanFactory.java:522) ~[spring-beans-6.1.8.jar:6.1.8]
        at org.springframework.beans.factory.support.AbstractBeanFactory.lambda$doGetBean$0(AbstractBeanFactory.java:337) ~[spring-beans-6.1.8.jar:6.1.8]
        at org.springframework.beans.factory.support.DefaultSingletonBeanRegistry.getSingleton(DefaultSingletonBeanRegistry.java:234) ~[spring-beans-6.1.8.jar:6.1.8]
        at org.springframework.beans.factory.support.AbstractBeanFactory.doGetBean(AbstractBeanFactory.java:335) ~[spring-beans-6.1.8.jar:6.1.8]
        at org.springframework.beans.factory.support.AbstractBeanFactory.getBean(AbstractBeanFactory.java:200) ~[spring-beans-6.1.8.jar:6.1.8]
        at org.springframework.beans.factory.config.DependencyDescriptor.resolveCandidate(DependencyDescriptor.java:254) ~[spring-beans-6.1.8.jar:6.1.8]
        at org.springframework.beans.factory.support.DefaultListableBeanFactory.doResolveDependency(DefaultListableBeanFactory.java:1443) ~[spring-beans-6.1.8.jar:6.1.8]
        at org.springframework.beans.factory.support.DefaultListableBeanFactory.resolveDependency(DefaultListableBeanFactory.java:1353) ~[spring-beans-6.1.8.jar:6.1.8]
        at org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor$AutowiredFieldElement.resolveFieldValue(AutowiredAnnotationBeanPostProcessor.java:784) ~[spring-beans-6.1.8.jar:6.1.8]
        ... 37 common frames omitted
Caused by: org.springframework.beans.factory.UnsatisfiedDependencyException: Error creating bean with name 'RW10Service': Unsatisfied dependency expressed through field 'crsInduDvIncRepository': Error creating bean with name 'CRSInduDvIncRepository' defined in com.crs.renderService.repositories.CRSInduDvIncRepository defined in @EnableJpaRepositories declared on JpaRepositoriesRegistrar.EnableJpaRepositoriesConfiguration: Could not create query for public abstract com.crs.renderService.models.CRSInduDvlpInc com.crs.renderService.repositories.CRSInduDvIncRepository.findByDateAndBranchNumberAndCrsInduDvlpId(java.util.Date,java.lang.String,java.lang.String); Reason: Failed to create query for method public abstract com.crs.renderService.models.CRSInduDvlpInc com.crs.renderService.repositories.CRSInduDvIncRepository.findByDateAndBranchNumberAndCrsInduDvlpId(java.util.Date,java.lang.String,java.lang.String); No property 'crsInduDvlpId' found for type 
'CRSInduDvlpInc'
        at org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor$AutowiredFieldElement.resolveFieldValue(AutowiredAnnotationBeanPostProcessor.java:787) ~[spring-beans-6.1.8.jar:6.1.8]
        at org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor$AutowiredFieldElement.inject(AutowiredAnnotationBeanPostProcessor.java:767) ~[spring-beans-6.1.8.jar:6.1.8]
        at org.springframework.beans.factory.annotation.InjectionMetadata.inject(InjectionMetadata.java:145) ~[spring-beans-6.1.8.jar:6.1.8]
        at org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor.postProcessProperties(AutowiredAnnotationBeanPostProcessor.java:508) ~[spring-beans-6.1.8.jar:6.1.8]
        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.populateBean(AbstractAutowireCapableBeanFactory.java:1421) ~[spring-beans-6.1.8.jar:6.1.8]
        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.doCreateBean(AbstractAutowireCapableBeanFactory.java:599) ~[spring-beans-6.1.8.jar:6.1.8]
        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBean(AbstractAutowireCapableBeanFactory.java:522) ~[spring-beans-6.1.8.jar:6.1.8]
        at org.springframework.beans.factory.support.AbstractBeanFactory.lambda$doGetBean$0(AbstractBeanFactory.java:337) ~[spring-beans-6.1.8.jar:6.1.8]
        at org.springframework.beans.factory.support.DefaultSingletonBeanRegistry.getSingleton(DefaultSingletonBeanRegistry.java:234) ~[spring-beans-6.1.8.jar:6.1.8]
        at org.springframework.beans.factory.support.AbstractBeanFactory.doGetBean(AbstractBeanFactory.java:335) ~[spring-beans-6.1.8.jar:6.1.8]
        at org.springframework.beans.factory.support.AbstractBeanFactory.getBean(AbstractBeanFactory.java:200) ~[spring-beans-6.1.8.jar:6.1.8]
        at org.springframework.beans.factory.config.DependencyDescriptor.resolveCandidate(DependencyDescriptor.java:254) ~[spring-beans-6.1.8.jar:6.1.8]
        at org.springframework.beans.factory.support.DefaultListableBeanFactory.doResolveDependency(DefaultListableBeanFactory.java:1443) ~[spring-beans-6.1.8.jar:6.1.8]
        at org.springframework.beans.factory.support.DefaultListableBeanFactory.resolveDependency(DefaultListableBeanFactory.java:1353) ~[spring-beans-6.1.8.jar:6.1.8]
        at org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor$AutowiredFieldElement.resolveFieldValue(AutowiredAnnotationBeanPostProcessor.java:784) ~[spring-beans-6.1.8.jar:6.1.8]
        ... 51 common frames omitted
Caused by: org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'CRSInduDvIncRepository' defined in com.crs.renderService.repositories.CRSInduDvIncRepository 
defined in @EnableJpaRepositories declared on JpaRepositoriesRegistrar.EnableJpaRepositoriesConfiguration: Could not create query for public abstract com.crs.renderService.models.CRSInduDvlpInc com.crs.renderService.repositories.CRSInduDvIncRepository.findByDateAndBranchNumberAndCrsInduDvlpId(java.util.Date,java.lang.String,java.lang.String); Reason: Failed to create query for method public abstract com.crs.renderService.models.CRSInduDvlpInc com.crs.renderService.repositories.CRSInduDvIncRepository.findByDateAndBranchNumberAndCrsInduDvlpId(java.util.Date,java.lang.String,java.lang.String); No property 'crsInduDvlpId' found for type 'CRSInduDvlpInc'
        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.initializeBean(AbstractAutowireCapableBeanFactory.java:1788) ~[spring-beans-6.1.8.jar:6.1.8]
        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.doCreateBean(AbstractAutowireCapableBeanFactory.java:600) ~[spring-beans-6.1.8.jar:6.1.8]
        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBean(AbstractAutowireCapableBeanFactory.java:522) ~[spring-beans-6.1.8.jar:6.1.8]
        at org.springframework.beans.factory.support.AbstractBeanFactory.lambda$doGetBean$0(AbstractBeanFactory.java:337) ~[spring-beans-6.1.8.jar:6.1.8]
        at org.springframework.beans.factory.support.DefaultSingletonBeanRegistry.getSingleton(DefaultSingletonBeanRegistry.java:234) ~[spring-beans-6.1.8.jar:6.1.8]
        at org.springframework.beans.factory.support.AbstractBeanFactory.doGetBean(AbstractBeanFactory.java:335) ~[spring-beans-6.1.8.jar:6.1.8]
        at org.springframework.beans.factory.support.AbstractBeanFactory.getBean(AbstractBeanFactory.java:200) ~[spring-beans-6.1.8.jar:6.1.8]
        at org.springframework.beans.factory.config.DependencyDescriptor.resolveCandidate(DependencyDescriptor.java:254) ~[spring-beans-6.1.8.jar:6.1.8]
        at org.springframework.beans.factory.support.DefaultListableBeanFactory.doResolveDependency(DefaultListableBeanFactory.java:1443) ~[spring-beans-6.1.8.jar:6.1.8]
        at org.springframework.beans.factory.support.DefaultListableBeanFactory.resolveDependency(DefaultListableBeanFactory.java:1353) ~[spring-beans-6.1.8.jar:6.1.8]
        at org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor$AutowiredFieldElement.resolveFieldValue(AutowiredAnnotationBeanPostProcessor.java:784) ~[spring-beans-6.1.8.jar:6.1.8]
        ... 65 common frames omitted
Caused by: org.springframework.data.repository.query.QueryCreationException: Could not create query for public abstract com.crs.renderService.models.CRSInduDvlpInc com.crs.renderService.repositories.CRSInduDvIncRepository.findByDateAndBranchNumberAndCrsInduDvlpId(java.util.Date,java.lang.String,java.lang.String); Reason: Failed to create query for method public abstract com.crs.renderService.models.CRSInduDvlpInc com.crs.renderService.repositories.CRSInduDvIncRepository.findByDateAndBranchNumberAndCrsInduDvlpId(java.util.Date,java.lang.String,java.lang.String); No property 'crsInduDvlpId' found for type 'CRSInduDvlpInc'
        at org.springframework.data.repository.query.QueryCreationException.create(QueryCreationException.java:101) ~[spring-data-commons-3.3.0.jar:3.3.0]
        at org.springframework.data.repository.core.support.QueryExecutorMethodInterceptor.lookupQuery(QueryExecutorMethodInterceptor.java:115) ~[spring-data-commons-3.3.0.jar:3.3.0]
        at org.springframework.data.repository.core.support.QueryExecutorMethodInterceptor.mapMethodsToQuery(QueryExecutorMethodInterceptor.java:99) ~[spring-data-commons-3.3.0.jar:3.3.0]     
        at org.springframework.data.repository.core.support.QueryExecutorMethodInterceptor.lambda$new$0(QueryExecutorMethodInterceptor.java:88) ~[spring-data-commons-3.3.0.jar:3.3.0]
        at java.base/java.util.Optional.map(Optional.java:260) ~[na:na]
        at org.springframework.data.repository.core.support.QueryExecutorMethodInterceptor.<init>(QueryExecutorMethodInterceptor.java:88) ~[spring-data-commons-3.3.0.jar:3.3.0]
        at org.springframework.data.repository.core.support.RepositoryFactorySupport.getRepository(RepositoryFactorySupport.java:357) ~[spring-data-commons-3.3.0.jar:3.3.0]
        at org.springframework.data.repository.core.support.RepositoryFactoryBeanSupport.lambda$afterPropertiesSet$5(RepositoryFactoryBeanSupport.java:286) ~[spring-data-commons-3.3.0.jar:3.3.0]
        at org.springframework.data.util.Lazy.getNullable(Lazy.java:135) ~[spring-data-commons-3.3.0.jar:3.3.0]
        at org.springframework.data.util.Lazy.get(Lazy.java:113) ~[spring-data-commons-3.3.0.jar:3.3.0]
        at org.springframework.data.repository.core.support.RepositoryFactoryBeanSupport.afterPropertiesSet(RepositoryFactoryBeanSupport.java:292) ~[spring-data-commons-3.3.0.jar:3.3.0]       
        at org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean.afterPropertiesSet(JpaRepositoryFactoryBean.java:132) ~[spring-data-jpa-3.3.0.jar:3.3.0]
        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.invokeInitMethods(AbstractAutowireCapableBeanFactory.java:1835) ~[spring-beans-6.1.8.jar:6.1.8]
        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.initializeBean(AbstractAutowireCapableBeanFactory.java:1784) ~[spring-beans-6.1.8.jar:6.1.8]
        ... 75 common frames omitted
Caused by: java.lang.IllegalArgumentException: Failed to create query for method public abstract com.crs.renderService.models.CRSInduDvlpInc com.crs.renderService.repositories.CRSInduDvIncRepository.findByDateAndBranchNumberAndCrsInduDvlpId(java.util.Date,java.lang.String,java.lang.String); No property 'crsInduDvlpId' found for type 'CRSInduDvlpInc'
        at org.springframework.data.jpa.repository.query.PartTreeJpaQuery.<init>(PartTreeJpaQuery.java:106) ~[spring-data-jpa-3.3.0.jar:3.3.0]
        at org.springframework.data.jpa.repository.query.JpaQueryLookupStrategy$CreateQueryLookupStrategy.resolveQuery(JpaQueryLookupStrategy.java:124) ~[spring-data-jpa-3.3.0.jar:3.3.0]      
        at org.springframework.data.jpa.repository.query.JpaQueryLookupStrategy$CreateIfNotFoundQueryLookupStrategy.resolveQuery(JpaQueryLookupStrategy.java:258) ~[spring-data-jpa-3.3.0.jar:3.3.0]
        at org.springframework.data.jpa.repository.query.JpaQueryLookupStrategy$AbstractQueryLookupStrategy.resolveQuery(JpaQueryLookupStrategy.java:95) ~[spring-data-jpa-3.3.0.jar:3.3.0]     
        at org.springframework.data.repository.core.support.QueryExecutorMethodInterceptor.lookupQuery(QueryExecutorMethodInterceptor.java:111) ~[spring-data-commons-3.3.0.jar:3.3.0]
        ... 87 common frames omitted
Caused by: org.springframework.data.mapping.PropertyReferenceException: No property 'crsInduDvlpId' found for type 'CRSInduDvlpInc'
        at org.springframework.data.mapping.PropertyPath.<init>(PropertyPath.java:94) ~[spring-data-commons-3.3.0.jar:3.3.0]
        at org.springframework.data.mapping.PropertyPath.create(PropertyPath.java:455) ~[spring-data-commons-3.3.0.jar:3.3.0]
        at org.springframework.data.mapping.PropertyPath.create(PropertyPath.java:431) ~[spring-data-commons-3.3.0.jar:3.3.0]
        at org.springframework.data.mapping.PropertyPath.lambda$from$0(PropertyPath.java:384) ~[spring-data-commons-3.3.0.jar:3.3.0]
        at java.base/java.util.concurrent.ConcurrentMap.computeIfAbsent(ConcurrentMap.java:330) ~[na:na]
        at org.springframework.data.mapping.PropertyPath.from(PropertyPath.java:366) ~[spring-data-commons-3.3.0.jar:3.3.0]
        at org.springframework.data.mapping.PropertyPath.from(PropertyPath.java:344) ~[spring-data-commons-3.3.0.jar:3.3.0]
        at org.springframework.data.repository.query.parser.Part.<init>(Part.java:81) ~[spring-data-commons-3.3.0.jar:3.3.0]
        at org.springframework.data.repository.query.parser.PartTree$OrPart.lambda$new$0(PartTree.java:259) ~[spring-data-commons-3.3.0.jar:3.3.0]
        at java.base/java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:212) ~[na:na]
        at java.base/java.util.stream.ReferencePipeline$2$1.accept(ReferencePipeline.java:194) ~[na:na]
        at java.base/java.util.Spliterators$ArraySpliterator.forEachRemaining(Spliterators.java:1024) ~[na:na]
        at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:556) ~[na:na]
        at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:546) ~[na:na]
        at java.base/java.util.stream.ReduceOps$ReduceOp.evaluateSequential(ReduceOps.java:921) ~[na:na]
        at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:265) ~[na:na]
        at java.base/java.util.stream.ReferencePipeline.collect(ReferencePipeline.java:702) ~[na:na]
        at org.springframework.data.repository.query.parser.PartTree$OrPart.<init>(PartTree.java:260) ~[spring-data-commons-3.3.0.jar:3.3.0]
        at org.springframework.data.repository.query.parser.PartTree$Predicate.lambda$new$0(PartTree.java:389) ~[spring-data-commons-3.3.0.jar:3.3.0]
        at java.base/java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:212) ~[na:na]
        at java.base/java.util.stream.ReferencePipeline$2$1.accept(ReferencePipeline.java:194) ~[na:na]
        at java.base/java.util.Spliterators$ArraySpliterator.forEachRemaining(Spliterators.java:1024) ~[na:na]
        at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:556) ~[na:na]
        at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:546) ~[na:na]
        at java.base/java.util.stream.ReduceOps$ReduceOp.evaluateSequential(ReduceOps.java:921) ~[na:na]
        at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:265) ~[na:na]
        at java.base/java.util.stream.ReferencePipeline.collect(ReferencePipeline.java:702) ~[na:na]
        at org.springframework.data.repository.query.parser.PartTree$Predicate.<init>(PartTree.java:390) ~[spring-data-commons-3.3.0.jar:3.3.0]
        at org.springframework.data.repository.query.parser.PartTree.<init>(PartTree.java:103) ~[spring-data-commons-3.3.0.jar:3.3.0]
        at org.springframework.data.jpa.repository.query.PartTreeJpaQuery.<init>(PartTreeJpaQuery.java:100) ~[spring-data-jpa-3.3.0.jar:3.3.0]
        ... 91 common frames omitted

PS F:\Projects\CRS Projects\CRS ReWork\backend\renderService> 
