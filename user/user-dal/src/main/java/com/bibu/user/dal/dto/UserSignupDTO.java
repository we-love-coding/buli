package com.bibu.user.dal.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * Author XY
 * Date 2024/3/30 下午5:27
 */
@Setter
@Getter
public class UserSignupDTO extends ParentDTO {

    private String email;

    private String phone;

    private String nickName;

    private String password;

    private Integer gender;

    private String avatarImage;
}
