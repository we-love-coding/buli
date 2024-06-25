package com.bibu.user.common.enums;

import lombok.Getter;

import java.util.Objects;

/**
 * Author XY
 * Date 2024/3/30 下午5:32
 */
@Getter
public enum SignupTypeEnum {

    EMAIL(1, "邮箱"),
    PHONE(2, "手机"),;

    private final int type;

    private final String name;

    SignupTypeEnum(int type, String name) {
        this.type = type;
        this.name = name;
    }

    public static SignupTypeEnum getEnumByType(Integer type) {
        if (Objects.isNull(type)) {
            return null;
        }

        for (SignupTypeEnum typeEnum : SignupTypeEnum.values()) {
            if (type == typeEnum.getType()) {
                return typeEnum;
            }
        }

        return null;
    }
}
