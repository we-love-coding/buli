package com.bibu.order.facade.response;

import com.bibu.order.facade.parent.ParentResponse;
import lombok.Getter;
import lombok.Setter;

/**
 * Author XY
 * Date 2024/4/27 下午3:13
 */
@Setter
@Getter
public class ReceiverInfoResp extends ParentResponse {

    private String receiver;
    private String mobile;
    private String province;
    private String city;
    private String district;
    private String detailAddress;
}
