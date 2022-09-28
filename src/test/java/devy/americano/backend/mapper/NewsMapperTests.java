package devy.americano.backend.mapper;

import devy.americano.backend.domain.News;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@SpringBootTest
@Transactional
public class NewsMapperTests {

    @Autowired
    private NewsMapper newsMapper;

    @Test
    public void testInsertNews() throws Exception {
        News news = new News();
        news.setTitle("테스트");
        news.setLink("https://www.test.com");
        news.setDescription("test");
        news.setAuthor("test");
        news.setImage("https://www.test.com/image.jpg");
        news.setPubDate(LocalDateTime.now().toString());

        newsMapper.insertNews(news);
        newsMapper.insertNews(news);
    }

}
