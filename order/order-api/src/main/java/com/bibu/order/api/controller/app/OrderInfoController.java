package com.bibu.order.api.controller.app;


import com.bibu.order.domain.biz.app.OrderInfoBizService;
import com.bibu.order.facade.request.*;
import com.bibu.order.facade.response.OrderConfirmResp;
import com.bibu.order.facade.response.OrderSubmitResp;
import com.x.common.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 订单表 前端控制器
 * </p>
 *
 * @author YiXie
 * @since 2024-05-01
 */
@RestController
@RequestMapping("/app/orderInfo")
public class OrderInfoController {

    @Autowired
    private OrderInfoBizService orderInfoBizService;

    @PostMapping("/confirmOrder")
    public ResponseResult<OrderConfirmResp> confirmOrder(@RequestBody OrderConfirmReq req) {
        return ResponseResult.success(orderInfoBizService.confirmOrder(req));
    }

    @PostMapping("/submitOrder")
    public ResponseResult<OrderSubmitResp> submitOrder(@RequestBody OrderSubmitReq req) {
        return ResponseResult.success(orderInfoBizService.submitOrder(req));
    }

    @PostMapping("/payOrder")
    public ResponseResult<Boolean> payOrder(@RequestBody OrderPayReq req) {
        return ResponseResult.success(orderInfoBizService.payOrder(req));
    }

    @PostMapping("/cancelOrder")
    public ResponseResult<OrderSubmitResp> cancelOrder(@RequestBody OrderCancelReq req) {
        return ResponseResult.success(orderInfoBizService.cancelOrder(req));
    }

    @PostMapping("/receiptOrder")
    public ResponseResult<OrderSubmitResp> receiptOrder(@RequestBody OrderReceiptReq req) {
        return ResponseResult.success(orderInfoBizService.receiptOrder(req));
    }
    @PostMapping("/deleteOrder")
    public ResponseResult<OrderSubmitResp> deleteOrder(@RequestBody OrderDeleteReq req) {
        return ResponseResult.success(orderInfoBizService.deleteOrder(req));
    }

}

