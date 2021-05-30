package demo.concurrency;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

public class LockCounter {
  private int sum = 0;
  // 可重入锁 + 公平锁
  private Lock lock = new ReentrantLock(true);
  private int addAndGet() {
    try {
      lock.lock();
      return ++sum;
    } finally {
      // 释放锁
      lock.unlock();
    }
  }

  public int getSum() {
    return sum;
  }

  public static void main(String[] args) {
    int loopNum = 100_0000;
    System.out.println(loopNum);
    LockCounter lockCounter = new LockCounter();
    IntStream.range(0, loopNum).parallel()
      .forEach(i -> lockCounter.addAndGet());
    System.out.println(lockCounter.getSum());
  }
}
