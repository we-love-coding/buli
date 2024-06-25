package com.bibu.order.domain.biz.app.impl;

import com.bibu.order.dal.entity.GoodsShoppingCart;
import com.bibu.order.domain.biz.app.GoodsShoppingCartBizService;
import com.bibu.order.domain.service.GoodsShoppingCartService;
import com.bibu.order.facade.request.GoodsShoppingCartReq;
import com.x.common.context.UserInfoContext;
import com.x.common.context.dto.UserInfoDTO;
import com.x.common.enums.YesOrNoEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Author XY
 * Date 2024/5/1 下午10:14
 */
@Service
public class GoodsShoppingCartBizServiceImpl implements GoodsShoppingCartBizService {


    @Autowired
    private GoodsShoppingCartService goodsShoppingCartService;

    @Override
    public Boolean joinGoodsShoppingCart(GoodsShoppingCartReq req) {
        Date date = new Date();
        Long goodsId = req.getGoodsId();
        Integer goodsNum = req.getGoodsNum();

        // 查商品信息
        UserInfoDTO user = UserInfoContext.getUser();
        Long userId = user.getUserId();
        String nickName = user.getNickName();

        GoodsShoppingCart goodsShoppingCart = goodsShoppingCartService.getGoodsShoppingCart(goodsId, userId);
        if (goodsShoppingCart != null) {
            int sum = Integer.sum(goodsShoppingCart.getGoodsNum(), goodsNum);
            goodsShoppingCart.setGoodsNum(sum);
            goodsShoppingCart.setStatus(1);
            goodsShoppingCart.setUpdateTime(date);
            goodsShoppingCart.setIsDelete(YesOrNoEnum.NO.getStatus());
            goodsShoppingCart.setUpdater(nickName);
        } else {
            goodsShoppingCart = new GoodsShoppingCart();
            goodsShoppingCart.setUserId(userId);
            goodsShoppingCart.setGoodsId(goodsId);
            goodsShoppingCart.setGoodsNum(goodsNum);
            goodsShoppingCart.setStatus(1);
            goodsShoppingCart.setUpdater(nickName);
            goodsShoppingCart.setCreator(nickName);
            goodsShoppingCart.setCreateTime(date);
            goodsShoppingCart.setUpdateTime(date);
            goodsShoppingCart.setIsDelete(YesOrNoEnum.NO.getStatus());
        }

        return goodsShoppingCartService.saveOrUpdate(goodsShoppingCart);
    }
}
