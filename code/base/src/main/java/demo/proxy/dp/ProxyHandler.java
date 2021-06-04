package demo.proxy.dp;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyHandler implements InvocationHandler {
  private Object target;

  public ProxyHandler() {}

  public ProxyHandler(Object target) {
    this.target = target;
  }
  /*
    JDK动态代理是代理的接口 强制转换的时候需要使用接口
    不能使用类 ClassCastException
   */
  public Object bind(Object target) {
    this.target = target;
    return Proxy.newProxyInstance(target.getClass().getClassLoader(),
      target.getClass().getInterfaces(), this);
  }

  @Override
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    System.out.println("==jdk 接口动态代理 before");
    Object ret = method.invoke(target, args);
    System.out.println("==jdk 接口动态代理 after");
    return ret;
  }
}
