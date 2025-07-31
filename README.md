20:47:24.875 [main] ERROR org.springframework.boot.SpringApplication -- Application run failed
java.lang.IllegalArgumentException: Failure in creating proxy URL. Proxy port is required!
	at io.fabric8.kubernetes.client.utils.HttpClientUtils.getProxyUri(HttpClientUtils.java:99)
	at io.fabric8.kubernetes.client.utils.HttpClientUtils.configureProxy(HttpClientUtils.java:239)
	at io.fabric8.kubernetes.client.utils.HttpClientUtils.applyCommonConfiguration(HttpClientUtils.java:214)
	at io.fabric8.kubernetes.client.http.HttpClient$Factory.newBuilder(HttpClient.java:50)
	at io.fabric8.kubernetes.client.utils.HttpClientUtils.createHttpClient(HttpClientUtils.java:161)
	at io.fabric8.kubernetes.client.DefaultKubernetesClient.<init>(DefaultKubernetesClient.java:45)
	at io.fabric8.kubernetes.client.DefaultKubernetesClient.<init>(DefaultKubernetesClient.java:37)
	at org.springframework.cloud.kubernetes.profile.KubernetesProfileEnvironmentPostProcessor.isInsideKubernetes(KubernetesProfileEnvironmentPostProcessor.java:74)
	at org.springframework.cloud.kubernetes.profile.KubernetesProfileEnvironmentPostProcessor.postProcessEnvironment(KubernetesProfileEnvironmentPostProcessor.java:52)
	at org.springframework.boot.env.EnvironmentPostProcessorApplicationListener.onApplicationEnvironmentPreparedEvent(EnvironmentPostProcessorApplicationListener.java:132)
	at org.springframework.boot.env.EnvironmentPostProcessorApplicationListener.onApplicationEvent(EnvironmentPostProcessorApplicationListener.java:115)
	at org.springframework.context.event.SimpleApplicationEventMulticaster.doInvokeListener(SimpleApplicationEventMulticaster.java:185)
	at org.springframework.context.event.SimpleApplicationEventMulticaster.invokeListener(SimpleApplicationEventMulticaster.java:178)
	at org.springframework.context.event.SimpleApplicationEventMulticaster.multicastEvent(SimpleApplicationEventMulticaster.java:156)
	at org.springframework.context.event.SimpleApplicationEventMulticaster.multicastEvent(SimpleApplicationEventMulticaster.java:138)
	at org.springframework.boot.context.event.EventPublishingRunListener.multicastInitialEvent(EventPublishingRunListener.java:136)
	at org.springframework.boot.context.event.EventPublishingRunListener.environmentPrepared(EventPublishingRunListener.java:81)
	at org.springframework.boot.SpringApplicationRunListeners.lambda$environmentPrepared$2(SpringApplicationRunListeners.java:64)
	at java.base/java.lang.Iterable.forEach(Iterable.java:75)
	at org.springframework.boot.SpringApplicationRunListeners.doWithListeners(SpringApplicationRunListeners.java:118)
	at org.springframework.boot.SpringApplicationRunListeners.doWithListeners(SpringApplicationRunListeners.java:112)
	at org.springframework.boot.SpringApplicationRunListeners.environmentPrepared(SpringApplicationRunListeners.java:63)
	at org.springframework.boot.SpringApplication.prepareEnvironment(SpringApplication.java:353)
	at org.springframework.boot.SpringApplication.run(SpringApplication.java:313)
	at org.springframework.boot.SpringApplication.run(SpringApplication.java:1361)
	at org.springframework.boot.SpringApplication.run(SpringApplication.java:1350)
	at com.example.Fincore.FincoreApplication.main(FincoreApplication.java:10)

Process finished with exit code 1


What is this issue why i am getting 

My Pom.xml 
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
	<description>Project for Spring Boot Kubernates</description>
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

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-jpa</artifactId>
		</dependency>

		<dependency>
			<groupId>com.oracle.database.jdbc</groupId>
			<artifactId>ojdbc11</artifactId>
			<scope>runtime</scope>
		</dependency>

		<!-- https://mvnrepository.com/artifact/org.springframework.cloud/spring-cloud-starter-kubernetes-discoveryclient -->
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-kubernetes-discoveryclient</artifactId>
			<version>2.1.3</version>
		</dependency>

		<!-- https://mvnrepository.com/artifact/org.springframework.cloud/spring-cloud-starter-kubernetes-config -->
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-kubernetes-config</artifactId>
			<version>1.1.10.RELEASE</version>
		</dependency>

		<!-- https://mvnrepository.com/artifact/org.springframework.cloud/spring-cloud-starter-kubernetes-client-all -->
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-kubernetes-client-all</artifactId>
			<version>3.3.0</version>
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

application.properties file
# ========== Application Configuration ==========
spring.application.name=fin-service
# Unique service name in Kubernetes

# ========== Server Configuration ==========
# Local port application will run on
server.port=8089

# ========== Oracle DB Configuration ==========
spring.datasource.url=jdbc:oracle:thin:@MY Secret Data
spring.datasource.username=MY Secret Data
spring.datasource.password=MY Secret Data

# Recommended Oracle JDBC driver class
spring.datasource.driver-class-name=oracle.jdbc.OracleDriver


# ========== Kubernetes Discovery Settings ==========
# Enable discovery
spring.cloud.kubernetes.discovery.enabled=true
# Limit to same namespace
spring.cloud.kubernetes.discovery.all-namespaces=false
spring.cloud.kubernetes.discovery.service-labels[app]=fin-service


Tell what was that issue and why it suddenly occuring help me to resolve the issue.
