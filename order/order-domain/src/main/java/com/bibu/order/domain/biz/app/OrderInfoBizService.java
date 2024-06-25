package com.bibu.order.domain.biz.app;

import com.bibu.order.facade.request.*;
import com.bibu.order.facade.response.OrderConfirmResp;
import com.bibu.order.facade.response.OrderSubmitResp;

/**
 * Author XY
 * Date 2024/5/1 下午10:07
 */
public interface OrderInfoBizService {

    OrderConfirmResp confirmOrder(OrderConfirmReq req);

    OrderSubmitResp submitOrder(OrderSubmitReq req);

    Boolean payOrder(OrderPayReq req);

    Boolean cancelOrder(OrderCancelReq req);

    Boolean receiptOrder(OrderReceiptReq req);

    Boolean deleteOrder(OrderDeleteReq req);


}
