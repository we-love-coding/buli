package com.bibu.order.domain.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bibu.order.dal.entity.GoodsShoppingCart;
import com.bibu.order.dal.mapper.GoodsShoppingCartMapper;
import com.bibu.order.domain.service.GoodsShoppingCartService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.x.common.enums.YesOrNoEnum;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 购物车 服务实现类
 * </p>
 *
 * @author YiXie
 * @since 2024-05-01
 */
@Service
public class GoodsShoppingCartServiceImpl extends ServiceImpl<GoodsShoppingCartMapper, GoodsShoppingCart> implements GoodsShoppingCartService {

    @Override
    public GoodsShoppingCart getGoodsShoppingCart(Long goodsId, Long userId) {
        QueryWrapper<GoodsShoppingCart> wrapper = new QueryWrapper<>();
        wrapper.eq("goods_id", goodsId);
        wrapper.eq("user_id", userId);
        wrapper.eq("is_delete", YesOrNoEnum.NO.getStatus());
        wrapper.last("limit 1");
        return getOne(wrapper);
    }
}
