package com.demo.gateway.filter;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author caozhongyu
 * @FileName: UriKeyResolver
 * @Description:
 * @create 19-12-11
 */
public class UriKeyResolver implements KeyResolver {

  @Override
  public Mono<String> resolve(ServerWebExchange exchange) {
    return Mono.just("/provider/queryUser");
  }
}
