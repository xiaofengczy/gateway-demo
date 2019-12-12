package com.demo.gateway.service;

import com.demo.gateway.entity.User;
import reactor.core.publisher.Mono;

/**
 * FileName: UserService Description:
 *
 * @author caozhongyu
 * @create 2019/12/12
 */
public interface UserService {

  Mono<Integer> saveUser(User user);

}