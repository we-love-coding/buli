//package com.bibu.promotion.api.aop;
//
//import com.alibaba.fastjson.JSON;
//import com.bibu.promotion.common.annotations.DistributedLock;
//import com.bibu.promotion.common.enums.PromotionExceptionEnum;
//import com.bibu.promotion.common.exceptions.PromotionException;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Pointcut;
//import org.redisson.api.RLock;
//import org.redisson.api.RedissonClient;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import java.util.HashMap;
//import java.util.Map;
//import java.util.concurrent.TimeUnit;
//
///**
// * Author XY
// * Date 2024/5/9 下午4:18
// */
//@Aspect
//@Component
//public class DistributedLockAspect extends AbstractAspect {
//
//    @Autowired
//    private RedissonClient redissonClient;
//
//    @Pointcut("@annotation(com.bibu.promotion.common.annotations.DistributedLock)")
//    public void pointcut() {
//
//    }
//
//    @Around("pointcut() && @annotation(distributedLock)")
//    public Object distributedLock(ProceedingJoinPoint joinPoint, DistributedLock distributedLock) throws Throwable {
//        String lockKey = distributedLock.lockKey();
//        try {
//            boolean isTryLock;
//            switch (distributedLock.controlType()) {
//                case USER_CONTROL:
//                    lockKey = this.getKey(distributedLock.lockKey());
//                    isTryLock = redissonClient.getLock(lockKey).tryLock(distributedLock.timeout(), TimeUnit.MILLISECONDS);
//                    break;
//                case ACCURATE_CONTROL:
//                    lockKey = distributedLock.lockKey() + (String)JSON.parseObject(JSON.toJSONString(joinPoint.getArgs()[0]), HashMap.class).get("key");
//                    isTryLock = redissonClient.getLock(lockKey).tryLock(distributedLock.timeout(), TimeUnit.MILLISECONDS);
//                    break;
//                case GLOBAL_CONTROL:
//                    lockKey = distributedLock.lockKey();
//                    isTryLock = redissonClient.getLock(lockKey).tryLock(distributedLock.timeout(), TimeUnit.MILLISECONDS);
//                    break;
//                default:throw new PromotionException(PromotionExceptionEnum.ACTIVITY_IS_OVER);
//            }
//            if (!isTryLock) {
//                throw new PromotionException(PromotionExceptionEnum.ACTIVITY_IS_OVER);
//            }
//            return joinPoint.proceed();
//        } catch (Exception e) {
//            throw new PromotionException(PromotionExceptionEnum.ACTIVITY_IS_OVER);
//        } finally {
//            RLock lock = redissonClient.getLock(lockKey);
//            if (lock.isLocked()) {
//                lock.unlock();
//            }
//        }
//    }
//}
