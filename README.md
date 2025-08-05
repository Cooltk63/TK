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

		<!-- https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-actuator -->
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-actuator</artifactId>
			<version>3.5.4</version>
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



is this right dependecy for need to add more dependency for deploying the spring boot application tanzu kubernative clusert using the service discovry by names that is using dns name is this right or need some different or new depeneccies suggest me any dependceny which redice the more coading and used for efficient reliable and scalable application developement.
