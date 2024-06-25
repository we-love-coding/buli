package com.bibu.content.api.config;


import com.bibu.content.api.intercepter.UserTokenCheckInterceptor;
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
    private UserTokenCheckInterceptor userTokenCheckInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(userTokenCheckInterceptor)
                .addPathPatterns("/**");
    }
}
