Docker file as per below
# Stage 1: Build the application
FROM maven:3.9.9-openjdk-22 AS build

WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests

# Stage 2: Deploy the WAR file on Tomcat
FROM tomcat:10.0

RUN rm -rf /usr/local/tomcat/webapps/*

COPY --from=build /app/target/*.war /usr/local/tomcat/webapps/app.war

# Set environment variables for database connection
ENV DB_USERNAME=myuser
ENV DB_PASSWORD=mypassword
ENV DB_PORT=5432
ENV DB_SID=mydatabase
ENV DB_DRIVER=org.postgresql.Driver

# Expose the port
EXPOSE 8080

# Start Tomcat
CMD ["catalina.sh", "run"]



Compose.yml file as per below
version: '3.8'

services:
  app_dev:
    build:
      context: .
    env_file:
      - .env.dev
    ports:
      - "8080:8080"

  app_test:
    build:
      context: .
    env_file:
      - .env.test
    ports:
      - "8081:8080"  # Expose on a different port

  app_prod:
    build:
      context: .
    env_file:
      - .env.prod
    ports:
      - "8082:8080"  # Expose on another different port


      I wanted to secure the DB details dont wanted to show easily so tell me the way of securing the DB details 

      I wanted to topest way to secure building the docker image and easy to understand.
