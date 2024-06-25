package com.bibu.product.facade.response;

import com.bibu.product.facade.parent.ParentResponse;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * Author XY
 * Date 2024/5/3 下午2:24
 */
@Setter
@Getter
public class GoodsResp extends ParentResponse {

    private Long id;

    private String fullName;

    private String description;

    private BigDecimal listPrice;

    private BigDecimal salePrice;

    private Long categoryId;

    private Integer status;
}
