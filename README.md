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


Console Output:

2025-07-29 :: 17:15:18.472 || INFO :: HikariPool.java: | 580 | ::  HikariPool-1 - Added connection oracle.jdbc.driver.T4CConnection@4438938e
2025-07-29 :: 17:15:18.474 || INFO :: HikariDataSource.java: | 122 | ::  HikariPool-1 - Start completed.
2025-07-29 :: 17:15:18.507 || INFO :: LogHelper.java: | 31 | ::  HHH000204: Processing PersistenceUnitInfo [name: default]
2025-07-29 :: 17:15:18.546 || INFO :: Version.java: | 44 | ::  HHH000412: Hibernate ORM core version 6.6.22.Final
2025-07-29 :: 17:15:18.574 || INFO :: RegionFactoryInitiator.java: | 50 | ::  HHH000026: Second-level cache disabled
2025-07-29 :: 17:15:19.077 || INFO :: SpringPersistenceUnitInfo.java: | 87 | ::  No LoadTimeWeaver setup: ignoring JPA class transformer
2025-07-29 :: 17:15:19.509 || INFO :: JdbcEnvironmentInitiator.java: | 163 | ::  HHH10001005: Database info:
	Database JDBC URL [Connecting through datasource 'HikariDataSource (HikariPool-1)']
	Database driver: undefined/unknown
	Database version: 19.27
	Autocommit mode: undefined/unknown
	Isolation level: undefined/unknown
	Minimum pool size: undefined/unknown
	Maximum pool size: undefined/unknown
2025-07-29 :: 17:15:20.017 || INFO :: JtaPlatformInitiator.java: | 59 | ::  HHH000489: No JTA platform available (set 'hibernate.transaction.jta.platform' to enable JTA platform integration)
2025-07-29 :: 17:15:20.026 || INFO :: AbstractEntityManagerFactoryBean.java: | 447 | ::  Initialized JPA EntityManagerFactory for persistence unit 'default'
2025-07-29 :: 17:15:20.812 || INFO :: StartupInfoLogger.java: | 59 | ::  Started FincoreApplication in 5.346 seconds (process running for 6.942)
2025-07-29 :: 17:15:20.825 || INFO :: AbstractEntityManagerFactoryBean.java: | 660 | ::  Closing JPA EntityManagerFactory for persistence unit 'default'
2025-07-29 :: 17:15:20.829 || INFO :: HikariDataSource.java: | 349 | ::  HikariPool-1 - Shutdown initiated...
2025-07-29 :: 17:15:20.949 || INFO :: HikariDataSource.java: | 351 | ::  HikariPool-1 - Shutdown completed.


How to resolve thisn issue.
