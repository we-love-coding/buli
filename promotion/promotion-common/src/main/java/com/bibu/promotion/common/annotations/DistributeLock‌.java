package com.bibu.promotion.common.annotations;

import com.bibu.promotion.common.enums.ControlTypeEnum;

import java.lang.annotation.*;

/**
 * Author XY
 * Date 2024/5/9 下午4:19
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DistributeLock‌ {

    String lockKey() default "lockKey";

    long timeout() default 3;

    ControlTypeEnum controlType() default ControlTypeEnum.USER_CONTROL;
}
