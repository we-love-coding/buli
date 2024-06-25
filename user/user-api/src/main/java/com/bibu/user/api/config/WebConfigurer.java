package com.bibu.user.api.config;


import com.bibu.user.api.interceptor.UserInfoInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Author XY
 * Date 2024/4/1 下午11:22
 */
@Configuration
public class WebConfigurer implements WebMvcConfigurer {

    @Autowired
    private UserInfoInterceptor userInfoInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(userInfoInterceptor)
                .addPathPatterns("/user")
                .excludePathPatterns("/user/signup", "/user/login");
    }
}
