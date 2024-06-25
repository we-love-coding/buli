package com.bibu.promotion.domain.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bibu.promotion.dal.dto.CouponAccountDTO;
import com.bibu.promotion.dal.entity.CouponAccount;
import com.bibu.promotion.dal.mapper.CouponAccountMapper;
import com.bibu.promotion.domain.service.CouponAccountService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.x.common.enums.YesOrNoEnum;
import org.springframework.stereotype.Service;

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
public class CouponAccountServiceImpl extends ServiceImpl<CouponAccountMapper, CouponAccount> implements CouponAccountService {

    @Override
    public long countCoupon(Long couponTemplateId) {
        QueryWrapper<CouponAccount> wrapper = new QueryWrapper<>();
        wrapper.eq("coupon_template_id", couponTemplateId);
        wrapper.eq("is_delete", YesOrNoEnum.NO.getStatus());
        return count(wrapper);
    }

    @Override
    public List<CouponAccount> getCouponAccountList(CouponAccountDTO couponAccountDTO) {
        QueryWrapper<CouponAccount> wrapper = new QueryWrapper<>();
        wrapper.eq("is_delete", YesOrNoEnum.NO.getStatus());
        wrapper.eq("user_id", couponAccountDTO.getUserId());
        if (couponAccountDTO.getEffectiveStartTime() != null) {
            wrapper.le("effective_start_time", couponAccountDTO.getEffectiveStartTime());
        }
        if (couponAccountDTO.getEffectiveEndTime() != null) {
            wrapper.ge("effective_end_time", couponAccountDTO.getEffectiveEndTime());
        }
        if (couponAccountDTO.getStatus() != null) {
            wrapper.eq("status", couponAccountDTO.getStatus());
        }
        return list(wrapper);
    }

    @Override
    public List<Long> getCouponTemplateIdList(CouponAccountDTO couponAccountDTO) {
        return null;
    }
}
