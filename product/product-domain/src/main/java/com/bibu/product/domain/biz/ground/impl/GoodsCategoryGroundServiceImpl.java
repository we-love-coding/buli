package com.bibu.product.domain.biz.ground.impl;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.bibu.product.dal.entity.GoodsCategory;
import com.bibu.product.domain.biz.ground.GoodsCategoryGroundService;
import com.bibu.product.domain.service.GoodsCategoryService;
import com.bibu.product.facade.request.GoodsCategorySaveReq;
import com.bibu.product.facade.response.GoodsCategoryResp;
import com.x.common.utils.BeanUtils;
import com.x.common.enums.YesOrNoEnum;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Author XY
 * Date 2024/5/1 下午7:54
 */
@Service
public class GoodsCategoryGroundServiceImpl implements GoodsCategoryGroundService {

    @Autowired
    private GoodsCategoryService goodsCategoryService;

    @Override
    public GoodsCategoryResp saveGoodsCategory(GoodsCategorySaveReq req) {
        Date date = new Date();
        GoodsCategory goodsCategory = new GoodsCategory();
        goodsCategory.setCategoryName(req.getCategoryName());
        goodsCategory.setParentId(req.getParentId());
        goodsCategory.setStatus(req.getStatus());
        goodsCategory.setUpdateTime(date);
        goodsCategory.setCreateTime(date);
        goodsCategory.setUpdater("System");
        goodsCategory.setCreator("System");
        goodsCategory.setIsDelete(YesOrNoEnum.NO.getStatus());
        goodsCategoryService.save(goodsCategory);
        GoodsCategoryResp goodsCategoryResp = new GoodsCategoryResp();
        goodsCategoryResp.setId(goodsCategory.getId());
        goodsCategoryResp.setParentId(goodsCategory.getParentId());
        goodsCategoryResp.setCategoryName(goodsCategory.getCategoryName());
        goodsCategoryResp.setStatus(goodsCategory.getStatus());
        return goodsCategoryResp;
    }

    @Override
    public List<GoodsCategoryResp> findGoodsCategoryList() {
        List<GoodsCategory> goodsCategoryList = goodsCategoryService.getGoodsCategoryList(null);
        if (CollectionUtils.isEmpty(goodsCategoryList)) {
            return null;
        }

        Map<Long, List<GoodsCategory>> map = new HashMap<>();
        goodsCategoryList.forEach(goodsCategory ->  map.computeIfAbsent(goodsCategory.getParentId(), key -> new ArrayList<>()).add(goodsCategory));
        List<GoodsCategoryResp> list = new ArrayList<>();
        this.processGoodCategory(null, map.get(0L), list, map);
        return list;
    }

    private void processGoodCategory(GoodsCategoryResp goodsCategoryResp, List<GoodsCategory> goodsCategoryList, List<GoodsCategoryResp> items, Map<Long, List<GoodsCategory>> map) {
        for (GoodsCategory goodsCategory : goodsCategoryList) {
            Long id = goodsCategory.getId();
            Integer status = goodsCategory.getStatus();
            if (!NumberUtils.INTEGER_ONE.equals(status)) {
                continue;
            }
            // 根分类
            GoodsCategoryResp root = BeanUtils.convertBean(goodsCategory, GoodsCategoryResp.class);
            items.add(root);
            List<GoodsCategory> categoriesList = map.get(id);
            if (categoriesList == null) {
                continue;
            }
            processGoodCategory(root, categoriesList, new ArrayList<>(), map);
        }
        if (goodsCategoryResp != null) {
            goodsCategoryResp.setItems(items);
        }
    }
}
