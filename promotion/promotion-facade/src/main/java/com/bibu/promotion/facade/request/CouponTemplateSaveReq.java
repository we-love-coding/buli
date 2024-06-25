package com.bibu.promotion.facade.request;

import com.bibu.promotion.facade.parent.ParentRequest;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Author XY
 * Date 2024/5/8 下午2:42
 */
@Setter
@Getter
public class CouponTemplateSaveReq extends ParentRequest {

    private Long id;
    private String templateName;
    private Integer offerNum;
    private Integer discountType;
    private BigDecimal achieveAmount;
    private BigDecimal discountAmount;
    private BigDecimal discountPercentage;
    private Date startTime;
    private Date endTime;
    private Integer status;
}
