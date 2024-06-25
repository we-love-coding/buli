package com.bibu.order.domain.service.impl;

import com.bibu.order.dal.entity.OrderGoods;
import com.bibu.order.dal.mapper.OrderGoodsMapper;
import com.bibu.order.domain.service.OrderGoodsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单明细表 服务实现类
 * </p>
 *
 * @author YiXie
 * @since 2024-05-01
 */
@Service
public class OrderGoodsServiceImpl extends ServiceImpl<OrderGoodsMapper, OrderGoods> implements OrderGoodsService {

}
