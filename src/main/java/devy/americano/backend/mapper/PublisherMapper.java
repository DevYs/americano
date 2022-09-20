package devy.americano.backend.mapper;

import devy.americano.backend.domain.Publisher;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PublisherMapper {

    List<Publisher> selectAllPublisher();

}
