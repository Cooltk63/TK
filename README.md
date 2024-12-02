<?xml version="1.0" encoding="UTF-8"?>
<project xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns="http://maven.apache.org/POM/4.0.0"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>3.3.0</version>
    </parent>
    <groupId>com</groupId>
    <artifactId>DASHBOARD</artifactId>
    <version>0.0.2</version>
    <packaging>war</packaging>
    <name>DASHBOARD</name>
    <description>DASHBOARD</description>
    <properties> 
    <java.version>8</java.version>
    </properties>

   <build>
   <sourceDirectory>src</sourceDirectory>
    <resources>
        <resource>
            <directory>WebContent</directory>
        </resource>
    </resources>
    <plugins>
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-war-plugin</artifactId>
            <version>3.4.0</version>
            <configuration>
                <warSourceDirectory>WebContent</warSourceDirectory>
                <failOnMissingWebXml>false</failOnMissingWebXml>
            </configuration>
        </plugin>
    </plugins>
</build>
</project>
