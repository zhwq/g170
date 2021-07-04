package com.g170.rpcfx.demo.provider;

import com.g170.rpcfx.api.RpcfxException;
import com.g170.rpcfx.demo.api.User;
import com.g170.rpcfx.demo.api.UserService;

public class UserServiceImpl implements UserService {

    @Override
    public User findById(int id) throws RpcfxException {
//         模拟异常
      if (id % 2 == 0) {
        // 伪造一个异常
        int e = id / 0;
      }
      return new User(id, "KK" + System.currentTimeMillis());
    }
}
