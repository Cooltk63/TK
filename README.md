version: "3.9"

services:
  # ====================
  # Redis Service
  # ====================
  redis:
    image: redis/redis-stack-server:latest
    command: ["redis-server", "--appendonly", "yes"]
    networks:
      - appnet
    ports:
      - "6379:6379"   # expose in dev for testing
    profiles: ["dev", "prod"]

  # ====================
  # API Gateway
  # ====================
  api-gateway:
    build:
      context: ./api-gateway
      dockerfile: Dockerfile
    image: api-gateway:latest
    depends_on:
      - redis
      - product-service
      - fincore-service
    environment:
      - SPRING_PROFILES_ACTIVE=${SPRING_PROFILES_ACTIVE}
      - SPRING_DATA_REDIS_HOST=redis
      - SPRING_DATA_REDIS_PORT=6379
    networks:
      - appnet
    ports:
      - "8080:8080"  # expose only gateway
    profiles: ["dev", "prod"]

  # ====================
  # Product Service
  # ====================
  product-service:
    build:
      context: ./product-service
      dockerfile: Dockerfile
    image: product-service:latest
    environment:
      - SPRING_PROFILES_ACTIVE=${SPRING_PROFILES_ACTIVE}
      - GATEWAY_BASE_URL=http://api-gateway:8080
      - SPRING_DATA_REDIS_HOST=redis
      - SPRING_DATA_REDIS_PORT=6379
    networks:
      - appnet
    depends_on:
      - redis
    # Only expose ports in dev for debugging
    ports:
      - "8081:8080"
    profiles: ["dev", "prod"]

  # ====================
  # Fincore Service
  # ====================
  fincore-service:
    build:
      context: ./fincore-service
      dockerfile: Dockerfile
    image: fincore-service:latest
    environment:
      - SPRING_PROFILES_ACTIVE=${SPRING_PROFILES_ACTIVE}
      - GATEWAY_BASE_URL=http://api-gateway:8080
      - SPRING_DATA_REDIS_HOST=redis
      - SPRING_DATA_REDIS_PORT=6379
    networks:
      - appnet
    depends_on:
      - redis
    ports:
      - "8089:8080"
    profiles: ["dev", "prod"]

networks:
  appnet:
    driver: bridge