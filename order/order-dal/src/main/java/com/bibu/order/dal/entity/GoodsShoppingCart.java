package com.bibu.order.dal.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 购物车
 * </p>
 *
 * @author YiXie
 * @since 2024-05-01
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("goods_shopping_cart")
public class GoodsShoppingCart implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 商品ID
     */
    private Long goodsId;

    /**
     * 商品数量
     */
    private Integer goodsNum;

    /**
     * 购物车记录状态（例如：0->未选中；1->已选中）
     */
    private Integer status;

    private Date createTime;

    private Date updateTime;

    private String updater;

    private String creator;

    private Integer isDelete;


}
