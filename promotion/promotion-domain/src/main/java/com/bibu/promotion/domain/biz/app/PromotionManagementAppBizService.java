package com.bibu.promotion.domain.biz.app;

import com.bibu.promotion.facade.request.PromotionReq;
import com.bibu.promotion.facade.response.PromotionResp;

/**
 * Author XY
 * Date 2024/5/10 下午7:23
 */
public interface PromotionManagementAppBizService {
    PromotionResp findPromotion(PromotionReq req);
}
