package com.bibu.promotion.common.exceptions;

import com.bibu.promotion.common.enums.PromotionExceptionEnum;
import com.x.common.enums.ExceptionEnum;
import com.x.common.exceptions.ParentException;
import com.x.common.enums.ExceptionEnum;

/**
 * Author XY
 * Date 2024/5/6 下午8:31
 */
public class PromotionException extends ParentException {

    public PromotionException(PromotionExceptionEnum exceptionEnum) {
        super(exceptionEnum.getCode(), exceptionEnum.getMessage());
    }

    public PromotionException(PromotionExceptionEnum exceptionEnum, String message) {
        super(exceptionEnum.getCode(), message);
    }

    public PromotionException(Integer code, String message) {
        super(code, message);
    }

    public PromotionException(ExceptionEnum exceptionEnum) {
        super(exceptionEnum);
    }
}
