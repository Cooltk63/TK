import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class TestLoggerService {
    private static final Logger logger = LoggerFactory.getLogger(TestLoggerService.class);

    public void logFramework() {
        logger.info("Logger class: " + logger.getClass().getName());
    }
}