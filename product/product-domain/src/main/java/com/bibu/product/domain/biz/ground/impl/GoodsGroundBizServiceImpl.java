package com.bibu.product.domain.biz.ground.impl;

import com.bibu.product.dal.entity.Goods;
import com.bibu.product.dal.entity.GoodsImage;
import com.bibu.product.domain.biz.ground.GoodsGroundBizService;
import com.bibu.product.domain.service.GoodsImageService;
import com.bibu.product.domain.service.GoodsService;
import com.bibu.product.facade.request.GoodsImageReq;
import com.bibu.product.facade.request.GoodsSaveReq;
import com.x.common.utils.BeanUtils;
import com.x.common.enums.YesOrNoEnum;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Author XY
 * Date 2024/5/1 下午7:51
 */
@Service
public class GoodsGroundBizServiceImpl implements GoodsGroundBizService {

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private GoodsImageService goodsImageService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveGoods(GoodsSaveReq req) {
        Date date = new Date();
        Goods goods = BeanUtils.convertBean(req, Goods.class);
        goods.setUpdater("System");
        goods.setCreator("System");
        goods.setUpdateTime(date);
        goods.setCreateTime(date);
        goods.setIsDelete(YesOrNoEnum.NO.getStatus());
        goodsService.save(goods);

        List<GoodsImageReq> imageList = req.getImageList();
        if (CollectionUtils.isEmpty(imageList)) {
            return;
        }
        Long goodsId = goods.getId();
        List<GoodsImage> goodsImageList = new ArrayList<>();
        for (GoodsImageReq skuImagesReq : imageList) {
            GoodsImage goodsImage = BeanUtils.convertBean(skuImagesReq, GoodsImage.class);
            goodsImage.setGoodsId(goodsId);
            goodsImageList.add(goodsImage);
        }
        goodsImageService.saveBatch(goodsImageList);
    }
}
