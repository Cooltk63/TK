<?xml version="1.0" encoding="UTF-8"?>
<project xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns="http://maven.apache.org/POM/4.0.0"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>3.3.0</version>
    </parent>
    <repositories>
        <!-- <repository>
            <id>my-repo-1</id>
            <name>My Repository</name>
            <url>file:///C:/Users/V1012981/.m2/repository/</url>
        </repository> -->
        <!-- <repository>
            <id>my-repo-2</id>
            <name>My Repository</name> 
            <url>http://10.191.167.23:443/repository/</url>
        </repository> 
        <repositories>-->
       <repository>
           <id>my-repo-1</id>
           <name>My Repository</name>
           <url>file:///E:/Share/RepositoryToYogesh/repository/</url>
       </repository>
        <repository>
           <id>my-repo</id>
           <name>My Repository</name>
           <url>file:///C:/Users/V1012981/.m2/repository/</url>
       </repository>
        <repository>
           <id>my-repo-2</id>
           <name>My Repository</name>
           <url>file:///E:/SSO_JAR/repository/</url>
       </repository> 



    </repositories>
    <groupId>com.crs</groupId>
    <artifactId>SsoLoginService</artifactId>
    <version>0.0.2</version>
    <packaging>war</packaging>
    <name>SsoLoginService</name>
    <description>SsoLoginService</description>
    <properties>
        <java.version>17</java.version>
    </properties>
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
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
            <scope>runtime</scope>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>com.oracle.database.jdbc</groupId>
            <artifactId>ojdbc11</artifactId>
            <scope>runtime</scope>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-tomcat</artifactId>
            <scope>provided</scope>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
        </dependency>




        <!-- https://mvnrepository.com/artifact/javax.activation/activation -->
        <dependency>
            <groupId>javax.activation</groupId>
            <artifactId>activation</artifactId>
            <version>1.1</version>
        </dependency>

        <!-- https://mvnrepository.com/artifact/avalon-framework/avalon-framework -->
        <dependency>
            <groupId>avalon-framework</groupId>
            <artifactId>avalon-framework</artifactId>
            <version>4.1.3</version>
        </dependency>

<!-- https://mvnrepository.com/artifact/org.bouncycastle/bcprov-jdk15 -->
<dependency>
    <groupId>org.bouncycastle</groupId>
    <artifactId>bcprov-jdk15</artifactId>
    <version>1.46</version>
</dependency>


<!-- https://mvnrepository.com/artifact/commons-codec/commons-codec -->
<dependency>
    <groupId>commons-codec</groupId>
    <artifactId>commons-codec</artifactId>
    <version>1.3</version>
</dependency>


<!-- https://mvnrepository.com/artifact/commons-collections/commons-collections -->
<dependency>
    <groupId>commons-collections</groupId>
    <artifactId>commons-collections</artifactId>
    <version>3.2.1</version>
</dependency>


<!-- https://mvnrepository.com/artifact/commons-httpclient/commons-httpclient -->
<dependency>
    <groupId>commons-httpclient</groupId>
    <artifactId>commons-httpclient</artifactId>
    <version>3.1</version>
</dependency>


<!-- https://mvnrepository.com/artifact/commons-io/commons-io -->
<dependency>
    <groupId>commons-io</groupId>
    <artifactId>commons-io</artifactId>
    <version>2.4</version>
</dependency>


<!-- https://mvnrepository.com/artifact/commons-lang/commons-lang -->
<dependency>
    <groupId>commons-lang</groupId>
    <artifactId>commons-lang</artifactId>
    <version>2.6</version>
</dependency>


<!-- https://mvnrepository.com/artifact/commons-logging/commons-logging -->
<dependency>
    <groupId>commons-logging</groupId>
    <artifactId>commons-logging</artifactId>
    <version>1.1</version>
</dependency>


<!-- https://mvnrepository.com/artifact/org.owasp.esapi/esapi -->
<dependency>
    <groupId>org.owasp.esapi</groupId>
    <artifactId>esapi</artifactId>
    <version>2.0.1</version>
</dependency>


        <!-- Jar not found -->
        <!-- https://mvnrepository.com/artifact/javax.jms/jms -->
        <dependency>
            <groupId>javax.jms</groupId>
            <artifactId>jms</artifactId>
            <version>1.1</version>
        </dependency>

<!-- Jar not found -->
        <!-- https://mvnrepository.com/artifact/com.sun.jmx/jmxri -->
<dependency>
    <groupId>com.sun.jmx</groupId>
    <artifactId>jmxri</artifactId>
    <version>1.2.1</version>
