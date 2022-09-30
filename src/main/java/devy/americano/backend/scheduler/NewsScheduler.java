package devy.americano.backend.scheduler;

import devy.americano.backend.domain.News;
import devy.americano.backend.service.NewsService;
import devy.americano.backend.service.PublisherRssService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@EnableScheduling
@RequiredArgsConstructor
public class NewsScheduler {

    private final Logger logger = LoggerFactory.getLogger(NewsScheduler.class);

    private final long INITIAL_DELAY_VALUE = 5 * 60 * 1000;
    private final long FIXED_RATE_VALUE = 10 * 60 * 1000;

    private final PublisherRssService publisherRssService;

    private final NewsService newsService;

    public static boolean isCrawling = false;

    @Scheduled(fixedRate = FIXED_RATE_VALUE, initialDelay = INITIAL_DELAY_VALUE)
    public void run() {
        List<News> newsList = publisherRssService.rss();
        newsService.insertNewsList(newsList);
    }

}
