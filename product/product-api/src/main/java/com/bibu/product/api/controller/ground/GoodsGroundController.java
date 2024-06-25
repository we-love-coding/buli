package com.bibu.product.api.controller.ground;

import com.bibu.product.domain.biz.ground.GoodsGroundBizService;
import com.bibu.product.facade.request.GoodsSaveReq;
import com.x.common.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author XY
 * Date 2024/5/1 下午7:48
 */
@RestController
@RequestMapping("/ground/admin")
public class GoodsGroundController {

    @Autowired
    private GoodsGroundBizService goodsGroundBizService;

    @PostMapping("/saveGoods")
    public ResponseResult<Void> saveSku(@RequestBody GoodsSaveReq req) {
        goodsGroundBizService.saveGoods(req);
        return ResponseResult.success();
    }
}
