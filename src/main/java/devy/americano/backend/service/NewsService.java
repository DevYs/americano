package devy.americano.backend.service;

import devy.americano.backend.domain.News;
import devy.americano.backend.domain.NewsContents;
import devy.americano.backend.mapper.NewsMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 뉴스관련 처리
 */
@RequiredArgsConstructor
@Service
public class NewsService {
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

}
