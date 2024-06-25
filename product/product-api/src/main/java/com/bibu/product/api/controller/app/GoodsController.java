package com.bibu.product.api.controller.app;


import com.bibu.product.domain.biz.app.GoodsAppService;
import com.bibu.product.facade.request.GoodsSearchReq;
import com.bibu.product.facade.response.GoodsResp;
import com.github.pagehelper.PageInfo;
import com.x.common.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 商品表 前端控制器
 * </p>
 *
 * @author YiXie
 * @since 2024-05-01
 */
@RestController
@RequestMapping("/app/goods")
public class GoodsController {

    @Autowired
    private GoodsAppService goodsAppService;

    @PostMapping("/findGoodsListByPage")
    public ResponseResult<PageInfo<GoodsResp>> findGoodsListByPage(@RequestBody GoodsSearchReq req) {
        return ResponseResult.success(goodsAppService.findGoodsListByPage(req));
    }

}

