package com.bibu.order.domain.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bibu.order.dal.entity.OrderInfo;
import com.bibu.order.dal.mapper.OrderInfoMapper;
import com.bibu.order.domain.service.OrderInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.x.common.enums.YesOrNoEnum;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单表 服务实现类
 * </p>
 *
 * @author YiXie
 * @since 2024-05-01
 */
@Service
public class OrderInfoServiceImpl extends ServiceImpl<OrderInfoMapper, OrderInfo> implements OrderInfoService {



    @Override
    public OrderInfo getOrderInfoByOrderCode(String orderCode, Long userId) {
        QueryWrapper<OrderInfo> wrapper = new QueryWrapper<>();
        wrapper.eq("order_code", orderCode);
        wrapper.eq("user_id", userId);
        wrapper.eq("is_delete", YesOrNoEnum.NO.getStatus());
        wrapper.last("limit 1");
        return getOne(wrapper);
    }
}
