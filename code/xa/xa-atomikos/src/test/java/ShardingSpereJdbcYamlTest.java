import cn.hutool.core.io.IoUtil;
import org.apache.shardingsphere.driver.api.yaml.YamlShardingSphereDataSourceFactory;
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
public class ShardingSpereJdbcYamlTest {
  DataSource dataSource;
  @Before public void init() throws IOException, SQLException {
    // 创建 ShardingSphereDataSource
    // URL --> File
    InputStream inputStream =  getClass().getClassLoader().getResourceAsStream("sharding-order.yaml");
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
}
