package com.g170.shardingsphere.demo.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "t_order")
@Data
public class Order {
  @Id
  @Column(name = "order_id")
  private Long orderId;
  @Column(name = "user_id")
  private Long userId;
//  @Column(name = "order_no")
//  private String orderNo;
//  @Column(name = "create_time")
//  private Date createTime;
}
