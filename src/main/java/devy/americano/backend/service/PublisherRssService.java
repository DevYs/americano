package devy.americano.backend.service;

import devy.americano.backend.domain.News;
import devy.americano.backend.domain.PublisherRss;
import devy.americano.backend.mapper.PublisherRssMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/** 언론사 RSS 요청 및 관련 처리 */
@RequiredArgsConstructor
@Service
public class PublisherRssService {

    private final Logger logger = LoggerFactory.getLogger(PublisherRssService.class);

    /** 언론사 RSS 정보 Mapper */
    private final PublisherRssMapper publisherRssMapper;

    public List<News> rss() {
        List<News> newsList = new ArrayList<>();
        List<PublisherRss> publisherRssList = publisherRssMapper.selectAllPublisherRss();
        RssParser rssParser;

        for(PublisherRss publisherRss : publisherRssList) {
            if(publisherRss.getPublisherRssNo() == 5
                    || publisherRss.getPublisherRssNo() == 9
                    || publisherRss.getPublisherRssNo() == 17
                    || publisherRss.getPublisherRssNo() == 35
                    || publisherRss.getPublisherRssNo() == 45
            ) {
                continue;
            }

            logger.info("Publisher " + publisherRss.getName());
            logger.info("RssUrl " + publisherRss.getRssUrl());
            try {
                rssParser = RssParser.rssParser(publisherRss);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }

            List<News> list = rssParser.getNewsList();
            for(News news : list) {
                if(news.getLink() != null && !news.getLink().trim().isEmpty()) {
                    try {
                        NewsCrawler.newsCrawler(publisherRss, news).image().author().pubDate();
                    } catch(NullPointerException e) {
                        logger.info("Error to Crawling : " + news.getLink());
                        e.printStackTrace();
                    }
                }
            }

            newsList.addAll(list);
        }

        List<News> list = newsList.stream().filter(news -> !news.hasNull()).collect(Collectors.toList());
        Collections.shuffle(list);

        logger.info("========================================================================");
        logger.info("========================================================================");
        logger.info("========================================================================");
        logger.info("뉴스 수집 완료");
        logger.info("========================================================================");
        logger.info("========================================================================");
        logger.info("========================================================================");
        return list;
    }

}
