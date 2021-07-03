package com.g170.shardingsphere.demo.service;

import com.g170.shardingsphere.demo.dao.OrderDao;
import com.g170.shardingsphere.demo.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
  @Autowired private OrderDao orderDao;
  public Order getOrderByOrderId(Long orderId) {
    return orderDao.selectByPrimaryKey(orderId);
  }
}