</dependency>


<!-- Jar not found  check once again-->
<!-- https://mvnrepository.com/artifact/com.sun.jdmk/jmxtools -->
<dependency>
    <groupId>com.sun.jdmk</groupId>
    <artifactId>jmxtools</artifactId>
    <version>1.2.1</version>
</dependency>


<!-- https://mvnrepository.com/artifact/joda-time/joda-time -->
<dependency>
    <groupId>joda-time</groupId>
    <artifactId>joda-time</artifactId>
    <version>1.6.2</version>
</dependency>


<!-- https://mvnrepository.com/artifact/log4j/log4j -->
<dependency>
    <groupId>log4j</groupId>
    <artifactId>log4j</artifactId>
    <version>1.2.17</version>
</dependency>


<!-- https://mvnrepository.com/artifact/logkit/logkit -->
<dependency>
    <groupId>logkit</groupId>
    <artifactId>logkit</artifactId>
    <version>1.0.1</version>
</dependency>


<!-- https://mvnrepository.com/artifact/javax.mail/mail -->
<dependency>
    <groupId>javax.mail</groupId>
    <artifactId>mail</artifactId>
    <version>1.4</version>
</dependency>

<!-- https://mvnrepository.com/artifact/ca.juliusdavies/not-yet-commons-ssl -->
<dependency>
    <groupId>ca.juliusdavies</groupId>
    <artifactId>not-yet-commons-ssl</artifactId>
    <version>0.3.9</version>
</dependency>


<!-- https://mvnrepository.com/artifact/org.opensaml/opensaml -->
<dependency>
    <groupId>org.opensaml</groupId>
    <artifactId>opensaml</artifactId>
    <version>2.5.3</version>
</dependency>


<!-- https://mvnrepository.com/artifact/org.opensaml/openws -->
<dependency>
    <groupId>org.opensaml</groupId>
    <artifactId>openws</artifactId>
    <version>1.4.4</version>
</dependency>

<!-- https://mvnrepository.com/artifact/xalan/serializer -->
<dependency>
    <groupId>xalan</groupId>
    <artifactId>serializer</artifactId>
    <version>2.7.1</version>
</dependency>


<!-- https://mvnrepository.com/artifact/org.slf4j/slf4j-api -->
<dependency>
    <groupId>org.slf4j</groupId>
    <artifactId>slf4j-api</artifactId>
    <version>1.7.5</version>
</dependency>


<!-- https://mvnrepository.com/artifact/org.slf4j/slf4j-log4j12 -->
<dependency>
    <groupId>org.slf4j</groupId>
    <artifactId>slf4j-log4j12</artifactId>
    <version>1.7.5</version>
    <scope>test</scope>
</dependency>


<!-- https://mvnrepository.com/artifact/org.apache.velocity/velocity -->
<dependency>
    <groupId>org.apache.velocity</groupId>
    <artifactId>velocity</artifactId>
    <version>1.5</version>
    <type>pom</type>
</dependency>


<!-- https://mvnrepository.com/artifact/xalan/xalan -->
<dependency>
    <groupId>xalan</groupId>
    <artifactId>xalan</artifactId>
    <version>2.7.1</version>
</dependency>


<!-- https://mvnrepository.com/artifact/xerces/xercesImpl -->
<dependency>
    <groupId>xerces</groupId>
    <artifactId>xercesImpl</artifactId>
    <version>2.10.0</version>
</dependency>



<!-- https://mvnrepository.com/artifact/xml-apis/xml-apis -->
<dependency>
    <groupId>xml-apis</groupId>
    <artifactId>xml-apis</artifactId>
    <version>1.4.01</version>
</dependency>


<!-- https://mvnrepository.com/artifact/xml-resolver/xml-resolver -->
<dependency>
    <groupId>xml-resolver</groupId>
    <artifactId>xml-resolver</artifactId>
    <version>1.2</version>
</dependency>


<!-- https://mvnrepository.com/artifact/org.apache.santuario/xmlsec -->
<dependency>
    <groupId>org.apache.santuario</groupId>
    <artifactId>xmlsec</artifactId>
    <version>1.4.5</version>
</dependency>


<!-- https://mvnrepository.com/artifact/org.opensaml/xmltooling -->
<dependency>
    <groupId>org.opensaml</groupId>
    <artifactId>xmltooling</artifactId>
    <version>1.3.4</version>
</dependency>


    </dependencies>
    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <version>3.3.0</version>
            </plugin>
        </plugins>
    </build>
</project>
