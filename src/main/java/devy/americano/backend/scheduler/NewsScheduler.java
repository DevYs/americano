package devy.americano.backend.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class NewsScheduler {

    private final Logger logger = LoggerFactory.getLogger(NewsScheduler.class);

    private final long FIXED_RATE_VALUE = 5 * 60 * 1000;

    @Scheduled(fixedRate = FIXED_RATE_VALUE)
    public void run() {
    }

}
