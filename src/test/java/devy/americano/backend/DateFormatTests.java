package devy.americano.backend;

import devy.americano.backend.service.DateFormatter;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

/** 언론사 RSS 정보 관련 쿼리 테스트 */
@SpringBootTest
public class DateFormatTests {
    private final Logger logger = LoggerFactory.getLogger(DateFormatTests.class);

    /** 언론사 RSS 정보가 0건인 경우 실패 */
    @Test
    public void testDateFormat() {
        LocalDateTime format = DateFormatter.format("2022.09.29");
        logger.info(format.toString());
    }

}
