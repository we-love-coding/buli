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
 * 用户表
 * </p>
 *
 * @author YiXie
 * @since 2024-05-01
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("user_info")
public class UserInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String nickName;

    private String avatarImage;

    /**
     * 性别：0:女，1:男，2:未知
     */
    private Integer gender;

    private String phone;

    private String email;

    private String password;

    private Date signupTime;

    private Date logoutTime;

    private Date createTime;

    private Date updateTime;

    private String updater;

    private String creator;

    private Integer isDelete;


}
