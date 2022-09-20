package devy.americano.backend.mapper;

import devy.americano.backend.domain.PublisherRss;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/** 언론사 RSS 정보 관련 쿼리 테스트 */
@SpringBootTest
@Transactional
public class PublisherRssMapperTests {

    /** 언론사 RSS 정보 Mapper */
    @Autowired
    private PublisherRssMapper publisherRssMapper;

    /** 언론사 RSS 정보가 0건인 경우 실패 */
    @Test
    public void testSelectAllPublisher() {
        // 모든 언론사 RSS 정보 조회
        List<PublisherRss> publishers = publisherRssMapper.selectAllPublisherRss();
        // 조회된 목록이 0건인지 테스트
        Assertions.assertNotEquals(publishers.size(), 0);
    }

}
