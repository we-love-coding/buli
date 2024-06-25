package com.bibu.product.common.exceptions;

import com.bibu.product.common.enums.ProductExceptionEnum;
import com.x.common.exceptions.ParentException;
import com.x.common.enums.ExceptionEnum;

/**
 * Author XY
 * Date 2024/5/6 下午6:28
 */
public class ProductException extends ParentException {

    public ProductException(ProductExceptionEnum exceptionEnum) {
        super(exceptionEnum.getCode(), exceptionEnum.getMessage());
    }

    public ProductException(ProductExceptionEnum exceptionEnum, String message) {
        super(exceptionEnum.getCode(), message);
    }

    public ProductException(ExceptionEnum exceptionEnum) {
        super(exceptionEnum.getCode(), exceptionEnum.getMessage());
    }

    public ProductException(Integer code, String message) {
        super(code, message);
    }
}
