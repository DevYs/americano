package devy.americano.backend.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class News {
    private int newsNo;
    private String title;
    private String link;
    private String description;
    private LocalDateTime pubDate;
    private String image;
    private String author;
    private LocalDateTime regDate;
}
