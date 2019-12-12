package com.demo.gateway.controller;

import com.demo.gateway.entity.User;
import com.yunsom.common.base.api.dto.Result;
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
public class WebFluxController {

  @PostMapping("/save")
  public Mono<Result<Integer>> save(@RequestBody User user) {
    return Mono.empty();
  }


}