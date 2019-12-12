package com.demo.gateway.controller;

import com.demo.gateway.entity.User;
import com.demo.gateway.reactor.exception.TestException;
import com.demo.gateway.service.UserService;
import com.demo.gateway.utils.Result;
import java.util.Objects;
import javax.annotation.Resource;
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

  @PostMapping("/save")
  public Mono<Result> save(@RequestBody User user) {
    //参数校验
    return validateParam(user)
        .switchIfEmpty(userService.saveUser(user))
        .flatMap(r -> toSuccess(r))
        .onErrorResume(e -> toFail(e));
  }

  @DeleteMapping("/delete")
  public Mono<Void> delete(@RequestParam String userId) {
     return userService.deleteUser(userId);
  }

  @PutMapping("/update")
  public Mono<Result> update(@RequestBody User user) {
    return userService.updateUser(user).flatMap(r->toSuccess(r));
  }

  @GetMapping("/find/{id}")
  public Mono<Result> queryById(@PathVariable String id) {
    return userService.queryById(id).flatMap(r->toSuccess(r));
  }

  @GetMapping("/find/all")
  public Flux<Result> queryAll() {
    return userService.queryAll().flatMap(r->toSuccess(r));
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
    TestException exception = (TestException) t;
    Result result = new Result();
    result.setCode(exception.getCode());
    result.setMessage(exception.getMsg());
    return Mono.just(result);
  }

}