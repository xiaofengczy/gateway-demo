package com.demo.gateway.dao;

import com.demo.gateway.entity.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

/**
 * FileName: UserDao Description:
 *
 * @author caozhongyu
 * @create 2019/12/12
 */
public interface UserDao extends ReactiveMongoRepository<User,String> {

}