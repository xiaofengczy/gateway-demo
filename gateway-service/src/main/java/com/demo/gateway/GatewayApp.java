package com.demo.gateway;

import com.demo.gateway.filter.UriKeyResolver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.core.ReactiveStringRedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializationContext;

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
