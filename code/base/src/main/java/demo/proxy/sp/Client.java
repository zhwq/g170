package demo.proxy.sp;

public class Client {
  public static void main(String[] args) {
    Service s = new Service();
    s.hello("静态代理");
    Proxy sProxy = new Proxy(s);
    sProxy.hello("静态代理2");
    sProxy.say("666");
  }
}
