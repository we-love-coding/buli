package com.bibu.order.dal.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 订单表
 * </p>
 *
 * @author YiXie
 * @since 2024-05-01
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("order_info")
public class OrderInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 订单类型（0->正常订单；1->秒杀订单）
     */
    private Integer orderType;

    /**
     * 订单编号
     */
    private String orderCode;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 支付方式（1->支付宝；2->微信）
     */
    private Integer payType;

    /**
     * 订单来源（0->PC订单；1->app订单）
     */
    private Integer sourceType;

    /**
     * 订单总金额
     */
    private BigDecimal totalAmount;

    /**
     * 应付金额（实际支付金额）
     */
    private BigDecimal payAmount;

    /**
     * 运费金额
     */
    private BigDecimal freightAmount;

    /**
     * 优惠券抵扣金额
     */
    private BigDecimal couponAmount;

    /**
     * 订单状态（0->待付款；10->待发货；20->待收货；30->已完成；40->已取消）
     */
    private Integer status;

    /**
     * 支付状态：0：未支付，1:已支付
     */
    private Integer isPay;

    private Date payTime;

    /**
     * 订单发货状态（例如：0->未发货；1->已发货）
     */
    private Integer isShip;

    /**
     * 发货时间
     */
    private Date shipTime;

    /**
     * 订单收货状态（例如：0->未收货；1->已收货）
     */
    private Integer isReceipt;

    /**
     * 收货时间
     */
    private Date receiptTime;

    /**
     * 物流单号
     */
    private String shipNumber;

    private Date createTime;

    private Date updateTime;

    private String updater;

    private String creator;

    private Integer isDelete;


}
