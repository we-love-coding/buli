package com.bibu.product.facade.response;

import com.bibu.product.facade.parent.ParentResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Author XY
 * Date 2024/5/1 下午8:50
 */
@Setter
@Getter
public class GoodsCategoryResp extends ParentResponse {

    private Long id;

    private Long parentId;

    private String categoryName;

    private Integer status;

    private List<GoodsCategoryResp> items;
}
