package servlet;


import entity.Banji;
import entity.Student;
import entity.User;
import util.JDBCUtil;
import util.PageInfo;
import vo.StudentBanji;

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
import java.util.ArrayList;
import java.util.List;

@WebServlet("/student")
public class StudentServlet extends HttpServlet {

    // 访问Servlet默认访问service方法
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 解决post请求乱码问题
//        req.setCharacterEncoding("UTF-8");

        /*HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            // 没有登录的凭证
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
            return;
        }*/

        // 把所有和学生相关的增删改查放到一个Servlet
        // http://localhost:8080/JavaWeb/student?method=selectAll
        // http://localhost:8080/JavaWeb/student?method=deleteById&id=2
        // http://localhost:8080/JavaWeb/student?method=insert
        String method = req.getParameter("method");
        if (method == null || method.equals("")) {
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
            case "selectById":
                selectById(req, resp);
                break;
            case "update":
                update(req, resp);
                break;
            case "selectByPage":
                selectByPage(req, resp);
                break;
            case "selectStudentBanjiByPage":
                selectStudentBanjiByPage(req, resp);
                break;
            case "getStudentInsertPage":
                getStudentInsertPage(req, resp);
                break;
        }

    }

    private void getStudentInsertPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("StudentServlet.getStudentInsertPage");
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Banji> list = new ArrayList<>();
        try {
            connection = JDBCUtil.getConnection();
            String sql = "select id,name from banji";
            statement = connection.prepareStatement(sql);
            System.out.println(statement);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                Banji banji = new Banji(id, name);
                list.add(banji);
            }
            for (Banji banji : list) {
                System.out.println(banji);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        req.setAttribute("banjiList", list);
        req.getRequestDispatcher("/student_insert.jsp").forward(req, resp);
    }

    private void selectStudentBanjiByPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("StudentServlet.selectStudentBanjiByPage");

        String pageNoStr = req.getParameter("pageNo");
        if (pageNoStr == null || pageNoStr == "") {
            pageNoStr = "1";
        }
        String pageSizeStr = req.getParameter("pageSize");
        if (pageSizeStr == null || pageSizeStr == "") {
            pageSizeStr = "5";
        }
        int pageNo = Integer.parseInt(pageNoStr);
        int pageSize = Integer.parseInt(pageSizeStr);

        int offset = (pageNo - 1) * pageSize;
        ArrayList<StudentBanji> list = getCurrentStudentBanjiPageList(offset, pageSize);

        int totalCount = getTotalCount();
        int totalPage = (int)Math.ceil((double) totalCount / pageSize);

        PageInfo pageInfo = new PageInfo(list, pageNo, totalPage, pageSize);

        req.setAttribute("pageInfo", pageInfo);
        req.getRequestDispatcher("/student_banji_list.jsp").forward(req, resp);
    }

    private ArrayList<StudentBanji> getCurrentStudentBanjiPageList(int offset, int pageSize) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        ArrayList<StudentBanji> list = new ArrayList<>();

        try {
            connection = JDBCUtil.getConnection();
            String sql = "SELECT s.id AS studentId,s.name AS studentName,s.age AS studentAge,\n" +
                    "s.gender AS studentGender,b.name AS banjiName\n" +
                    "FROM student AS s LEFT JOIN banji AS b\n" +
                    "ON s.banji_id=b.id LIMIT ?,?;";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, offset);
            statement.setInt(2, pageSize);
            System.out.println(statement);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int studentId = resultSet.getInt("studentId");
                String studentName = resultSet.getString("studentName");
                int studentAge = resultSet.getInt("studentAge");
                String studentGender = resultSet.getString("studentGender");
                String banjiName = resultSet.getString("banjiName");
                StudentBanji studentBanji = new StudentBanji(studentId, studentName, studentAge, studentGender, banjiName);
                list.add(studentBanji);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCUtil.close(connection, statement, resultSet);
        }

        return list;
    }

    private void selectByPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("StudentServlet.selectByPage");

        String pageNoStr = req.getParameter("pageNo");
        if (pageNoStr == null || pageNoStr == "") {
            pageNoStr = "1";
        }
        String pageSizeStr = req.getParameter("pageSize");
        if (pageSizeStr == null || pageSizeStr == "") {
            pageSizeStr = "5";
        }
        int pageNo = Integer.parseInt(pageNoStr);
        int pageSize = Integer.parseInt(pageSizeStr);

        int offset = (pageNo - 1) * pageSize;
        ArrayList<Student> list = getCurrentPageList(offset, pageSize);

        int totalCount = getTotalCount();
        int totalPage = (int)Math.ceil((double) totalCount / pageSize);

        PageInfo pageInfo = new PageInfo(list, pageNo, totalPage, pageSize);

        req.setAttribute("pageInfo", pageInfo);
        req.getRequestDispatcher("/student_list.jsp").forward(req, resp);
    }

    private int getTotalCount() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int totalCount = 0;

        try {
            connection = JDBCUtil.getConnection();
            String sql = "select count(*) from student";
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                totalCount = resultSet.getInt(1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCUtil.close(connection, statement, null);
        }
        return totalCount;
    }

    private ArrayList<Student> getCurrentPageList(int offset, int pageSize) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        ArrayList<Student> list = new ArrayList<>();

        try {
            connection = JDBCUtil.getConnection();
            String sql = "select id,name,age,gender from student limit ?,?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, offset);
            statement.setInt(2, pageSize);
            System.out.println(statement);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                String gender = resultSet.getString("gender");
                Student student = new Student(id, name, age, gender);
                list.add(student);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCUtil.close(connection, statement, resultSet);
        }

        return list;
    }

    private void update(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("StudentServlet.update");

        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String age = req.getParameter("age");
        String gender = req.getParameter("gender");

        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = JDBCUtil.getConnection();
            String sql = "update student set name=?,age=?,gender=? where id=?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            statement.setInt(2, Integer.parseInt(age));
            statement.setString(3, gender);
            statement.setInt(4, Integer.parseInt(id));
            System.out.println(statement);
            int count = statement.executeUpdate();
            System.out.println("count: " + count);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCUtil.close(connection, statement, null);
        }

        // 删除完成之后再去查找所有，展示最新的数据
        // 重定向: 让浏览器发送这个请求  /JavaWeb/selectAll
        resp.sendRedirect(req.getContextPath() + "/student?method=selectByPage");
    }

    private void selectById(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Student student = null;
        try {
            connection = JDBCUtil.getConnection();
            String sql = "select id,name,age,gender from student where id=?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            System.out.println(statement);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                String gender = resultSet.getString("gender");
                student = new Student(id, name, age, gender);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCUtil.close(connection, statement, resultSet);
        }

        // 把list数据放到一块内存区域里面
        req.setAttribute("student", student);
        // 跳转到student_list.jsp展示数据
        // dispatch: 分发 forward: 转发
        req.getRequestDispatcher("/student_update.jsp").forward(req, resp);
    }

    private void insert(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("StudentServlet.insert");

        String name = req.getParameter("name");
        String age = req.getParameter("age");
        String gender = req.getParameter("gender");
        String banjiId = req.getParameter("banjiId");

        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = JDBCUtil.getConnection();
            String sql = "insert into student(name,age,gender,banji_id) values(?,?,?,?)";
            statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            statement.setInt(2, Integer.parseInt(age));
            statement.setString(3, gender);
            statement.setInt(4, Integer.parseInt(banjiId));
            System.out.println(statement);
            int count = statement.executeUpdate();
            System.out.println("count: " + count);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCUtil.close(connection, statement, null);
        }

        // 删除完成之后再去查找所有，展示最新的数据
        // 重定向: 让浏览器发送这个请求  /JavaWeb/selectAll
        resp.sendRedirect(req.getContextPath() + "/student?method=selectByPage");
    }

    private void deleteById(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("StudentServlet.deleteById");
        String id = req.getParameter("id");

        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = JDBCUtil.getConnection();
            String sql = "delete from student where id=?";
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

        // 删除完成之后再去查找所有，展示最新的数据
        // 重定向: 让浏览器发送这个请求  /JavaWeb/selectAll
        resp.sendRedirect(req.getContextPath() + "/student?method=selectByPage");
    }

    private void selectAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        ArrayList<Student> list = new ArrayList<>();
        try {
            connection = JDBCUtil.getConnection();
            String sql = "select id,name,age,gender from student";
            statement = connection.prepareStatement(sql);
            System.out.println(statement);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                String gender = resultSet.getString("gender");
                Student student = new Student(id, name, age, gender);
                list.add(student);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCUtil.close(connection, statement, resultSet);
        }

        // 把list数据放到一块内存区域里面
        req.setAttribute("list", list);
        // 跳转到student_list.jsp展示数据
        // dispatch: 分发 forward: 转发
        req.getRequestDispatcher("/student_list.jsp").forward(req, resp);
    }
}
