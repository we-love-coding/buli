package com.bibu.order.domain.biz.app.impl;

import com.bibu.order.common.constants.OrderConstant;
import com.bibu.order.common.enums.OrderExceptionEnum;
import com.bibu.order.common.exceptions.OrderException;
import com.bibu.order.dal.entity.OrderGoods;
import com.bibu.order.dal.entity.OrderInfo;
import com.bibu.order.dal.entity.OrderReceiptInfo;
import com.bibu.order.domain.biz.app.OrderInfoBizService;
import com.bibu.order.domain.manager.ProductManager;
import com.bibu.order.domain.manager.UserManager;
import com.bibu.order.domain.service.OrderGoodsService;
import com.bibu.order.domain.service.OrderInfoService;
import com.bibu.order.domain.service.OrderReceiptInfoService;
import com.bibu.order.domain.tool.CodeGenerateTool;
import com.bibu.order.facade.request.*;
import com.bibu.order.facade.response.OrderConfirmResp;
import com.bibu.order.facade.response.OrderGoodsResp;
import com.bibu.order.facade.response.OrderSubmitResp;
import com.bibu.order.facade.response.ReceiverInfoResp;
import com.bibu.product.feign.request.GoodsQueryRemoteReq;
import com.bibu.product.feign.response.GoodsRemoteResp;
import com.bibu.user.facade.response.UserAddressResp;
import com.x.common.context.UserInfoContext;
import com.x.common.context.dto.UserInfoDTO;
import com.x.common.utils.BeanUtils;
import com.x.common.enums.YesOrNoEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.util.*;

/**
 * Author XY
 * Date 2024/5/1 下午10:07
 */
@Service
public class OrderInfoBizServiceImpl implements OrderInfoBizService {

    @Autowired
    private UserManager userManager;

    @Autowired
    private ProductManager productManager;

    @Autowired
    private OrderInfoService orderInfoService;

    @Autowired
    private OrderGoodsService orderGoodsService;

    @Autowired
    private CodeGenerateTool codeGenerateTool;

    @Autowired
    private OrderReceiptInfoService orderReceiptInfoService;

