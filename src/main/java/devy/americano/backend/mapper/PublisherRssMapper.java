package devy.americano.backend.mapper;

import devy.americano.backend.domain.PublisherRss;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/** 언론사 RSS 정보 Mapper */
@Mapper
public interface PublisherRssMapper {

    /**
     * 언론사 RSS 정보를 조회한다.
     * @return 언론사 RSS 정보
     */
    PublisherRss selectPublisherRssByNo(Integer rssNo);

    /**
     * 모든 언론사 RSS 정보를 조회한다.
     * @return 모든 언론사 RSS 정보
     */
    List<PublisherRss> selectAllPublisherRss();

}
