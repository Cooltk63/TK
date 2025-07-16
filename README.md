Service Logic::
public ResponseEntity<ResponseVO<List<Map<String, Object>>>> fetchRequest(Map<String, Object> payload) {
        ResponseVO<List<Map<String, Object>>> responseVO = new ResponseVO<>();

        log.info("Inside fetching request Data");
        try {
            responseVO.setStatusCode(HttpStatus.OK.value());
            responseVO.setMessage("Success fetched Pending Request Data");
            responseVO.setResult(requestMasterListRepository.findAllByRequeststatus("1"));
        } catch (NullPointerException e) {
            responseVO.setStatusCode(HttpStatus.OK.value());
            responseVO.setMessage("Failed to fetched Active FRN Data");
            responseVO.setResult(null);

        }


       Repository code::
       @Repository
public interface RequestMasterListRepository extends JpaRepository<IAM_Request_MasterList, Integer> {
    List<Map<String, Object>> findAllByRequeststatus(String requeststatus);
}

Model Class::
Entity
@Table(name = "IAM_REQUEST_MASTER_LIST")
@Getter
@Setter
public class IAM_Request_MasterList {

    @Column(name = "UCN_NO")
    String ucnno;

    @Column(name = "FRN_NO")
    String frnno;

    @Column(name = "FIRM_NAME")
    String firmname;

    @Column(name = "PAN_NO")
    String panno;

    @Column(name = "GSTN_NO")
    String gstnno;

    @Column(name = "ADDRESS")
    String address;

    @Column(name = "CITY")
    String city;

    @Column(name = "STATE")
    String state;

    @Column(name = "DISTRICT")
    String district;

    @Column(name = "PIN_CODE")
    String pincode;

    @Column(name = "MOB_NO")
    String mobno;

    @Column(name = "CONTACT_PERSON")
    String contactperson;

    @Column(name = "POC_EMAIL")
    String pcemail;

    @Column(name = "FIRM_TYPE")
    String firmtype;

    @Column(name = "POC_DESIGNATION")
    String podesignation;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "REQUEST_ID")
    String requestid;

    @Column(name = "REQUEST_CREATED_BY")
    String requestcreatedby;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "REQUEST_CREATED_TIMESTAMP")
    Date requestcreatedtimestamp;

    @Column(name = "REQUEST_RESOLVED_BY")
    String requestresolvedby;

    @Column(name = "REQUEST_RESOLVED_TIMESTAMP")
    Date requestresolvedtimestamp;

    @Column(name = "REQUEST_STATUS")
    String requeststatus;

    @Column(name = "REQUEST_TYPE")
    String requesttype;

    @Column(name = "REQUEST_EMPANELMENT_TYPE")
    String requestempanelmenttype;

    @Column(name = "REQUEST_EMPANELMENT_SUB_TYPE")
    String requestempanelmentsubtype;

    Error getting :
    Application run failed
