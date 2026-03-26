package exam.pojo;

public class material {
    private String number;
    private String wuzifenlei;
    private String name;
    private int guige;
    private String caizhi;

    @Override
    public String toString() {
        return "material{" +
                "id='" + number + '\'' +
                ", wuzifenlei='" + wuzifenlei + '\'' +
                ", name='" + name + '\'' +
                ", guige=" + guige +
                ", caizhi='" + caizhi + '\'' +
                '}';
    }

    public String getId() {
        return number;
    }

    public void setId(String id) {
        this.number = id;
    }

    public String getWuzifenlei() {
        return wuzifenlei;
    }

    public void setWuzifenlei(String wuzifenlei) {
        this.wuzifenlei = wuzifenlei;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGuige() {
        return guige;
    }

    public void setGuige(int guige) {
        this.guige = guige;
    }

    public String getCaizhi() {
        return caizhi;
    }

    public void setCaizhi(String caizhi) {
        this.caizhi = caizhi;
    }
}
