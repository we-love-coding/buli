package com.bibu.user.common.enums;

import lombok.Getter;

/**
 * Author XY
 * Date 2024/5/2 下午9:23
 */
@Getter
public enum UserExceptionEnum {

    SIGNUP_FAIL_EXCEPTION(10010, "注册失败"),
    PHONE_MISS_EXCEPTION(10020, "手机号缺失"),
    PHONE_EXCEPTION(10021, "手机号码错误"),
    EMAIL_MISS_EXCEPTION(10030, "邮箱缺失"),
    EMAIL_EXCEPTION(10031, "邮箱错误"),

    LOGIN_FAIL_EXCEPTION(10100, "登陆失败"),
    LOGIN_PASSWORD_EXCEPTION(10101, "账号密码错误"),

    USER_NOT_EXIST(10200, "用户不存在"),


    ;

    private final Integer code;

    private final String message;

    UserExceptionEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
