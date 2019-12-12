package com.demo.gateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author caozhongyu
 * @FileName: FallbackController
 * @Description:
 * @create 19-12-11
 */
@RestController
public class FallbackController {

  @GetMapping("/fallback")
  public String fallback(){
    return "query user timeout";
  }

}
