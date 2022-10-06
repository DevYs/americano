package devy.americano.backend.service;

import devy.americano.backend.domain.News;
import devy.americano.backend.domain.NewsContents;
import devy.americano.backend.domain.PublisherRss;
import devy.americano.backend.mapper.NewsMapper;
import devy.americano.backend.mapper.PublisherRssMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 뉴스관련 처리
 */
@RequiredArgsConstructor
@Service
public class NewsService {
    private final Logger logger = LoggerFactory.getLogger(NewsService.class);
    private final PublisherRssMapper publisherRssMapper;
    private final NewsMapper newsMapper;

    private boolean inAddingNews = false;

    private final Random random = new Random();

    private final List<News> beforeUpdate = new ArrayList<>();

    public List<News> newsList() {
        return newsMapper.selectAllNews();
    }

    public void newsCrawling() {
        if(inAddingNews) {
            return;
        }

        List<News> newsList = newsMapper.selectNewsNotYet();
        if(newsList.size() == 0) {
            return;
        }

        if(beforeUpdate.size() == 20) {
            for(News news : beforeUpdate) {
                newsMapper.updateNews(news);
            }

            beforeUpdate.clear();

            return;
        }

        News news = newsList.get(0);
        PublisherRss publisherRss = publisherRssMapper.selectPublisherRssByNo(news.getPublisherRssNo());

        if(news.getLink() != null && !news.getLink().trim().isEmpty()) {
            try {
                logger.info(news.getLink());
                NewsCrawler.newsCrawler(publisherRss, news).image().author().pubDate();
                news.setRegDateLDT(LocalDateTime.now());
                news.setCardType(random.nextInt(10) < 3 ? 1 : 2);
                beforeUpdate.add(news);
            } catch(Exception e) {
                logger.info("Error to Crawling : " + news.getLink());
                e.printStackTrace();
                news.setCardType(-1);
                newsMapper.updateNews(news);
            }
        }
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

        inAddingNews = true;

        for(News news : newsList) {
            newsMapper.insertNews(news);
        }

        inAddingNews = false;
    }

    public void removeOldNews() {
        int removeBefore = 6;
        LocalDateTime date = LocalDateTime.now().minusHours(9 + removeBefore);

        if(inAddingNews) {
            return;
        }

        newsMapper.deleteOldNews(date);
    }

}
