package com.bibu.promotion.common.enums;

import lombok.Getter;

/**
 * Author XY
 * Date 2024/5/6 下午8:32
 */
@Getter
public enum PromotionExceptionEnum {

    ACTIVITY_IS_OVER(40010, "活动结束"),;

    private final int code;

    private final String message;

    PromotionExceptionEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

}
