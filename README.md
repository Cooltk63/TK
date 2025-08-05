Fincore Service Controller :

package com.example.Fincore.testController;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@RestController
@RequestMapping("/fincore")
public class ServiceController {

    @Value("${product.service.url}")
    private String productServiceUrl;



    @PostMapping("/PostMapData")
    public String PostMappingtest(){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>("SAMPLE TEST PARAM PASSING ", headers);

       String data=new RestTemplate().postForObject(productServiceUrl+"/service/getProductOne",request, String.class);

        return data.toString();
    }

    //Sending List<String> Data to Another Service & Received List<Map<String, Objects>> Data as Response from Service
    @PostMapping("/GetMapData")
    public String GetMappingtest(){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        List<String> ListData = Arrays.asList(new String[]{"1","2","3"});
        HttpEntity<List<String>> request = new HttpEntity<>(ListData, headers);

        String data= new RestTemplate().postForObject(productServiceUrl+"/service/getProductTwo",request, List.class).toString();

        return data;
    }


    @PostMapping("/getFincoreService")
    public String CallProductService(@RequestBody String data){

        return "Received the Data from Product Service"+data;
    }

    @PostMapping("/testingfin")
    public String sampleCall(){

        return "Received the Data from Product Service";
    }

ProdyctService Controller ::
package com.example.ProductService.testController;

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
public class ProductServiceController {

    @Value("${fincore.service.url}")
    private String fincoreServiceUrl;

    @PostMapping("/PostMapData")
    public String PostMappingtest(){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>("SAMPLE TEST PARAM PASSING ", headers);
       String data=new RestTemplate().postForObject(fincoreServiceUrl+"/service/getFincoreService",request, String.class);
        return data.toString();
    }

    //Sending List<String> Data to Another Service & Received List<Map<String, Objects>> Data as Response from Service
    @PostMapping("/getPostData")
    public String PostData(){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        List<String> ListData = new ArrayList<>();
        ListData.add("1");
        ListData.add("2");
        ListData.add("3");
        HttpEntity<List<String>> request = new HttpEntity<>(ListData, headers);
        List<Map<String, Objects>> data=new RestTemplate().postForObject(fincoreServiceUrl+"/service/PostMapData",request, List.class);

        return data.toString();
    }
    @PostMapping("/getProductData")
    public String CallProductService(@RequestBody String data){
        return "Received the Data from Product Service"+data;
    }

    @PostMapping("/getProductTwo")
    public String getProductTwo(@RequestBody List<String> data){
        return "Received getProductTwo the Data from Product Service ::"+data;
    }
}
}



Application Properties Files

1. Product Service (Main application.properties) :: 

# ========== Application Configuration ==========
spring.application.name=Product-Service
# Unique service name in Kubernetes

# ========== Server Configuration ==========
# Local port application will run on
server.port=8081
server.address=0.0.0.0


# Recommended Oracle JDBC driver class
spring.datasource.driver-class-name=oracle.jdbc.OracleDriver


# ========== Kubernetes Discovery Settings ==========
# Enable discovery
spring.cloud.kubernetes.discovery.enabled=true
# Limit to same namespace
spring.cloud.kubernetes.discovery.all-namespaces=false
spring.cloud.kubernetes.discovery.service-labels[app]=Product-Service

# ========== Logging ==========
# Console log pattern (Color-coded output)
logging.pattern.console=%d{yyyy-MM-dd :: HH:mm:ss.SSS ||} %highlight(%-5level:: %file: | %line |){ERROR=bold red, WARN=yellow, INFO=white, DEBUG=green, TRACE=green} ::  %msg%n
spring.profiles.active=dev

2. Product Service (dev application-dev.properties) :: 
# Services URL's
fincore.service.url=http://localhost:8089

3.Fincore Service (Main application.properties) :: 
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


4.Fincore Service (dev application-dev.properties) :: 
# Services URL's
product.service.url=http://localhost:8081


I need the both service call each other with data or without data API's or request of both get and post mapping wihtout fail use the resttemplate for calling other service
