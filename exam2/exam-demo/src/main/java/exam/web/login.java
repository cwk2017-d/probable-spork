package exam.web;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import exam.mapper.UserMapper;
import exam.pojo.pass;

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
        Writer writer = resp.getWriter();
        
        try {
            String id = req.getParameter("id");
            String password = req.getParameter("password");
            
            // 边界情况处理：检查输入是否为空
            if (id == null || id.trim().isEmpty() || password == null || password.trim().isEmpty()) {
                writer.write("<html><body>");
                writer.write("<h2>登录失败，账户或密码不能为空</h2>");
                writer.write("<p>3秒后将跳转到登录界面...</p>");
                writer.write("<script type='text/javascript'>");
                writer.write("setTimeout(function() { window.location.href = './login.html'; }, 3000);");
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
                pass p = usermapper.selectpass(id, password);
                
                if (p != null) {
                    String role = p.getRole();
                    String state = p.getState();
                    
                    if (!state.equals("正常")) {
                        writer.write("<html><body>");
                        writer.write("<h2>账户已冻结！登录失败</h2>");
                        writer.write("<p>3秒后将跳转到登录界面...</p>");
                        writer.write("<script type='text/javascript'>");
                        writer.write("setTimeout(function() { window.location.href = './login.html'; }, 3000);");
                        writer.write("</script>");
                        writer.write("</body></html>");
                    } else {
                        if (role.equals("部门")) {
                            writer.write("<html><body>");
                            writer.write("<h2>登录成功</h2>");
                            writer.write("<p>3秒后将跳转到部门主页面...</p>");
                            writer.write("<script type='text/javascript'>");
                            writer.write("setTimeout(function() { window.location.href = './departmentScreen.html'; }, 3000);");
                            writer.write("</script>");
                            writer.write("</body></html>");
                        } else if (role.equals("办公室")) {
                            writer.write("<html><body>");
                            writer.write("<h2>登录成功</h2>");
                            writer.write("<p>3秒后将跳转到办公室主页面...</p>");
                            writer.write("<script type='text/javascript'>");
                            writer.write("setTimeout(function() { window.location.href = './officeScreen.html'; }, 3000);");
                            writer.write("</script>");
                            writer.write("</body></html>");
                        } else if (role.equals("副厂长")) {
                            writer.write("<html><body>");
                            writer.write("<h2>登录成功</h2>");
                            writer.write("<p>3秒后将跳转到副厂长主页面...</p>");
                            writer.write("<script type='text/javascript'>");
                            writer.write("setTimeout(function() { window.location.href = './viceDirectorScreen.html'; }, 3000);");
                            writer.write("</script>");
                            writer.write("</body></html>");
                        } else if (role.equals("厂长")) {
                            writer.write("<html><body>");
                            writer.write("<h2>登录成功</h2>");
                            writer.write("<p>3秒后将跳转到厂长主页面...</p>");
                            writer.write("<script type='text/javascript'>");
                            writer.write("setTimeout(function() { window.location.href = './directorScreen.html'; }, 3000);");
                            writer.write("</script>");
                            writer.write("</body></html>");
                        } else if (role.equals("系统管理")) {
                            writer.write("<html><body>");
                            writer.write("<h2>登录成功</h2>");
                            writer.write("<p>3秒后将跳转到系统管理主页面...</p>");
                            writer.write("<script type='text/javascript'>");
                            writer.write("setTimeout(function() { window.location.href = './systemScreen.html'; }, 3000);");
                            writer.write("</script>");
                            writer.write("</body></html>");
                        } else {
                            writer.write("<html><body>");
                            writer.write("<h2>登录失败，角色未知</h2>");
                            writer.write("<p>3秒后将跳转到登录界面...</p>");
                            writer.write("<script type='text/javascript'>");
                            writer.write("setTimeout(function() { window.location.href = './login.html'; }, 3000);");
                            writer.write("</script>");
                            writer.write("</body></html>");
                        }
                    }
                } else {
                    writer.write("<html><body>");
                    writer.write("<h2>登录失败，请重新输入</h2>");
                    writer.write("<p>3秒后将跳转到登录界面...</p>");
                    writer.write("<script type='text/javascript'>");
                    writer.write("setTimeout(function() { window.location.href = './login.html'; }, 3000);");
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
            writer.write("<h2>登录失败，系统错误：" + e.getMessage() + "</h2>");
            writer.write("<p>3秒后将跳转到登录界面...</p>");
            writer.write("<script type='text/javascript'>");
            writer.write("setTimeout(function() { window.location.href = './login.html'; }, 3000);");
            writer.write("</script>");
            writer.write("</body></html>");
            e.printStackTrace();
        }
    }
}
