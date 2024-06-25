package com.bibu.promotion.api.controller.app;


import com.bibu.promotion.domain.biz.app.PromotionManagementAppBizService;
import com.bibu.promotion.facade.request.PromotionReq;
import com.bibu.promotion.facade.response.PromotionResp;
import com.x.common.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 促销活动表 前端控制器
 * </p>
 *
 * @author YiXie
 * @since 2024-05-08
 */
@RestController
@RequestMapping("/app/promotionManagement")
public class PromotionManagementAppController {

    @Autowired
    private PromotionManagementAppBizService promotionManagementAppBizService;

    @RequestMapping("/findPromotion")
    public ResponseResult<PromotionResp> findPromotion(@RequestBody PromotionReq req) {
        return ResponseResult.success(promotionManagementAppBizService.findPromotion(req));
    }
}