    @Override
    public OrderConfirmResp confirmOrder(OrderConfirmReq req) {
        UserInfoDTO user = UserInfoContext.getUser();
        Long userId = user.getUserId();

        UserAddressResp userAddressResp = userManager.findUserAddressByRPC(userId);

        // 收货人
        OrderConfirmResp orderConfirmResp = new OrderConfirmResp();
        orderConfirmResp.setReceiver(BeanUtils.convertBean(userAddressResp, ReceiverInfoResp.class));

        // 查询商品信息（数据库）
        Map<Long, Integer> map = new HashMap<>();
        List<OrderGoodsReq> goodsReqList = req.getGoodsReqList();
        for (OrderGoodsReq orderGoodsReq : goodsReqList) {
            Long goodsId = orderGoodsReq.getGoodsId();
            Integer goodsNum = orderGoodsReq.getGoodsNum();
            map.merge(goodsId, goodsNum, Integer::sum);
        }

        GoodsQueryRemoteReq remoteReq = new GoodsQueryRemoteReq();
        remoteReq.setGoodsIdList(new ArrayList<>(map.keySet()));
        List<GoodsRemoteResp> goodsList = productManager.findGoodsListByRPC(remoteReq);

        List<OrderGoodsResp> goodsRespList = new ArrayList<>();
        BigDecimal payAmount = BigDecimal.ZERO;
        BigDecimal totalAmount = BigDecimal.ZERO;
        for (GoodsRemoteResp goodsRemoteResp : goodsList) {
            Long goodsId = goodsRemoteResp.getId();
            BigDecimal salePrice = goodsRemoteResp.getSalePrice();
            BigDecimal listPrice = goodsRemoteResp.getListPrice();
            Integer goodsNum = map.get(goodsId);
            BigDecimal totalSaleAmount = salePrice.multiply(new BigDecimal(goodsNum));
            BigDecimal totalListAmount = listPrice.multiply(new BigDecimal(goodsNum));
            payAmount = payAmount.add(totalSaleAmount);
            totalAmount = totalAmount.add(totalListAmount);
            OrderGoodsResp orderGoodsResp = BeanUtils.convertBean(goodsRemoteResp, OrderGoodsResp.class);
            orderGoodsResp.setGoodsId(goodsRemoteResp.getId());
            orderGoodsResp.setGoodsName(goodsRemoteResp.getFullName());
            orderGoodsResp.setGoodsNum(goodsNum);
            orderGoodsResp.setTotalSaleAmount(totalSaleAmount);
            orderGoodsResp.setTotalListAmount(totalListAmount);
            goodsRespList.add(orderGoodsResp);
        }

        orderConfirmResp.setGoodsList(goodsRespList);
        orderConfirmResp.setPayAmount(payAmount);
        orderConfirmResp.setTotalAmount(totalAmount);
        orderConfirmResp.setFreightAmount(BigDecimal.ZERO);
        orderConfirmResp.setCouponAmount(BigDecimal.ZERO);

        return orderConfirmResp;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public OrderSubmitResp submitOrder(OrderSubmitReq req) {
        UserInfoDTO user = UserInfoContext.getUser();
        Long userId = user.getUserId();

        BigDecimal totalAmountReq = req.getTotalAmount();
        BigDecimal payAmountReq = req.getPayAmount();

        UserAddressResp userAddressResp = userManager.findUserAddressByRPC(userId);
        List<Long> goodsIdList = new ArrayList<>();
        Map<Long, Integer> map = new HashMap<>();
        List<OrderGoodsReq> goodsReqList = req.getGoodsReqList();
        for (OrderGoodsReq orderGoodsReq : goodsReqList) {
            Long goodsId = orderGoodsReq.getGoodsId();
            Integer goodsNum = orderGoodsReq.getGoodsNum();
            map.merge(goodsId, goodsNum, Integer::sum);
            goodsIdList.add(goodsId);
        }
        GoodsQueryRemoteReq remoteReq = new GoodsQueryRemoteReq();
        remoteReq.setGoodsIdList(goodsIdList);
        List<GoodsRemoteResp> goodsList = productManager.findGoodsListByRPC(remoteReq);
        List<OrderGoodsResp> goodsRespList = new ArrayList<>();

        BigDecimal payAmount = BigDecimal.ZERO;
        BigDecimal totalAmount = BigDecimal.ZERO;
        Map<Long, GoodsRemoteResp> goodsRemoteRespMap = new HashMap<>();
        for (GoodsRemoteResp goodsRemoteResp : goodsList) {
            Long goodsId = goodsRemoteResp.getId();
            BigDecimal salePrice = goodsRemoteResp.getSalePrice();
            BigDecimal listPrice = goodsRemoteResp.getListPrice();
            Integer goodsNum = map.get(goodsId);
            BigDecimal totalSaleAmount = salePrice.multiply(new BigDecimal(goodsNum));
            BigDecimal totalListAmount = listPrice.multiply(new BigDecimal(goodsNum));
            payAmount = payAmount.add(totalSaleAmount);
            totalAmount = totalAmount.add(totalListAmount);
            OrderGoodsResp orderGoodsResp = BeanUtils.convertBean(goodsRemoteResp, OrderGoodsResp.class);
            orderGoodsResp.setGoodsId(goodsRemoteResp.getId());
            orderGoodsResp.setGoodsName(goodsRemoteResp.getFullName());
            orderGoodsResp.setGoodsNum(goodsNum);
            orderGoodsResp.setTotalSaleAmount(totalSaleAmount);
            orderGoodsResp.setTotalListAmount(totalListAmount);
            goodsRespList.add(orderGoodsResp);
            goodsRemoteRespMap.put(goodsId, goodsRemoteResp);

        }

        if (payAmountReq.compareTo(payAmount) != 0) {
            throw new OrderException(OrderExceptionEnum.ORDER_SUBMIT_EXCEPTION, "支付金额错误");
        } else if (totalAmountReq.compareTo(totalAmount) != 0) {
            throw new OrderException(OrderExceptionEnum.ORDER_SUBMIT_EXCEPTION, "总金额错误");
        }
        Date date = new Date();

        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setUserId(userId);
        orderInfo.setCouponAmount(BigDecimal.ZERO);
        orderInfo.setFreightAmount(BigDecimal.ZERO);
        orderInfo.setOrderCode(codeGenerateTool.generateCode(OrderConstant.ORDER_CODE_PREFIX));
        orderInfo.setOrderType(req.getOrderType());
        orderInfo.setPayType(0);
        orderInfo.setSourceType(req.getSourceType());
        orderInfo.setStatus(0);
        orderInfo.setTotalAmount(totalAmount);
        orderInfo.setPayAmount(payAmount);
        orderInfo.setIsPay(0);
        orderInfo.setUpdateTime(date);
        orderInfo.setCreateTime(date);
        orderInfo.setCreator(user.getNickName());
        orderInfo.setUpdater(user.getNickName());
        boolean saveBool = orderInfoService.save(orderInfo);
        if (!saveBool) {
            throw new OrderException(OrderExceptionEnum.ORDER_SUBMIT_EXCEPTION);
        }
        Long orderId = orderInfo.getId();
        List<OrderGoods> orderGoodsList = new ArrayList<>();
        for (OrderGoodsReq orderGoodsReq : goodsReqList) {
            Long goodsId = orderGoodsReq.getGoodsId();
            Integer goodsNum = orderGoodsReq.getGoodsNum();
            BigDecimal salePrice = orderGoodsReq.getSalePrice();
            BigDecimal listPrice = orderGoodsReq.getListPrice();
            GoodsRemoteResp goodsRemoteResp = goodsRemoteRespMap.get(goodsId);
            if (goodsRemoteResp == null) {
                throw new OrderException(OrderExceptionEnum.ORDER_SUBMIT_EXCEPTION);
            } else if (goodsRemoteResp.getSalePrice().compareTo(salePrice) != 0) {
                throw new OrderException(OrderExceptionEnum.ORDER_SUBMIT_EXCEPTION);
            } else if (goodsRemoteResp.getListPrice().compareTo(listPrice) != 0) {
                throw new OrderException(OrderExceptionEnum.ORDER_SUBMIT_EXCEPTION);
            }

            OrderGoods orderGoods = new OrderGoods();
            orderGoods.setOrderId(orderId);
            orderGoods.setGoodsId(goodsId);
            orderGoods.setGoodsNum(goodsNum);
            orderGoods.setGoodsPrice(salePrice.multiply(new BigDecimal(goodsNum)));
            orderGoods.setCreateTime(date);
            orderGoods.setUpdateTime(date);
            orderGoods.setCreator(user.getNickName());
            orderGoods.setUpdater(user.getNickName());
            orderGoodsList.add(orderGoods);
        }

        boolean saveBatchBool = orderGoodsService.saveBatch(orderGoodsList);
        if (!saveBatchBool) {
            throw new OrderException(OrderExceptionEnum.ORDER_SUBMIT_EXCEPTION);
        }

        OrderReceiptInfo orderReceiptInfo = new OrderReceiptInfo();
        orderReceiptInfo.setOrderId(orderId);
        orderReceiptInfo.setReceiptUser(userAddressResp.getReceiver());
        orderReceiptInfo.setReceiptPhone(userAddressResp.getMobile());
        orderReceiptInfo.setReceiptProvince(userAddressResp.getProvince());
        orderReceiptInfo.setReceiptCity(userAddressResp.getCity());
        orderReceiptInfo.setReceiptDistrict(userAddressResp.getDistrict());
        orderReceiptInfo.setReceiptDetailAddress(userAddressResp.getDetailAddress());
        orderReceiptInfo.setUpdateTime(date);
        orderReceiptInfo.setCreateTime(date);
        orderReceiptInfo.setUpdater(userId.toString());
        orderReceiptInfo.setCreator(userId.toString());
        orderReceiptInfo.setIsDelete(YesOrNoEnum.NO.getStatus());
        boolean saveSingle = orderReceiptInfoService.save(orderReceiptInfo);
        if (!saveSingle) {
            throw new OrderException(OrderExceptionEnum.ORDER_SUBMIT_EXCEPTION);
        }

        OrderSubmitResp orderSubmitResp = new OrderSubmitResp();
        orderSubmitResp.setReceiver(BeanUtils.convertBean(userAddressResp, ReceiverInfoResp.class));
        orderSubmitResp.setGoodsList(goodsRespList);
        orderSubmitResp.setPayAmount(payAmount);
        orderSubmitResp.setTotalAmount(totalAmount);
        orderSubmitResp.setCouponAmount(BigDecimal.ZERO);
        orderSubmitResp.setFreightAmount(BigDecimal.ZERO);
        return orderSubmitResp;
    }

    @Override
    public Boolean payOrder(OrderPayReq req) {
        String orderCode = req.getOrderCode();
        UserInfoDTO user = UserInfoContext.getUser();
        OrderInfo orderInfo = orderInfoService.getOrderInfoByOrderCode(orderCode, user.getUserId());
        if (orderInfo == null) {
            throw new OrderException(OrderExceptionEnum.ORDER_SUBMIT_EXCEPTION, "该订单不存在");
        } else if (orderInfo.getIsPay() == YesOrNoEnum.YES.getStatus()) {
            throw new OrderException(OrderExceptionEnum.ORDER_SUBMIT_EXCEPTION, "该订单已支付，无须重复支付");
        } else if (orderInfo.getStatus() != 1) {
            throw new OrderException(OrderExceptionEnum.ORDER_SUBMIT_EXCEPTION, "该订单已支付，无须重复支付");
        }
        Date date = new Date();
        Long userId = user.getUserId();
        OrderInfo updateOrderInfo = new OrderInfo();
        updateOrderInfo.setId(orderInfo.getId());
        updateOrderInfo.setIsPay(YesOrNoEnum.YES.getStatus());
        updateOrderInfo.setPayTime(date);
        updateOrderInfo.setPayType(req.getPayType());
        updateOrderInfo.setStatus(10);
        updateOrderInfo.setUpdater(userId.toString());
        updateOrderInfo.setUpdateTime(date);
        return orderInfoService.updateById(updateOrderInfo);
    }

    @Override
    public Boolean cancelOrder(OrderCancelReq req) {
        String orderCode = req.getOrderCode();
        UserInfoDTO user = UserInfoContext.getUser();
        OrderInfo orderInfo = orderInfoService.getOrderInfoByOrderCode(orderCode, user.getUserId());
        if (orderInfo == null) {
            throw new OrderException(OrderExceptionEnum.ORDER_SUBMIT_EXCEPTION, "该订单不存在");
        } else if (orderInfo.getStatus() == 40) {
            throw new OrderException(OrderExceptionEnum.ORDER_SUBMIT_EXCEPTION, "该订单已取消");
        }
        Date date = new Date();
        Long userId = user.getUserId();
        OrderInfo updateOrderInfo = new OrderInfo();
        updateOrderInfo.setId(orderInfo.getId());
        updateOrderInfo.setStatus(40);
        updateOrderInfo.setUpdater(userId.toString());
        updateOrderInfo.setUpdateTime(date);
        return orderInfoService.updateById(updateOrderInfo);
    }

    @Override
    public Boolean receiptOrder(OrderReceiptReq req) {
        String orderCode = req.getOrderCode();
        UserInfoDTO user = UserInfoContext.getUser();
        OrderInfo orderInfo = orderInfoService.getOrderInfoByOrderCode(orderCode, user.getUserId());
        if (orderInfo == null) {
            throw new OrderException(OrderExceptionEnum.ORDER_SUBMIT_EXCEPTION, "该订单不存在");
        } else if (orderInfo.getStatus() == 40 || orderInfo.getStatus() == 30) {
            throw new OrderException(OrderExceptionEnum.ORDER_SUBMIT_EXCEPTION, "该订单已取消");
        }
        Date date = new Date();
        Long userId = user.getUserId();
        OrderInfo updateOrderInfo = new OrderInfo();
        updateOrderInfo.setId(orderInfo.getId());
        updateOrderInfo.setStatus(30);
        updateOrderInfo.setUpdater(userId.toString());
        updateOrderInfo.setUpdateTime(date);
        return orderInfoService.updateById(updateOrderInfo);
    }

    @Override
    public Boolean deleteOrder(OrderDeleteReq req) {
        String orderCode = req.getOrderCode();
        UserInfoDTO user = UserInfoContext.getUser();
        OrderInfo orderInfo = orderInfoService.getOrderInfoByOrderCode(orderCode, user.getUserId());
        if (orderInfo == null) {
            throw new OrderException(OrderExceptionEnum.ORDER_SUBMIT_EXCEPTION, "该订单不存在");
        } else if (orderInfo.getStatus() != 30) {
            throw new OrderException(OrderExceptionEnum.ORDER_SUBMIT_EXCEPTION, "该订单不可删除");
        }
        Date date = new Date();
        Long userId = user.getUserId();
        OrderInfo updateOrderInfo = new OrderInfo();
        updateOrderInfo.setId(orderInfo.getId());
        updateOrderInfo.setIsDelete(YesOrNoEnum.YES.getStatus());
        updateOrderInfo.setUpdater(userId.toString());
        updateOrderInfo.setUpdateTime(date);
        return orderInfoService.updateById(updateOrderInfo);
    }
}
