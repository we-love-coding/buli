package com.bibu.promotion.facade.response;

import com.bibu.promotion.facade.parent.ParentResponse;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * Author XY
 * Date 2024/5/10 下午6:56
 */
@Setter
@Getter
public class PromotionConfigResp extends ParentResponse {

    private Long id;

    private Integer configType;

    private String configValue;

    private BigDecimal discountPrice;
}
