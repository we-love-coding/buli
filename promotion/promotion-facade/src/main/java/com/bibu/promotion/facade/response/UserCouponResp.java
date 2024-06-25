package com.bibu.promotion.facade.response;

import com.bibu.promotion.facade.parent.ParentResponse;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Author XY
 * Date 2024/5/8 下午4:47
 */
@Setter
@Getter
public class UserCouponResp extends ParentResponse {

    private Long id;
    private Long couponTemplateId;
    private Date obtainTime;
    private Integer status;
    private Date effectiveStartTime;
    private Date effectiveEndTime;

    private String couponTemplateCode;
    private String couponTemplateName;
    private Integer discountType;
    private String discountTypeName;
    private BigDecimal achieveAmount;
    private BigDecimal discountAmount;
    private BigDecimal discountPercentage;

}
