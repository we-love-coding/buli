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
 * 订单明细表
 * </p>
 *
 * @author YiXie
 * @since 2024-05-01
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("order_goods")
public class OrderGoods implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Long orderId;

    private Long goodsId;

    private String goodsName;

    private Integer goodsNum;

    private BigDecimal goodsPrice;

    /**
     * 订单商品状态（例如：0->正常；1->退货；2->换货）
     */
    private Integer status;

    private Date createTime;

    private Date updateTime;

    private String updater;

    private String creator;

    private Integer isDelete;


}
