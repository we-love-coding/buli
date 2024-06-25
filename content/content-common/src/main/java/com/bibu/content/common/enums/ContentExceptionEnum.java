package com.bibu.content.common.enums;

import lombok.Getter;

/**
 * Author XY
 * Date 2024/5/3 下午1:47
 */
@Getter
public enum ContentExceptionEnum {

    CONTENT_FIND_EXCEPTION(20010, "内容查询异常"),
    ;


    private final int code;
    private final String message;

    ContentExceptionEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
