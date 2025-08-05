package com.bibu.user.domain.redis;

import com.bibu.user.domain.lock.DistributedLock;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;


import java.util.Collections;
import java.util.concurrent.TimeUnit;

/**
 * Author XY
 * Date 2025/8/5 下午2:29
 */
public class RedisDistributedLock implements DistributedLock {

    private final StringRedisTemplate redisTemplate;
    private static final String LOCK_PREFIX = "LOCK:";
    private static final String UNLOCK_SCRIPT =
            "if redis.call('get', KEYS[1]) == ARGV[1] then " +
                    "   return redis.call('del', KEYS[1]) " +
                    "else " +
                    "   return 0 " +
                    "end";

    public RedisDistributedLock(StringRedisTemplate stringRedisTemplate) {
        this.redisTemplate = stringRedisTemplate;
    }

    /**
     * 非阻塞获取锁（立即返回结果）
     *
     * @param lockKey   锁标识
     * @param requestId 请求ID（保证解锁时校验所有者）
     * @param expire    锁过期时间（单位：秒）
     * @return 是否获取成功
     */
    public boolean tryLock(String lockKey, String requestId, long expire) {
        String key = LOCK_PREFIX + lockKey;
        Boolean success = redisTemplate.opsForValue()
                .setIfAbsent(key, requestId, expire, TimeUnit.SECONDS);
        return Boolean.TRUE.equals(success);
    }

    /**
     * 带超时的阻塞获取锁
     *
     * @param lockKey   锁标识
     * @param requestId 请求ID
     * @param expire    锁过期时间（秒）
     * @param waitTime  最大等待时间（秒）
     * @return 是否获取成功
     * @throws InterruptedException 线程中断异常
     */
    public boolean tryLock(String lockKey, String requestId, long expire, long waitTime) {

        String key = LOCK_PREFIX + lockKey;
        long endTime = System.currentTimeMillis() + waitTime * 1000;

        while (System.currentTimeMillis() < endTime) {
            if (Boolean.TRUE.equals(redisTemplate.opsForValue()
                    .setIfAbsent(key, requestId, expire, TimeUnit.SECONDS))) {
                return true;
            }
            // 短暂等待后重试
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (Exception e) {

            }
        }
        return false;
    }

    /**
     * 安全释放锁（Lua脚本保证原子性）
     *
     * @param lockKey   锁标识
     * @param requestId 请求ID（需与加锁时一致）
     * @return 是否释放成功
     */
    public boolean unlock(String lockKey, String requestId) {
        String key = LOCK_PREFIX + lockKey;
        Long result = redisTemplate.execute(
                new DefaultRedisScript<>(UNLOCK_SCRIPT, Long.class),
                Collections.singletonList(key),
                requestId
        );
        return result != null && result == 1;
    }
}
