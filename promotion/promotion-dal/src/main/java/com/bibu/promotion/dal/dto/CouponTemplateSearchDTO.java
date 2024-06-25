package com.bibu.promotion.dal.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * Author XY
 * Date 2024/5/10 下午3:06
 */
@Setter
@Getter
public class CouponTemplateSearchDTO {

    private String templateCode;

    private String templateName;

    private Integer discountType;

    private Date startTime;

    private Date endTime;

    private Integer rStatus;
}
