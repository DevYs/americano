package devy.americano.backend.service;

import devy.americano.backend.domain.News;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
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

    /** 언론사 RSS 정보 parsing 테스트 */
    @Test
    public void testRequestRssByNo() {
        Logger logger = LoggerFactory.getLogger(PublisherRssServiceTests.class);
        List<News> newsList = publisherRssService.rssByNo(45);
        for(News news : newsList) {
            logger.info(news.toString());
        }
    }

    @Test
    public void testUtcTimeAnyMatch() {
        Integer[] utcTime = new Integer[] { 33, 49 };
        Assertions.assertTrue(Arrays.stream(utcTime).anyMatch(rssNo -> 33 == rssNo));
        Assertions.assertTrue(Arrays.stream(utcTime).anyMatch(rssNo -> 49 == rssNo));
        Assertions.assertNotEquals(Arrays.stream(utcTime).anyMatch(rssNo -> 40 == rssNo), true);
    }

}
