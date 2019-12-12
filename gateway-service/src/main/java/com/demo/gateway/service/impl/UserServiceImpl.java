package com.demo.gateway.service.impl;

import com.demo.gateway.dao.UserDao;
import com.demo.gateway.entity.User;
import com.demo.gateway.service.UserService;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * FileName: UserServiceImpl Description:
 *
 * @author caozhongyu
 * @create 2019/12/12
 */
@Service
public class UserServiceImpl implements UserService {

  @Resource
  private UserDao userDao;

  @Override
  public Mono<String> saveUser(User user) {
    Mono<User> save = userDao.save(user);
    return save.flatMap(s -> {
      System.out.println(s);
      return Mono.just(s.getId());
    });
  }

  @Override
  public Mono<Boolean> deleteUser(String userId) {
    return userDao.findById(userId).flatMap(user -> userDao.deleteById(userId).then(Mono.just(true)))
        .defaultIfEmpty(false);
  }

  @Override
  public Mono<User> updateUser(User user) {
    return userDao.save(user);
  }

  @Override
  public Mono<User> queryById(String userId) {
    return userDao.findById(userId);
  }

  @Override
  public Flux<User> queryAll() {
    return userDao.findAll();
  }
}