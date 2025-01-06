package com.crs.renderService.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;



@Getter
@Setter
@Entity
@IdClass(CRSInduDvlpIncExtended.class)
@Table(name = "CRS_INDU_DVLP_INC")
public class CRSInduDvlpInc {

    
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SR_NO")
    public String srNo;

    @Id
    @Column(name = "CRS_INDU_DVLP_BRNO", length = 5)
    public String branchcode;

    @Id
    @Column(name = "CRS_INDU_DVLP_DATE")
    // @Temporal(TemporalType.DATE)
    public Date date;

    @Column(name = "CRS_INDU_DVLP_OTHER", length = 30)
    public String other;

    @Column(name = "CRS_INDU_DVLP_PROCFEE", length = 30)
    public String procfee;

    @Column(name = "CRS_INDU_DVLP_TOTAL", length = 30)
    public String total;

    @Column(name = "CRS_INDU_DVLP_TOTAL_ADVANCES", length = 30)
    public String totaladvances;

    @Column(name = "REPORT_MASTER_LIST_ID_FK", length = 10)
    public String submissionid;
}


This above provided model class and  below is ist ID Class

package com.crs.renderService.models;

import java.io.Serializable;
import java.util.Date;


public class CRSInduDvlpIncExtended implements Serializable {
    
    public String branchcode;

    public Date date;
}

This is below repository interface as per below

package com.crs.renderService.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.crs.renderService.models.CRSInduDvlpInc;
import com.crs.renderService.models.CRSInduDvlpIncExtended;

@Repository
public interface CRSInduDvIncRepository extends JpaRepository<CRSInduDvlpInc,CRSInduDvlpIncExtended> {

   
    @Query(nativeQuery = true,value = "select rm.branch_code ,rm.REPORT_DATE,ax.CRS_INDU_DVLP_PROCFEE,ax.CRS_INDU_DVLP_OTHER,ax.CRS_INDU_DVLP_TOTAL,ax.CRS_INDU_DVLP_TOTAL_ADVANCES " + //
                " from report_submission rm , CRS_INDU_DVLP_INC ax " +
                " where rm.SUBMISSION_ID=ax.REPORT_MASTER_LIST_ID_FK and rm.SUBMISSION_ID=:submission_id")
    public List<String>getInduaData(@Param("submission_id")String submission_id);
    
    CRSInduDvlpInc findBydateAndbranchcode(Date date, String branchNumber /*, String crsInduDvlpId */);
    
    
}



This is the error i am getting

