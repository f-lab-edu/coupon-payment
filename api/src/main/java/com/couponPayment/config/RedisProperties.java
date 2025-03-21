package com.couponPayment.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Setter
@Getter
@ConfigurationProperties(prefix = "spring.redis.connection")
public class RedisProperties {
    private String host;
    private int port;
    private String password; // 필요하면 추가
}
