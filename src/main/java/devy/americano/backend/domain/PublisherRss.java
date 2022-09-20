package devy.americano.backend.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * 언론사 RSS Data
 */
@Getter @Setter @ToString
public class PublisherRss {
    /** 언론사 RSS 식별자 */
    private int publisherRssNo;
    /** 파비콘 URL */
    private String favicon;
    /** 언론사명 */
    private String name;
    /** 언론사 RSS URL */
    private String rssUrl;
    /** 등록일 */
    private LocalDateTime regDate;
    /** 수정일 */
    private LocalDateTime modDate;
}
