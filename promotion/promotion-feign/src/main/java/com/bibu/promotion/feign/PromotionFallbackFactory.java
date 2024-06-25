package com.bibu.promotion.feign;

import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * Author XY
 * Date 2024/5/6 下午8:45
 */
@Component
public class PromotionFallbackFactory implements FallbackFactory<PromotionClientFallback> {
    @Override
    public PromotionClientFallback create(Throwable cause) {
        return new PromotionClientFallback();
    }
}
