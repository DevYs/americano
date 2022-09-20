package devy.americano.backend.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

/** 언론사 RSS 정보 관련 쿼리 테스트 */
@SpringBootTest
@Transactional
public class PublisherRssServiceTests {

    /** 언론사 RSS 정보 Service */
    @Autowired
    private PublisherRssService publisherRssService;

    /** 언론사 RSS 정보가 0건인 경우 실패 */
    @Test
    public void testRequestRss() {
        publisherRssService.rss();
    }

}
