package com.bibu.order.common.exceptions;

import com.bibu.order.common.enums.OrderExceptionEnum;
import com.x.common.exceptions.ParentException;
import com.x.common.enums.ExceptionEnum;

/**
 * Author XY
 * Date 2024/5/3 下午1:45
 */
public class OrderException extends ParentException {

    public OrderException(OrderExceptionEnum exceptionEnum, String message) {
        super(exceptionEnum.getCode(), message);
    }

    public OrderException(OrderExceptionEnum exceptionEnum) {
        super(exceptionEnum.getCode(), exceptionEnum.getMessage());
    }

    public OrderException(Integer code, String message) {
        super(code, message);
    }

    public OrderException(ExceptionEnum exceptionEnum) {
        super(exceptionEnum);
    }
}
