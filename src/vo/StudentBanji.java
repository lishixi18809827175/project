package vo;

public class StudentBanji {
    private Integer studentId;
    private String studentName;
    private Integer studentAge;
    private String studentGender;
    private String banjiName;

    public StudentBanji() {
    }

    public StudentBanji(Integer studentId, String studentName, Integer studentAge, String studentGender, String banjiName) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.studentAge = studentAge;
        this.studentGender = studentGender;
        this.banjiName = banjiName;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Integer getStudentAge() {
        return studentAge;
    }

    public void setStudentAge(Integer studentAge) {
        this.studentAge = studentAge;
    }

    public String getStudentGender() {
        return studentGender;
    }

    public void setStudentGender(String studentGender) {
        this.studentGender = studentGender;
    }

    public String getBanjiName() {
        return banjiName;
    }

    public void setBanjiName(String banjiName) {
        this.banjiName = banjiName;
    }

    @Override
    public String toString() {
        return "StudentBanji{" +
                "studentId=" + studentId +
                ", studentName='" + studentName + '\'' +
                ", age=" + studentAge +
                ", studentGender='" + studentGender + '\'' +
                ", banjiName='" + banjiName + '\'' +
                '}';
    }
}
