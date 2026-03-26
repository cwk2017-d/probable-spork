package exam.web;

import exam.mapper.UserMapper;
import exam.pojo.pass;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;

@WebServlet("/login")
public class login extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8"); // 解决乱码问题
        resp.setContentType("text/html;charset=utf-8");
        String id = req.getParameter("id");
        String password = req.getParameter("password");
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper usermapper = sqlSession.getMapper(UserMapper.class);
        pass p=usermapper.selectpass(id,password);
        Writer writer = resp.getWriter();
        sqlSession.commit();
        sqlSession.close();
        if(p!=null){
            String role=p.getRole();
            if(role.equals("管理员")){
                writer.write("<html><body>");
                writer.write("<h2>登录成功</h2>");
                writer.write("<p>3秒后将跳转到管理员功能页...</p>");
                writer.write("<script type='text/javascript'>");
                writer.write("setTimeout(function() { window.location.href = './managerscreen.html'; }, 3000);");
                writer.write("</script>");
                writer.write("</body></html>");
            }
            if(role.equals("职员")){
                writer.write("<html><body>");
                writer.write("<h2>登录成功</h2>");
                writer.write("<p>3秒后将跳转到仓库管理人员功能页...</p>");
                writer.write("<script type='text/javascript'>");
                writer.write("setTimeout(function() { window.location.href = './staffscreen.html'; }, 3000);");
                writer.write("</script>");
                writer.write("</body></html>");
            }


        }else {
            writer.write("<html><body>");
            writer.write("<h2>登录失败，请重新输入</h2>");
            writer.write("<p>3秒后将跳转到登录界面...</p>");
            writer.write("<script type='text/javascript'>");
            writer.write("setTimeout(function() { window.location.href = './login.html'; }, 3000);");
            writer.write("</script>");
            writer.write("</body></html>");
        }
    }
}
