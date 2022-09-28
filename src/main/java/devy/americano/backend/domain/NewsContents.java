package devy.americano.backend.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter @Setter @ToString
public class NewsContents {
    /** 뉴스기사 식별자 */
    private int newsNo;
    /** 언론사 이름 */
    private String publisher;
    /** 언론사 favicon */
    private String favicon;
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

    public void setPubDate(String pubDate) throws Exception {
        if(pubDate.contains("T")) {
            this.pubDate = LocalDateTime.parse(pubDate);
        } else {
            this.pubDate = LocalDateTime.parse(pubDate, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        }
    }
}
