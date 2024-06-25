package com.bibu.product.dal.entity;

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
 * 商品评论表
 * </p>
 *
 * @author YiXie
 * @since 2024-05-01
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("goods_comment")
public class GoodsComment implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Long goodsId;

    private Long userId;

    private String content;

    private BigDecimal score;

    private Date commentTime;

    private Date updateTime;

    private Date createTime;

    private String updater;

    private String creator;

    private Integer isDelete;


}
