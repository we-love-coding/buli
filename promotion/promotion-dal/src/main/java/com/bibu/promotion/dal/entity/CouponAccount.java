package com.bibu.promotion.dal.entity;

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
 * 
 * </p>
 *
 * @author YiXie
 * @since 2024-05-08
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("coupon_account")
public class CouponAccount implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Long couponTemplateId;

    private Long userId;

    private Date obtainTime;

    private Integer status;

    private Date effectiveStartTime;

    private Date effectiveEndTime;

    private Date updateTime;

    private Date createTime;

    private String updater;

    private String creator;

    private Integer isDelete;


}
