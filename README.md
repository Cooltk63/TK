# Use Red Hat UBI with OpenJDK 17 (if accessible internally)
FROM registry.access.redhat.com/ubi8/openjdk-17:1.17

# Set working directory inside container
WORKDIR /app

# Set environment variable (optional)
ENV SPRING_PROFILES_ACTIVE=prod

# Copy JAR file into the container
COPY target/product-service.jar app.jar

# Run your Spring Boot app
CMD ["java", "-jar", "app.jar"]