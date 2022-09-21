package devy.americano.backend.service;

import devy.americano.backend.domain.News;
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

    public void insertNewsList(List<News> newsList) {
        for(News news : newsList) {
            newsMapper.insertNews(news);
        }
    }

}
