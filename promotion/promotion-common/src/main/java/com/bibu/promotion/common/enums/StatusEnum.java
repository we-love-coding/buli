package com.bibu.promotion.common.enums;

import com.bibu.promotion.common.exceptions.PromotionException;
import lombok.Getter;
import lombok.Setter;

/**
 * Author XY
 * Date 2024/5/10 下午3:00
 */
@Getter
public enum StatusEnum {

    UN_PUBLISHED(1, "未发布"),
    PUBLISHED(2, "已发布"),
    CANCELLED(3, "已取消"),;

    private final int status;
    private final String name;

    StatusEnum(int status, String name) {
        this.status = status;
        this.name = name;
    }

    public static StatusEnum getEnumByStatus(int status) {
        for (StatusEnum statusEnum : values()) {
            if (statusEnum.getStatus() == status) {
                return statusEnum;
            }
        }
        throw new PromotionException(PromotionExceptionEnum.ACTIVITY_IS_OVER);
    }
}
