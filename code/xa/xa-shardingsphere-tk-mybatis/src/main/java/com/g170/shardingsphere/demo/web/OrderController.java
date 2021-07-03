package com.g170.shardingsphere.demo.web;

import com.g170.shardingsphere.demo.entity.Order;
import com.g170.shardingsphere.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {
  @Autowired
  OrderService orderService;

  @GetMapping("xa/getOrder/{orderId}")
  public Order getOrder(@PathVariable Long orderId) {
    return orderService.getOrderByOrderId(orderId);
  }
}
