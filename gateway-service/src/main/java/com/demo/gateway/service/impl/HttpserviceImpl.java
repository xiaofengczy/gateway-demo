package com.demo.gateway.service.impl;

import com.demo.gateway.entity.User;
import com.demo.gateway.service.Httpservice;
import com.yunsom.common.base.api.dto.Result;
import com.yunsom.common.base.exception.BusinessException;
import javax.annotation.Resource;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * FileName: HttpserviceImpl Description:
 *
 * @author caozhongyu
 * @create 2019/12/13
 */
@Service
public class HttpserviceImpl implements Httpservice {

  @Resource
  private WebClient.Builder webClientBuilder;

  @Override
  public Mono<User> queryById(String userId) {
    String uri = "/provider/query/{userId}";
//    Map<String,Object> param = new HashMap<>();
//    param.put("userId",userId);
    Mono<Result<User>> resultMono = webClientBuilder
        .build()
        .get()
        .uri(uri, userId)
//        .uri(uri,param)
        .retrieve()
        .bodyToMono(new ParameterizedTypeReference<Result<User>>() {
        });
    return resultMono.flatMap(r -> {
      if (!r.ifSuccess()) {
        return Mono.error(new BusinessException(r.getMessage(), r.getCode()));
      }
      return Mono.just(r.getData());
    });
  }

  @Override
  public Mono<Integer> saveUser(User user) {
    String uri = "/provider/query/{userId}";
    Mono<Result<Integer>> resultMono = webClientBuilder
        .build()
        .post()
        .uri(uri)
        .syncBody(user)
        .retrieve()
        .bodyToMono(new ParameterizedTypeReference<Result<Integer>>() {
        });
    return resultMono.flatMap(r -> {
      if (!r.ifSuccess()) {
        return Mono.error(new BusinessException(r.getMessage(), r.getCode()));
      }
      return Mono.just(r.getData());
    });
  }


}