package com.bibu.user.facade.request;

import com.bibu.user.facade.parent.ParentRequest;
import lombok.Getter;
import lombok.Setter;

/**
 * Author XY
 * Date 2024/4/27 下午1:41
 */
@Setter
@Getter
public class UserTokenReq extends ParentRequest {

    private String token;

}
