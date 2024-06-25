package com.bibu.order.domain.service;

import com.bibu.order.dal.entity.GoodsShoppingCart;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 购物车 服务类
 * </p>
 *
 * @author YiXie
 * @since 2024-05-01
 */
public interface GoodsShoppingCartService extends IService<GoodsShoppingCart> {

    GoodsShoppingCart getGoodsShoppingCart(Long goodsId, Long userId);
}
