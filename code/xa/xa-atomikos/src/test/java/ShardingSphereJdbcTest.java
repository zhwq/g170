import com.zaxxer.hikari.HikariDataSource;
import org.apache.shardingsphere.driver.api.ShardingSphereDataSourceFactory;
import org.apache.shardingsphere.infra.config.RuleConfiguration;
import org.apache.shardingsphere.infra.config.algorithm.ShardingSphereAlgorithmConfiguration;
import org.apache.shardingsphere.sharding.api.config.ShardingRuleConfiguration;
import org.apache.shardingsphere.sharding.api.config.rule.ShardingTableRuleConfiguration;
import org.apache.shardingsphere.sharding.api.config.strategy.sharding.StandardShardingStrategyConfiguration;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
@RunWith(JUnit4.class)
public class ShardingSphereJdbcTest {

  DataSource dataSource;
  /*
  根据 user_id 取模分库, 且根据 order_id 取模分表的 2 库 2 表的配置
   */
  @Before public void init() throws SQLException {
    // 配置真实数据源
    Map<String, DataSource> dataSourceMap = new HashMap<>();

    // 配置第 1 个数据源
    HikariDataSource dataSource1 = new HikariDataSource();
    dataSource1.setDriverClassName("com.mysql.jdbc.Driver");
    dataSource1.setJdbcUrl("jdbc:mysql://localhost:8266/g170_mall");
    dataSource1.setUsername("g170_mall");
    dataSource1.setPassword("g170_mall@123");
    dataSourceMap.put("ds0", dataSource1);

    // 配置第 2 个数据源
//    HikariDataSource dataSource2 = new HikariDataSource();
//    dataSource2.setDriverClassName("com.mysql.jdbc.Driver");
//    dataSource2.setJdbcUrl("jdbc:mysql://localhost:8266/ds1");
//    dataSource2.setUsername("xa");
//    dataSource2.setPassword("xa");
//    dataSourceMap.put("ds1", dataSource2);

    // 配置 t_order 表规则
    ShardingTableRuleConfiguration orderTableRuleConfig =
//      new ShardingTableRuleConfiguration("t_order", "ds${0..1}.t_order${0..1}");
      new ShardingTableRuleConfiguration("t_order", "ds0.t_order_${0..1}");

    // 配置分库策略
    orderTableRuleConfig.setDatabaseShardingStrategy(
      new StandardShardingStrategyConfiguration("user_id", "dbShardingAlgorithm"));

    // 配置分表策略
    orderTableRuleConfig.setTableShardingStrategy(
      new StandardShardingStrategyConfiguration("order_id", "tableShardingAlgorithm"));

    // 配置分片规则
    ShardingRuleConfiguration shardingRuleConfig = new ShardingRuleConfiguration();
    shardingRuleConfig.getTables().add(orderTableRuleConfig);

    // 配置分库算法 简化处理 不做分库
    Properties dbShardingAlgorithmrProps = new Properties();
//    dbShardingAlgorithmrProps.setProperty("algorithm-expression", "ds${user_id % 2}");
    dbShardingAlgorithmrProps.setProperty("algorithm-expression", "ds0");
    shardingRuleConfig.getShardingAlgorithms().put("dbShardingAlgorithm", new ShardingSphereAlgorithmConfiguration("INLINE", dbShardingAlgorithmrProps));

    // 配置分表算法
    Properties tableShardingAlgorithmrProps = new Properties();
    tableShardingAlgorithmrProps.setProperty("algorithm-expression", "t_order${order_id % 2}");
    shardingRuleConfig.getShardingAlgorithms().put("tableShardingAlgorithm", new ShardingSphereAlgorithmConfiguration("INLINE", tableShardingAlgorithmrProps));

    // 创建 ShardingSphereDataSource
    dataSource = ShardingSphereDataSourceFactory.createDataSource(dataSourceMap, (Collection)Collections.singleton(shardingRuleConfig), new Properties());
  }

  @Test public void testQuery() throws SQLException {
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
