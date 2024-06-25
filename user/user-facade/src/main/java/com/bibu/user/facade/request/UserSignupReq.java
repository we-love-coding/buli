package com.bibu.user.facade.request;

import com.bibu.user.facade.parent.ParentRequest;
import lombok.Getter;
import lombok.Setter;

/**
 * Author XY
 * Date 2024/3/30 下午5:00
 */
@Setter
@Getter
public class UserSignupReq extends ParentRequest {

    private String email;

    private String phone;

    private String nickName;

    private String password;

    private Integer signupType;

    private Integer gender;

    private String avatarImg;

}
