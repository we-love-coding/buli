package com.bibu.product.api.config;

import com.bibu.product.api.interceptor.UserTokenCheckInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Author XY
 * Date 2024/5/6 下午6:14
 */
@Configuration
public class WebConfigurer implements WebMvcConfigurer {

    @Autowired
    private UserTokenCheckInterceptor userTokenCheckInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(userTokenCheckInterceptor)
                .addPathPatterns("/app/**").excludePathPatterns("/rpc/**");
    }
}
