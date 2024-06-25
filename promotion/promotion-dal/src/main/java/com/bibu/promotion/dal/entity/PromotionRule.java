package com.bibu.promotion.dal.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author YiXie
 * @since 2024-05-08
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("promotion_rule")
public class PromotionRule implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Long promotionId;

    private Integer type;

    private BigDecimal achieveAmount;

    private BigDecimal discountAmount;

    private BigDecimal discountPercentage;

    private LocalDateTime updateTime;

    private LocalDateTime createTime;

    private String updater;

    private String creator;

    private Integer isDelete;


}
