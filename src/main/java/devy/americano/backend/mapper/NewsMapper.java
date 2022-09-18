package devy.americano.backend.mapper;

import devy.americano.backend.domain.News;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NewsMapper {

    List<News> selectNews();

}
