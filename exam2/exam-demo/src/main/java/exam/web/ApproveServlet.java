package exam.web;

import exam.mapper.UserMapper;
import exam.pojo.text;
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
import java.util.List;

@WebServlet("/approve")
public class ApproveServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=utf-8");
        Writer writer = resp.getWriter();
        
        try {
            String resource = "mybatis-config.xml";
            InputStream inputStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            SqlSession sqlSession = sqlSessionFactory.openSession();
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            
            List<text> textList = userMapper.selectPendingText();
            
            writer.write("<!DOCTYPE html>");
            writer.write("<html lang=\"zh-CN\">");
            writer.write("<head>");
            writer.write("<meta charset=\"UTF-8\">");
            writer.write("<title>审批公文</title>");
            writer.write("<style>");
            writer.write("body { font-family: Arial, sans-serif; background-color: #eeeeee; margin: 0; display: flex; flex-direction: column; align-items: center; justify-content: flex-start; height: 100vh; }");
            writer.write("h1 { margin: 20px 0; font-size: 30px; color: #333; }");
            writer.write(".container { width: 80%; max-width: 800px; background-color: white; padding: 20px; border-radius: 8px; box-shadow: 0 0 10px rgba(0,0,0,0.1); }");
            writer.write(".text-item { border: 1px solid #ddd; padding: 15px; margin-bottom: 10px; border-radius: 4px; }");
            writer.write(".text-content { margin-bottom: 10px; }");
            writer.write(".text-meta { font-size: 14px; color: #666; margin-bottom: 10px; }");
            writer.write("form { display: inline-block; margin-right: 10px; }");
            writer.write("input[type='submit'] { padding: 5px 10px; border: none; border-radius: 4px; cursor: pointer; }");
            writer.write(".approve { background-color: #28a745; color: white; }");
            writer.write(".reject { background-color: #dc3545; color: white; }");
            writer.write(".back { margin-top: 20px; display: inline-block; padding: 10px 20px; background-color: #007bff; color: white; text-decoration: none; border-radius: 4px; }");
            writer.write("</style>");
            writer.write("</head>");
            writer.write("<body>");
            writer.write("<h1>审批公文</h1>");
            writer.write("<div class=\"container\">");
            
            if (textList.isEmpty()) {
                writer.write("<p>暂无待审批的公文</p>");
            } else {
                for (text t : textList) {
                    writer.write("<div class=\"text-item\">");
                    writer.write("<div class=\"text-content\">" + t.getText() + "</div>");
                    writer.write("<div class=\"text-meta\">日期: " + t.getDate() + " | 状态: " + t.getState() + "</div>");
                    writer.write("<form action='/exam-demo/approve' method='post'>");
                    writer.write("<input type='hidden' name='id' value='" + t.getId() + "'>");
                    writer.write("<input type='hidden' name='action' value='approve'>");
                    writer.write("<input type='submit' value='批准' class='approve'>");
                    writer.write("</form>");
                    writer.write("<form action='/exam-demo/approve' method='post'>");
                    writer.write("<input type='hidden' name='id' value='" + t.getId() + "'>");
                    writer.write("<input type='hidden' name='action' value='reject'>");
                    writer.write("<input type='submit' value='拒绝' class='reject'>");
                    writer.write("</form>");
                    writer.write("</div>");
                }
            }
            
            writer.write("<a href='./viceDirectorScreen.html' class='back'>返回主页面</a>");
            writer.write("</div>");
            writer.write("</body>");
            writer.write("</html>");
            
            sqlSession.close();
        } catch (Exception e) {
            writer.write("<html><body>");
            writer.write("<h2>错误: " + e.getMessage() + "</h2>");
            writer.write("<a href='./viceDirectorScreen.html'>返回主页面</a>");
            writer.write("</body></html>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=utf-8");
        Writer writer = resp.getWriter();
        
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            String action = req.getParameter("action");
            String state = action.equals("approve") ? "已批准" : "已拒绝";
            
            String resource = "mybatis-config.xml";
            InputStream inputStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            SqlSession sqlSession = sqlSessionFactory.openSession();
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            
            int result = userMapper.approveText(id, state);
            sqlSession.commit();
            sqlSession.close();
            
            if (result > 0) {
                writer.write("<html><body>");
                writer.write("<h2>审批成功！</h2>");
                writer.write("<p>1秒后将跳转到审批页面...</p>");
                writer.write("<script type='text/javascript'>");
                writer.write("setTimeout(function() { window.location.href = './approve'; }, 1000);");
                writer.write("</script>");
                writer.write("</body></html>");
            } else {
                writer.write("<html><body>");
                writer.write("<h2>审批失败，请重试！</h2>");
                writer.write("<a href='./approve'>返回审批页面</a>");
                writer.write("</body></html>");
            }
        } catch (Exception e) {
            writer.write("<html><body>");
            writer.write("<h2>错误: " + e.getMessage() + "</h2>");
            writer.write("<a href='./approve'>返回审批页面</a>");
            writer.write("</body></html>");
        }
    }
}