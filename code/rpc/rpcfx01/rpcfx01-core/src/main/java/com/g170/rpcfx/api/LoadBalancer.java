package com.g170.rpcfx.api;

import java.util.List;

public interface LoadBalancer {

    String select(List<String> urls);

}
