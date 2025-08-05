package com.bibu.user.domain.redis;

import java.util.concurrent.TimeUnit;

/**
 * Author XY
 * Date 2025/8/4 下午4:44
 */
public interface RedisClient {

    // 字符串操作
    void set(String key, String value);
    void setWithTTL(String key, String value, long ttl, TimeUnit unit);
    String get(String key);

    // 原子操作
    Long increment(String key);
    Long decrement(String key);

    // 过期控制
    Boolean expire(String key, long time, TimeUnit unit);
    Boolean delete(String key);
}
