package devy.americano.backend.service;

import devy.americano.backend.mapper.NewsMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 뉴스관련 처리
 */
@RequiredArgsConstructor
@Service
public class NewsService {

    private final NewsMapper NewsMapper;

}
