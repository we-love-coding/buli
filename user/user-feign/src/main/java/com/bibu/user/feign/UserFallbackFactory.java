package com.bibu.user.feign;

import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * Author XY
 * Date 2024/4/27 下午1:25
 */
@Component
public class UserFallbackFactory implements FallbackFactory<UserFallbackFactory> {
    @Override
    public UserFallbackFactory create(Throwable cause) {
        return new UserFallbackFactory();
    }
}
