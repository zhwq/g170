package demo.proxy.dp;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class ProxyManager implements InvocationHandler {
  private Object target;

  public ProxyManager(Object target) {
    this.target = target;
  }

  @Override
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    System.out.println("动态代理");
    return method.invoke(target, args);
  }
}
