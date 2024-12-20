# Use the Tomcat image as the base
FROM tomcat:latest

# Set the working directory inside the container
WORKDIR /usr/local/tomcat/webapps/

# Copy the WAR file from the relative build context into the container
COPY target/dockerDemo.war /usr/local/tomcat/webapps/dockerDemo.war

# Expose the Tomcat port
EXPOSE 8080

# Start the Tomcat server
CMD ["catalina.sh", "run"]