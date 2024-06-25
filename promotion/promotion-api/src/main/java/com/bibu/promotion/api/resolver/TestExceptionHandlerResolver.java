package com.bibu.promotion.api.resolver;

import com.alibaba.fastjson2.JSON;
import com.x.common.ResponseResult;
import com.x.common.exceptions.ParentException;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.core.MethodParameter;
import org.springframework.core.Ordered;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Author XY
 * Date 2024/5/12 下午2:32
 */
//@Component
public class TestExceptionHandlerResolver implements HandlerExceptionResolver, BeanFactoryAware,ResponseBodyAdvice<Object>, Ordered {

    private BeanFactory beanFactory;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        ParentException parentException = (ParentException)ex;
        Integer errorCode = parentException.getErrorCode();
        String errorMessage = parentException.getErrorMessage();

        ResponseResult responseResult = new ResponseResult<>();
        responseResult.setCode(errorCode);
        responseResult.setSuccess(Boolean.FALSE);
        responseResult.setMessage(errorMessage);

        return new ModelAndView();
    }

    @Override
    public int getOrder() {
        return 0;
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
