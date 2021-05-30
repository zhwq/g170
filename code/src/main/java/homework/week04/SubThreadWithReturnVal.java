package homework.week04;

import lombok.Data;
import lombok.SneakyThrows;

import java.util.concurrent.*;

/**
 * 0402 有多少种方式
 * 在main函数启动一个新线程或线程池
 * 异步运行一个方法
 * 拿到这个方法的返回值后 退出主线程
 * <p/>
 *  子线程分2种:
 *  ① 无返回值 实现: extend Thread  或者 implements Runnable
 *  ② 有返回值 实现: implements Callable<> + FutureTask{run + set}
 *  线程的创建:
 *  ①线程池 Executor
 *  Future:
 *  ① FutureTask
 *  ② jdk8 CompletableFuture
 *  async:
 *  ① CountDownLatch
 */
@Data
public class SubThreadWithReturnVal {

  private int result;

  private static int sum() {
    return fibo(36);
  }

  private static int fibo(int a) {
    if ( a < 2)
      return 1;
    return fibo(a-1) + fibo(a-2);
  }

  private static String service() {
    return "异步执行方法返回值";
  }

  @SneakyThrows
  public static void main(String[] args) {
    long start=System.currentTimeMillis();
    int result;
    // while 循环阻塞主线程
    result = getSubResult();
    System.out.println("①异步计算结果为：" + result);
    // callable future task
    result = getSubResultByCallable();
    System.out.println("②异步计算结果为：" + result);
    // callable executors future
    result = getSubResultByCallcableWithThreadpool();
    System.out.println("③异步计算结果为：" + result);
    // ④ 同步线程
    CountDownLatch countDownLatch = new CountDownLatch(1);
    final int[] arr = new int[1];
    new Thread(() -> {
      arr[0] = sum();
      // Decrements the count of the latch, releasing all waiting threads if the count reaches zero
      countDownLatch.countDown();
    }).start();
    countDownLatch.await();
    System.out.println("④异步计算结果为：" + result);
    // ⑤ java8 CompletableFuture 实现了Future接口
    // 一个 CompletableFuture 代表一个任务 默认使用ForkJoinPool创建线程 守护进程
    result = CompletableFuture.supplyAsync(() -> {
      System.out.println("⑤ Thread.currentThread().isDaemon()=" + Thread.currentThread().isDaemon());
      return sum();
    }).get();
    System.out.println("⑤异步计算结果为：" + result);
    result = CompletableFuture.supplyAsync(() -> {
      System.out.println("⑥ Thread.currentThread().isDaemon()=" + Thread.currentThread().isDaemon());
      return sum();
    }, Executors.newSingleThreadExecutor()).get();
    System.out.println("⑥异步计算结果为：" + result);
//    System.out.println("异步计算结果为：" + result);
    System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");
    // 然后退出main线程
    System.exit(0);
  }

  @SneakyThrows
  private static int getSubResult() {
    int result;
    // 在这里创建一个线程或线程池，
    // 异步执行 下面方法
    // result = sum(); //这是得到的返回值
    SubThreadWithReturnVal subMain = new SubThreadWithReturnVal();
    Thread t = new Thread(() -> {
      int sum = sum();
      subMain.setResult(sum);
      System.out.println(sum);
    });
    t.start();
//    确保  拿到result 并输出
    /*
     * ① 阻塞主线程 直到子线程返回
     */
/*
    while (subMain.getResult() == 0) {
      Thread.sleep(1000);
    }
*/
    // Thread的join方法来阻塞主线程，直到子线程返回
    t.join();
    result = subMain.getResult();
    return result;
  }
  // ② callable + futuretask
  private static int getSubResultByCallable() throws ExecutionException, InterruptedException {
    FutureTask futureTask = new FutureTask(() -> sum());
    Thread t = new Thread(futureTask);
    t.start();
    return (int) futureTask.get();
  }
  // ③
  private static int getSubResultByCallcableWithThreadpool() throws ExecutionException, InterruptedException {
    ExecutorService executor = Executors.newSingleThreadExecutor();
    Future future = executor.submit(() -> sum());
    return (int) future.get();
  }
}
