package com.bibu.promotion.common.enums;

import lombok.Getter;

/**
 * Author XY
 * Date 2024/5/6 下午8:32
 */
@Getter
public enum PromotionExceptionEnum {

    ACTIVITY_IS_OVER(40010, "活动结束"),
    USER_COUPON_OBTAIN_COMPLETED(40020, "用户券已领取完"),
    COUPON_OBTAIN_COMPLETED(40030, "券已领取完"),
    COUPON_OBTAIN_FAIL(40040, "券领取失败"),
    ;

    private final int code;

    private final String message;

    PromotionExceptionEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

}