org.springframework.beans.factory.UnsatisfiedDependencyException: Error creating bean with name 'empanelmentController' defined in file [F:\Projects\CRS Projects\CRS_IAM\backend\IAMService\target\classes\com\crs\iamservice\Controller\EmpanelmentController.class]: Unsatisfied dependency expressed through constructor parameter 0: Error creating bean with name 'empanelmentServiceImpl' defined in file [F:\Projects\CRS Projects\CRS_IAM\backend\IAMService\target\classes\com\crs\iamservice\Service\EmpanelmentServiceImpl.class]: Unsatisfied dependency expressed through constructor parameter 3: Error creating bean with name 'RequestService': Unsatisfied dependency expressed through field 'requestMasterListRepository': Error creating bean with name 'requestMasterListRepository' defined in com.crs.iamservice.Repository.RequestMasterListRepository defined in @EnableJpaRepositories declared on JpaRepositoriesRegistrar.EnableJpaRepositoriesConfiguration: Could not create query for public abstract java.util.List com.crs.iamservice.Repository.RequestMasterListRepository.findAllByRequeststatus(java.lang.String); Reason: Failed to create query for method public abstract java.util.List com.crs.iamservice.Repository.RequestMasterListRepository.findAllByRequeststatus(java.lang.String); No property 'empty' found for type 'IAM_Request_MasterList'
	at org.springframework.beans.factory.support.ConstructorResolver.createArgumentArray(ConstructorResolver.java:795)
	at org.springframework.beans.factory.support.ConstructorResolver.autowireConstructor(ConstructorResolver.java:237)
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.autowireConstructor(AbstractAutowireCapableBeanFactory.java:1357)
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBeanInstance(AbstractAutowireCapableBeanFactory.java:1194)
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.doCreateBean(AbstractAutowireCapableBeanFactory.java:562)
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBean(AbstractAutowireCapableBeanFactory.java:522)
	at org.springframework.beans.factory.support.AbstractBeanFactory.lambda$doGetBean$0(AbstractBeanFactory.java:337)
	at org.springframework.beans.factory.support.DefaultSingletonBeanRegistry.getSingleton(DefaultSingletonBeanRegistry.java:234)
	at org.springframework.beans.factory.support.AbstractBeanFactory.doGetBean(AbstractBeanFactory.java:335)
	at org.springframework.beans.factory.support.AbstractBeanFactory.getBean(AbstractBeanFactory.java:200)
	at org.springframework.beans.factory.support.DefaultListableBeanFactory.preInstantiateSingletons(DefaultListableBeanFactory.java:975)
	at org.springframework.context.support.AbstractApplicationContext.finishBeanFactoryInitialization(AbstractApplicationContext.java:962)
	at org.springframework.context.support.AbstractApplicationContext.refresh(AbstractApplicationContext.java:624)
	at org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext.refresh(ServletWebServerApplicationContext.java:146)
	at org.springframework.boot.SpringApplication.refresh(SpringApplication.java:754)
	at org.springframework.boot.SpringApplication.refreshContext(SpringApplication.java:456)
	at org.springframework.boot.SpringApplication.run(SpringApplication.java:335)
	at org.springframework.boot.SpringApplication.run(SpringApplication.java:1363)
	at org.springframework.boot.SpringApplication.run(SpringApplication.java:1352)
	at com.crs.iamservice.IamServiceApplication.main(IamServiceApplication.java:10)
	at java.base/jdk.internal.reflect.DirectMethodHandleAccessor.invoke(DirectMethodHandleAccessor.java:103)
	at java.base/java.lang.reflect.Method.invoke(Method.java:580)
	at org.springframework.boot.devtools.restart.RestartLauncher.run(RestartLauncher.java:50)
Caused by: org.springframework.beans.factory.UnsatisfiedDependencyException: Error creating bean with name 'empanelmentServiceImpl' defined in file [F:\Projects\CRS Projects\CRS_IAM\backend\IAMService\target\classes\com\crs\iamservice\Service\EmpanelmentServiceImpl.class]: Unsatisfied dependency expressed through constructor parameter 3: Error creating bean with name 'RequestService': Unsatisfied dependency expressed through field 'requestMasterListRepository': Error creating bean with name 'requestMasterListRepository' defined in com.crs.iamservice.Repository.RequestMasterListRepository defined in @EnableJpaRepositories declared on JpaRepositoriesRegistrar.EnableJpaRepositoriesConfiguration: Could not create query for public abstract java.util.List com.crs.iamservice.Repository.RequestMasterListRepository.findAllByRequeststatus(java.lang.String); Reason: Failed to create query for method public abstract java.util.List com.crs.iamservice.Repository.RequestMasterListRepository.findAllByRequeststatus(java.lang.String); No property 'empty' found for type 'IAM_Request_MasterList'
	at org.springframework.beans.factory.support.ConstructorResolver.createArgumentArray(ConstructorResolver.java:795)
	at org.springframework.beans.factory.support.ConstructorResolver.autowireConstructor(ConstructorResolver.java:237)
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.autowireConstructor(AbstractAutowireCapableBeanFactory.java:1357)
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBeanInstance(AbstractAutowireCapableBeanFactory.java:1194)
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.doCreateBean(AbstractAutowireCapableBeanFactory.java:562)
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBean(AbstractAutowireCapableBeanFactory.java:522)
	at org.springframework.beans.factory.support.AbstractBeanFactory.lambda$doGetBean$0(AbstractBeanFactory.java:337)
	at org.springframework.beans.factory.support.DefaultSingletonBeanRegistry.getSingleton(DefaultSingletonBeanRegistry.java:234)
	at org.springframework.beans.factory.support.AbstractBeanFactory.doGetBean(AbstractBeanFactory.java:335)
	at org.springframework.beans.factory.support.AbstractBeanFactory.getBean(AbstractBeanFactory.java:200)
	at org.springframework.beans.factory.config.DependencyDescriptor.resolveCandidate(DependencyDescriptor.java:254)
	at org.springframework.beans.factory.support.DefaultListableBeanFactory.doResolveDependency(DefaultListableBeanFactory.java:1443)
	at org.springframework.beans.factory.support.DefaultListableBeanFactory.resolveDependency(DefaultListableBeanFactory.java:1353)
	at org.springframework.beans.factory.support.ConstructorResolver.resolveAutowiredArgument(ConstructorResolver.java:904)
	at org.springframework.beans.factory.support.ConstructorResolver.createArgumentArray(ConstructorResolver.java:782)
	... 22 common frames omitted
