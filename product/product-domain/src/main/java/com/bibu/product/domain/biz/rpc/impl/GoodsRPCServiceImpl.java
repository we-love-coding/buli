package com.bibu.product.domain.biz.rpc.impl;

import com.bibu.product.dal.entity.Goods;
import com.bibu.product.domain.biz.rpc.GoodsRPCService;
import com.bibu.product.domain.service.GoodsService;
import com.bibu.product.facade.request.GoodsQueryReq;
import com.bibu.product.facade.response.GoodsResp;
import com.x.common.utils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Author XY
 * Date 2024/5/3 下午2:21
 */
@Service
public class GoodsRPCServiceImpl implements GoodsRPCService {

    @Autowired
    private GoodsService goodsService;

    @Override
    public List<GoodsResp> getGoodsListByRPC(GoodsQueryReq req) {
        List<Long> goodsIdList = req.getGoodsIdList();
        List<Goods> goodsList = goodsService.getGoodsList(goodsIdList);
        if (CollectionUtils.isEmpty(goodsList)) {
            return new ArrayList<>();
        }
        return goodsList.stream().map(goods -> BeanUtils.convertBean(goods, GoodsResp.class)).collect(Collectors.toList());
    }
}
