package com.g170.dubbo.demo.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Table(name = "t_account")
public class Account implements Serializable {
  @Id
  @Column(name = "id")
  private Long id;
  @Column(name = "account_no")
  private Long accountNo;
  @Column(name = "account_name")
  private String accountName;
  @Column(name = "account_type")
  private String accountType;
  @Column(name = "amount")
  private Double amount;

}
