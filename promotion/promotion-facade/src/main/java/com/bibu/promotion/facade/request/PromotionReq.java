package com.bibu.promotion.facade.request;

import com.bibu.promotion.facade.parent.ParentRequest;
import lombok.Getter;
import lombok.Setter;

/**
 * Author XY
 * Date 2024/5/10 下午7:16
 */
@Setter
@Getter
public class PromotionReq extends ParentRequest {

    private String pCode;
}
