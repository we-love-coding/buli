package com.bibu.order.domain.biz.app;

import com.bibu.order.facade.request.GoodsShoppingCartReq;

/**
 * Author XY
 * Date 2024/5/1 下午10:14
 */
public interface GoodsShoppingCartBizService {
    Boolean joinGoodsShoppingCart(GoodsShoppingCartReq req);

}
