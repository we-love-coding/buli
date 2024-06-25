package com.bibu.product.feign;

import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * Author XY
 * Date 2024/5/3 下午2:37
 */
@Component
public class ProductFallbackFactory implements FallbackFactory<ProductClientFallback> {
    @Override
    public ProductClientFallback create(Throwable cause) {
        return new ProductClientFallback();
    }
}
