<dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
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
            <artifactId>bcprov-jdk18on</artifactId>
            <version>1.76</version>
        </dependency>


        <!-- https://mvnrepository.com/artifact/org.bouncycastle/bcpkix-jdk18on -->
        <dependency>
            <groupId>org.bouncycastle</groupId>
            <artifactId>bcpkix-jdk18on</artifactId>
            <version>1.76</version>
        </dependency>


        <!-- https://mvnrepository.com/artifact/org.bouncycastle/bcutil-jdk18on -->
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

        <dependency>
            <groupId>org.apache.logging.log4j</groupId>
            <artifactId>log4j-core</artifactId>
        </dependency>


        <!-- https://mvnrepository.com/artifact/commons-configuration/commons-configuration -->
        <dependency>
            <groupId>commons-configuration</groupId>
            <artifactId>commons-configuration</artifactId>
            <version>1.6</version>
        </dependency>

        <!--XXXXXXXXXXX-->

        <!-- Spring Boot Starter without default logging -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter</artifactId>
            <exclusions>
                <exclusion>
                    <groupId>org.springframework.boot</groupId>
                    <artifactId>spring-boot-starter-logging</artifactId>
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
        </dependency>

        <!--XXXXXXXXXXX-->

    </dependencies>
	
	log4j2.properties file code as per below
	# Root logger configuration
rootLogger.level = info
rootLogger.appenderRefs = console
rootLogger.appenderRef.console.ref = ConsoleAppender

# Console Appender
appender.console.type = Console
appender.console.name = ConsoleAppender
appender.console.target = SYSTEM_OUT
appender.console.layout.type = PatternLayout
appender.console.layout.pattern = %style{%d{yyyy-MM-dd HH:mm:ss}}{cyan} %highlight{%-5level} %style{[%t]}{magenta} %style{%c{1.}}{yellow} - %msg%n


after I am getting the log4j
2025-02-17 16:18:30 INFO  [http-nio-4587-exec-2] c.c.e.s.HrmsServiceImpl - signedSenderTokenHash : null

I need colored log as per the log type but It failed to do so tell me where should I need to changes this where to check is log4j2 working as logging not old logback or logger deafult used by spring boot
