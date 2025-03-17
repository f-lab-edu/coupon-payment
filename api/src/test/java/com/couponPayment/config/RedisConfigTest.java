package com.couponPayment.config;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.redis.DataRedisTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.redis.connection.RedisServerCommands;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@DataRedisTest
@Import(RedisConfig.class)
class RedisConfigTest {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Test
    public void testRedisTemplateSetAndGet() {
        String key = "test-key";
        String value = "test-value";

        redisTemplate.opsForValue().set(key, value);
        Object retrievedValue = redisTemplate.opsForValue().get(key);

        assertThat(retrievedValue).isEqualTo(value);
    }

    @Test
    public void clearAllRedis() {
        // 현재 Redis 데이터베이스의 모든 키 삭제
        redisTemplate.execute((RedisCallback<Object>) connection -> {
            if (connection instanceof RedisServerCommands) {
                ((RedisServerCommands) connection).flushDb();
            }
            return null;
        });
    }
}