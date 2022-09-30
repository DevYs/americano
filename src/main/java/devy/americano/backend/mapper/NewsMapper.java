package devy.americano.backend.mapper;

import devy.americano.backend.domain.News;
import devy.americano.backend.domain.NewsContents;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.List;

/** 뉴스기사 Mapper */
@Mapper
public interface NewsMapper {

    List<News> selectAllNews();

    List<News> selectNewsNotYet();

    List<NewsContents> selectNewsContents(@Param("limit") int limit, @Param("offset") int offset);

    int insertNews(News news);

    int updateNews(News news);

    int deleteOldNews(@Param("date") LocalDateTime date);
}
