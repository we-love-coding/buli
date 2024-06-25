package com.bibu.order.facade.request;

import com.bibu.order.facade.parent.ParentRequest;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

/**
 * Author XY
 * Date 2024/4/27 下午5:40
 */
@Setter
@Getter
public class OrderSubmitReq extends ParentRequest {

    private List<OrderGoodsReq> goodsReqList;

    private BigDecimal totalAmount;

    private BigDecimal payAmount;

    private BigDecimal freightAmount;

    private BigDecimal couponAmount;

    private Integer orderType;

    private Integer sourceType;
}
