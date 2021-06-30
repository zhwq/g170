import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@RunWith(JUnit4.class)
public class ShardingSphereSpringDsTest {
  ApplicationContext applicationContext;
  DataSource dataSource;
  @Before
  public void init() {
    applicationContext = new ClassPathXmlApplicationContext("spring-context-sharding.xml");
    dataSource = (DataSource) applicationContext.getBean("shardingDataSource");
  }

  @Test public void testDs() throws SQLException {
    Connection connection = dataSource.getConnection();
    // org.apache.shardingsphere.driver.jdbc.core.connection.ShardingSphereConnection@522a32b1
    System.out.println(connection);
  }

  @Test public void testQuery() throws SQLException {
    // sharding-column 和取模的列不一致会导致异常 java.lang.NullPointerException: Cannot invoke method mod() on null object
    final String sql = "select order_id, user_id from t_order where user_id = ?";
    Connection connection = dataSource.getConnection();
    PreparedStatement preparedStatement = connection.prepareStatement(sql);
    preparedStatement.setInt(1, 1);
    ResultSet resultSet = preparedStatement.executeQuery();
    while (resultSet.next()) {
      Object orderId = resultSet.getObject("order_id");
      System.out.println("订单号 order_id=" + orderId);
    }
    resultSet.close();
    preparedStatement.close();
    connection.close();
  }

}
