package devy.americano.backend.service;

import devy.americano.backend.domain.News;
import devy.americano.backend.domain.PublisherRss;
import devy.americano.backend.mapper.PublisherRssMapper;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/** 언론사 RSS 요청 및 관련 처리 */
@RequiredArgsConstructor
@Service
public class PublisherRssService {

    private final Logger logger = LoggerFactory.getLogger(PublisherRssService.class);

    /** 언론사 RSS 정보 Mapper */
    private final PublisherRssMapper publisherRssMapper;

    public List<News> rss() {
        List<PublisherRss> publisherRssList = publisherRssMapper.selectAllPublisherRss();
        PublisherRss publisherRss = publisherRssList.get(1);
        try {
            RssParser rssParser = RssParser.rssParser(publisherRss.getRssUrl());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

}
