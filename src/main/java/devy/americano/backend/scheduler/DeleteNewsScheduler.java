package devy.americano.backend.scheduler;

import devy.americano.backend.service.NewsService;
import devy.americano.backend.service.PublisherRssService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
@RequiredArgsConstructor
public class DeleteNewsScheduler {
    private final Logger logger = LoggerFactory.getLogger(DeleteNewsScheduler.class);

    private final long INITIAL_DELAY_VALUE = 5 * 60 * 1000;
    private final long FIXED_RATE_VALUE = 60 * 60 * 1000;

    private final PublisherRssService publisherRssService;

    private final NewsService newsService;

    @Scheduled(fixedRate = FIXED_RATE_VALUE, initialDelay = INITIAL_DELAY_VALUE)
    public void run() {
        if(publisherRssService.isCrawling()) {
            return;
        }
        newsService.removeOldNews();
    }

}
