package com.bibu.product.api.controller.rpc;

import com.bibu.product.domain.biz.rpc.GoodsRPCService;
import com.bibu.product.facade.request.GoodsQueryReq;
import com.bibu.product.facade.response.GoodsResp;
import com.x.common.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Author XY
 * Date 2024/5/3 下午2:20
 */
@RestController
@RequestMapping("/rpc/goods")
public class GoodsRPCController {

    @Autowired
    private GoodsRPCService goodsRPCService;

    @PostMapping("/findGoodsListByRPC")
    public ResponseResult<List<GoodsResp>> findGoodsListByRPC(@RequestBody GoodsQueryReq req) {
        List<GoodsResp> goodsList = goodsRPCService.getGoodsListByRPC(req);
        return ResponseResult.success(goodsList);
    }
}
