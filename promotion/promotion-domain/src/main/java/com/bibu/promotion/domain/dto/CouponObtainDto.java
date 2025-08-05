package com.bibu.promotion.domain.dto;

import com.bibu.promotion.dal.entity.CouponTemplate;
import com.x.common.context.dto.UserInfoDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Author XY
 * Date 2025/8/5 下午9:37
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CouponObtainDto {
    private CouponTemplate couponTemplate;

    private UserInfoDTO user;
}
