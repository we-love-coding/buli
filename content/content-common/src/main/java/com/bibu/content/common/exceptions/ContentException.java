package com.bibu.content.common.exceptions;

import com.bibu.content.common.enums.ContentExceptionEnum;
import com.x.common.exceptions.ParentException;
import com.x.common.enums.ExceptionEnum;

/**
 * Author XY
 * Date 2024/5/20 上午11:19
 */
public class ContentException extends ParentException {

    public ContentException(ContentExceptionEnum exceptionEnum, String message) {
        super(exceptionEnum.getCode(), message);
    }

    public ContentException(ContentExceptionEnum exceptionEnum) {
        super(exceptionEnum.getCode(), exceptionEnum.getMessage());
    }

    public ContentException(Integer code, String message) {
        super(code, message);
    }

    public ContentException(ExceptionEnum exceptionEnum) {
        super(exceptionEnum);
    }
}
