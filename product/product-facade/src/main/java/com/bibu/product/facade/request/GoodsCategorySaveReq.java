package com.bibu.product.facade.request;

import com.bibu.product.facade.parent.ParentRequest;
import lombok.Getter;
import lombok.Setter;

/**
 * Author XY
 * Date 2024/5/1 下午8:09
 */
@Setter
@Getter
public class GoodsCategorySaveReq extends ParentRequest {
    private Long parentId;

    private String categoryName;

    private Integer status;
}
