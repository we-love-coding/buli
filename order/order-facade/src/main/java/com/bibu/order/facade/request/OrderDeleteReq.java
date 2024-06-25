package com.bibu.order.facade.request;

import com.bibu.order.facade.parent.ParentRequest;
import lombok.Getter;
import lombok.Setter;

/**
 * Author XY
 * Date 2024/5/13 下午12:48
 */
@Setter
@Getter
public class OrderDeleteReq extends ParentRequest {

    private String orderCode;
}
