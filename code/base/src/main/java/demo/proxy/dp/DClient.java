package demo.proxy.dp;

import demo.proxy.IService;
import demo.proxy.Service;
import lombok.SneakyThrows;

import java.lang.reflect.*;

public class DClient {

  private static void test1() {
    Service target = new Service();
    ProxyHandler myProxyManager = new ProxyHandler(target);
    Object proxyInstance = Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), myProxyManager);
    IService proxyService = (IService) proxyInstance;
    proxyService.hello("jdk dynamic proxy");
  }
  @SneakyThrows
  public static void main(String[] args) {
//    Class proxyClass = Proxy.getProxyClass(DClient.class.getClassLoader(), IService.class);
//    Constructor constructor = proxyClass.getConstructor(InvocationHandler.class);
//    test1();
    ProxyHandler proxyHandler = new ProxyHandler();
    IService s = (IService)proxyHandler.bind(new Service());
    s.hello("proxy");
  }
}
