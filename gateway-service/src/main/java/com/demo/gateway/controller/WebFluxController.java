package com.demo.gateway.controller;

import com.demo.gateway.entity.User;
import com.demo.gateway.reactor.exception.TestException;
import com.demo.gateway.service.UserService;
import com.demo.gateway.utils.Result;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * FileName: WebFluxController Description:
 *
 * @author caozhongyu
 * @create 2019/12/12
 */
@RestController
public class WebFluxController {

  @Resource
  private UserService userService;
  private Logger logger = LoggerFactory.getLogger(WebFluxController.class);

  @GetMapping("/test1")
  public String test1() {
    logger.info("test1");
    String result = getStr();
    logger.info("test1");
    return result;
  }

  @GetMapping("/test2")
  public Mono<String> test2() {
    logger.info("test2");
    Mono<String> result = Mono.fromSupplier(() -> getStr());
    logger.info("test2");
    return result;
  }

  private String getStr() {
    try {
      TimeUnit.SECONDS.sleep(5);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    return "hello webflux";
  }

  @PostMapping("/save")
  public Mono<Result> save(@RequestBody User user) {
    //参数校验
    return validateParam(user)
        .switchIfEmpty(userService.saveUser(user))
        .flatMap(r -> toSuccess(r))
        .onErrorResume(e -> toFail(e));
  }

  @DeleteMapping("/delete")
  public Mono<Result> delete(@RequestParam String userId) {
    return userService.deleteUser(userId).flatMap(r -> {
      if (!r) {
        return toFail(r);
      }
      return toSuccess(r);
    });
  }

  @PutMapping("/update")
  public Mono<Result> update(@RequestBody User user) {
    return userService.updateUser(user).flatMap(r -> toSuccess(r));
  }

  @GetMapping("/find/{id}")
  public Mono<Result> queryById(@PathVariable String id) {
    return userService.queryById(id).flatMap(r -> toSuccess(r));
  }

  @GetMapping("/find/all")
  public Flux<Result> queryAll() {
    return userService.queryAll().flatMap(r -> toSuccess(r));
  }

  private Mono validateParam(User user) {
    if (Objects.isNull(user.getUserName())) {
      return Mono.error(new TestException("用户名不能为空", "1001"));
    }
    if (Objects.isNull(user.getPhone())) {
      return Mono.error(new TestException("手机号不能为空", "1001"));
    }
    return Mono.empty();
  }

  public Mono<Result> toSuccess(Object o) {
    Result result = new Result();
    result.setCode("0000");
    result.setMessage("success");
    result.setData(o);
    return Mono.just(result);
  }

  public Mono<Result> toFail(Object t) {
    Result result = new Result();
    result.setCode("1001");
    result.setMessage("fail");
    result.setData(t);
    return Mono.just(result);
  }

}