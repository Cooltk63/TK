
org.springframework.beans.factory.UnsatisfiedDependencyException: Error creating bean with name 'RW38Controller': Unsatisfied dependency expressed through field 'rw38Service': Error creating bean with name 'RW38Service': Unsatisfied dependency expressed through field 'crsHedgeInvRepository': Error creating bean with name 'CRS_HedgeInvRepository' defined in com.crs.commonReportsService.repositories.CRS_HedgeInvRepository defined in @EnableJpaRepositories declared on JpaRepositoriesRegistrar.EnableJpaRepositoriesConfiguration: Could not create query for public abstract com.crs.commonReportsService.models.CRS_HedgeInv com.crs.commonReportsService.repositories.CRS_HedgeInvRepository.findByHedgeinv_branchAndHedgeinv_date(java.lang.String,java.lang.String); Reason: Failed to create query for method public abstract com.crs.commonReportsService.models.CRS_HedgeInv com.crs.commonReportsService.repositories.CRS_HedgeInvRepository.findByHedgeinv_branchAndHedgeinv_date(java.lang.String,java.lang.String); No property 'hedgeinv' found for type 'CRS_HedgeInv'; Did you mean 'hedgeinvId'
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
	at com.crs.commonReportsService.commonReportsService.main(commonReportsService.java:12) ~[classes/:na]
	at java.base/jdk.internal.reflect.DirectMethodHandleAccessor.invoke(DirectMethodHandleAccessor.java:103) ~[na:na]
	at java.base/java.lang.reflect.Method.invoke(Method.java:580) ~[na:na]
	at org.springframework.boot.devtools.restart.RestartLauncher.run(RestartLauncher.java:50) ~[spring-boot-devtools-3.3.0.jar:3.3.0]
Caused by: org.springframework.beans.factory.UnsatisfiedDependencyException: Error creating bean with name 'RW38Service': Unsatisfied dependency expressed through field 'crsHedgeInvRepository': Error creating bean with name 'CRS_HedgeInvRepository' defined in com.crs.commonReportsService.repositories.CRS_HedgeInvRepository defined in @EnableJpaRepositories declared on JpaRepositoriesRegistrar.EnableJpaRepositoriesConfiguration: Could not create query for public abstract com.crs.commonReportsService.models.CRS_HedgeInv com.crs.commonReportsService.repositories.CRS_HedgeInvRepository.findByHedgeinv_branchAndHedgeinv_date(java.lang.String,java.lang.String); Reason: Failed to create query for method public abstract com.crs.commonReportsService.models.CRS_HedgeInv com.crs.commonReportsService.repositories.CRS_HedgeInvRepository.findByHedgeinv_branchAndHedgeinv_date(java.lang.String,java.lang.String); No property 'hedgeinv' found for type 'CRS_HedgeInv'; Did you mean 'hedgeinvId'
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
Caused by: org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'CRS_HedgeInvRepository' defined in com.crs.commonReportsService.repositories.CRS_HedgeInvRepository defined in @EnableJpaRepositories declared on JpaRepositoriesRegistrar.EnableJpaRepositoriesConfiguration: Could not create query for public abstract com.crs.commonReportsService.models.CRS_HedgeInv com.crs.commonReportsService.repositories.CRS_HedgeInvRepository.findByHedgeinv_branchAndHedgeinv_date(java.lang.String,java.lang.String); Reason: Failed to create query for method public abstract com.crs.commonReportsService.models.CRS_HedgeInv com.crs.commonReportsService.repositories.CRS_HedgeInvRepository.findByHedgeinv_branchAndHedgeinv_date(java.lang.String,java.lang.String); No property 'hedgeinv' found for type 'CRS_HedgeInv'; Did you mean 'hedgeinvId'
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
	... 37 common frames omitted
