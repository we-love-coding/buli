package com.bibu.promotion.dal.entity;

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
 * 
 * </p>
 *
 * @author YiXie
 * @since 2024-05-08
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("coupon_template")
public class CouponTemplate implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String templateCode;

    private String templateName;

    /**
     * 券类型：1:满减券、2:折扣券、3:代金券
     */
    private Integer discountType;

    private BigDecimal achieveAmount;

    private BigDecimal discountAmount;

    private BigDecimal discountPercentage;

    private Date startTime;

    private Date endTime;

    private Integer status;

    private Date updateTime;

    private Date createTime;

    private String updater;

    private String creator;

    private Integer isDelete;

    private Integer offerNum;

}
