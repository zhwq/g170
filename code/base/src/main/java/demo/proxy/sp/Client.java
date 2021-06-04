package demo.proxy.sp;

import demo.proxy.Service;

public class Client {

  static void staticProxy() {
    Service s = new Service();
    s.hello("静态代理");
    SProxy sProxy = new SProxy(s);
    sProxy.hello("静态代理2");
    sProxy.say("666");
  }

  public static void main(String[] args) {
    staticProxy();
  }
}
