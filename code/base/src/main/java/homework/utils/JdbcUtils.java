package homework.utils;

import java.sql.*;

public final class JdbcUtils {
  private JdbcUtils() {}

  public static void close(ResultSet rs, Statement statement, Connection connection) {
      try {
        if (rs!= null) {
          rs.close();
        }
      } catch (SQLException e) {
//        e.printStackTrace();
      } finally {
          try {
            if (statement != null) {
              statement.close();
            }
          } catch (SQLException e) {
//            e.printStackTrace();
          } finally {
            try {
              if (connection != null) {
                connection.close();
              }
            } catch (SQLException e) {
//              e.printStackTrace();
            }
          }
      }
  }

  public static Connection getConnection(final String className, final String jdbcUrl,
                                         final String user, final String password)
    throws SQLException, ClassNotFoundException {
    Class.forName(className);
    return DriverManager.getConnection(jdbcUrl, user, password);
  }
}
