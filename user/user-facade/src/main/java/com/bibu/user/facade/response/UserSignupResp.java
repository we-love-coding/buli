package com.bibu.user.facade.response;

import com.bibu.user.facade.parent.ParentResponse;
import lombok.Getter;
import lombok.Setter;

/**
 * Author XY
 * Date 2024/3/30 下午5:16
 */
@Setter
@Getter
public class UserSignupResp extends ParentResponse {

    private String message;
}
