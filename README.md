This is docker File 

# Use Red Hat UBI with OpenJDK 17 (if accessible internally)
FROM cimg/openjdk:24.0.2-node

# Set working directory inside container
WORKDIR /app

ARG JAR_FILE=target/*.jar
# Set environment variable (optional)
ENV SPRING_PROFILES_ACTIVE=dev

# Copy JAR file into the container
COPY ${JAR_FILE} app.jar

EXPOSE 8089

# Run your Spring Boot app
CMD ["java", "-jar", "app.jar"]

##ENTRYPOINT ["top", "-b"]


This is my Controller ::
package com.example.Fincore.testController;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/service")
public class ServiceController {

    @Value("${product.service.url}")
    private String productServiceUrl;



    @PostMapping("/PostMapData")
    public String PostMappingtest(){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>("SAMPLE TEST PARAM PASSING ", headers);

       String data=new RestTemplate().postForObject(productServiceUrl+"/product/getProductOne",request, String.class);

        return data.toString();
    }

    //Sending List<String> Data to Another Service & Received List<Map<String, Objects>> Data as Response from Service
    @PostMapping("/GetMapData")
    public String GetMappingtest(){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        List<String> ListData = new ArrayList<>();
        ListData.add("1");
        ListData.add("2");
        ListData.add("3");
        HttpEntity<List<String>> request = new HttpEntity<>(ListData, headers);

        List<Map<String, Objects>> data=new RestTemplate().postForObject(productServiceUrl+"/product/getProductTwo",request, List.class);

        return data.toString();
    }


    @PostMapping("/getProductData")
    public String CallProductService(@RequestBody String data){

        return "Received the Data from Product Service"+data;
    }
}

My application.properties files as per below ::

# ========== Application Configuration ==========
spring.application.name=fin-service
# Unique service name in Kubernetes

# ========== Server Configuration ==========
# Local port application will run on
server.port=8089


# Recommended Oracle JDBC driver class
spring.datasource.driver-class-name=oracle.jdbc.OracleDriver


# ========== Kubernetes Discovery Settings ==========
# Enable discovery
spring.cloud.kubernetes.discovery.enabled=true
# Limit to same namespace
spring.cloud.kubernetes.discovery.all-namespaces=false
spring.cloud.kubernetes.discovery.service-labels[app]=fin-service

# ========== Logging ==========
# Console log pattern (Color-coded output)
logging.pattern.console=%d{yyyy-MM-dd :: HH:mm:ss.SSS ||} %highlight(%-5level:: %file: | %line |){ERROR=bold red, WARN=yellow, INFO=white, DEBUG=green, TRACE=green} ::  %msg%n

spring.profiles.active=dev


application-dev.properties file ::

# Services URL's
product.service.url=http://localhost:8091


This is my Postman API Request
localhost:8089/service/getProductData

I am getting issue on postman like service or api I am trying to acces is not existed or not runnig even if my container is runnig & I have also check with the logs and using the localhost application running this work fine without any issue but there is issue after running application inside container getting the issue tell me is there anything wrong with my any files or sugest me any changes required 


My Console Output or Logs for Docker Container ::

2025-08-04T05:17:57.119901100Z WARNING: A terminally deprecated method in sun.misc.Unsafe has been called
2025-08-04T05:17:57.119953100Z WARNING: sun.misc.Unsafe::allocateMemory has been called by io.netty.util.internal.PlatformDependent0$2 (jar:nested:/app/app.jar/!BOOT-INF/lib/netty-common-4.1.123.Final.jar!/)
2025-08-04T05:17:57.119958900Z WARNING: Please consider reporting this to the maintainers of class io.netty.util.internal.PlatformDependent0$2
2025-08-04T05:17:57.119961400Z WARNING: sun.misc.Unsafe::allocateMemory will be removed in a future release
2025-08-04T05:17:58.630896100Z 
2025-08-04T05:17:58.630944100Z   .   ____          _            __ _ _
2025-08-04T05:17:58.630949300Z  /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
2025-08-04T05:17:58.630951600Z ( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
2025-08-04T05:17:58.630953500Z  \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
2025-08-04T05:17:58.630955200Z   '  |____| .__|_| |_|_| |_\__, | / / / /
2025-08-04T05:17:58.630956900Z  =========|_|==============|___/=/_/_/_/
2025-08-04T05:17:58.630958800Z 
2025-08-04T05:17:58.632122200Z  :: Spring Boot ::                (v3.5.4)
2025-08-04T05:17:58.632168400Z 
2025-08-04T05:17:58.791487500Z 2025-08-04 :: 05:17:58.785 || INFO :: StartupInfoLogger.java: | 53 | ::  Starting FincoreApplication v0.0.1-SNAPSHOT using Java 24.0.2 with PID 1 (/app/app.jar started by circleci in /app)
2025-08-04T05:17:58.793118400Z 2025-08-04 :: 05:17:58.792 || INFO :: SpringApplication.java: | 658 | ::  The following 1 profile is active: "dev"
2025-08-04T05:18:00.033674100Z 2025-08-04 :: 05:18:00.032 || INFO :: RepositoryConfigurationDelegate.java: | 145 | ::  Bootstrapping Spring Data JPA repositories in DEFAULT mode.
2025-08-04T05:18:00.092119300Z 2025-08-04 :: 05:18:00.091 || INFO :: RepositoryConfigurationDelegate.java: | 213 | ::  Finished Spring Data repository scanning in 32 ms. Found 0 JPA repository interfaces.
2025-08-04T05:18:00.365720900Z 2025-08-04 :: 05:18:00.365 || INFO :: GenericScope.java: | 280 | ::  BeanFactory id=7009ad92-0f98-3bab-b4ab-017ee24e133d
2025-08-04T05:18:01.038315900Z 2025-08-04 :: 05:18:01.037 || INFO :: TomcatWebServer.java: | 111 | ::  Tomcat initialized with port 8089 (http)
2025-08-04T05:18:01.057082400Z 2025-08-04 :: 05:18:01.056 || INFO :: DirectJDKLog.java: | 168 | ::  Starting service [Tomcat]
2025-08-04T05:18:01.057480500Z 2025-08-04 :: 05:18:01.057 || INFO :: DirectJDKLog.java: | 168 | ::  Starting Servlet engine: [Apache Tomcat/10.1.43]
2025-08-04T05:18:01.089700000Z 2025-08-04 :: 05:18:01.088 || INFO :: DirectJDKLog.java: | 168 | ::  Initializing Spring embedded WebApplicationContext
2025-08-04T05:18:01.090260300Z 2025-08-04 :: 05:18:01.089 || INFO :: ServletWebServerApplicationContext.java: | 301 | ::  Root WebApplicationContext: initialization completed in 2156 ms
2025-08-04T05:18:01.452711800Z 2025-08-04 :: 05:18:01.450 || INFO :: HikariDataSource.java: | 109 | ::  HikariPool-1 - Starting...
2025-08-04T05:18:02.022820900Z 2025-08-04 :: 05:18:02.022 || INFO :: HikariPool.java: | 580 | ::  HikariPool-1 - Added connection oracle.jdbc.driver.T4CConnection@efe49ab
2025-08-04T05:18:02.026682000Z 2025-08-04 :: 05:18:02.024 || INFO :: HikariDataSource.java: | 122 | ::  HikariPool-1 - Start completed.
2025-08-04T05:18:02.110239100Z 2025-08-04 :: 05:18:02.109 || INFO :: LogHelper.java: | 31 | ::  HHH000204: Processing PersistenceUnitInfo [name: default]
2025-08-04T05:18:02.318761100Z 2025-08-04 :: 05:18:02.317 || INFO :: Version.java: | 44 | ::  HHH000412: Hibernate ORM core version 6.6.22.Final
2025-08-04T05:18:02.437829900Z 2025-08-04 :: 05:18:02.435 || INFO :: RegionFactoryInitiator.java: | 50 | ::  HHH000026: Second-level cache disabled
2025-08-04T05:18:03.096955700Z 2025-08-04 :: 05:18:03.096 || INFO :: SpringPersistenceUnitInfo.java: | 87 | ::  No LoadTimeWeaver setup: ignoring JPA class transformer
2025-08-04T05:18:03.699736800Z 2025-08-04 :: 05:18:03.698 || INFO :: JdbcEnvironmentInitiator.java: | 163 | ::  HHH10001005: Database info:
2025-08-04T05:18:03.699783200Z 	Database JDBC URL [Connecting through datasource 'HikariDataSource (HikariPool-1)']
2025-08-04T05:18:03.699788900Z 	Database driver: undefined/unknown
2025-08-04T05:18:03.699790900Z 	Database version: 19.28
2025-08-04T05:18:03.699792600Z 	Autocommit mode: undefined/unknown
2025-08-04T05:18:03.699794100Z 	Isolation level: undefined/unknown
2025-08-04T05:18:03.699795600Z 	Minimum pool size: undefined/unknown
2025-08-04T05:18:03.699797200Z 	Maximum pool size: undefined/unknown
2025-08-04T05:18:04.240593800Z 2025-08-04 :: 05:18:04.239 || INFO :: JtaPlatformInitiator.java: | 59 | ::  HHH000489: No JTA platform available (set 'hibernate.transaction.jta.platform' to enable JTA platform integration)
2025-08-04T05:18:04.247944100Z 2025-08-04 :: 05:18:04.247 || INFO :: AbstractEntityManagerFactoryBean.java: | 447 | ::  Initialized JPA EntityManagerFactory for persistence unit 'default'
2025-08-04T05:18:04.356743600Z 2025-08-04 :: 05:18:04.355 || WARN :: JpaBaseConfiguration.java: | 258 | ::  spring.jpa.open-in-view is enabled by default. Therefore, database queries may be performed during view rendering. Explicitly configure spring.jpa.open-in-view to disable this warning
2025-08-04T05:18:05.094049700Z 2025-08-04 :: 05:18:05.093 || INFO :: TomcatWebServer.java: | 243 | ::  Tomcat started on port 8089 (http) with context path '/'
2025-08-04T05:18:05.110690600Z 2025-08-04 :: 05:18:05.109 || INFO :: StartupInfoLogger.java: | 59 | ::  Started FincoreApplication in 8.493 seconds (process running for 9.719)
2025-08-04T05:18:05.114136100Z This is Fincore Project

