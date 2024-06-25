package com.bibu.user.facade.response;

import com.bibu.user.facade.parent.ParentResponse;
import lombok.Getter;
import lombok.Setter;

/**
 * Author XY
 * Date 2024/4/1 下午1:56
 */
@Setter
@Getter
public class UserInfoResp extends ParentResponse {

    private Long userId;

    private String nickName;

    private String avatarImage;

    private Integer gender;

    private String phone;

    private String email;

    private String token;

    private String refreshToken;

}
