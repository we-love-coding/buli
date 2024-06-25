package com.bibu.promotion.facade.request;

import com.bibu.promotion.facade.parent.PageParentRequest;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * Author XY
 * Date 2024/5/8 下午4:45
 */
@Setter
@Getter
public class UserCouponSearchReq extends PageParentRequest {

    private Integer status;

    private Date effectiveStartTime;

    private Date effectiveEndTime;
}
