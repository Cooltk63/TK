log4j2.xml

<?xml version="1.0" encoding="UTF-8"?>
<Configuration status="WARN">
    <Properties>
        <!-- Force Enable ANSI Colors -->
        <Property name="log4j2.enableJansi">true</Property>
    </Properties>

    <Appenders>
        <Console name="Console" target="SYSTEM_OUT">
            <PatternLayout>
                <Pattern>%d{yyyy-MM-dd HH:mm:ss} %highlight(%-5level){TRACE:orange, DEBUG:yellow, INFO:cyan, WARN:red bold, ERROR:red bold} [%style(%file:%line){cyan}] :: %msg%n</Pattern>
            </PatternLayout>
        </Console>
    </Appenders>

    <Loggers>
        <Root level="trace">
            <AppenderRef ref="Console"/>
        </Root>
    </Loggers>
</Configuration>

pom.xml depedencies

 <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
            <exclusions>
                <exclusion>
                    <groupId>org.springframework.boot</groupId>
                    <artifactId>spring-boot-starter-logging</artifactId>
                </exclusion>
            </exclusions>
        </dependency>
        <dependency>
            <groupId>com.oracle.database.jdbc</groupId>
            <artifactId>ojdbc11</artifactId>
            <scope>runtime</scope>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
        </dependency>

        <dependency>
            <groupId>org.json</groupId>
            <artifactId>json</artifactId>
            <version>20210307</version>
        </dependency>
        <dependency>
            <groupId>org.bouncycastle</groupId>
            <artifactId>bcpkix-jdk18on</artifactId>
            <version>1.76</version>
        </dependency>
        <dependency>
            <groupId>org.bouncycastle</groupId>
            <artifactId>bcutil-jdk18on</artifactId>
            <version>1.76</version>
        </dependency>
        <!-- https://mvnrepository.com/artifact/jakarta.annotation/jakarta.annotation-api -->
        <dependency>
            <groupId>jakarta.annotation</groupId>
            <artifactId>jakarta.annotation-api</artifactId>
            <version>2.1.1</version>
        </dependency>
        <!-- https://mvnrepository.com/artifact/commons-configuration/commons-configuration -->
        <dependency>
            <groupId>commons-configuration</groupId>
            <artifactId>commons-configuration</artifactId>
            <version>1.6</version>
            <exclusions>
                <exclusion>
                    <groupId>commons-logging</groupId>
                    <artifactId>commons-logging</artifactId>
                </exclusion>
            </exclusions>
        </dependency>
        <!-- Log4j2 Starter -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-log4j2</artifactId>
        </dependency>
        <!-- Optional: Log4j2 Core (if needed for advanced features) -->
        <dependency>
            <groupId>org.apache.logging.log4j</groupId>
            <artifactId>log4j-core</artifactId>
            <version>2.23.1</version>
        </dependency>
        <!-- SLF4J to Log4j2 Bridge (Fixes SLF4J conflicts) -->
        <dependency>
            <groupId>org.apache.logging.log4j</groupId>
            <artifactId>log4j-slf4j-impl</artifactId>
            <version>2.23.1</version>
        </dependency>
    </dependencies>

log as per below
2025-02-17 17:40:28 disableAnsi=true(TRACE){TRACE:orange, DEBUG:yellow, INFO:cyan, WARN:red bold, ERROR:red bold} [disableAnsi=true(LoggingProviderImpl.java:230){cyan}] :: Finish a request.

    application.properties file as per below
    #Default Profile is Development
spring.profiles.active=dev

#Servlet Config
server.servlet.context-path=/
server.port=4587

#DB Config
spring.datasource.driver-class-name=oracle.jdbc.OracleDriver
#spring.jpa.show-sql=true
#spring.jpa.properties.hibernate.format_sql=true
#logging.level.org.hibernate.SQL=DEBUG
logging.level.org.hibernate.type.descriptor.sql.BasicBinder=TRACE

#Frontend View Config
spring.mvc.view.prefix=/templates/
spring.mvc.view.suffix=.html

    
