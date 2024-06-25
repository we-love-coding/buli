package com.bibu.user.dal.entity;

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
 * 用户收货地址
 * </p>
 *
 * @author YiXie
 * @since 2024-04-27
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("user_address")
public class UserAddress implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Long userId;

    private String receiver;

    private String mobile;

    private String province;

    private String city;

    private String district;

    private String detailAddress;

    private Integer isDefault;

    private Date updateTime;

    private Date createTime;

    private String updater;

    private String creator;


}
