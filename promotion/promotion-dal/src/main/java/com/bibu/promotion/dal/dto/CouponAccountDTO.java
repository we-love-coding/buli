package com.bibu.promotion.dal.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * Author XY
 * Date 2024/5/8 下午4:53
 */
@Setter
@Getter
public class CouponAccountDTO {

    private Integer status;

    private Long userId;

    private Date effectiveStartTime;

    private Date effectiveEndTime;
}
