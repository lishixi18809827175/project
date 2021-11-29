package entity;

public class User {
    private Integer id;
    private String name;
    private String password;
    private Integer age;
    private Integer level;

    public User() {
    }

    public User(Integer id, String name, String password, Integer age, Integer level) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.age = age;
        this.level = level;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", age=" + age +
                ", level=" + level +
                '}';
    }
}
