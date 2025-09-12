[12/09, 3:52 pm] Falguni Nakhwa - TCS: import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;

    public KafkaProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String message) {
        kafkaTemplate.send("my_topic", message);
        System.out.println("Produced message: " + message);
    }
}
[12/09, 3:52 pm] Falguni Nakhwa - TCS: import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {
    @KafkaListener(topics = "my_topic", groupId = "my-group")
    public void consume(String message) {
        System.out.println("Consumed message: " + message);
    }
}