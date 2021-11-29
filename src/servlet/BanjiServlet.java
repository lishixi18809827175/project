package servlet;

import entity.Banji;
import util.JDBCUtil;
import util.JSONUtil;
import util.PageInfo;
import vo.BanjiCountVO;

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
import java.util.List;

@WebServlet("/banji")
public class BanjiServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("method");

        if (method == null || method == "") {
            method = "selectByPage";
        }

        switch (method) {
            case "selectAll":
                selectAll(req, resp);
                break;
            case "selectByPage":
                selectByPage(req, resp);
                break;
            case "insert":
                insert(req, resp);
                break;
            case "deleteById":
                deleteById(req, resp);
                break;
            case "selectById":
                selectById(req, resp);
                break;
            case "update":
                update(req, resp);
                break;
            case "selectBanjiCount":
                selectBanjiCount(req, resp);
        }
    }

    private void selectBanjiCount(HttpServletRequest req, HttpServletResponse resp) {
        System.out.println("BanjiServlet.selectBanjiCount");

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<BanjiCountVO> list = new ArrayList<>();

        try {
            connection = JDBCUtil.getConnection();
            String sql = "SELECT b.name,COUNT(*) AS count\n" +
                    "FROM student AS s INNER JOIN banji AS b ON s.banji_id=b.id\n" +
                    "GROUP BY b.id";
            statement = connection.prepareStatement(sql);
            System.out.println(statement);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                int value = resultSet.getInt("count");
                BanjiCountVO banjiCountVO = new BanjiCountVO(name, value);
                list.add(banjiCountVO);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCUtil.close(connection, statement, resultSet);
        }

        JSONUtil.array2Json(list, resp);
    }

    private void update(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("BanjiServlet.update");

        String id = req.getParameter("id");
        String name = req.getParameter("name");

        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = JDBCUtil.getConnection();
            String sql = "update banji set name=? where id=?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            statement.setInt(2, Integer.parseInt(id));
            System.out.println(statement);
            int count = statement.executeUpdate();
            System.out.println("count: " + count);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCUtil.close(connection, statement, null);
        }

        resp.sendRedirect(req.getContextPath() + "/banji?method=selectByPage");
    }

    private void selectById(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("BanjiServlet.selectById");
        int id = Integer.parseInt(req.getParameter("id"));

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Banji banji = null;

        try {
            connection = JDBCUtil.getConnection();
            String sql = "select id,name from banji where id=?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            System.out.println(statement);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                banji = new Banji(id, name);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCUtil.close(connection, statement, resultSet);
        }

        req.setAttribute("banji", banji);
        req.getRequestDispatcher("/banji_update.jsp").forward(req, resp);
    }

    private void deleteById(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("BanjiServlet.deleteById");

        String id = req.getParameter("id");

        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = JDBCUtil.getConnection();
            String sql = "delete from banji where id=?";
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

        resp.sendRedirect(req.getContextPath() + "/banji?method=selectByPage");
    }

    private void insert(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("BanjiServlet.insert");

        String name = req.getParameter("name");

        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = JDBCUtil.getConnection();
            String sql = "insert into banji(name) values(?)";
            statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            System.out.println(statement);
            int count = statement.executeUpdate();
            System.out.println("count: " + count);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCUtil.close(connection, statement, null);
        }

        resp.sendRedirect(req.getContextPath() + "/banji?method=selectByPage");
    }

    private void selectByPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("BanjiServlet.selectByPage");

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
        ArrayList<Banji> list = getCurrentPageList(offset, pageSize);
        int totalCount = getTotalCount();
        int totalPage = (int)Math.ceil((double)totalCount / pageSize);

        PageInfo pageInfo = new PageInfo(list, pageNo, totalPage, pageSize);

        req.setAttribute("pageInfo", pageInfo);
        req.getRequestDispatcher("/banji_list.jsp").forward(req, resp);
    }

    private int getTotalCount() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int totalCount = 0;

        try {
            connection = JDBCUtil.getConnection();
            String sql = "select count(*) from banji";
            statement = connection.prepareStatement(sql);
            System.out.println(statement);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                totalCount = resultSet.getInt(1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCUtil.close(connection, statement, resultSet);
        }

        return totalCount;
    }

    private ArrayList<Banji> getCurrentPageList(int offset, int pageSize) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        ArrayList<Banji> list = new ArrayList<>();

        try {
            connection = JDBCUtil.getConnection();
            String sql = "select id,name from banji limit ?,?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, offset);
            statement.setInt(2, pageSize);
            System.out.println(statement);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                Banji banji = new Banji(id, name);
                list.add(banji);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCUtil.close(connection, statement, resultSet);
        }

        return list;
    }

    private void selectAll(HttpServletRequest req, HttpServletResponse resp) {
        System.out.println("BanjiServlet.selectAll");

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
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCUtil.close(connection, statement, resultSet);
        }

        JSONUtil.array2Json(list, resp);
    }
}
