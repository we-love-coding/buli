package com.bibu.product.facade.request;

import com.bibu.product.facade.parent.ParentRequest;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Author XY
 * Date 2024/5/3 下午2:23
 */
@Setter
@Getter
public class GoodsQueryReq extends ParentRequest {

    private List<Long> goodsIdList;
}
