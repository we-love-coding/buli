package com.bibu.promotion.domain.biz.ground;

import com.bibu.promotion.facade.request.CouponTemplateSaveReq;
import com.bibu.promotion.facade.request.CouponTemplateSearchReq;
import com.bibu.promotion.facade.response.CouponTemplateListResp;
import com.github.pagehelper.PageInfo;

/**
 * Author XY
 * Date 2024/5/8 下午1:47
 */
public interface CouponTemplateGroundBizService {
    void saveCouponTemplate(CouponTemplateSaveReq req);

    PageInfo<CouponTemplateListResp> findCouponTemplateListByPage(CouponTemplateSearchReq req);
}
