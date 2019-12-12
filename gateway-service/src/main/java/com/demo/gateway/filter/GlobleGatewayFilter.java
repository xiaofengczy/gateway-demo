//package com.demo.gateway.filter;
//
//import java.util.Arrays;
//import java.util.List;
//import org.springframework.cloud.gateway.filter.GatewayFilterChain;
//import org.springframework.cloud.gateway.filter.GlobalFilter;
//import org.springframework.core.Ordered;
//import org.springframework.http.HttpStatus;
//import org.springframework.stereotype.Component;
//import org.springframework.util.StringUtils;
//import org.springframework.web.server.ServerWebExchange;
//import reactor.core.publisher.Mono;
//
///**
// * @author caozhongyu
// * @FileName: GlobleGatewayFilter
// * @Description:
// * @create 19-12-11
// */
//@Component
//public class GlobleGatewayFilter implements GlobalFilter, Ordered {
//
//  @Override
//  public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
//    String authorization = exchange.getRequest().getHeaders().getFirst("Authorization");
//    if(StringUtils.isEmpty(authorization)){
//      exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
//      return exchange.getResponse().setComplete();
//    }
//    List<String> authors = Arrays.asList(authorization.split(" "));
//    if(StringUtils.isEmpty(authors)){
//      exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
//      return exchange.getResponse().setComplete();
//    }
//    return chain.filter(exchange);
//  }
//
//  @Override
//  public int getOrder() {
//    return 0;
//  }
//}
