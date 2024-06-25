package com.bibu.order.facade.request;

import com.bibu.order.facade.parent.ParentResponse;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * Author XY
 * Date 2024/4/27 下午3:30
 */
@Setter
@Getter
public class OrderGoodsReq extends ParentResponse {

    private Long goodsId;

    private Integer goodsNum;

    private BigDecimal listPrice;

    private BigDecimal salePrice;
}
