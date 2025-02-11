#**this is the main application.properties**
spring.application.name=checkerWorklistService

#Default Profile is Development
spring.profiles.active=dev

#Servlet Config
server.servlet.context-path=/
server.port=8087

#DB Config
spring.datasource.driver-class-name=oracle.jdbc.OracleDriver
#spring.jpa.show-sql=true
#spring.jpa.properties.hibernate.format_sql=true
#logging.level.org.hibernate.SQL=DEBUG
logging.level.org.hibernate.type.descriptor.sql.BasicBinder=TRACE

#Frontend View Config
spring.mvc.view.prefix=/templates/
spring.mvc.view.suffix=.html
spring.application.name=checkerWorklistService

**application-dev.properties**
# For DEV Environment
spring.datasource.url=jdbc:oracle:thin:@IP:PORT:CTX
spring.datasource.username=XYZ
spring.datasource.password=XYZ

for Dev tomcat server set the below line
**spring.profiles.active=dev**

**application-prod.properties**
# For PROD Environment
spring.datasource.url=jdbc:oracle:thin:@IP:PORT:CTX
spring.datasource.username=XYZ
spring.datasource.password=XYZ

for Prod tomcat server set the below line
**spring.profiles.active=dev
**

**application-uat.properties**
# For UAT Environment
spring.datasource.url=jdbc:oracle:thin:@IP:PORT:CTX
spring.datasource.username=XYZ
spring.datasource.password=XYZ

for Uat tomcat server set the below line
**spring.profiles.active=uat**

Is it correct every environment added line will override the main application.properties file (spring.profiles.active=dev) this line 
I had bit confusion

