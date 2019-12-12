package com.demo.gateway.utils;

import lombok.Data;

/**
 * FileName: Result Description:
 *
 * @author caozhongyu
 * @create 2019/12/12
 */
@Data
public class Result<T> {

  private String message;

  private String code;

  private T data;

  public Result() {
  }

  public static <E> Result<E> success(E data) {
    Result<E> result = new Result();
    result.setData(data);
    result.setCode("000000");
    return result;
  }

  public static <E> Result<E> fail(E data, String message, String code) {
    Result<E> result = new Result();
    result.setMessage(message);
    result.setCode(code);
    return result;
  }

  public static <E> Result<E> fail(String message, String code) {
    Result<E> result = new Result();
    result.setMessage(message);
    result.setCode(code);
    return result;
  }

  public static <E> Result<E> fail(String message) {
    Result<E> result = new Result();
    result.setMessage(message);
    result.setCode("100003");
    return result;
  }
}