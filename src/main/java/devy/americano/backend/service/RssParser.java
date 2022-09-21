package devy.americano.backend.service;

import devy.americano.backend.domain.News;
import devy.americano.backend.domain.PublisherRss;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class RssParser {
    private final PublisherRss publisherRss;
    private final Document document;

    private RssParser(PublisherRss publisherRss, Document document) {
        this.publisherRss = publisherRss;
        this.document = document;
    }

    public static RssParser rssParser(PublisherRss publisherRss) throws IOException {
        Document document = Jsoup.connect(publisherRss.getRssUrl()).ignoreContentType(true).get();
        return new RssParser(publisherRss, document);
    }

    public List<News> getNewsList() {
        List<News> newsList = new ArrayList<>();
        Elements items = document.getElementsByTag("item");

        int limit = items.size();
        if(10 < limit) {
            limit = 10;
        }

        if(items.size() == 0) {
            return newsList;
        }

        for(int i=0; i<limit; i++) {
            Element item = items.get(i);
            News news = getNews(item);
            newsList.add(news);
        }

        return newsList;
    }

    private News getNews(Element item) {
        News news = new News();
        news.setPublisherRssNo(this.publisherRss.getPublisherRssNo());
        news.setTitle(getTitle(item));
        news.setDescription(getDescription(item));
        news.setLink(getLink(item));
        news.setPubDate(getPubDate(item));
        news.setImage(getImage(item));
        news.setAuthor(getAuthor(item));
        return news;
    }

    private String getTitle(Element item) {
        if(0 < item.getElementsByTag("title").size()) {
            return item.getElementsByTag("title").get(0).text();
        }

        return null;
    }

    private String getDescription(Element item) {
        if(0 < item.getElementsByTag("description").size()) {
            return item.getElementsByTag("description").get(0).text();
        }

        return null;
    }

    private String getLink(Element item) {
        if(0 < item.getElementsByTag("link").size()) {
            return item.getElementsByTag("link").get(0).text();
        }

        return null;
    }

    private LocalDateTime getPubDate(Element item) {
        String pubDate = null;

        if(0 < item.getElementsByTag("pubDate").size()) {
            pubDate = item.getElementsByTag("pubDate").get(0).text();
        }

        if(0 < item.getElementsByTag("dc:date").size()) {
            pubDate = item.getElementsByTag("dc:date").get(0).text();
        }

        return DateFormatter.format(pubDate);
    }

    private String getImage(Element item) {
        if(0 < item.getElementsByTag("image url").size()) {
            return item.getElementsByTag("image url").get(0).text();
        }

        if(0 < item.getElementsByTag("image").size()) {
            return item.getElementsByTag("image").get(0).text();
        }

        if(0 < item.getElementsByTag("media:content").size()) {
            return item.getElementsByTag("media:content").get(0).text();
        }

        if(0 < item.getElementsByTag("media:thumbnail").size()) {
            return item.getElementsByTag("media:thumbnail").get(0).text();
        }

        return null;
    }

    private String getAuthor(Element item) {
        String author = null;
        if(0 < item.getElementsByTag("author").size()) {
            author = item.getElementsByTag("author").get(0).text();
        }

        if(0 < item.getElementsByTag("dc:creator").size()) {
            author = item.getElementsByTag("dc:creator").get(0).text();
        }

        if(author != null && author.matches("[0-9]+")) {
            return null;
        }

        if(author != null && author.trim().equals(publisherRss.getName().trim())) {
            return null;
        }

        return author;
    }
}
