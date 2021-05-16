package demo.jvm0104;

public class HelloByteCode2 {
  static HelloByteCode2 helloByteCode2 = new HelloByteCode2();
  public int add() {
    int a = 10;
    int b = 20;
    return a + b;
  }

  public static void main(String[] args) {
    HelloByteCode2 helloByteCode21 = new HelloByteCode2();
    System.out.println(helloByteCode21.add());
  }
}