Caused by: org.springframework.beans.factory.UnsatisfiedDependencyException: Error creating bean with name 'RequestService': Unsatisfied dependency expressed through field 'requestMasterListRepository': Error creating bean with name 'requestMasterListRepository' defined in com.crs.iamservice.Repository.RequestMasterListRepository defined in @EnableJpaRepositories declared on JpaRepositoriesRegistrar.EnableJpaRepositoriesConfiguration: Could not create query for public abstract java.util.List com.crs.iamservice.Repository.RequestMasterListRepository.findAllByRequeststatus(java.lang.String); Reason: Failed to create query for method public abstract java.util.List com.crs.iamservice.Repository.RequestMasterListRepository.findAllByRequeststatus(java.lang.String); No property 'empty' found for type 'IAM_Request_MasterList'
	at org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor$AutowiredFieldElement.resolveFieldValue(AutowiredAnnotationBeanPostProcessor.java:787)
	at org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor$AutowiredFieldElement.inject(AutowiredAnnotationBeanPostProcessor.java:767)
	at org.springframework.beans.factory.annotation.InjectionMetadata.inject(InjectionMetadata.java:145)
	at org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor.postProcessProperties(AutowiredAnnotationBeanPostProcessor.java:508)
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.populateBean(AbstractAutowireCapableBeanFactory.java:1421)
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.doCreateBean(AbstractAutowireCapableBeanFactory.java:599)
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBean(AbstractAutowireCapableBeanFactory.java:522)
	at org.springframework.beans.factory.support.AbstractBeanFactory.lambda$doGetBean$0(AbstractBeanFactory.java:337)
	at org.springframework.beans.factory.support.DefaultSingletonBeanRegistry.getSingleton(DefaultSingletonBeanRegistry.java:234)
	at org.springframework.beans.factory.support.AbstractBeanFactory.doGetBean(AbstractBeanFactory.java:335)
	at org.springframework.beans.factory.support.AbstractBeanFactory.getBean(AbstractBeanFactory.java:200)
	at org.springframework.beans.factory.config.DependencyDescriptor.resolveCandidate(DependencyDescriptor.java:254)
	at org.springframework.beans.factory.support.DefaultListableBeanFactory.doResolveDependency(DefaultListableBeanFactory.java:1443)
	at org.springframework.beans.factory.support.DefaultListableBeanFactory.resolveDependency(DefaultListableBeanFactory.java:1353)
	at org.springframework.beans.factory.support.ConstructorResolver.resolveAutowiredArgument(ConstructorResolver.java:904)
	at org.springframework.beans.factory.support.ConstructorResolver.createArgumentArray(ConstructorResolver.java:782)
	... 36 common frames omitted
Caused by: org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'requestMasterListRepository' defined in com.crs.iamservice.Repository.RequestMasterListRepository defined in @EnableJpaRepositories declared on JpaRepositoriesRegistrar.EnableJpaRepositoriesConfiguration: Could not create query for public abstract java.util.List com.crs.iamservice.Repository.RequestMasterListRepository.findAllByRequeststatus(java.lang.String); Reason: Failed to create query for method public abstract java.util.List com.crs.iamservice.Repository.RequestMasterListRepository.findAllByRequeststatus(java.lang.String); No property 'empty' found for type 'IAM_Request_MasterList'
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.initializeBean(AbstractAutowireCapableBeanFactory.java:1788)
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.doCreateBean(AbstractAutowireCapableBeanFactory.java:600)
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBean(AbstractAutowireCapableBeanFactory.java:522)
	at org.springframework.beans.factory.support.AbstractBeanFactory.lambda$doGetBean$0(AbstractBeanFactory.java:337)
	at org.springframework.beans.factory.support.DefaultSingletonBeanRegistry.getSingleton(DefaultSingletonBeanRegistry.java:234)
	at org.springframework.beans.factory.support.AbstractBeanFactory.doGetBean(AbstractBeanFactory.java:335)
	at org.springframework.beans.factory.support.AbstractBeanFactory.getBean(AbstractBeanFactory.java:200)
	at org.springframework.beans.factory.config.DependencyDescriptor.resolveCandidate(DependencyDescriptor.java:254)
	at org.springframework.beans.factory.support.DefaultListableBeanFactory.doResolveDependency(DefaultListableBeanFactory.java:1443)
	at org.springframework.beans.factory.support.DefaultListableBeanFactory.resolveDependency(DefaultListableBeanFactory.java:1353)
	at org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor$AutowiredFieldElement.resolveFieldValue(AutowiredAnnotationBeanPostProcessor.java:784)


 How to Reoslve the error 
