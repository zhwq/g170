package demo;

public class ByteTest {
  public static void main(String[] args) {
    byte b = 3;
    byte c = (byte)(255 - b);
    byte d = (byte)~b;
    // 0000 0000 1111 1111
    byte e = (byte)(b & 0xff);
    byte f = (byte)(b ^ 0xff);
  }
}
