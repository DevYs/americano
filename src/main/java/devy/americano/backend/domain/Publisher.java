package devy.americano.backend.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * 언론사 RSS Data
 */
@Getter @Setter @ToString
public class Publisher {
    private int publisherNo;
    private String name;
    private String rssUrl;
    private LocalDateTime regDate;
    private LocalDateTime modDate;
}
