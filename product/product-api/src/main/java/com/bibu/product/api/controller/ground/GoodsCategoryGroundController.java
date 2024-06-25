package com.bibu.product.api.controller.ground;

import com.bibu.product.domain.biz.ground.GoodsCategoryGroundService;
import com.bibu.product.facade.request.GoodsCategorySaveReq;
import com.bibu.product.facade.response.GoodsCategoryResp;
import com.x.common.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Author XY
 * Date 2024/5/1 下午7:52
 */
@RestController
@RequestMapping("/ground/admin")
public class GoodsCategoryGroundController {

    @Autowired
    private GoodsCategoryGroundService goodsCategoryGroundService;

    @PostMapping("/saveGoodsCategory")
    public ResponseResult<GoodsCategoryResp> saveGoodsCategory(@RequestBody GoodsCategorySaveReq req) {
        GoodsCategoryResp goodsCategoryResp = goodsCategoryGroundService.saveGoodsCategory(req);
        return ResponseResult.success(goodsCategoryResp);
    }

    @PostMapping("/findGoodsCategoryList")
    public ResponseResult<List<GoodsCategoryResp>> findGoodsCategoryList() {
        List<GoodsCategoryResp> list = goodsCategoryGroundService.findGoodsCategoryList();
        return ResponseResult.success(list);
    }
}
