package com.bibu.product.facade.request;

import com.bibu.product.facade.parent.ParentRequest;
import lombok.Getter;
import lombok.Setter;

/**
 * Author XY
 * Date 2024/5/1 下午8:48
 */
@Setter
@Getter
public class GoodsImageReq extends ParentRequest {

    private String imageUrl;

    private Integer sort;

    private Integer isMain;
}
