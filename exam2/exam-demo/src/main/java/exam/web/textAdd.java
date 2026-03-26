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

@WebServlet("/textadd")
public class textAdd extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8"); // 解决乱码问题
        resp.setContentType("text/html;charset=utf-8");
        Writer writer = resp.getWriter();
        
        try {
            String text = req.getParameter("text");
            String date = req.getParameter("date");
            
            // 边界情况处理：检查输入是否为空
            if (text == null || text.trim().isEmpty() || date == null || date.trim().isEmpty()) {
                writer.write("<html><body>");
                writer.write("<h3>上传失败，公文内容或日期不能为空！<h3>");
                writer.write("<p>1秒后将跳转到公文拟制页面...</p>");
                writer.write("<script type='text/javascript'>");
                writer.write("setTimeout(function() { window.location.href = './textAdd.html'; }, 1000);");
                writer.write("</script>");
                writer.write("</body></html>");
                return;
            }
            
            String resource = "mybatis-config.xml";
            InputStream inputStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            SqlSession sqlSession = sqlSessionFactory.openSession();
            
            try {
                UserMapper usermapper = sqlSession.getMapper(UserMapper.class);
                int t = usermapper.textadd(text, date);
                sqlSession.commit();
                
                if (t > 0) {
                    writer.write("<html><body>");
                    writer.write("<h3>上传成功！<h3>");
                    writer.write("<p>1秒后将跳转到部门主界面...</p>");
                    writer.write("<script type='text/javascript'>");
                    writer.write("setTimeout(function() { window.location.href = './departmentScreen.html'; }, 1000);");
                    writer.write("</script>");
                    writer.write("</body></html>");
                } else {
                    writer.write("<html><body>");
                    writer.write("<h3>上传失败！请重新输入！<h3>");
                    writer.write("<p>1秒后将跳转到公文拟制页面...</p>");
                    writer.write("<script type='text/javascript'>");
                    writer.write("setTimeout(function() { window.location.href = './textAdd.html'; }, 1000);");
                    writer.write("</script>");
                    writer.write("</body></html>");
                }
            } finally {
                if (sqlSession != null) {
                    sqlSession.close();
                }
            }
        } catch (Exception e) {
            writer.write("<html><body>");
            writer.write("<h3>上传失败，系统错误：" + e.getMessage() + "<h3>");
            writer.write("<p>1秒后将跳转到公文拟制页面...</p>");
            writer.write("<script type='text/javascript'>");
            writer.write("setTimeout(function() { window.location.href = './textAdd.html'; }, 1000);");
            writer.write("</script>");
            writer.write("</body></html>");
            e.printStackTrace();
        }
    }
}
