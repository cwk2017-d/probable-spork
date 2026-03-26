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
import java.util.List;

@WebServlet("/system")
public class SystemServlet extends HttpServlet {
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
            
            List<pass> userList = userMapper.selectAllUsers();
            
            writer.write("<!DOCTYPE html>");
            writer.write("<html lang=\"zh-CN\">");
            writer.write("<head>");
            writer.write("<meta charset=\"UTF-8\">");
            writer.write("<title>系统管理</title>");
            writer.write("<style>");
            writer.write("body { font-family: Arial, sans-serif; background-color: #eeeeee; margin: 0; display: flex; flex-direction: column; align-items: center; justify-content: flex-start; height: 100vh; }");
            writer.write("h1 { margin: 20px 0; font-size: 30px; color: #333; }");
            writer.write(".container { width: 80%; max-width: 1000px; background-color: white; padding: 20px; border-radius: 8px; box-shadow: 0 0 10px rgba(0,0,0,0.1); }");
            writer.write(".section { margin-bottom: 30px; }");
            writer.write("h2 { font-size: 20px; color: #333; margin-bottom: 15px; }");
            writer.write("table { width: 100%; border-collapse: collapse; margin-bottom: 20px; }");
            writer.write("th, td { padding: 10px; text-align: left; border-bottom: 1px solid #ddd; }");
            writer.write("th { background-color: #f2f2f2; }");
            writer.write(".form-group { margin-bottom: 10px; }");
            writer.write("label { display: inline-block; width: 100px; }");
            writer.write("input[type='text'], input[type='password'], select { padding: 5px; width: 200px; }");
            writer.write("input[type='submit'] { padding: 5px 15px; background-color: #007bff; color: white; border: none; border-radius: 4px; cursor: pointer; }");
            writer.write(".action-buttons { display: flex; gap: 5px; }");
            writer.write(".btn { padding: 3px 10px; border: none; border-radius: 4px; cursor: pointer; text-decoration: none; font-size: 14px; }");
            writer.write(".btn-edit { background-color: #ffc107; color: black; }");
            writer.write(".btn-delete { background-color: #dc3545; color: white; }");
            writer.write(".back { margin-top: 20px; display: inline-block; padding: 10px 20px; background-color: #6c757d; color: white; text-decoration: none; border-radius: 4px; }");
            writer.write("</style>");
            writer.write("</head>");
            writer.write("<body>");
            writer.write("<h1>系统管理</h1>");
            writer.write("<div class=\"container\">");
            
            // 用户管理部分
            writer.write("<div class=\"section\">");
            writer.write("<h2>用户管理</h2>");
            writer.write("<table>");
            writer.write("<tr><th>ID</th><th>密码</th><th>角色</th><th>状态</th><th>操作</th></tr>");
            
            for (pass user : userList) {
                writer.write("<tr>");
                writer.write("<td>" + user.getId() + "</td>");
                writer.write("<td>" + user.getPassword() + "</td>");
                writer.write("<td>" + user.getRole() + "</td>");
                writer.write("<td>" + user.getState() + "</td>");
                writer.write("<td>");
                writer.write("<div class=\"action-buttons\">");
                writer.write("<a href='./system?action=edit&id=" + user.getId() + "' class='btn btn-edit'>编辑</a>");
                writer.write("<a href='./system?action=delete&id=" + user.getId() + "' class='btn btn-delete' onclick='return confirm(\"确认删除该用户吗？\");'>删除</a>");
                writer.write("</div>");
                writer.write("</td>");
                writer.write("</tr>");
            }
            
            writer.write("</table>");
            
