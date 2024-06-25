package com.bibu.product.domain.biz.app.impl;

import com.bibu.product.dal.entity.Goods;
import com.bibu.product.domain.biz.app.GoodsAppService;
import com.bibu.product.domain.service.GoodsService;
import com.bibu.product.facade.request.GoodsSearchReq;
import com.bibu.product.facade.response.GoodsResp;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.x.common.utils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Author XY
 * Date 2024/5/6 下午2:05
 */
@Service
public class GoodsAppServiceImpl implements GoodsAppService {

    @Autowired
    private GoodsService goodsService;

    @Override
    public PageInfo<GoodsResp> findGoodsListByPage(GoodsSearchReq req) {
        Page<GoodsResp> page = PageHelper.startPage(req.getPageNum(), req.getPageSize());

        List<Goods> list = goodsService.list();
        if (CollectionUtils.isEmpty(list)) {
            return new PageInfo<>();
        }
        List<GoodsResp> goodsList = list.stream().map(g -> BeanUtils.convertBean(g, GoodsResp.class)).collect(Collectors.toList());

        PageInfo<GoodsResp> pageInfo = new PageInfo<>(page);
        pageInfo.setList(goodsList);
        PageHelper.clearPage();
        return pageInfo;
    }
}
