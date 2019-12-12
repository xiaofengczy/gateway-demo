package com.demo.gateway;

import com.demo.gateway.filter.UriKeyResolver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;

/**
 * @author caozhongyu
 * @FileName: GatewayApp
 * @Description:
 * @create 19-12-10
 */
@SpringBootApplication
@EnableDiscoveryClient
public class GatewayApp {

  public static void main(String[] args) {
    SpringApplication.run(GatewayApp.class);
  }

  @Bean
  KeyResolver uriKeyResolver(){
    return new UriKeyResolver();
  }

}
