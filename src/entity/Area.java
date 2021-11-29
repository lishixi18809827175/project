package entity;

public class Area {
    private Integer id;
    private String area;

    public Area() {
    }

    public Area(Integer id, String area) {
        this.id = id;
        this.area = area;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    @Override
    public String toString() {
        return "Area{" +
                "id=" + id +
                ", area='" + area + '\'' +
                '}';
    }
}
