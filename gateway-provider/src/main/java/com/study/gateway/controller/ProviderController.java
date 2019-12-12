package com.study.gateway.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author caozhongyu
 * @FileName: ProviderController
 * @Description:
 * @create 19-12-10
 */
@RestController
public class ProviderController {

  @GetMapping("/provider/queryUser")
  public String queryUser(HttpServletRequest request) {
    String header = request.getHeader("context-user");
    String name = request.getParameter("name");
    System.out.println(name);
    System.out.println(header);
    return "hello world queryUser";
  }

  @GetMapping("/provider/getUser")
  public String getUser(HttpServletRequest request) {
    String header = request.getHeader("context-user");
    String name = request.getParameter("name");
    System.out.println(name);
    System.out.println(header);
    return "hello world getUser";
  }

}
