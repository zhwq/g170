package demo.proxy.sp;

public class Service implements IService {
  @Override
  public void hello(String message) {
    System.out.println("hello " + message);
  }

  @Override
  public void say(String message) {
    System.out.println("say " + message);
  }
}
