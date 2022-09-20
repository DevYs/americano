package devy.americano.backend.mapper;

import devy.americano.backend.domain.Publisher;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Transactional
public class PublisherMapperTests {

    @Autowired
    private PublisherMapper publisherMapper;

    /** 언론사 RSS 정보가 0건인 경우 실패 */
    @Test
    public void testSelectAllPublisher() {
        List<Publisher> publishers = publisherMapper.selectAllPublisher();
        Assertions.assertNotEquals(publishers.size(), 0);
    }

}
