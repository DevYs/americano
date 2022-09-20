package devy.americano.backend.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class NewsMapperTests {

    @Autowired
    private NewsMapper newsMapper;

}
