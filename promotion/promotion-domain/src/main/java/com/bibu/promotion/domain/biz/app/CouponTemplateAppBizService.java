package com.bibu.promotion.domain.biz.app;

import com.bibu.promotion.facade.request.CouponTemplateObtainReq;

import java.util.Map;

/**
 * Author XY
 * Date 2024/5/8 下午1:45
 */
public interface CouponTemplateAppBizService {
    boolean obtainCoupon(CouponTemplateObtainReq req);

    void setCouponObtainNum(Map<String, String> map);
}
