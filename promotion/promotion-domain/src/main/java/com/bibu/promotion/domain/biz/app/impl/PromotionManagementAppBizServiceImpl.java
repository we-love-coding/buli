package com.bibu.promotion.domain.biz.app.impl;

import com.bibu.product.feign.request.GoodsQueryRemoteReq;
import com.bibu.product.feign.response.GoodsRemoteResp;
import com.bibu.promotion.common.enums.ConfigTypeEnum;
import com.bibu.promotion.common.enums.PromotionExceptionEnum;
import com.bibu.promotion.common.enums.StatusEnum;
import com.bibu.promotion.common.exceptions.PromotionException;
import com.bibu.promotion.dal.entity.CouponTemplate;
import com.bibu.promotion.dal.entity.PromotionConfig;
import com.bibu.promotion.dal.entity.PromotionManagement;
import com.bibu.promotion.domain.biz.app.PromotionManagementAppBizService;
import com.bibu.promotion.domain.manager.ProductManager;
import com.bibu.promotion.domain.service.CouponTemplateService;
import com.bibu.promotion.domain.service.PromotionConfigService;
import com.bibu.promotion.domain.service.PromotionManagementService;
import com.bibu.promotion.facade.request.PromotionReq;
import com.bibu.promotion.facade.response.CouponTemplateResp;
import com.bibu.promotion.facade.response.PromotionGoodsResp;
import com.bibu.promotion.facade.response.PromotionResp;
import com.google.common.collect.Lists;
import com.x.common.utils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Author XY
 * Date 2024/5/10 下午7:23
 */
@Service
public class PromotionManagementAppBizServiceImpl implements PromotionManagementAppBizService {

    @Autowired
    private PromotionManagementService promotionManagementService;
    @Autowired
    private PromotionConfigService promotionConfigService;

    @Autowired
    private ProductManager productManager;

    @Autowired
    private CouponTemplateService couponTemplateService;

    @Override
    public PromotionResp findPromotion(PromotionReq req) {
        Date date = new Date();
        String pCode = req.getPCode();
        PromotionManagement promotionManagement = promotionManagementService.getPromotionManagement(pCode);
        if (promotionManagement == null) {
            throw new PromotionException(PromotionExceptionEnum.ACTIVITY_IS_OVER);
        } else if (promotionManagement.getStatus() != StatusEnum.PUBLISHED.getStatus()) {
            throw new PromotionException(PromotionExceptionEnum.ACTIVITY_IS_OVER);
        } else if (promotionManagement.getStartTime().compareTo(date) > 0) {
            throw new PromotionException(PromotionExceptionEnum.ACTIVITY_IS_OVER, "活动还未开始");
        } else if (promotionManagement.getEndTime().compareTo(date) < 0) {
            throw new PromotionException(PromotionExceptionEnum.ACTIVITY_IS_OVER);
        }

        Long pid = promotionManagement.getId();
        List<PromotionConfig> promotionConfigList = promotionConfigService.getPromotionConfigList(pid);
        if (CollectionUtils.isEmpty(promotionConfigList)) {
            throw new PromotionException(PromotionExceptionEnum.ACTIVITY_IS_OVER);
        }

        List<Long> goodsIdList = Lists.newArrayList();
        List<Long> couponTemplateIdList = Lists.newArrayList();
        for (PromotionConfig promotionConfig : promotionConfigList) {
            Integer configType = promotionConfig.getConfigType();
            String configValue = promotionConfig.getConfigValue();
            if (configType == ConfigTypeEnum.GOODS_TYPE.getType()) {
                goodsIdList.add(Long.parseLong(configValue));
            } else if (configType == ConfigTypeEnum.COUPON_TYPE.getType()) {
                couponTemplateIdList.add(Long.parseLong(configValue));
            }
        }

        List<PromotionGoodsResp> pGoodsList = null;
        if (CollectionUtils.isNotEmpty(goodsIdList)) {
            GoodsQueryRemoteReq remoteReq = new GoodsQueryRemoteReq();
            remoteReq.setGoodsIdList(goodsIdList);
            List<GoodsRemoteResp> goodsList = productManager.findGoodsListByRPC(remoteReq);
            if (CollectionUtils.isNotEmpty(goodsList)) {
                pGoodsList = goodsList.stream().map(g -> BeanUtils.convertBean(g, PromotionGoodsResp.class)).collect(Collectors.toList());
            }
        }
        List<CouponTemplateResp> pCouponList = null;
        if (CollectionUtils.isNotEmpty(couponTemplateIdList)) {
            List<CouponTemplate> couponList = couponTemplateService.getCouponTemplateList(couponTemplateIdList);
            if (CollectionUtils.isNotEmpty(couponList)) {
                pCouponList = couponList.stream().map(c -> BeanUtils.convertBean(c, CouponTemplateResp.class)).collect(Collectors.toList());
            }
        }

        if (pGoodsList == null && pCouponList == null) {
            return null;
        }

        PromotionResp promotionResp = BeanUtils.convertBean(promotionManagement, PromotionResp.class);
        promotionResp.setCouponList(pCouponList);
        promotionResp.setGoodsList(pGoodsList);
        return promotionResp;
    }


}
