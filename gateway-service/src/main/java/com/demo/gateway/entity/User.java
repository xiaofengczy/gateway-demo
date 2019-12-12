package com.demo.gateway.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * FileName: User Description:
 *
 * @author caozhongyu
 * @create 2019/12/12
 */
@Data
@Document
public class User {

  @Id
  private String id;

  private String userName;

  private String phone;

}