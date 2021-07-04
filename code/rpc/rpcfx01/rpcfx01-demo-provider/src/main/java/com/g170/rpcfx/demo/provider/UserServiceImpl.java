package com.g170.rpcfx.demo.provider;

import com.g170.rpcfx.demo.api.User;
import com.g170.rpcfx.demo.api.UserService;

public class UserServiceImpl implements UserService {

    @Override
    public User findById(int id) {
        return new User(id, "KK" + System.currentTimeMillis());
    }
}
