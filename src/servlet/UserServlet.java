package servlet;

import entity.User;
import util.JDBCUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/user")
public class UserServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        String method = req.getParameter("method");

        switch (method) {
            case "login":
                login(req, resp);
                break;
            case "logout":
                logout(req, resp);
                break;
            case "register":
                register(req, resp);
                break;
        }
    }

    private void register(HttpServletRequest req, HttpServletResponse resp) {
        System.out.println("UserServlet.register");
    }

    private void logout(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("UserServlet.logout");
        HttpSession session = req.getSession();
        session.invalidate();

        resp.sendRedirect(req.getContextPath() + "/login.jsp");
    }

    private void login(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("UserServlet.login");
        // 首先判断验证码对不对，只有验证码正确，再去判断用户名和密码对不对
        String code = req.getParameter("code");
        HttpSession session = req.getSession();
        String sessionCode = (String) session.getAttribute("sessionCode");
        /*if (sessionCode.equals(code) == false) {

        }*/
        if (!sessionCode.equals(code)) {
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
            return;
        }

        String name = req.getParameter("name");
        String password = req.getParameter("password");

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        User user = null;

        try {
            connection = JDBCUtil.getConnection();
            String sql = "select id,name,password,age,level from user where name=? and password=?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            statement.setString(2, password);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                int age = resultSet.getInt("age");
                int level = resultSet.getInt("level");
                user = new User(id, name, password, age, level);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCUtil.close(connection, statement, resultSet);
        }

        if (user != null) {
            session = req.getSession();
            session.setAttribute("user", user);

            resp.sendRedirect(req.getContextPath() + "/");
        } else {
            resp.sendRedirect(req.getContextPath() + "/fail.html");
        }
    }
}
