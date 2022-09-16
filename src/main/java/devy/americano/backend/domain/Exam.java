package devy.americano.backend.domain;

public class Exam {

    private int examNo;
    private String examName;

    public int getExamNo() {
        return examNo;
    }

    public void setExamNo(int examNo) {
        this.examNo = examNo;
    }

    public String getExamName() {
        return examName;
    }

    public void setExamName(String examName) {
        this.examName = examName;
    }

    @Override
    public String toString() {
        return "Exam{" +
                "examNo=" + examNo +
                ", examName='" + examName + '\'' +
                '}';
    }
}