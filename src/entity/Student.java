package entity;

public class Student {
    private Integer id;
    private String name;
    private Integer age;
    private String gender;
    private Integer banjiId;

    public Student() {
        // this(1, "zhangsan", 23, "男");
        // new Student(1, "zhangsan", 23, "男");
    }

    public Student(Integer id, String name, Integer age, String gender) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    public Student(Integer id, String name, Integer age, String gender, Integer banjiId) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.banjiId = banjiId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getBanjiId() {
        return banjiId;
    }

    public void setBanjiId(Integer banjiId) {
        this.banjiId = banjiId;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", banjiId=" + banjiId +
                '}';
    }
}
