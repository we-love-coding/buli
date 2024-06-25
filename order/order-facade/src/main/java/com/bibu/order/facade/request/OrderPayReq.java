package com.bibu.order.facade.request;

import com.bibu.order.facade.parent.ParentRequest;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * Author XY
 * Date 2024/5/13 下午12:27
 */
@Setter
@Getter
public class OrderPayReq extends ParentRequest {

    private String orderCode;

    private Integer payType;

    private BigDecimal payAmount;
}
