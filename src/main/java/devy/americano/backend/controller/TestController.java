package devy.americano.backend.controller;

import devy.americano.backend.domain.PublisherRss;
import devy.americano.backend.mapper.PublisherRssMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class TestController {

    private final Logger logger = LoggerFactory.getLogger(TestController.class);

    private final PublisherRssMapper publisherRssMapper;

    @GetMapping("/rss")
    public List<PublisherRss> publisherRssList() {
        return publisherRssMapper.selectAllPublisherRss();
    }

}
