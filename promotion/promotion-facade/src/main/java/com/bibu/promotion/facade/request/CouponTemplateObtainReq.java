package com.bibu.promotion.facade.request;

import com.bibu.promotion.facade.parent.ParentRequest;
import lombok.Getter;
import lombok.Setter;

/**
 * Author XY
 * Date 2024/5/8 下午3:07
 */
@Setter
@Getter
public class CouponTemplateObtainReq extends ParentRequest {

    private String templateCode;

}
