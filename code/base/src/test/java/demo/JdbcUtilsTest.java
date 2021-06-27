package demo;

import homework.utils.JdbcUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Properties;

@RunWith(JUnit4.class)
public class JdbcUtilsTest {
  Properties properties;
  Connection connection;
  @Before
  public void init() throws IOException, SQLException, ClassNotFoundException {
    final String config = "jdbc.properties";
    InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(config);
    properties = new Properties();
    properties.load(inputStream);

    final String driverClassName = properties.getProperty("driverClassName");
    final String url = properties.getProperty("master.url");
    final String username = properties.getProperty("username");
    final String password = properties.getProperty("password");
    connection = JdbcUtils.getConnection(driverClassName, url, username, password);
  }

  @Test public void printJdbcProperties() {
    Enumeration enums = properties.propertyNames();
    while (enums.hasMoreElements()) {
      String key = (String)enums.nextElement();
      String val = properties.getProperty(key);
      System.out.println(key + "=" + val);
    }
  }

  @Test public void testInsertOne() throws SQLException {
    // insert into product(barcode, product_name) values ('test001', '测试商品001');
    final String sql = "insert into product_t(barcode, product_name) values (?, ?)";
    PreparedStatement preparedStatement = connection.prepareStatement(sql);
    // Parameter index out of range (0 < 1 ) 游标从1开始
    preparedStatement.setString(1, "test002");
    preparedStatement.setString(2, "测试商品002");
    preparedStatement.execute();
    JdbcUtils.close(null, preparedStatement, connection);
  }
  /*
    使用PreparedStatement实现更高效的批量插入
    如果不能使用batch方法，在url最后添加 ?rewriteBatchedStatements=true
   */
  @Test public void testInsertBatch() throws SQLException {
    // insert into product(barcode, product_name) values ('test001', '测试商品001');
    final String sql = "insert into product_t(barcode, product_name) values (?, ?)";
    PreparedStatement preparedStatement = connection.prepareStatement(sql);
    connection.setAutoCommit(false);
    long start, end;
    start = System.currentTimeMillis();
    System.out.println("开始数据插入: " + start);
     for (int i = 1;i <= 100 * 10000; i++) {
      preparedStatement.setString(1, "test202");
      preparedStatement.setString(2, "测试商品202");
      preparedStatement.addBatch();
      if (i % 3000 == 0) {
        preparedStatement.executeBatch();
        preparedStatement.clearBatch();
      }
    }
    connection.commit();
    end = System.currentTimeMillis();
    System.out.println("完成数据插入: " + System.currentTimeMillis());
    System.out.println("花费时间:" + (end - start));
  }
}
/*
开始数据插入: 1624263398082
完成数据插入: 1624263479412
花费时间:81330
url +rewriteBatchedStatements=true
开始数据插入: 1624263557309
完成数据插入: 1624263563271
花费时间:5962
 */
