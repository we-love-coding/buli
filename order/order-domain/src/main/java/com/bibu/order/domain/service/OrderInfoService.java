package com.bibu.order.domain.service;

import com.bibu.order.dal.entity.OrderInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 订单表 服务类
 * </p>
 *
 * @author YiXie
 * @since 2024-05-01
 */
public interface OrderInfoService extends IService<OrderInfo> {

    OrderInfo getOrderInfoByOrderCode(String orderCode, Long userId);
}
