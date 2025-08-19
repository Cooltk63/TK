C:\Users\v1012297>docker images
REPOSITORY                 TAG           IMAGE ID       CREATED          SIZE
api-gateway                latest        b412084079a3   36 minutes ago   3.6GB
fincore-service            latest        eeba607b5bd9   53 minutes ago   3.53GB
product-service            latest        7f44ddd751f9   2 hours ago      3.6GB
cimg/openjdk               24.0.2-node   7de6cbbe2cbc   3 weeks ago      3.43GB
redis/redis-stack-server   latest        3751e8743b31   6 weeks ago      824MB

C:\Users\v1012297>docker network ls
NETWORK ID     NAME             DRIVER    SCOPE
e92f9da361f2   bridge           bridge    local
ae8374b507be   host             host      local
eff958cf0962   my-app-network   bridge    local
4cc6f9f437be   none             null      local


C:\Users\v1012297>docker run -d --network my-app-network  --name product-service -p 8081:8081 product-service
631980baeb1984679ed612d36469f16cf8794db0d96c8429d6c5dd641a79f3f1

C:\Users\v1012297>docker run -d --network my-app-network  --name fincore-service -p 8089:8089 fincore-service
7b3901c467d73c52b98835c88d5967dc1a07833ad25e4a6d61b64e19b331d822

C:\Users\v1012297>docker run -d --network my-app-network  --name api-gateway -p 8080:8080 api-gateway
1cd6e626fdf4feb3344f09d34dd3f3e06de09d7471bbdcd1e78d7d7859eb1a85

C:\Users\v1012297>docker run -d --name redis -p 6379:6379 redis/redis-stack-server
605cd0f9c4c1a42cbde9d3daaa1f496258956da049281788801bcc316b189738

C:\Users\v1012297>docker ps
CONTAINER ID   IMAGE                      COMMAND               CREATED          STATUS          PORTS                                         NAMES
1cd6e626fdf4   api-gateway                "java -jar app.jar"   8 seconds ago    Up 8 seconds    0.0.0.0:8080->8080/tcp, [::]:8080->8080/tcp   api-gateway
7b3901c467d7   fincore-service            "java -jar app.jar"   13 seconds ago   Up 13 seconds   0.0.0.0:8089->8089/tcp, [::]:8089->8089/tcp   fincore-service
631980baeb19   product-service            "java -jar app.jar"   17 seconds ago   Up 17 seconds   0.0.0.0:8081->8081/tcp, [::]:8081->8081/tcp   product-service
98458fc7b1dc   redis/redis-stack-server   "/entrypoint.sh"      13 minutes ago   Up 13 minutes   0.0.0.0:6379->6379/tcp, [::]:6379->6379/tcp   redis

I have provided the above docker images I have created along with that I have created the network & to run all images on containers but I am facing issue that main purpose of the API gateway how this gonna work between this as I am calling Product-Service to Fincore-Service I can able to call or get response from the Fincore service without intercepted by the api gateway or anything look like api gateway just standalone microserservice 

below is code for calling fincore-service from product-service

 @PostMapping("/getFincoreList")
    public List<String> getFincoreList() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>("", headers);

        List<String> listData=new RestTemplate().postForObject(ServiceUrl + "/Fincore/getFinList", request, List.class);
        log.info("Received Data from Fincore Service::"+listData);
        return listData;
    }
	
	Fincore-Service Controller code as per below
	 // Sending List to Product Service & Received List Data Response
    @PostMapping("/getFinList")
    public List<String> getFinList() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        List<String> listData = List.of("1", "2", "3");

        return listData;
    }
	
	
	Response Received in Postman :: http://localhost:8081/Product/getFincoreList
	[
    "1",
    "2",
    "3"
	]
	
	
	Guide me how does the api gateway gonna intercept each request as you told me earlier or guide me what setuo is remaining to make this api gateway middle man before calling any service or incoming api call or outgoing 
	
	Both Fincore-Service & Product-Service Pom.xml file as per below
	
	<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>
	<parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>3.5.4</version>
		<relativePath/> <!-- lookup parent from repository -->
	</parent>
	<groupId>com.example</groupId>
	<artifactId>Fincore</artifactId>
	<version>0.0.1-SNAPSHOT</version>
	<packaging>jar</packaging>
	<name>Fincore</name>
	<description>Project for Spring Boot Kubernates Discovery</description>
	<url/>
	<licenses>
		<license/>
	</licenses>
	<developers>
	</developers>
	<scm>
		<connection/>
		<developerConnection/>
		<tag/>
		<url/>
	</scm>
	<properties>
		<java.version>17</java.version>
	</properties>
	<dependencies>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter</artifactId>
		</dependency>

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>


		<!--<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-jpa</artifactId>
		</dependency>

		<dependency>
			<groupId>com.oracle.database.jdbc</groupId>
			<artifactId>ojdbc11</artifactId>
			<scope>runtime</scope>
		</dependency>-->

		<!-- https://mvnrepository.com/artifact/org.springframework.cloud/spring-cloud-starter-kubernetes-discoveryclient -->
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-kubernetes-discoveryclient</artifactId>
			<version>3.3.0</version>
		</dependency>

		<!-- https://mvnrepository.com/artifact/org.springframework.cloud/spring-cloud-starter-kubernetes-config -->
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-kubernetes-config</artifactId>
			<version>1.1.10.RELEASE</version>
		</dependency>

		<!-- https://mvnrepository.com/artifact/org.projectlombok/lombok -->
		<dependency>
			<groupId>org.projectlombok</groupId>
			<artifactId>lombok</artifactId>
			<version>1.18.38</version>
		</dependency>



	</dependencies>
	<dependencyManagement>
		<dependencies>
				<!-- https://mvnrepository.com/artifact/org.springframework.cloud/spring-cloud-dependencies -->
				<dependency>
					<groupId>org.springframework.cloud</groupId>
					<artifactId>spring-cloud-dependencies</artifactId>
					<version>2025.0.0</version>
					<type>pom</type>
					<scope>import</scope>
				</dependency>
		</dependencies>
	</dependencyManagement>


	<build>
		<finalName>${project.artifactId}</finalName>
		<plugins>
			<plugin>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-maven-plugin</artifactId>
			</plugin>
		</plugins>
	</build>

</project>
