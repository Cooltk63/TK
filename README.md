version: "3.8"

services:
  tomcat-app:
    build:
      context: .
      dockerfile: Dockerfile
    container_name: springboot-tomcat-dev
    ports:
      - "8080:8080"
    environment:
      DB_USERNAME: ${DB_USERNAME}
      DB_PASSWORD: ${DB_PASSWORD}
      DB_HOST: ${DB_HOST}
      DB_PORT: ${DB_PORT}
      DB_SID: ${DB_SID}
      SPRING_PROFILES_ACTIVE: ${SPRING_PROFILE}




# Environment-specific variables
DB_USERNAME=devUser
DB_PASSWORD=devPass
DB_HOST=10.67.78.89
DB_PORT=1522
DB_SID=DEVDB
SPRING_PROFILE=dev



docker-compose -f docker-compose.dev.yml --env-file .env.dev up --build