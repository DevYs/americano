package devy.americano.backend.mapper;

import devy.americano.backend.domain.Exam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ExamMapper {

    List<Exam> selectExam();

    int insertExam();

}
