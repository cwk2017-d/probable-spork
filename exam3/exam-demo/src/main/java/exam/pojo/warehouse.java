package exam.pojo;

public class warehouse {
    private String id;
    private String name;
    private int guige;
    private String caizhi;
    private String gongyingshang;
    private String logo;
    private String wuzifenlei;

    @Override
    public String toString() {
        return "warehouse{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", guige='" + guige + '\'' +
                ", caizhi='" + caizhi + '\'' +
                ", gongyingshang='" + gongyingshang + '\'' +
                ", logo='" + logo + '\'' +
                ", wuzifenlei='" + wuzifenlei + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getGongyingshang() {
        return gongyingshang;
    }

    public void setGongyingshang(String gongyingshang) {
        this.gongyingshang = gongyingshang;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getWuzifenlei() {
        return wuzifenlei;
    }

    public void setWuzifenlei(String wuzifenlei) {
        this.wuzifenlei = wuzifenlei;
    }
}
