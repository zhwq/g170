package demo.proxy;

public interface IService {
  default void hello(String message) {
    System.out.println("hello");
  }
  void say(String message);
}
