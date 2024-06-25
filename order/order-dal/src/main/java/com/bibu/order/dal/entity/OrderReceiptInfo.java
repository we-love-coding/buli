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
 * 
 * </p>
 *
 * @author YiXie
 * @since 2024-05-13
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("order_receipt_info")
public class OrderReceiptInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Long orderId;

    private String receiptUser;

    private String receiptPhone;

    private String receiptProvince;

    private String receiptCity;

    private String receiptDistrict;

    private String receiptDetailAddress;

    private Date createTime;

    private Date updateTime;

    private String updater;

    private String creator;

    private Integer isDelete;


}
