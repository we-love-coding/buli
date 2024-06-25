package com.bibu.promotion.domain.service;

import com.bibu.promotion.dal.dto.CouponTemplateSearchDTO;
import com.bibu.promotion.dal.entity.CouponTemplate;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author YiXie
 * @since 2024-05-08
 */
public interface CouponTemplateService extends IService<CouponTemplate> {

    CouponTemplate getCouponTemplate(String templateCode);

    List<CouponTemplate> getCouponTemplateList(List<Long> couponTemplateIdList);

    List<CouponTemplate> getCouponTemplateListBySearch(CouponTemplateSearchDTO couponTemplateSearchDTO);
}
