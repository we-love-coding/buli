package com.bibu.order.facade.request;

import com.bibu.order.facade.parent.ParentRequest;
import lombok.Getter;
import lombok.Setter;

/**
 * Author XY
 * Date 2024/4/26 下午4:49
 */
@Setter
@Getter
public class GoodsShoppingCartReq extends ParentRequest {

    private Long goodsId;

    private Integer goodsNum;
}
