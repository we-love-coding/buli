package com.bibu.promotion.facade.response;

import com.bibu.promotion.facade.parent.ParentResponse;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Author XY
 * Date 2024/5/10 下午1:40
 */
@Setter
@Getter
public class CouponTemplateResp extends ParentResponse {

    private Long id;
    private String templateCode;
    private String templateName;
    private Integer offerNum;
    private Integer discountType;
    private String discountTypeStr;
    private BigDecimal achieveAmount;
    private BigDecimal discountAmount;
    private BigDecimal discountPercentage;
    private Date startTime;
    private Date endTime;
    private Integer rStatus;
    private String rStatusStr;
}