ce.models.CrsEduIntSubcdEwsId
2025-01-06T18:15:55.256+05:30  WARN 32964 --- [renderService] [  restartedMain] org.hibernate.mapping.RootClass          : HHH000039: Composite-id class does not override hashCode(): com.crs.renderService.models.CrsEduIntSubcdEwsId
2025-01-06T18:15:55.257+05:30  WARN 32964 --- [renderService] [  restartedMain] org.hibernate.mapping.RootClass          : HHH000038: Composite-id class does not override equals(): com.crs.renderService.models.CRSHousDvlpIncExtended
2025-01-06T18:15:55.258+05:30  WARN 32964 --- [renderService] [  restartedMain] org.hibernate.mapping.RootClass          : HHH000039: Composite-id class does not override hashCode(): com.crs.renderService.models.CRSHousDvlpIncExtended
2025-01-06T18:15:55.260+05:30  WARN 32964 --- [renderService] [  restartedMain] org.hibernate.mapping.RootClass          : HHH000038: Composite-id class does not override equals(): com.crs.renderService.models.CrsAddIntSubventionId
2025-01-06T18:15:55.261+05:30  WARN 32964 --- [renderService] [  restartedMain] org.hibernate.mapping.RootClass          : HHH000039: Composite-id class does not override hashCode(): com.crs.renderService.models.CrsAddIntSubventionId
2025-01-06T18:15:55.262+05:30  WARN 32964 --- [renderService] [  restartedMain] org.hibernate.mapping.RootClass          : HHH000038: Composite-id class does not override equals(): com.crs.renderService.models.CrsWoshgId
2025-01-06T18:15:55.263+05:30  WARN 32964 --- [renderService] [  restartedMain] org.hibernate.mapping.RootClass          : HHH000039: Composite-id class does not override hashCode(): com.crs.renderService.models.CrsWoshgId
2025-01-06T18:15:55.264+05:30  WARN 32964 --- [renderService] [  restartedMain] org.hibernate.mapping.RootClass          : HHH000038: Composite-id class does not override equals(): com.crs.renderService.models.CRSAgrDvlpIncExtended
2025-01-06T18:15:55.265+05:30  WARN 32964 --- [renderService] [  restartedMain] org.hibernate.mapping.RootClass          : HHH000039: Composite-id class does not override hashCode(): com.crs.renderService.models.CRSAgrDvlpIncExtended
2025-01-06T18:15:55.266+05:30  WARN 32964 --- [renderService] [  restartedMain] org.hibernate.mapping.RootClass          : HHH000038: Composite-id class does not override equals(): com.crs.renderService.models.CRSInduDvlpIncExtended
2025-01-06T18:15:55.267+05:30  WARN 32964 --- [renderService] [  restartedMain] org.hibernate.mapping.RootClass          : HHH000039: Composite-id class does not override hashCode(): com.crs.renderService.models.CRSInduDvlpIncExtended
2025-01-06T18:15:55.269+05:30  WARN 32964 --- [renderService] [  restartedMain] org.hibernate.mapping.RootClass          : HHH000038: Composite-id class does not override equals(): com.crs.renderService.models.CRSInfraDvlpIncExtended
2025-01-06T18:15:55.270+05:30  WARN 32964 --- [renderService] [  restartedMain] org.hibernate.mapping.RootClass          : HHH000039: Composite-id class does not override hashCode(): com.crs.renderService.models.CRSInfraDvlpIncExtended
2025-01-06T18:15:55.271+05:30  WARN 32964 --- [renderService] [  restartedMain] org.hibernate.mapping.RootClass          : HHH000038: Composite-id class does not override equals(): com.crs.renderService.models.CrsStCreditId
2025-01-06T18:15:55.272+05:30  WARN 32964 --- [renderService] [  restartedMain] org.hibernate.mapping.RootClass          : HHH000039: Composite-id class does not override hashCode(): com.crs.renderService.models.CrsStCreditId
2025-01-06T18:15:57.351+05:30  INFO 32964 --- [renderService] [  restartedMain] o.h.e.t.j.p.i.JtaPlatformInitiator       : HHH000489: No JTA platform available (set 'hibernate.transaction.jta.platform' to enable JTA platform integration)
2025-01-06T18:15:57.360+05:30  INFO 32964 --- [renderService] [  restartedMain] j.LocalContainerEntityManagerFactoryBean : Initialized JPA EntityManagerFactory for persistence unit 'default'
2025-01-06T18:15:58.225+05:30  INFO 32964 --- [renderService] [  restartedMain] o.s.d.j.r.query.QueryEnhancerFactory     : Hibernate is in classpath; If applicable, HQL parser will be used.
2025-01-06T18:15:58.968+05:30  WARN 32964 --- [renderService] [  restartedMain] ConfigServletWebServerApplicationContext : Exception encountered during context initialization - cancelling refresh attempt: org.springframework.beans.factory.UnsatisfiedDependencyException: Error creating bean with name 'renderController': Unsatisfied dependency expressed through field 'renderService': Error creating 
bean with name 'RenderService': Unsatisfied dependency expressed through field 'serviceInvoker': Error creating bean with name 'serviceInvoker': Unsatisfied dependency expressed through field 'rw10Service': Error creating bean with name 'RW10Service': Unsatisfied dependency expressed through field 'crsInduDvIncRepository': Error creating bean with name 'CRSInduDvIncRepository' defined in com.crs.renderService.repositories.CRSInduDvIncRepository defined in @EnableJpaRepositories declared on JpaRepositoriesRegistrar.EnableJpaRepositoriesConfiguration: Could not create query for public abstract com.crs.renderService.models.CRSInduDvlpInc com.crs.renderService.repositories.CRSInduDvIncRepository.findBydateAndbranchcode(java.util.Date,java.lang.String); Reason: Failed to create query for method public abstract com.crs.renderService.models.CRSInduDvlpInc com.crs.renderService.repositories.CRSInduDvIncRepository.findBydateAndbranchcode(java.util.Date,java.lang.String); No property 'andbranchcode' found for type 'Date'; Traversed path: CRSInduDvlpInc.date
2025-01-06T18:15:58.973+05:30  INFO 32964 --- [renderService] [  restartedMain] j.LocalContainerEntityManagerFactoryBean : Closing JPA EntityManagerFactory for persistence unit 'default'
2025-01-06T18:15:58.983+05:30  INFO 32964 --- [renderService] [  restartedMain] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Shutdown initiated...
2025-01-06T18:15:59.067+05:30  INFO 32964 --- [renderService] [  restartedMain] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Shutdown completed.
2025-01-06T18:15:59.074+05:30  INFO 32964 --- [renderService] [  restartedMain] o.apache.catalina.core.StandardService   : Stopping service [Tomcat]
2025-01-06T18:15:59.122+05:30  INFO 32964 --- [renderService] [  restartedMain] .s.b.a.l.ConditionEvaluationReportLogger : 

