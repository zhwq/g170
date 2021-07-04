package com.g170.dubbo.demo.provider;

import com.g170.dubbo.demo.api.AccountService;
import com.g170.dubbo.demo.dao.AccountDao;
import com.g170.dubbo.demo.entity.Account;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

@DubboService(version = "1.0.0", tag = "red", weight = 100)
public class AccountServiceImpl implements AccountService {
  @Autowired
  private AccountDao accountDao;
  public Account getEntityByPK(Long id) {
    return accountDao.selectByPrimaryKey(id);
  }
}
