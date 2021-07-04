package com.g170.dubbo.demo.provider;

import com.g170.dubbo.demo.api.User;
import com.g170.dubbo.demo.api.UserService;
import org.apache.dubbo.config.annotation.DubboService;

@DubboService(version = "1.0.0")
public class UserServiceImpl implements UserService {

    @Override
    public User findById(int id) {
        return new User(id, "KK" + System.currentTimeMillis());
    }
}
