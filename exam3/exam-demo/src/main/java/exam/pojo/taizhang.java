package exam.pojo;

public class taizhang {
    private String id;
    private String name;
    private int guige;
    private String caizhi;
    private String gongyingshang;
    private String pingpai;
    private String wuzifenlei;
    private String caozuo;
    private int shuliang;
    private  String jiliang;
    private String cunfang;

    @Override
    public String toString() {
        return "taizhang{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", guige=" + guige +
                ", caizhi='" + caizhi + '\'' +
                ", gongyingshang='" + gongyingshang + '\'' +
                ", pingpai='" + pingpai + '\'' +
                ", wuzifenlei='" + wuzifenlei + '\'' +
                ", caozuo='" + caozuo + '\'' +
                ", shuliang=" + shuliang +
                ", jiliang='" + jiliang + '\'' +
                ", cunfang='" + cunfang + '\'' +
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

    public String getPingpai() {
        return pingpai;
    }

    public void setPingpai(String pingpai) {
        this.pingpai = pingpai;
    }

    public String getWuzifenlei() {
        return wuzifenlei;
    }

    public void setWuzifenlei(String wuzifenlei) {
        this.wuzifenlei = wuzifenlei;
    }

    public String getCaozuo() {
        return caozuo;
    }

    public void setCaozuo(String caozuo) {
        this.caozuo = caozuo;
    }

    public int getShuliang() {
        return shuliang;
    }

    public void setShuliang(int shuliang) {
        this.shuliang = shuliang;
    }

    public String getJiliang() {
        return jiliang;
    }

    public void setJiliang(String jiliang) {
        this.jiliang = jiliang;
    }

    public String getCunfang() {
        return cunfang;
    }

    public void setCunfang(String cunfang) {
        this.cunfang = cunfang;
    }
}
