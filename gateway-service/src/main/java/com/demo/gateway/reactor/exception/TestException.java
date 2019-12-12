package com.demo.gateway.reactor.exception;

import com.yunsom.common.base.exception.BusinessException;

/**
 * FileName: TestException Description:
 *
 * @author caozhongyu
 * @create 2019/12/12
 */
public class TestException extends BusinessException {

  public TestException(String msg,Throwable t){
    super(msg);
  }
}