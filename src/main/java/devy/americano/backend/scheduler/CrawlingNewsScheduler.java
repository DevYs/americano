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
public class CrawlingNewsScheduler {
    private final Logger logger = LoggerFactory.getLogger(CrawlingNewsScheduler.class);

    private final long FIXED_RATE_VALUE = 1 * 1000;

    private final NewsService newsService;

    @Scheduled(fixedRate = FIXED_RATE_VALUE)
    public void run() {
        newsService.newsCrawling();
    }

}
