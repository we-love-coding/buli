package com.bibu.promotion.api.controller.app;


import com.bibu.promotion.domain.biz.app.CouponTemplateAppBizService;
import com.bibu.promotion.facade.request.CouponTemplateObtainReq;
import com.x.common.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author YiXie
 * @since 2024-05-08
 */
@RestController
@RequestMapping("/app/couponTemplate")
public class CouponTemplateAppController {


    @Autowired
    private CouponTemplateAppBizService couponTemplateAppBizService;


    @PostMapping("/obtainCoupon")
    public ResponseResult<Boolean> obtainCoupon(@RequestBody CouponTemplateObtainReq req) {
        return ResponseResult.success(couponTemplateAppBizService.obtainCoupon(req));
    }

}

