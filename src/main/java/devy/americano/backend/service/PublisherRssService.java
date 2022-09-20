package devy.americano.backend.service;

import devy.americano.backend.mapper.PublisherRssMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/** 언론사 RSS 요청 및 관련 처리 */
@RequiredArgsConstructor
@Service
public class PublisherRssService {

    /** 언론사 RSS 정보 Mapper */
    private final PublisherRssMapper publisherRssMapper;




}
