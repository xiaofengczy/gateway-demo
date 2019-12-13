package com.demo.gateway.service;

import com.demo.gateway.entity.User;
import reactor.core.publisher.Mono;

/**
 * FileName: Httpservice Description:
 *
 * @author caozhongyu
 * @create 2019/12/13
 */
public interface Httpservice {

  Mono<User> queryById(String userId);

  Mono<Integer> saveUser(User user);
}