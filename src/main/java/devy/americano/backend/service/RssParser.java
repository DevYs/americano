package devy.americano.backend.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;

public class RssParser {
    private final Document document;

    private RssParser(Document document) {
        this.document = document;
    }

    public static RssParser rssParser(String url) throws IOException {
        return new RssParser(Jsoup.parse(new URL(url), 3000));
    }

    public String getTitle() {
        return null;
    }

    public String getLink() {
        return null;
    }

    public LocalDateTime getPubDate() {
        return null;
    }

    public String getImage() {
        return null;
    }
}
