package exam.mapper;

import exam.pojo.pass;
import exam.pojo.text;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    pass selectpass(@Param("id") String id, @Param("password") String password);
    int textadd(@Param("text") String text,@Param("date") String date);
    List<text> selectAllText();
    List<text> selectPendingText();
    int approveText(@Param("id") int id, @Param("state") String state);
    List<pass> selectAllUsers();
    int addUser(@Param("id") String id, @Param("password") String password, @Param("role") String role, @Param("state") String state);
    int updateUser(@Param("id") String id, @Param("password") String password, @Param("role") String role, @Param("state") String state);
    int deleteUser(@Param("id") String id);
}
