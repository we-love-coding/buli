package com.bibu.promotion.feign;

import org.springframework.cloud.openfeign.FeignClient;

/**
 * Author XY
 * Date 2024/5/6 下午8:44
 */
@FeignClient(name = "promotion", fallbackFactory = PromotionFallbackFactory.class)
public interface PromotionClient {
}
