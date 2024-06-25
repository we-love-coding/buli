package com.bibu.promotion.api.controller.ground;

import com.bibu.promotion.domain.biz.ground.CouponTemplateGroundBizService;
import com.bibu.promotion.facade.request.CouponTemplateSaveReq;
import com.bibu.promotion.facade.request.CouponTemplateSearchReq;
import com.bibu.promotion.facade.response.CouponTemplateListResp;
import com.github.pagehelper.PageInfo;
import com.x.common.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author XY
 * Date 2024/5/8 下午1:59
 */
@RestController
@RequestMapping("/ground/admin")
public class CouponTemplateGroundController {

    @Autowired
    private CouponTemplateGroundBizService couponTemplateGroundBizService;

    @PostMapping("/saveCouponTemplate")
    public ResponseResult<Void> saveCouponTemplate(@RequestBody CouponTemplateSaveReq req) {
        couponTemplateGroundBizService.saveCouponTemplate(req);
        return ResponseResult.success();
    }

    @PostMapping("/findCouponTemplateListByPage")
    public ResponseResult<PageInfo<CouponTemplateListResp>> findCouponTemplateListByPage(@RequestBody CouponTemplateSearchReq req) {
        return ResponseResult.success(couponTemplateGroundBizService.findCouponTemplateListByPage(req));
    }
}
