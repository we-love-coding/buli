package com.bibu.promotion.common.enums;

import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Author XY
 * Date 2024/5/10 下午4:36
 */
@Getter
public enum DiscountTypeEnum {

    FULL_DISCOUNT_COUPON(1, "满减券"),
    DISCOUNT_COUPON(2, "折扣券"),
    VOUCHER_COUPON(3, "代金券"),;

    private final int type;
    private final String name;

    public static final Map<Integer, DiscountTypeEnum> DISCOUNT_TYPE_ENUM_MAP;

    static {
        DISCOUNT_TYPE_ENUM_MAP = new HashMap<>();
        Arrays.stream(DiscountTypeEnum.values()).forEach(discountTypeEnum -> DISCOUNT_TYPE_ENUM_MAP.put(discountTypeEnum.getType(), discountTypeEnum));
    }

    DiscountTypeEnum(int type, String name) {
        this.type = type;
        this.name = name;
    }

    public static String getNameByType(int type) {
        for (DiscountTypeEnum discountTypeEnum : values()) {
            if (discountTypeEnum.getType() == type) {
                return discountTypeEnum.getName();
            }
        }
        return StringUtils.EMPTY;
    }
}
