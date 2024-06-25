package com.bibu.product.common.enums;

import lombok.Getter;

/**
 * Author XY
 * Date 2024/5/3 下午1:47
 */
@Getter
public enum ProductExceptionEnum {

    GOODS_NOT_EXIST_EXCEPTION(30010, "该商品不存在"),
    ;


    private final int code;
    private final String message;

    ProductExceptionEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
