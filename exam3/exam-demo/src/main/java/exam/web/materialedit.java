package exam.web;

import exam.mapper.UserMapper;
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

@WebServlet("/materialedit")
public class materialedit extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8"); // 解决乱码问题
        resp.setContentType("text/html;charset=utf-8");
        String number = req.getParameter("number");
        String wuzifenlei = req.getParameter("wuzifenlei");
        String name = req.getParameter("name");
        int guige = Integer.parseInt(req.getParameter("guige"));
        String caizhi = req.getParameter("caizhi");
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper usermapper = sqlSession.getMapper(UserMapper.class);
        int e=usermapper.materialedit(number,wuzifenlei,name,guige,caizhi);
        Writer writer=resp.getWriter();
        sqlSession.commit();
        sqlSession.close();
        if(e>0){
            writer.write("<html><body>");
            writer.write("<h2>修改成功！</h2>");
            writer.write("<p>3秒后将跳转到管理员功能页...</p>");
            writer.write("<script type='text/javascript'>");
            writer.write("setTimeout(function() { window.location.href = './managerscreen.html'; }, 3000);");
            writer.write("</script>");
            writer.write("</body></html>");
        }else {
            writer.write("<html><body>");
            writer.write("<h2>修改失败！</h2>");
            writer.write("<p>3秒后将跳转到修改物资类别基本信息界面...</p>");
            writer.write("<script type='text/javascript'>");
            writer.write("setTimeout(function() { window.location.href = './materialedit.html'; }, 3000);");
            writer.write("</script>");
            writer.write("</body></html>");
        }
    }
}
