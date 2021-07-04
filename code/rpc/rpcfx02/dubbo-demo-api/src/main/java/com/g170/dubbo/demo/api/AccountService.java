package com.g170.dubbo.demo.api;

import com.g170.dubbo.demo.entity.Account;

public interface AccountService {
  Account getEntityByPK(Long id);
}
