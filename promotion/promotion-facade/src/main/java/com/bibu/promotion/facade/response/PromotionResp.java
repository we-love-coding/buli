package com.bibu.promotion.facade.response;

import com.bibu.promotion.facade.parent.ParentResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

/**
 * Author XY
 * Date 2024/5/10 下午6:01
 */
@Setter
@Getter
public class PromotionResp extends ParentResponse {

    private Long id;
    private String code;
    private Integer type;
    private String typeStr;
    private String name;
    private String description;
    private Date startTime;
    private Date endTime;
    private Integer rStatus;
    private String rStatusStr;

    private List<CouponTemplateResp> couponList;

    private List<PromotionGoodsResp> goodsList;

}
