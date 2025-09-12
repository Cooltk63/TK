version: '3.8'
services:
  kafka:
    image: apache/kafka:3.7.0
    container_name: kafka
    hostname: kafka
    ports:
      - "9092:9092"   # client connections
      - "9093:9093"   # controller listener
    environment:
      KAFKA_PROCESS_ROLES: broker,controller
      KAFKA_NODE_ID: 1
      KAFKA_CONTROLLER_QUORUM_VOTERS: "1@kafka:9093"
      KAFKA_LISTENERS: PLAINTEXT://0.0.0.0:9092,CONTROLLER://0.0.0.0:9093
      KAFKA_LISTENER_SECURITY_PROTOCOL_MAP: PLAINTEXT:PLAINTEXT,CONTROLLER:PLAINTEXT
      KAFKA_INTER_BROKER_LISTENER_NAME: PLAINTEXT
      KAFKA_CONTROLLER_LISTENER_NAMES: CONTROLLER
      # we advertise localhost:9092 for clients running on host
      KAFKA_ADVERTISED_LISTENERS: PLAINTEXT://localhost:9092
    volumes:
      - kafka-data:/var/lib/kafka/data

volumes:
  kafka-data: