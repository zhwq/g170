package com.g170.rpcfx.demo.api;

import com.g170.rpcfx.api.RpcfxException;

public interface UserService {

    User findById(int id) throws RpcfxException;

}
