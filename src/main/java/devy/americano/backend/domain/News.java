package devy.americano.backend.domain;

import devy.americano.backend.service.DateFormatter;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/** 뉴스기사 */
@Getter @Setter @ToString
public class News {
    /** 뉴스기사 식별자 */
    private int newsNo;
    /** 언론사 RSS 식별자 */
    private int publisherRssNo;
    /** 뉴스기사 제목 */
    private String title;
    /** 뉴스기사 원본 링크 */
    private String link;
    /** 뉴스기사 요약 */
    private String description;
    /** 발행일 */
    private LocalDateTime pubDate;
    /** 기사내용 이미지 */
    private String image;
    /** 기자명 */
    private String author;
    /** 카드타입 */
    private int cardType;
    /** 등록일 */
    private LocalDateTime regDate;

    public void setPubDate(String pubDate) throws Exception {
        if(pubDate.contains("T")) {
            this.pubDate = LocalDateTime.parse(pubDate);
        } else {
            this.pubDate = LocalDateTime.parse(pubDate, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        }
    }

    public void setRegDateLDT(LocalDateTime regDate) {
        this.regDate = regDate;
    }

    public void setRegDate(String regDate) {
        regDate = regDate.replace("T", " ");
        this.regDate = LocalDateTime.parse(regDate, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")).plusHours(9);
    }

    public boolean hasNull() {
        if(title == null) { return true; }
        if(link == null) { return true; }
        if(description == null) { return true; }
        if(pubDate == null) { return true; }
        if(image == null) { return true; }
        if(!image.contains("http://") && !image.contains("https://")) { return true; }

        return false;
    }
}
