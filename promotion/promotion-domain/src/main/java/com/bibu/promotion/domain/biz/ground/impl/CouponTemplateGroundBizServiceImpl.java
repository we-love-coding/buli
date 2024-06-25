package com.bibu.promotion.domain.biz.ground.impl;

import com.bibu.promotion.common.constants.PromotionConstant;
import com.bibu.promotion.common.enums.DiscountTypeEnum;
import com.bibu.promotion.common.enums.RStatusConvertEnum;
import com.bibu.promotion.common.enums.StatusEnum;
import com.bibu.promotion.dal.dto.CouponTemplateSearchDTO;
import com.bibu.promotion.dal.entity.CouponTemplate;
import com.bibu.promotion.domain.biz.app.CouponTemplateAppBizService;
import com.bibu.promotion.domain.biz.ground.CouponTemplateGroundBizService;
import com.bibu.promotion.domain.service.CouponTemplateService;
import com.bibu.promotion.domain.tool.CouponCodeGenerator;
import com.bibu.promotion.facade.request.CouponTemplateSaveReq;
import com.bibu.promotion.facade.request.CouponTemplateSearchReq;
import com.bibu.promotion.facade.response.CouponTemplateListResp;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.x.common.utils.BeanUtils;
import com.x.common.enums.YesOrNoEnum;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Author XY
 * Date 2024/5/8 下午1:47
 */
@Service
public class CouponTemplateGroundBizServiceImpl implements CouponTemplateGroundBizService {

    @Autowired
    private CouponTemplateService couponTemplateService;

    @Override
    public void saveCouponTemplate(CouponTemplateSaveReq req) {
        Date date = new Date();
        CouponTemplate couponTemplate = BeanUtils.convertBean(req, CouponTemplate.class);
        couponTemplate.setUpdateTime(date);
        couponTemplate.setUpdater("System");
        couponTemplate.setIsDelete(YesOrNoEnum.NO.getStatus());
        if (req.getId() != null) {
            // 更新
            couponTemplate.setTemplateCode(CouponCodeGenerator.generateCouponCode(PromotionConstant.COUPON_PREFIX, PromotionConstant.INT_SIX));
        } else {
            // 保存
            couponTemplate.setCreateTime(date);
            couponTemplate.setCreator("System");
        }
        couponTemplateService.save(couponTemplate);
    }

    @Override
    public PageInfo<CouponTemplateListResp> findCouponTemplateListByPage(CouponTemplateSearchReq req) {
        Date date = new Date();
        CouponTemplateSearchDTO couponTemplateSearchDTO = BeanUtils.convertBean(req, CouponTemplateSearchDTO.class);
        Page<CouponTemplateListResp> page = PageHelper.startPage(req.getPageNum(), req.getPageSize());
        List<CouponTemplate> couponTemplateList = couponTemplateService.getCouponTemplateListBySearch(couponTemplateSearchDTO);
        if (CollectionUtils.isEmpty(couponTemplateList)) {
            return new PageInfo<>();
        }

        List<CouponTemplateListResp> list = Lists.newArrayListWithCapacity(couponTemplateList.size());
        for (CouponTemplate couponTemplate : couponTemplateList) {
            CouponTemplateListResp couponTemplateListResp = BeanUtils.convertBean(couponTemplate, CouponTemplateListResp.class);
            Integer status = couponTemplate.getStatus();
            Date startTime = couponTemplate.getStartTime();
            Date endTime = couponTemplate.getEndTime();
            Integer discountType = couponTemplate.getDiscountType();
            RStatusConvertEnum rStatusConvertEnum = RStatusConvertEnum.convertRStatusEnum(status, startTime, endTime, date);
            couponTemplateListResp.setRStatus(rStatusConvertEnum.getRStatus());
            couponTemplateListResp.setRStatusStr(rStatusConvertEnum.getRName());
            couponTemplateListResp.setDiscountTypeStr(DiscountTypeEnum.DISCOUNT_TYPE_ENUM_MAP.get(discountType).getName());
            list.add(couponTemplateListResp);
        }

        PageInfo<CouponTemplateListResp> pageInfo = new PageInfo<>(page);
        pageInfo.setList(list);
        PageHelper.clearPage();
        return pageInfo;
    }

}
