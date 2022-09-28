package devy.americano.backend.controller;

import devy.americano.backend.domain.News;
import devy.americano.backend.domain.NewsContents;
import devy.americano.backend.service.NewsService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/news")
@RequiredArgsConstructor
public class NewsController {

    private final Logger logger = LoggerFactory.getLogger(NewsController.class);

    private final NewsService newsService;

    @GetMapping("/list")
    public List<News> newsList() {
        return newsService.newsList();
    }

    @GetMapping("/search/{pageNo}")
    public List<NewsContents> newsList(@PathVariable("pageNo") Integer pageNo) {
        return newsService.searchNewsContents(pageNo);
    }

}
