package servlet;

import entity.Teacher;
import util.JDBCUtil;
import util.PageInfo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("/teacher")
public class TeacherServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        String method = req.getParameter("method");

        if (method == null || method == "") {
            method = "selectByPage";
        }

        switch (method) {
            case "selectAll":
                selectAll(req, resp);
                break;
            case "deleteById":
                deleteById(req, resp);
                break;
            case "insert":
                insert(req, resp);
                break;
            case "update":
                update(req, resp);
                break;
            case "selectById":
                selectById(req, resp);
                break;
            case "selectByPage":
                selectByPage(req, resp);
                break;
        }
    }

    private void selectByPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("TeacherServlet.selectByPage");
        String pageNoStr = req.getParameter("pageNo");
        if (pageNoStr == null || pageNoStr.equals("")) {
            pageNoStr = "1";
        }
        String pageSizeStr = req.getParameter("pageSize");
        if (pageSizeStr == null || pageSizeStr.equals("")) {
            pageSizeStr = "5";
        }
        int pageNo = Integer.parseInt(pageNoStr);
        int pageSize = Integer.parseInt(pageSizeStr);

        int offset = (pageNo - 1) * pageSize;
        // 得到当前页的数据的集合
        ArrayList<Teacher> list = getCurrentPageList(offset, pageSize);
        // 得到所有数据的数量
        int totalCount = getTotalCount();
        // 向上取整，算出总的页数
        int totalPage = (int)Math.ceil((double)totalCount / pageSize);

        PageInfo pageInfo = new PageInfo(list, pageNo, totalPage, pageSize);

        req.setAttribute("pageInfo", pageInfo);
        req.getRequestDispatcher("/teacher_list.jsp").forward(req, resp);
    }

    private int getTotalCount() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int totalCount = 0;
        try {
            connection = JDBCUtil.getConnection();
            String sql = "select count(*) from teacher";
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            // 这里用while和if都可以
            if (resultSet.next()) {
                // 代表第一列
                totalCount = resultSet.getInt(1);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return totalCount;
    }

    private ArrayList<Teacher> getCurrentPageList(int offset, int pageSize) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        ArrayList<Teacher> list = new ArrayList<>();
        try {
            connection = JDBCUtil.getConnection();
            String sql = "select id,name,age,address from teacher limit ?,?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, offset);
            statement.setInt(2, pageSize);
            System.out.println(statement);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                String address = resultSet.getString("address");
                Teacher teacher = new Teacher(id, name, age, address);
                list.add(teacher);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCUtil.close(connection, statement, resultSet);
        }

        return list;
    }

    private void selectById(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("TeacherServlet.selectById");
        int id = Integer.parseInt(req.getParameter("id"));

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Teacher teacher = null;

        try {
            connection = JDBCUtil.getConnection();
            String sql = "select id,name,age,address from teacher where id=?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            System.out.println(statement);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                String address = resultSet.getString("address");
                teacher = new Teacher(id, name, age, address);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCUtil.close(connection, statement, resultSet);
        }

        req.setAttribute("teacher", teacher);
        req.getRequestDispatcher("/teacher_update.jsp").forward(req, resp);
    }

    private void update(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("TeacherServlet.update");
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String age = req.getParameter("age");
        String address = req.getParameter("address");

        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = JDBCUtil.getConnection();
            String sql = "update teacher set name=?,age=?,address=? where id=?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            statement.setInt(2, Integer.parseInt(age));
            statement.setString(3, address);
            statement.setInt(4, Integer.parseInt(id));
            System.out.println(statement);
            int count = statement.executeUpdate();
            System.out.println("count: " + count);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCUtil.close(connection, statement, null);
        }

        resp.sendRedirect(req.getContextPath() + "/teacher?method=selectByPage");
    }

    private void insert(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("TeacherServlet.insert");
        String name = req.getParameter("name");
        String age = req.getParameter("age");
        String address = req.getParameter("address");

        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = JDBCUtil.getConnection();
            String sql = "insert into teacher(name,age,address) values(?,?,?)";
            statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            statement.setInt(2, Integer.parseInt(age));
            statement.setString(3, address);
            System.out.println(statement);
            int count = statement.executeUpdate();
            System.out.println("count: " + count);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCUtil.close(connection, statement, null);
        }

        resp.sendRedirect(req.getContextPath() + "/teacher?method=selectByPage");
    }

    private void deleteById(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("TeacherServlet.deleteById");
        String id = req.getParameter("id");

        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = JDBCUtil.getConnection();
            String sql = "delete from teacher where id=?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, Integer.parseInt(id));
            System.out.println(statement);
            int count = statement.executeUpdate();
            System.out.println("count: " + count);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCUtil.close(connection, statement, null);
        }

        resp.sendRedirect(req.getContextPath() + "/teacher?method=selectByPage");
    }

    private void selectAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("TeacherServlet.selectAll");
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        ArrayList<Teacher> list = new ArrayList<>();

        try {
            connection = JDBCUtil.getConnection();
            String sql = "select id,name,age,address from teacher";
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            System.out.println(statement);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                String address = resultSet.getString("address");
                Teacher teacher = new Teacher(id, name, age, address);
                list.add(teacher);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCUtil.close(connection, statement, resultSet);
        }

        req.setAttribute("list", list);
        req.getRequestDispatcher("/teacher_list.jsp").forward(req, resp);
    }
}
