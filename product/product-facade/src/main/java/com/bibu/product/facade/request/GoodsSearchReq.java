package com.bibu.product.facade.request;

import com.bibu.product.facade.parent.PageParentRequest;
import lombok.Getter;
import lombok.Setter;

/**
 * Author XY
 * Date 2024/5/6 下午2:00
 */
@Setter
@Getter
public class GoodsSearchReq extends PageParentRequest {

    private String fullName;
}
