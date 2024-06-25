package com.bibu.promotion.facade.request;

import com.bibu.promotion.facade.parent.PageParentRequest;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * Author XY
 * Date 2024/5/10 下午1:38
 */
@Setter
@Getter
public class CouponTemplateSearchReq extends PageParentRequest {

    private String templateCode;

    private String templateName;

    private Integer discountType;

    private Date startTime;

    private Date endTime;
}
