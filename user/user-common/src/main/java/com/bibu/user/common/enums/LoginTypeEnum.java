package com.bibu.user.common.enums;

import lombok.Getter;

import java.util.Objects;

/**
 * Author XY
 * Date 2024/4/1 下午3:19
 */
@Getter
public enum LoginTypeEnum {

    NICKNAME(0, "用户名"),
    EMAIL(1, "邮箱"),
    PHONE(2, "手机"),
    ;

    private final int type;

    private final String name;

    LoginTypeEnum(int type, String name) {
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
