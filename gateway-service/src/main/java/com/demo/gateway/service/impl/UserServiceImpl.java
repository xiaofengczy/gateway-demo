package com.demo.gateway.service.impl;

import com.demo.gateway.entity.User;
import com.demo.gateway.service.UserService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

/**
 * FileName: UserServiceImpl Description:
 *
 * @author caozhongyu
 * @create 2019/12/12
 */
@Service
public class UserServiceImpl implements UserService {

  @Override
  public Mono<Integer> saveUser(User user) {
    return null;
  }
}