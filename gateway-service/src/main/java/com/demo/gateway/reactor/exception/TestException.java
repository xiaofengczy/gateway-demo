package com.demo.gateway.reactor.exception;

import lombok.Data;

/**
 * FileName: TestException Description:
 *
 * @author caozhongyu
 * @create 2019/12/12
 */
@Data
public class TestException extends Exception {

  private String msg;

  private String code;

  public TestException(String msg,Throwable t){
    super(msg);
  }

  public TestException(String msg,String code){
    this.msg = msg;
    this.code = code;
  }

}