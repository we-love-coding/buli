package com.bibu.content.api.config;

import com.alibaba.fastjson2.JSON;
import com.x.common.ResponseResult;
import com.x.common.exceptions.ParentException;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * Author XY
 * Date 2024/5/11 下午2:01
 */
@ControllerAdvice
public class ExceptionAdvice implements ResponseBodyAdvice<Object> {

    @ExceptionHandler(ParentException.class)
    @ResponseBody
    public ResponseResult<Void> handleServiceException(ParentException parentException, HttpServletRequest request) {
        return ResponseResult.fail(parentException.getErrorCode(), parentException.getErrorMessage());
    }

    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        System.out.println("body:" + JSON.toJSONString(body));
        return body;
    }
}
