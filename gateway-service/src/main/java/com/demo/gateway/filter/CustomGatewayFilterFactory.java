package com.demo.gateway.filter;

import java.util.Arrays;
import java.util.List;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

/**
 * @author caozhongyu
 * @FileName: CustomGatewayFilter
 * @Description:
 * @create 19-12-11
 */
@Component
public class CustomGatewayFilterFactory extends
    AbstractGatewayFilterFactory<CustomGatewayFilterFactory.Config> {

  private Logger logger = LoggerFactory.getLogger(CustomGatewayFilterFactory.class);

  public CustomGatewayFilterFactory() {
    super(Config.class);
  }

  @Override
  public GatewayFilter apply(Config config) {
    return (exchange, chain) -> {
      if (!config.isEnabled()) {
        return chain.filter(exchange);
      }
      exchange.getAttributes().put("start_time", System.currentTimeMillis());
      return chain.filter(exchange).then(
          Mono.fromRunnable(() -> {
            Long startTime = exchange.getAttribute("start_time");
            if (startTime != null) {
              StringBuilder sb = new StringBuilder(exchange.getRequest().getURI().getRawPath())
                  .append(": ")
                  .append(System.currentTimeMillis() - startTime)
                  .append("ms");
              sb.append(" params:").append(exchange.getRequest().getQueryParams());
              logger.info(sb.toString());
            }
          })
      );
    };
  }

  @Override
  public List<String> shortcutFieldOrder() {
    return Arrays.asList("enabled");
  }

  @Data
  public static class Config {
    public Config() {}
    private boolean enabled;
  }
}
