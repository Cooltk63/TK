# Generate cluster id
docker run --rm apache/kafka:3.7.0 kafka-storage random-uuid
# Example output: XyZCluster123Id

# Start container once
docker compose up -d kafka

# Format storage inside container (replace with your cluster id)
docker exec -it kafka kafka-storage format \
  --config /opt/kafka/config/kraft/server.properties \
  --cluster-id XyZCluster123Id

# Restart broker
docker compose restart kafka



package com.example.demo.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    @Bean
    public NewTopic devTopic() {
        return TopicBuilder.name("dev-topic")
                .partitions(3)
                .replicas(1)
                .build();
    }
}