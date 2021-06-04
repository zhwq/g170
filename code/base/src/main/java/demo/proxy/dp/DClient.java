package demo.proxy.dp;

import demo.proxy.IService;
import demo.proxy.Service;
import lombok.SneakyThrows;

import java.lang.reflect.*;

public class DClient {
  @SneakyThrows
  public static void main(String[] args) {
//    Class proxyClass = Proxy.getProxyClass(DClient.class.getClassLoader(), IService.class);
//    Constructor constructor = proxyClass.getConstructor(InvocationHandler.class);

    Service target = new Service();
    ProxyManager myProxyManager = new ProxyManager(target);
    // JDK动态代理是代理的接口 强制转换的时候需要使用接口
    // 不能使用类 ClassCastException
    Object proxyInstance = Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), myProxyManager);
    IService proxyService = (IService) proxyInstance;
    System.out.println(proxyService);
  }
}
