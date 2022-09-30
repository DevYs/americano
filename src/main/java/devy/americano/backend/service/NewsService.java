package devy.americano.backend.service;

import devy.americano.backend.domain.News;
import devy.americano.backend.domain.NewsContents;
import devy.americano.backend.mapper.NewsMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 뉴스관련 처리
 */
@RequiredArgsConstructor
@Service
public class NewsService {
    private final Logger logger = LoggerFactory.getLogger(NewsService.class);
    private final NewsMapper newsMapper;

    public List<News> newsList() {
        return newsMapper.selectAllNews();
    }

    public List<NewsContents> searchNewsContents(int pageNo) {
        int limit = 20;
        int offset = (pageNo - 1) * limit;
        return newsMapper.selectNewsContents(limit, offset);
    }

    public void insertNewsList(List<News> newsList) {
        if(newsList == null || newsList.size() == 0) {
            return;
        }

        for(News news : newsList) {
            newsMapper.insertNews(news);
        }
    }

    public void removeOldNews() {
        int removeBefore = 6;
        LocalDateTime date = LocalDateTime.now().minusHours(9 + removeBefore);
        newsMapper.deleteOldNews(date);
    }

}
