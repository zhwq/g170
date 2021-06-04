package demo.proxy.sp;

public interface IService {
  default void hello(String message) {
    System.out.println("hello");
  }
  void say(String message);
}
