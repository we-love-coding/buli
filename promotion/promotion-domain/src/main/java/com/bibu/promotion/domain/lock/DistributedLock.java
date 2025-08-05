package com.bibu.promotion.domain.lock;

/**
 * Author XY
 * Date 2025/8/4 下午5:07
 */
public interface DistributedLock {

    /**
     * 非阻塞获取锁（立即返回结果）
     *
     * @param lockKey   锁标识
     * @param requestId 请求ID（保证解锁时校验所有者）
     * @param expire    锁过期时间（单位：秒）
     * @return 是否获取成功
     */
    boolean tryLock(String lockKey, String requestId, long expire);

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
    boolean tryLock(String lockKey, String requestId, long expire, long waitTime);

    /**
     * 安全释放锁（Lua脚本保证原子性）
     *
     * @param lockKey   锁标识
     * @param requestId 请求ID（需与加锁时一致）
     * @return 是否释放成功
     */
    boolean unlock(String lockKey, String requestId);
}
