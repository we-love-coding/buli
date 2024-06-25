package com.bibu.user.facade.response;

import com.bibu.user.facade.parent.ParentResponse;
import lombok.Getter;
import lombok.Setter;

/**
 * Author XY
 * Date 2024/4/27 下午4:11
 */
@Setter
@Getter
public class UserAddressResp extends ParentResponse {

    private String receiver;
    private String mobile;
    private String province;
    private String city;
    private String district;
    private String detailAddress;
    private Integer isDefault;
}
