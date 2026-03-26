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

@WebServlet("/warehouseadd")
public class warehouseadd extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8"); // 解决乱码问题
        resp.setContentType("text/html;charset=utf-8");
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        int guige = Integer.parseInt(req.getParameter("guige"));
        String caizhi = req.getParameter("caizhi");
        String gongyingshang = req.getParameter("gongyingshang");
        String logo = req.getParameter("logo");
        String wuzifenlei = req.getParameter("wuzifenlei");
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper usermapper = sqlSession.getMapper(UserMapper.class);
        int a=usermapper.warehouseadd(id,name,guige,caizhi,gongyingshang,logo,wuzifenlei);
        Writer writer=resp.getWriter();
        sqlSession.commit();
        sqlSession.close();
        if(a>0){
            writer.write("<html><body>");
            writer.write("<h2>添加成功！</h2>");
            writer.write("<p>1秒后将跳转到管理员功能页...</p>");
            writer.write("<script type='text/javascript'>");
            writer.write("setTimeout(function() { window.location.href = './managerscreen.html'; }, 1000);");
            writer.write("</script>");
            writer.write("</body></html>");
        }else {
            writer.write("<html><body>");
            writer.write("<h2>添加失败！请重新输入</h2>");
            writer.write("<script type='text/javascript'>");
            writer.write("setTimeout(function() { window.location.href = './warehouseadd.html'; }, 1000);");
            writer.write("</script>");
            writer.write("</body></html>");
        }



    }
}
