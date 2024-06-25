package com.bibu.promotion.domain.service;

import com.bibu.promotion.dal.dto.CouponAccountDTO;
import com.bibu.promotion.dal.entity.CouponAccount;
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
public interface CouponAccountService extends IService<CouponAccount> {

    long countCoupon(Long couponTemplateId);

    List<CouponAccount> getCouponAccountList(CouponAccountDTO couponAccountDTO);

    List<Long> getCouponTemplateIdList(CouponAccountDTO couponAccountDTO);
}
