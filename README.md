# 1. Use Tomcat with JDK 17
FROM tomcat:10.1-jdk17

# 2. Clean default apps (optional)
RUN rm -rf /usr/local/tomcat/webapps/*

# 3. Set timezone (for consistency in logs)
ENV TZ=Asia/Kolkata

# 4. Copy your Spring Boot WAR file into the Tomcat webapps directory
COPY target/your-app.war /usr/local/tomcat/webapps/ROOT.war

# 5. Expose port 8080 for Tomcat
EXPOSE 8080

# 6. Start Tomcat server
CMD ["catalina.sh", "run"]



version: '3.8'

services:
  springboot-app:
    build: .
    container_name: my-springboot-container
    ports:
      - "8080:8080"
    environment:
      - SPRING_DATASOURCE_URL=jdbc:oracle:thin:@10.67.78.89:1522:XE
      - SPRING_DATASOURCE_USERNAME=testUsernsme
      - SPRING_DATASOURCE_PASSWORD=password1
    restart: always