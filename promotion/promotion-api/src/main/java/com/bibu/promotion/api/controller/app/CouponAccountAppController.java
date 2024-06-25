package com.bibu.promotion.api.controller.app;


import com.bibu.promotion.domain.biz.app.CouponAccountAppBizService;
import com.bibu.promotion.facade.request.UserCouponSearchReq;
import com.bibu.promotion.facade.response.UserCouponResp;
import com.github.pagehelper.PageInfo;
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
@RequestMapping("/app/couponAccount")
public class CouponAccountAppController {

    @Autowired
    private CouponAccountAppBizService couponAccountAppBizService;

    @PostMapping("/findUserCouponListByPage")
    public ResponseResult<PageInfo<UserCouponResp>> findUserCouponListByPage(@RequestBody UserCouponSearchReq req) {
        return ResponseResult.success(couponAccountAppBizService.findUserCouponListByPage(req));
    }
}

