package devy.americano.backend.scheduler;

import devy.americano.backend.service.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
@RequiredArgsConstructor
public class NewsRemoveScheduler {
    private final long FIXED_RATE_VALUE = 1 * 60 * 1000;

    private final NewsService newsService;

    @Scheduled(fixedRate = FIXED_RATE_VALUE)
    public void run() {
        newsService.removeOldNews();
    }

}
