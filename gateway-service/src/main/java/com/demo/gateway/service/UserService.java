package com.demo.gateway.service;

import com.demo.gateway.entity.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * FileName: UserService Description:
 *
 * @author caozhongyu
 * @create 2019/12/12
 */
public interface UserService {

  Mono<String> saveUser(User user);

  Mono<Void> deleteUser(String userId);

  Mono<User> updateUser(User user);

  Mono<User> queryById(String userId);

  Flux<User> queryAll();
}