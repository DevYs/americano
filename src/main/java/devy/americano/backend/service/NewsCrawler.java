package devy.americano.backend.service;

import devy.americano.backend.domain.News;
import devy.americano.backend.domain.PublisherRss;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class NewsCrawler {
    private final PublisherRss publisherRss;
    private final News news;
    private final Document document;

    private NewsCrawler(PublisherRss publisherRss, News news, Document document) {
        this.publisherRss = publisherRss;
        this.news = news;
        this.document = document;
    }

    public static NewsCrawler newsCrawler(PublisherRss publisherRss, News news) {
        try {
            return new NewsCrawler(publisherRss, news, Jsoup.connect(news.getLink()).get());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public NewsCrawler image() throws NullPointerException {
        if(news.getImage() != null && 0 < news.getImage().trim().length()) {
            return this;
        }

        if(0 < document.select("meta[property='og:image']").size()) {
            String content = document.select("meta[property='og:image']").get(0).attr("content");
            if(0 < content.trim().length()) {
                news.setImage(content);
                return this;
            }
        }

        return this;
    }

    public NewsCrawler author() throws NullPointerException {
        if(news.getAuthor() != null && 0 < news.getAuthor().trim().length()) {
            return this;
        }

        if(0 < document.select("meta[property='dd:author']").size()) {
            String content = document.select("meta[property='dd:author']").get(0).attr("content");
            if(0 < content.trim().length() && !content.matches("[0-9]+")) {
                news.setAuthor(content);
                return this;
            }
        }

        if(0 < document.select("meta[property='dable:author']").size()) {
            String content = document.select("meta[property='dable:author']").get(0).attr("content");
            if(0 < content.trim().length() && !content.matches("[0-9]+")) {
                news.setAuthor(content);
                return this;
            }
        }

        if(0 < document.select("meta[name='dable:author']").size()) {
            String content = document.select("meta[name='dable:author']").get(0).attr("content");
            if(0 < content.trim().length() && !content.matches("[0-9]+")) {
                news.setAuthor(content);
                return this;
            }
        }

        if(0 < document.select("meta[property='og:article:author']").size()) {
            String content = document.select("meta[property='og:article:author']").get(0).attr("content");
            if(0 < content.trim().length() && !content.matches("[0-9]+")) {
                news.setAuthor(content);
                return this;
            }
        }

        if(news.getAuthor() != null && news.getAuthor().trim().equals(publisherRss.getName().trim())) {
            news.setAuthor(null);
        }

        return this;
    }

    public NewsCrawler pubDate() throws NullPointerException {
        if(news.getPubDate() != null) {
            return this;
        }

        if(0 < document.select("meta[property='article:published_time']").size()) {
            String content = document.select("meta[property='article:published_time']").get(0).attr("content");
            if(0 < content.trim().length()) {
                news.setPubDate(DateFormatter.format(content));
                return this;
            }
        }

        return this;
    }

}
