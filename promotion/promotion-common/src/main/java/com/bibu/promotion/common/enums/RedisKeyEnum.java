package com.bibu.promotion.common.enums;

import com.x.common.utils.StringUtil;
import lombok.Getter;

import java.util.concurrent.TimeUnit;

/**
 * Author XY
 * Date 2025/8/5 下午7:43
 */
@Getter
public enum RedisKeyEnum {
    COUPON_TOTAL_NUM("coupon_total_num:{0}", 24L, TimeUnit.HOURS, "一张券被领取的数量"),
    COUPON_OBTAIN_TOTAL_NUM("coupon_obtain_total_num:{0}", 24L, TimeUnit.HOURS, "一张券被领取的数量"),
    USER_COUPON_OBTAIN_TOTAL_NUM("user_coupon_obtain_total_num:{0}_{1}", 24L, TimeUnit.HOURS, "用户一张券被领取的数量");

    private final String prefix;
    private final Long expire;
    private final TimeUnit timeUnit;
    private final String desc;

    RedisKeyEnum(String prefix, Long expire, TimeUnit timeUnit, String desc) {
        this.prefix = prefix;
        this.expire = expire;
        this.timeUnit = timeUnit;
        this.desc = desc;
    }


    public String buildKey(Object... args) {
        return StringUtil.format(this.prefix, args);
    }
}
