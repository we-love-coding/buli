package com.bibu.promotion.domain.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bibu.promotion.common.enums.RStatusConvertEnum;
import com.bibu.promotion.dal.dto.CouponTemplateSearchDTO;
import com.bibu.promotion.dal.entity.CouponTemplate;
import com.bibu.promotion.dal.mapper.CouponTemplateMapper;
import com.bibu.promotion.domain.service.CouponTemplateService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.x.common.enums.YesOrNoEnum;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author YiXie
 * @since 2024-05-08
 */
@Service
public class CouponTemplateServiceImpl extends ServiceImpl<CouponTemplateMapper, CouponTemplate> implements CouponTemplateService {

    @Override
    public CouponTemplate getCouponTemplate(String templateCode) {
        Date date = new Date();
        QueryWrapper<CouponTemplate> wrapper = new QueryWrapper<>();
        wrapper.eq("template_code", templateCode);
        wrapper.eq("status", 1);
        wrapper.eq("is_delete", YesOrNoEnum.NO.getStatus());
        wrapper.le("start_time", date);
        wrapper.ge("end_time", date);
        wrapper.last("limit 1");
        return getOne(wrapper);
    }

    @Override
    public List<CouponTemplate> getCouponTemplateList(List<Long> couponTemplateIdList) {
        QueryWrapper<CouponTemplate> wrapper = new QueryWrapper<>();
        wrapper.in("id", couponTemplateIdList);
        wrapper.eq("is_delete", YesOrNoEnum.NO.getStatus());
        return list(wrapper);
    }

    @Override
    public List<CouponTemplate> getCouponTemplateListBySearch(CouponTemplateSearchDTO couponTemplateSearchDTO) {
        QueryWrapper<CouponTemplate> wrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(couponTemplateSearchDTO.getTemplateName())) {
            wrapper.like("template_name", couponTemplateSearchDTO.getTemplateName());
        }
        if (StringUtils.isNotBlank(couponTemplateSearchDTO.getTemplateCode())) {
            wrapper.eq("template_code", couponTemplateSearchDTO.getTemplateCode());
        }
        if (couponTemplateSearchDTO.getDiscountType() != null) {
            wrapper.eq("discount_type", couponTemplateSearchDTO.getDiscountType());
        }
        Integer rStatus = couponTemplateSearchDTO.getRStatus();
        if (rStatus != null) {
            Date date = new Date();
            RStatusConvertEnum convertEnum = RStatusConvertEnum.getEnumByRStatus(rStatus);
            wrapper.eq("status", convertEnum.getStatusEnum().getStatus());
            switch (convertEnum) {
                case UN_PUBLISHED:
                case CANCELLED:
                    break;
                case UN_STARTED:
                    wrapper.gt("start_time", date);
                    break;
                case IN_PROGRESS:
                    wrapper.le("start_time", date);
                    wrapper.ge("end_time", date);
                    break;
                case COMPLETED:
                    wrapper.lt("end_time", date);
                    break;
            }
        }
        if (couponTemplateSearchDTO.getStartTime() != null) {
            wrapper.ge("start_time", couponTemplateSearchDTO.getStartTime());
        }
        if (couponTemplateSearchDTO.getEndTime() != null) {
            wrapper.le("end_time", couponTemplateSearchDTO.getEndTime());
        }
        wrapper.eq("is_delete", YesOrNoEnum.NO.getStatus());
        wrapper.orderByDesc("id");
        return list(wrapper);
    }
}
