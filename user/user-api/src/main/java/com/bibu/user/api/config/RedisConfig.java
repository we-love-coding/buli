package com.bibu.user.api.config;

import com.bibu.user.domain.lock.DistributedLock;
import com.bibu.user.domain.redis.RedisClient;
import com.bibu.user.domain.redis.RedisDistributedLock;
import com.bibu.user.domain.redis.RedisServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * Author XY
 * Date 2024/5/2 下午3:04
 */
@Configuration
public class RedisConfig {

    @Bean
    public RedisClient redisClient(StringRedisTemplate redisTemplate) {
        return new RedisServiceImpl(redisTemplate);
    }


    @Bean
    public DistributedLock distributedLock(StringRedisTemplate redisTemplate) {
        return new RedisDistributedLock(redisTemplate);
    }
}