Caused by: org.springframework.data.repository.query.QueryCreationException: Could not create query for public abstract com.crs.commonReportsService.models.CRS_HedgeInv com.crs.commonReportsService.repositories.CRS_HedgeInvRepository.findByHedgeinv_branchAndHedgeinv_date(java.lang.String,java.lang.String); Reason: Failed to create query for method public abstract com.crs.commonReportsService.models.CRS_HedgeInv com.crs.commonReportsService.repositories.CRS_HedgeInvRepository.findByHedgeinv_branchAndHedgeinv_date(java.lang.String,java.lang.String); No property 'hedgeinv' found for type 'CRS_HedgeInv'; Did you mean 'hedgeinvId'
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
	... 47 common frames omitted
Caused by: java.lang.IllegalArgumentException: Failed to create query for method public abstract com.crs.commonReportsService.models.CRS_HedgeInv com.crs.commonReportsService.repositories.CRS_HedgeInvRepository.findByHedgeinv_branchAndHedgeinv_date(java.lang.String,java.lang.String); No property 'hedgeinv' found for type 'CRS_HedgeInv'; Did you mean 'hedgeinvId'
	at org.springframework.data.jpa.repository.query.PartTreeJpaQuery.<init>(PartTreeJpaQuery.java:106) ~[spring-data-jpa-3.3.0.jar:3.3.0]
	at org.springframework.data.jpa.repository.query.JpaQueryLookupStrategy$CreateQueryLookupStrategy.resolveQuery(JpaQueryLookupStrategy.java:124) ~[spring-data-jpa-3.3.0.jar:3.3.0]
	at org.springframework.data.jpa.repository.query.JpaQueryLookupStrategy$CreateIfNotFoundQueryLookupStrategy.resolveQuery(JpaQueryLookupStrategy.java:258) ~[spring-data-jpa-3.3.0.jar:3.3.0]
	at org.springframework.data.jpa.repository.query.JpaQueryLookupStrategy$AbstractQueryLookupStrategy.resolveQuery(JpaQueryLookupStrategy.java:95) ~[spring-data-jpa-3.3.0.jar:3.3.0]
	at org.springframework.data.repository.core.support.QueryExecutorMethodInterceptor.lookupQuery(QueryExecutorMethodInterceptor.java:111) ~[spring-data-commons-3.3.0.jar:3.3.0]
	... 59 common frames omitted
Caused by: org.springframework.data.mapping.PropertyReferenceException: No property 'hedgeinv' found for type 'CRS_HedgeInv'; Did you mean 'hedgeinvId'
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
	... 63 common frames omitted


Process finished with exit code 0

Getting this error as per above  


This is my Repository file code 

package com.crs.commonReportsService.repositories;

import com.crs.commonReportsService.models.CRS_HedgeInv;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/*Author: V1012297
 * Description: Report RW-38 Repository */

@Repository
public interface CRS_HedgeInvRepository extends JpaRepository<CRS_HedgeInv,Integer> {

  @Query(
            nativeQuery = true,
            value ="select " +
                    "TO_CHAR(ch.HEDGEINV_INVDATE, 'YYYY-MM-DD') As HEDGEINV_INVDATE, " +
                    "ch.HEDGEINV_PAN, " +
                    "ch.HEDGEINV_CUSTOMER, " +
                    "ch.HEDGEINV_AMOUNT ," +
                    "ch.HEDGEINV_ID AS RWOSEQ," +
                    "ch.HEDGEINV_ID," +
                    "'false'" +
                    "from CRS_HEDGEINV ch " +
                    "where ch.REPORT_MASTER_LIST_ID_FK=:SUBMISSION_ID " +
                    "order by ch.HEDGEINV_ID"
    )
    List<List<String>> getHedgeInvData(@Param("SUBMISSION_ID") String SUBMISSION_ID);

    int countByhedgeinvId(int hedgeId);

    int deleteByhedgeinvId(int hedgeId);

    boolean existsByhedgeinvId(int hedgeId);

  CRS_HedgeInv findByHedgeinv_branchAndHedgeinv_date(String branch, String date);
}


