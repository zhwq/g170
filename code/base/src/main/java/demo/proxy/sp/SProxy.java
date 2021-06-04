package demo.proxy.sp;

import demo.proxy.IService;
import demo.proxy.Service;

public class SProxy implements IService {
  private Service service;
  SProxy(Service service) {
    this.service = service;
  }
  @Override
  public void hello(String message) {
    System.out.println("==>proxy begin");
    this.service.hello(message);
    System.out.println("<==proxy end");
  }

  @Override
  public void say(String message) {
    System.out.println("==>proxy begin");
    this.service.say(message);
    System.out.println("<==proxy end");
  }

}
