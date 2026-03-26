package exam.mapper;

import exam.pojo.material;
import exam.pojo.pass;
import exam.pojo.taizhang;
import exam.pojo.warehouse;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    pass selectpass(@Param("id") String id, @Param("password") String password);
    int warehouseadd(@Param("id") String id,@Param("name") String name,@Param("guige") int guige,@Param("caizhi") String caizhi,@Param("gongyingshang") String gongyingshang,@Param("logo") String logo,@Param("wuzifenlei") String wuzifenlei);
    int warehousedelete(@Param("id") String id);
    warehouse selectwarehouse(@Param("id") String id);
    int warehouseedit(@Param("id") String id,@Param("name") String name,@Param("guige") int guige,@Param("caizhi") String caizhi,@Param("gongyingshang") String gongyingshang,@Param("logo") String logo,@Param("wuzifenlei") String wuzifenlei);
    int materialadd(@Param("number") String number,@Param("wuzufenlei") String wuzufenlei,@Param("name") String name,@Param("guige") int guige,@Param("caizhi") String caizhi);
    material selectmaterial(@Param("number") String number,@Param("wuzifenlei") String wuzifenlei);
    int materialdelete(@Param("number") String number);
    int materialedit(@Param("number") String number,@Param("wuzufenlei") String wuzufenlei,@Param("name") String name,@Param("guige") int guige,@Param("caizhi") String caizhi);
    taizhang taizhangselect(@Param("id") String id);
    int ruku(@Param("id") String id,@Param("shuliang") int shuliang,@Param("jiliang") String jiliang,@Param("cunfang") String cunfang);
}
