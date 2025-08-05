package com.bibu.promotion.common.enums;

import lombok.Getter;

/**
 * Author XY
 * Date 2024/5/9 下午4:37
 */
@Getter
public enum ControlTypeEnum {

    USER_CONTROL(1, "用户控制"),
    GLOBAL_CONTROL(2, "全局控制"),
    ACCURATE_CONTROL(3, "精准控制"),;

    private final int type;

    private final String name;

    ControlTypeEnum(int type, String name) {
        this.type = type;
        this.name = name;
    }


}
