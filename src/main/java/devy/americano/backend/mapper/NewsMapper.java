package devy.americano.backend.mapper;

import devy.americano.backend.domain.News;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/** 뉴스기사 Mapper */
@Mapper
public interface NewsMapper {

    List<News> selectNews();

}
