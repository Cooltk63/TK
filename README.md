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

#EXPOSE 8089

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
#server.port=8089


# Recommended Oracle JDBC driver class
spring.datasource.driver-class-name=oracle.jdbc.OracleDriver


# ========== Kubernetes Discovery Settings ==========
# Enable discovery
spring.cloud.kubernetes.discovery.enabled=true
# Limit to same namespace
spring.cloud.kubernetes.discovery.all-namespaces=false
spring.cloud.kubernetes.discovery.service-labels[app]=fin-service

# ========== Health Checks ==========
#management.endpoint.health.probe.enabled=true
#management.health.probes.enabled=true
#management.endpoint.health.show-details=always
#management.endpoints.web.exposure.include=health,info

# ========== Logging ==========
# Console log pattern (Color-coded output)
logging.pattern.console=%d{yyyy-MM-dd :: HH:mm:ss.SSS ||} %highlight(%-5level:: %file: | %line |){ERROR=bold red, WARN=yellow, INFO=white, DEBUG=green, TRACE=green} ::  %msg%n


# ========== Kubernetes ConfigMap/Secret Mounting ==========
# These can be used in Kubernetes to override sensitive info (e.g., DB credentials)
# Spring Cloud Kubernetes automatically maps these if mounted correctly

# Environment Variables:
# spring.datasource.username=${DB_USER}
# spring.datasource.password=${DB_PASS}

# inject via K8s Secret/ConfigMap to keep credentials secure.



spring.profiles.active=dev


application-dev.properties file ::

# Services URL's
product.service.url=http://localhost:8091


This is my Postman API Request
localhost:8089/service/getProductData

I am getting issue on postman like service or api I am trying to acces is not existed or not runnig even if my container is runnig & I have also check with the logs and using the localhost application running this work fine without any issue but there is issue after running application inside container getting the issue tell me is there anything wrong with my any files or sugest me any changes required 
