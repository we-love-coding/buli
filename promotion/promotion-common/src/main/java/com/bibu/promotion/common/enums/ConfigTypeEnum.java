package com.bibu.promotion.common.enums;

import lombok.Getter;

/**
 * Author XY
 * Date 2024/5/11 下午12:09
 */
@Getter
public enum ConfigTypeEnum {

    GOODS_TYPE(1, "商品"),
    COUPON_TYPE(2, "优惠券"),
    POINTS_TYPE(3, "积分"),;

    private final int type;

    private final String name;

    ConfigTypeEnum(int type, String name) {
        this.type = type;
        this.name = name;
    }
}
