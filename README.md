# Stage 1: Build WAR using Maven
FROM maven:3.9.4-eclipse-temurin-17 AS build

# Set working directory in container
WORKDIR /app

# Copy source code into container
COPY . .

# Build the WAR file (skip tests if you want)
RUN mvn clean package -DskipTests

# Stage 2: Tomcat base image
FROM tomcat:10.1-jdk17-temurin

# Clean default webapps
RUN rm -rf /usr/local/tomcat/webapps/*

# Copy WAR from build stage
COPY --from=build /app/target/*.war /usr/local/tomcat/webapps/ROOT.war

# Expose port
EXPOSE 8080

# Start Tomcat
CMD ["catalina.sh", "run"]