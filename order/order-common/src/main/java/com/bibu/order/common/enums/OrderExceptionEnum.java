package com.bibu.order.common.enums;

import lombok.Getter;

/**
 * Author XY
 * Date 2024/5/3 下午1:47
 */
@Getter
public enum OrderExceptionEnum {

    ORDER_SUBMIT_EXCEPTION(20010, "下单异常"),
    ;


    private final int code;
    private final String message;

    OrderExceptionEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
