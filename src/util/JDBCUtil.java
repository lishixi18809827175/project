package util;

import java.sql.*;

public class JDBCUtil {

    private JDBCUtil() {

    }

    // 静态代码块  只执行一次，保证这个驱动只加载一次
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/study?useUnicode=true&characterEncoding=UTF-8", "root", "1234");
        return connection;
    }


    public static void close(Connection connection, PreparedStatement statement, ResultSet resultSet) {
        // 先打开的后关闭
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }


}
