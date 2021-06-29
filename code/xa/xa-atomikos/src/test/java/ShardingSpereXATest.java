import cn.hutool.core.io.IoUtil;
import org.apache.shardingsphere.driver.api.yaml.YamlShardingSphereDataSourceFactory;
import org.apache.shardingsphere.transaction.core.TransactionType;
import org.apache.shardingsphere.transaction.core.TransactionTypeHolder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import javax.sql.DataSource;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@RunWith(JUnit4.class)
public class ShardingSpereXATest {
  DataSource dataSource;
  @Before
  public void init() throws IOException, SQLException {
    InputStream inputStream =  getClass().getClassLoader().getResourceAsStream("sharding-order-2_2.yaml");
    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    IoUtil.copy(inputStream, byteArrayOutputStream);
    byte[] yamlBytes = byteArrayOutputStream.toByteArray();
    dataSource = YamlShardingSphereDataSourceFactory.createDataSource(yamlBytes);
  }
  @Test
  public void testQueryByYaml() throws SQLException {
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

  @Test public void testXA() {
    // 默认使用atomikos
    TransactionTypeHolder.set(TransactionType.XA);
    Connection connection;
    try {
      connection = dataSource.getConnection();
      connection.setAutoCommit(false);
      // 定义表中order_id做唯一约束
      // user_id = 5 7 的记录都落到库ds_1
      // order_id = 1 的记录都落到表t_order_1
      // order_id值冲突 Duplicate entry '1' for key 'PRIMARY'
      insertOne(connection, 1,5);
      insertOne(connection, 1,6);
      insertOne(connection, 1,7);
      connection.commit();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  private void insertOne(Connection connection, int orderId, int userId) throws SQLException {
    final String insertSql = "insert into t_order(order_id, user_id) values(?, ?)";
    PreparedStatement preparedStatement = connection.prepareStatement(insertSql);
    preparedStatement.setObject(1, orderId);
    preparedStatement.setObject(2, userId);
    preparedStatement.executeUpdate();
  }

}
