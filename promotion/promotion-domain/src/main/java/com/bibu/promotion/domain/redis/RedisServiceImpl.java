package com.bibu.promotion.domain.redis;

import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.concurrent.TimeUnit;

/**
 * Author XY
 * Date 2025/8/4 下午4:50
 */
public class RedisServiceImpl implements RedisClient {

    private final StringRedisTemplate redisTemplate;

    public RedisServiceImpl(StringRedisTemplate stringRedisTemplate) {
        this.redisTemplate = stringRedisTemplate;
    }

    @Override
    public void set(String key, String value) {
        redisTemplate.boundValueOps(key).set(value);
    }

    @Override
    public void setWithTTL(String key, String value, long ttl, TimeUnit unit) {
        redisTemplate.boundValueOps(key).set(value, ttl, unit);
    }

    @Override
    public String get(String key) {
        return redisTemplate.boundValueOps(key).get();
    }

    @Override
    public Long increment(String key) {
        return redisTemplate.boundValueOps(key).increment();
    }

    @Override
    public Long decrement(String key) {
        return redisTemplate.boundValueOps(key).decrement();
    }

    @Override
    public Boolean expire(String key, long time, TimeUnit unit) {
        return redisTemplate.boundValueOps(key).expire(time, unit);
    }

    @Override
    public Boolean delete(String key) {
        RedisOperations<String, String> operations = redisTemplate.boundValueOps(key).getOperations();
        return operations.delete(key);
    }
}
