package com.bibu.promotion.facade.response;

import com.bibu.promotion.facade.parent.ParentResponse;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * Author XY
 * Date 2024/5/11 上午11:41
 */
@Setter
@Getter
public class PromotionGoodsResp extends ParentResponse {

    private Long id;

    private String fullName;

    private String description;

    private BigDecimal listPrice;

    private BigDecimal salePrice;

    private Long categoryId;

    private Integer status;
}