Error starting ApplicationContext. To display the condition evaluation report re-run your application with 'debug' enabled.
2025-01-06T18:15:59.239+05:30 ERROR 32964 --- [renderService] [  restartedMain] o.s.boot.SpringApplication               : Application run failed

org.springframework.beans.factory.UnsatisfiedDependencyException: Error creating bean with name 'renderController': Unsatisfied dependency expressed through field 'renderService': Error creating bean 
with name 'RenderService': Unsatisfied dependency expressed through field 'serviceInvoker': Error creating bean with name 'serviceInvoker': Unsatisfied dependency expressed through field 'rw10Service': Error creating bean with name 'RW10Service': Unsatisfied dependency expressed through field 'crsInduDvIncRepository': Error creating bean with name 'CRSInduDvIncRepository' defined in com.crs.renderService.repositories.CRSInduDvIncRepository defined in @EnableJpaRepositories declared on JpaRepositoriesRegistrar.EnableJpaRepositoriesConfiguration: Could not create query for public abstract com.crs.renderService.models.CRSInduDvlpInc com.crs.renderService.repositories.CRSInduDvIncRepository.findBydateAndbranchcode(java.util.Date,java.lang.String); Reason: Failed to create query for method public abstract com.crs.renderService.models.CRSInduDvlpInc com.crs.renderService.repositories.CRSInduDvIncRepository.findBydateAndbranchcode(java.util.Date,java.lang.String); No property 'andbranchcode' 
found for type 'Date'; Traversed path: CRSInduDvlpInc.date
        at org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor$AutowiredFieldElement.resolveFieldValue(AutowiredAnnotationBeanPostProcessor.java:787) ~[spring-beans-6.1.8.jar:6.1.8]
        at org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor$AutowiredFieldElement.inject(AutowiredAnnotationBeanPostProcessor.java:767) ~[spring-beans-6.1.8.jar:6.1.8]        at org.springframework.beans.factory.annotation.InjectionMetadata.inject(InjectionMetadata.java:145) ~[spring-beans-6.1.8.jar:6.1.8]
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
Caused by: org.springframework.beans.factory.UnsatisfiedDependencyException: Error creating bean with name 'RenderService': Unsatisfied dependency expressed through field 'serviceInvoker': Error creating bean with name 'serviceInvoker': Unsatisfied dependency expressed through field 'rw10Service': Error creating bean with name 'RW10Service': Unsatisfied dependency expressed through field 'crsInduDvIncRepository': Error creating bean with name 'CRSInduDvIncRepository' defined in com.crs.renderService.repositories.CRSInduDvIncRepository defined in @EnableJpaRepositories declared on JpaRepositoriesRegistrar.EnableJpaRepositoriesConfiguration: Could not create query for public abstract com.crs.renderService.models.CRSInduDvlpInc com.crs.renderService.repositories.CRSInduDvIncRepository.findBydateAndbranchcode(java.util.Date,java.lang.String); Reason: Failed to create query for method public abstract com.crs.renderService.models.CRSInduDvlpInc com.crs.renderService.repositories.CRSInduDvIncRepository.findBydateAndbranchcode(java.util.Date,java.lang.String); No property 'andbranchcode' found for type 'Date'; Traversed path: CRSInduDvlpInc.date
        at org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor$AutowiredFieldElement.resolveFieldValue(AutowiredAnnotationBeanPostProcessor.java:787) ~[spring-beans-6.1.8.jar:6.1.8]
        at org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor$AutowiredFieldElement.inject(AutowiredAnnotationBeanPostProcessor.java:767) ~[spring-beans-6.1.8.jar:6.1.8]        at org.springframework.beans.factory.annotation.InjectionMetadata.inject(InjectionMetadata.java:145) ~[spring-beans-6.1.8.jar:6.1.8]
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
Caused by: org.springframework.beans.factory.UnsatisfiedDependencyException: Error creating bean with name 'serviceInvoker': Unsatisfied dependency expressed through field 'rw10Service': Error creating bean with name 'RW10Service': Unsatisfied dependency expressed through field 'crsInduDvIncRepository': Error creating bean with name 'CRSInduDvIncRepository' defined in com.crs.renderService.repositories.CRSInduDvIncRepository defined in @EnableJpaRepositories declared on JpaRepositoriesRegistrar.EnableJpaRepositoriesConfiguration: Could not create query for public abstract com.crs.renderService.models.CRSInduDvlpInc com.crs.renderService.repositories.CRSInduDvIncRepository.findBydateAndbranchcode(java.util.Date,java.lang.String); Reason: Failed to create query for method public abstract com.crs.renderService.models.CRSInduDvlpInc com.crs.renderService.repositories.CRSInduDvIncRepository.findBydateAndbranchcode(java.util.Date,java.lang.String); No property 'andbranchcode' found for type 
'Date'; Traversed path: CRSInduDvlpInc.date
        at org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor$AutowiredFieldElement.resolveFieldValue(AutowiredAnnotationBeanPostProcessor.java:787) ~[spring-beans-6.1.8.jar:6.1.8]
        at org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor$AutowiredFieldElement.inject(AutowiredAnnotationBeanPostProcessor.java:767) ~[spring-beans-6.1.8.jar:6.1.8]        at org.springframework.beans.factory.annotation.InjectionMetadata.inject(InjectionMetadata.java:145) ~[spring-beans-6.1.8.jar:6.1.8]
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
Caused by: org.springframework.beans.factory.UnsatisfiedDependencyException: Error creating bean with name 'RW10Service': Unsatisfied dependency expressed through field 'crsInduDvIncRepository': Error creating bean with name 'CRSInduDvIncRepository' defined in com.crs.renderService.repositories.CRSInduDvIncRepository defined in @EnableJpaRepositories declared on JpaRepositoriesRegistrar.EnableJpaRepositoriesConfiguration: Could not create query for public abstract com.crs.renderService.models.CRSInduDvlpInc com.crs.renderService.repositories.CRSInduDvIncRepository.findBydateAndbranchcode(java.util.Date,java.lang.String); Reason: Failed to create query for method public abstract com.crs.renderService.models.CRSInduDvlpInc com.crs.renderService.repositories.CRSInduDvIncRepository.findBydateAndbranchcode(java.util.Date,java.lang.String); No property 'andbranchcode' found for type 'Date'; Traversed path: CRSInduDvlpInc.date
        at org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor$AutowiredFieldElement.resolveFieldValue(AutowiredAnnotationBeanPostProcessor.java:787) ~[spring-beans-6.1.8.jar:6.1.8]
