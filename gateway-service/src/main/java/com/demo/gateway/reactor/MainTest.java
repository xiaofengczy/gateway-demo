package com.demo.gateway.reactor;

import com.demo.gateway.reactor.exception.TestException;
import com.yunsom.common.base.exception.BusinessException;
import java.util.Arrays;
import java.util.Objects;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

//import com.yunsom.common.base.exception.BusinessException;

/**
 * FileName: MainTest Description:
 *
 * @author caozhongyu
 * @create 2019/12/12
 */
public class MainTest {

  private Logger logger = LoggerFactory.getLogger(MainTest.class);

  @Test
  public void testFluxData() {
    //直接创建
    Flux<Integer> just = Flux.just(1, 2, 3, 4, 5);
    just.subscribe(System.out::println);

    System.out.println("============================");

    //通过数组创建
    Flux<Integer> arrayFlux = Flux.fromArray(new Integer[]{11, 22, 33, 44, 55, 66});
    arrayFlux.subscribe(System.out::println);

    System.out.println("============================");
    //通过集合创建
    Flux<String> listFlux = Flux
        .fromIterable(Arrays.asList(new String[]{"jeck", "tom", "lilei"}));
    listFlux.subscribe(System.out::println);

    System.out.println("============================");
    //通过Stream创建
    Flux<Integer> streamFlux = Flux.fromStream(Arrays.stream(new Integer[]{123, 456, 789, 99}));
    streamFlux.subscribe(System.out::println);
  }


  @Test
  public void testFluxComplate() {
    Flux<Void> just = Flux.just();
    Flux<Object> empty = Flux.empty();
  }

  @Test
  public void testFluxError() {
    Flux<Object> errorFlux = Flux.error(new Exception("do error"));
  }

  @Test
  public void testFluxSubscribe() {
    Flux<Integer> just = Flux.range(1, 6);
    just.subscribe(i -> {
      Integer j = i / (i - 3);
      System.out.println(j);
    }, System.err::println);
  }

  @Test
  public void testMap() {
    Flux<Integer> just = Flux.range(1, 6);
    just.map(i -> i * 3).subscribe(System.out::println);
  }

  @Test
  public void testFlatMap() {
    Flux<String> just = Flux.just("hello", "world");
    Flux<String> stringFlux1 = just
        .flatMap(f -> Flux.fromArray(f.split("\\s*")));
    stringFlux1.subscribe(System.out::println);
  }

  @Test
  public void testFilter() {
    Flux<String> just = Flux.just("hello", "world");
    Flux<String> stringFlux = just.flatMap(f -> Flux.fromArray(f.split("\\s*")));

    //filter
    stringFlux.filter(s -> Objects.equals(s, "h"))
        .subscribe(System.out::println);

    //take
    stringFlux.take(2).subscribe(System.out::println);

    // skip
    stringFlux.skip(3).subscribe(System.out::println);
  }

  @Test
  public void testSyncToAsync() throws InterruptedException {
    CountDownLatch countDownLatch = new CountDownLatch(1);
    System.out.println(
        "main threadId:" + Thread.currentThread().getId() + ",threadName:" + Thread.currentThread()
            .getName());
    Mono.fromCallable(() -> getStringSync())
        .subscribeOn(Schedulers.elastic())
        .subscribe(s ->
                System.out.println(
                    "sub threadId:" + Thread.currentThread().getId() + ",threadName:" + Thread
                        .currentThread().getName() + ",s:" + s)
            , null, countDownLatch::countDown);
    countDownLatch.await(10, TimeUnit.SECONDS);
  }

  private String getStringSync() {
    try {
      TimeUnit.SECONDS.sleep(2);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    return "Hello, Reactor!";
  }


  @Test
  public void testError() {
    Flux.range(1, 6)
        .map(i -> 10 / (i - 4))
        .map(i -> i * i)
        .subscribe(System.out::println, System.err::println);
  }

  @Test
  public void testErrorReturn() {
    Flux.range(1, 6)
        .map(i -> 10 / (i - 4))
        .onErrorReturn(200)
        .subscribe(System.out::println);
  }

  @Test
  public void testErrorResume() {
    final Integer[] r = new Integer[1];
    Flux.range(1, 6)
        .flatMap(k -> getFromDB(r, k))
        .onErrorResume(e -> getFromCache(r[0]))
        .subscribe(System.out::println);
  }

  private Mono<Integer> getFromDB(Integer[] r, Integer k) {
    if (Objects.equals(k, 3)) {
      r[0] = k;
      return Mono.error(new BusinessException("查询用户异常"));
    }
    return Mono.just(k);
  }

  public Flux<Integer> getFromCache(Integer k) {
    System.out.println("查询缓存用户");
    return Flux.just(k * 3);
  }

  @Test
  public void testErrorMap() {
    Flux.range(1, 6)
        .map(i -> 10 / (i - 3))
        .onErrorMap(o -> new TestException("query fail", o))
        .subscribe(System.out::println, System.err::println);
  }


  @Test
  public void testDoOnError() {
    Flux.range(1, 6)
        .map(i -> 10 / (i - 3))
        .doOnError(e -> logger.error("查询用户信息失败:{}", e))
        .onErrorMap(o -> new TestException("查询失败", o))
        .subscribe(System.out::println, System.err::println);
  }

  @Test
  public void testDoFinally() {
    Flux.range(1, 6)
        .map(i -> 10 / (i - 3))
        .doOnError(e -> logger.error("查询用户信息失败:{}", e))
        .onErrorMap(o -> new TestException("查询失败", o))
        .subscribe(System.out::println, System.err::println);
  }

}

























