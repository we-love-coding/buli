package com.bibu.order.facade.response;

import com.bibu.order.facade.parent.ParentResponse;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

/**
 * Author XY
 * Date 2024/4/27 下午1:10
 */
@Setter
@Getter
public class OrderConfirmResp extends ParentResponse {

    private ReceiverInfoResp receiver;

    private List<OrderGoodsResp> goodsList;

    private BigDecimal totalAmount;

    private BigDecimal payAmount;

    private BigDecimal freightAmount;

    private BigDecimal couponAmount;

}
