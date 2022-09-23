package devy.americano.backend.service;

import devy.americano.backend.domain.News;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/** 언론사 RSS 정보 관련 쿼리 테스트 */
@SpringBootTest
@Transactional
public class PublisherRssServiceTests {

    /** 언론사 RSS 정보 Service */
    @Autowired
    private PublisherRssService publisherRssService;

    @Autowired
    private NewsService newsService;

    /** 언론사 RSS 정보가 0건인 경우 실패 */
    @Test
    public void testRequestRss() {
        List<News> newsList = publisherRssService.rss();
        newsService.insertNewsList(newsList);
    }

}
