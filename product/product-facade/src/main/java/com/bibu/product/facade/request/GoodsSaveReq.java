package com.bibu.product.facade.request;

import com.bibu.product.facade.parent.ParentRequest;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.util.List;

/**
 * Author XY
 * Date 2024/5/1 下午8:49
 */
@Setter
@Getter
public class GoodsSaveReq extends ParentRequest {

    private String fullName;

    private String description;

    private BigDecimal listPrice;

    private BigDecimal salePrice;

    private Long categoryId;

    private Integer stockNum;

    private Integer status;

    private List<GoodsImageReq> imageList;
}
