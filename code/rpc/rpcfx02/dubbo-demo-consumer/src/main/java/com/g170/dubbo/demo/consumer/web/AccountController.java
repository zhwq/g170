package com.g170.dubbo.demo.consumer.web;

import com.g170.dubbo.demo.api.AccountService;
import com.g170.dubbo.demo.entity.Account;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {

  @DubboReference(version = "1.0.0") //, url = "dubbo://127.0.0.1:12345")
  private AccountService accountService;

  @GetMapping("account/get/{id}")
  public Account get(@PathVariable Long id) {
    return accountService.getEntityByPK(id);
  }
}
