package com.bibu.order.api.controller.app;


import com.bibu.order.domain.biz.app.GoodsShoppingCartBizService;
import com.bibu.order.facade.request.GoodsShoppingCartReq;
import com.x.common.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 购物车 前端控制器
 * </p>
 *
 * @author YiXie
 * @since 2024-05-01
 */
@RestController
@RequestMapping("/app/goodsShoppingCart")
public class GoodsShoppingCartController {


    @Autowired
    private GoodsShoppingCartBizService goodsShoppingCartBizService;

    @PostMapping("/joinGoodsShoppingCart")
    public ResponseResult<Boolean> joinGoodsShoppingCart(@RequestBody GoodsShoppingCartReq req) {
        return ResponseResult.success(goodsShoppingCartBizService.joinGoodsShoppingCart(req));
    }
}

