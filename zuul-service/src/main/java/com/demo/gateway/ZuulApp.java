package com.demo.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @author caozhongyu
 * @FileName: ZuulApp
 * @Description:
 * @create 19-12-10
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableZuulProxy
public class ZuulApp {

  public static void main(String[] args) {
    SpringApplication.run(ZuulApp.class);
  }

}
