package com.bibu.promotion.api.aop;

import com.alibaba.fastjson.JSON;
import com.bibu.promotion.common.annotations.DistributeLock‌;
import com.bibu.promotion.common.enums.PromotionExceptionEnum;
import com.bibu.promotion.common.exceptions.PromotionException;
import com.bibu.promotion.domain.lock.DistributedLock;
import com.x.common.aops.AbstractAspect;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.UUID;

/**
 * Author XY
 * Date 2024/5/9 下午4:18
 */
@Aspect
@Component
public class DistributedLockAspect extends AbstractAspect {

    @Autowired
    private DistributedLock distributedLock;

    @Pointcut("@annotation(com.bibu.promotion.common.annotations.DistributeLock‌)")
    public void pointcut() {

    }

    @Around("pointcut() && @annotation(distributeLock‌)")
    public Object distributedLock(ProceedingJoinPoint joinPoint, DistributeLock‌ distributeLock‌) throws Throwable {
        String lockKey = distributeLock‌.lockKey();
        String uuidStr = UUID.randomUUID().toString();
        try {
            boolean isTryLock;
            switch (distributeLock‌.controlType()) {
                case USER_CONTROL:
                    lockKey = this.getKey(distributeLock‌.lockKey());
                    isTryLock = distributedLock.tryLock(lockKey, uuidStr, distributeLock‌.timeout());
                    break;
                case ACCURATE_CONTROL:
                    lockKey = distributeLock‌.lockKey() + (String)JSON.parseObject(JSON.toJSONString(joinPoint.getArgs()[0]), HashMap.class).get("key");
                    isTryLock = distributedLock.tryLock(lockKey, uuidStr, distributeLock‌.timeout());
                    break;
                case GLOBAL_CONTROL:
                    lockKey = distributeLock‌.lockKey();
                    isTryLock = distributedLock.tryLock(lockKey, uuidStr, distributeLock‌.timeout());
                    break;
                default:throw new PromotionException(PromotionExceptionEnum.ACTIVITY_IS_OVER);
            }
            if (!isTryLock) {
                throw new PromotionException(PromotionExceptionEnum.ACTIVITY_IS_OVER);
            }
            return joinPoint.proceed();
        } catch (Exception e) {
            throw new PromotionException(PromotionExceptionEnum.ACTIVITY_IS_OVER);
        } finally {
            distributedLock.unlock(lockKey, uuidStr);
        }
    }
}
