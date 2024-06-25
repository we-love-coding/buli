package com.bibu.user.facade.request;

import com.bibu.user.facade.parent.ParentRequest;
import lombok.Getter;
import lombok.Setter;

/**
 * Author XY
 * Date 2024/4/1 下午1:53
 */
@Setter
@Getter
public class UserLoginReq extends ParentRequest {

    private String username;

    private String password;
}
