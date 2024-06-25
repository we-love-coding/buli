package com.bibu.user.common.exceptions;

import com.bibu.user.common.enums.UserExceptionEnum;
import com.x.common.exceptions.ParentException;
import com.x.common.enums.ExceptionEnum;

/**
 * Author XY
 * Date 2024/5/2 下午9:10
 */
public class UserException extends ParentException {

    public UserException(UserExceptionEnum exceptionEnum) {
        super(exceptionEnum.getCode(), exceptionEnum.getMessage());
    }

    public UserException(UserExceptionEnum exceptionEnum, String message) {
        super(exceptionEnum.getCode(), message);
    }

    public UserException(ExceptionEnum exceptionEnum) {
        super(exceptionEnum.getCode(), exceptionEnum.getMessage());
    }

    public UserException(Integer code, String message) {
        super(code, message);
    }
}
