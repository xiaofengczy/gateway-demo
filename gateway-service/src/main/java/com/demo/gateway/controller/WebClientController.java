package com.demo.gateway.controller;

import com.demo.gateway.entity.User;
import com.demo.gateway.service.Httpservice;
import com.demo.gateway.utils.Result;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * FileName: WebFluxController Description:
 *
 * @author caozhongyu
 * @create 2019/12/12
 */
@RestController
public class WebClientController {

  @Resource
  private Httpservice httpservice;

  @GetMapping("/http/find/{id}")
  public Mono<Result> queryById(@PathVariable String id) {
    return httpservice.queryById(id).flatMap(r -> toSuccess(r));
  }

  @PostMapping("/http/save")
  public Mono<Result> save(@RequestBody User user) {
    return httpservice.saveUser(user).flatMap(r -> toSuccess(r));
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
    return Mono.just(result);
  }

}