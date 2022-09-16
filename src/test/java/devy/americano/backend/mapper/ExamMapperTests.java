package devy.americano.backend.mapper;

import devy.americano.backend.domain.Exam;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ExamMapperTests {

    @Autowired
    private ExamMapper examMapper;

    public void insertTestData() {
        examMapper.insertExam();
    }

    @Test
    public void testSelectExam() {
        insertTestData();
        List<Exam> exams = examMapper.selectExam();

        Assertions.assertNotEquals(exams.size(), 0);
    }

}
