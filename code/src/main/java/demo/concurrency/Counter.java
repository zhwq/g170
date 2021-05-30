package demo.concurrency;

import java.util.stream.IntStream;

public class Counter {
  private int sum = 0;
  // 可重入锁 + 公平锁
  private int addAndGet() {
    System.out.println("--" + sum);
    return ++sum;
  }

  public int getSum() {
    return sum;
  }

  public static void main(String[] args) {
    int loopNum = 100_0000;
    System.out.println(loopNum);
    Counter counter = new Counter();
    IntStream.range(0, loopNum).parallel()
      .forEach(i -> counter.addAndGet());
    System.out.println(counter.getSum());
  }
}
