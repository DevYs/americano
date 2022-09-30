package devy.americano.backend.service;

import devy.americano.backend.domain.News;
import devy.americano.backend.domain.PublisherRss;
import devy.americano.backend.mapper.PublisherRssMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

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
            logger.info("Publisher " + publisherRss.getName());
            logger.info("RssUrl " + publisherRss.getRssUrl());
            try {
                rssParser = RssParser.rssParser(publisherRss);
                if(rssParser == null) {
                    throw new Exception();
                }
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }

            List<News> list = rssParser.getNewsList();
            logger.info("Size " + list.size());
            newsList.addAll(list);
        }

        Collections.shuffle(newsList);

        logger.info("========================================================================");
        logger.info("========================================================================");
        logger.info("========================================================================");
        logger.info("뉴스 수집 완료");
        logger.info("========================================================================");
        logger.info("========================================================================");
        logger.info("========================================================================");
        return newsList;
    }

    public List<News> rssByNo(int rssNo) {
        PublisherRss publisherRss = publisherRssMapper.selectPublisherRssByNo(rssNo);
        RssParser rssParser = null;

        logger.info("Publisher " + publisherRss.getName());
        logger.info("RssUrl " + publisherRss.getRssUrl());
        try {
            rssParser = RssParser.rssParser(publisherRss);
            if(rssParser == null) {
                throw new Exception();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        List<News> newsList = rssParser.getNewsList();
        for(News news : newsList) {
            if(news.getLink() != null && !news.getLink().trim().isEmpty()) {
                try {
                    NewsCrawler.newsCrawler(publisherRss, news).image().author().pubDate();
                } catch(Exception e) {
                    logger.info("Error to Crawling : " + news.getLink());
                    e.printStackTrace();
                }
            }
        }

        Collections.shuffle(newsList.stream().filter(news -> !news.hasNull()).collect(Collectors.toList()));
        logger.info("뉴스 수집 완료");

        return newsList;
    }

    public List<PublisherRss> allPublisherRssList() {
        return publisherRssMapper.selectAllPublisherRss();
    }

}
