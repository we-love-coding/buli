package com.bibu.order.facade.request;

import com.bibu.order.facade.parent.ParentRequest;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Author XY
 * Date 2024/4/27 下午3:40
 */
@Setter
@Getter
public class OrderConfirmReq extends ParentRequest {

    private List<OrderGoodsReq> goodsReqList;
}
