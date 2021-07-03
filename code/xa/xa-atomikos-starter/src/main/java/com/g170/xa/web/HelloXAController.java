package com.g170.xa.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@RestController
public class HelloXAController {

  @Resource
  private DataSource dataSource;

  private void testQuery() throws SQLException {
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

  @RequestMapping("/xa")
  public String hello() throws SQLException {
    testQuery();
    return "hello shardingsphere xa with atomikos";
  }

}