            // 添加用户表单
            writer.write("<h3>添加用户</h3>");
            writer.write("<form action='/exam-demo/system' method='post'>");
            writer.write("<input type='hidden' name='action' value='add'>");
            writer.write("<div class=\"form-group\">");
            writer.write("<label>ID:</label>");
            writer.write("<input type='text' name='id' required>");
            writer.write("</div>");
            writer.write("<div class=\"form-group\">");
            writer.write("<label>密码:</label>");
            writer.write("<input type='password' name='password' required>");
            writer.write("</div>");
            writer.write("<div class=\"form-group\">");
            writer.write("<label>角色:</label>");
            writer.write("<select name='role' required>");
            writer.write("<option value='部门'>部门</option>");
            writer.write("<option value='办公室'>办公室</option>");
            writer.write("<option value='副厂长'>副厂长</option>");
            writer.write("<option value='厂长'>厂长</option>");
            writer.write("<option value='系统管理'>系统管理</option>");
            writer.write("</select>");
            writer.write("</div>");
            writer.write("<div class=\"form-group\">");
            writer.write("<label>状态:</label>");
            writer.write("<select name='state' required>");
            writer.write("<option value='正常'>正常</option>");
            writer.write("<option value='冻结'>冻结</option>");
            writer.write("</select>");
            writer.write("</div>");
            writer.write("<input type='submit' value='添加用户'>");
            writer.write("</form>");
            writer.write("</div>");
            
            writer.write("<a href='./systemScreen.html' class='back'>返回主页面</a>");
            writer.write("</div>");
            writer.write("</body>");
            writer.write("</html>");
            
            sqlSession.close();
        } catch (Exception e) {
            writer.write("<html><body>");
            writer.write("<h2>错误: " + e.getMessage() + "</h2>");
            writer.write("<a href='./systemScreen.html'>返回主页面</a>");
            writer.write("</body></html>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=utf-8");
        Writer writer = resp.getWriter();
        
        try {
            String action = req.getParameter("action");
            
            String resource = "mybatis-config.xml";
            InputStream inputStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            SqlSession sqlSession = sqlSessionFactory.openSession();
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            
            if (action.equals("add")) {
                String id = req.getParameter("id");
                String password = req.getParameter("password");
                String role = req.getParameter("role");
                String state = req.getParameter("state");
                
                int result = userMapper.addUser(id, password, role, state);
                sqlSession.commit();
                
                if (result > 0) {
                    writer.write("<html><body>");
                    writer.write("<h2>添加用户成功！</h2>");
                    writer.write("<p>1秒后将跳转到系统管理页面...</p>");
                    writer.write("<script type='text/javascript'>");
                    writer.write("setTimeout(function() { window.location.href = './system'; }, 1000);");
                    writer.write("</script>");
                    writer.write("</body></html>");
                } else {
                    writer.write("<html><body>");
                    writer.write("<h2>添加用户失败，请重试！</h2>");
                    writer.write("<a href='./system'>返回系统管理页面</a>");
                    writer.write("</body></html>");
                }
            } else if (action.equals("update")) {
                String id = req.getParameter("id");
                String password = req.getParameter("password");
                String role = req.getParameter("role");
                String state = req.getParameter("state");
                
                int result = userMapper.updateUser(id, password, role, state);
                sqlSession.commit();
                
                if (result > 0) {
                    writer.write("<html><body>");
                    writer.write("<h2>更新用户成功！</h2>");
                    writer.write("<p>1秒后将跳转到系统管理页面...</p>");
                    writer.write("<script type='text/javascript'>");
                    writer.write("setTimeout(function() { window.location.href = './system'; }, 1000);");
                    writer.write("</script>");
                    writer.write("</body></html>");
                } else {
                    writer.write("<html><body>");
                    writer.write("<h2>更新用户失败，请重试！</h2>");
                    writer.write("<a href='./system'>返回系统管理页面</a>");
                    writer.write("</body></html>");
                }
            }
            
            sqlSession.close();
        } catch (Exception e) {
            writer.write("<html><body>");
            writer.write("<h2>错误: " + e.getMessage() + "</h2>");
            writer.write("<a href='./system'>返回系统管理页面</a>");
            writer.write("</body></html>");
        }
    }
}