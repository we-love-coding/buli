package com.bibu.promotion.dal.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
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
@TableName("coupon_use_record")
public class CouponUseRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Long couponAccountId;

    private Long userId;

    private String orderCode;

    private LocalDateTime useDate;

    private LocalDateTime updateTime;

    private LocalDateTime createTime;

    private String updater;

    private String creator;

    private Integer isDelete;


}
