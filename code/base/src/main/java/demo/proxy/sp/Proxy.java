package demo.proxy.sp;

public class Proxy implements IService{
  private Service service;
  Proxy(Service service) {
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
