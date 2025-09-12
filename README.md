[12/09, 3:53 pm] Falguni Nakhwa - TCS: import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/kafka")
public class KafkaController {
    private final KafkaProducer producer;

    public KafkaController(KafkaProducer producer) {
        this.producer = producer;
    }

    @GetMapping("/publish")
    public String publishMessage(@RequestParam String message) {
        producer.sendMessage(message);
        return "Message sent: " + message;
    }
}
[12/09, 3:53 pm] Falguni Nakhwa - TCS: import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaTopicConfig {
    @Bean
    public NewTopic createTopic() {
        return new NewTopic("my_topic", 1, (short) 1);
    }
}