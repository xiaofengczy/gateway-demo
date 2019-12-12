package com.study.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author caozhongyu
 * @FileName: ProviderApp
 * @Description:
 * @create 19-12-10
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ProviderApp {

  public static void main(String[] args) {
    SpringApplication.run(ProviderApp.class);
  }

}
