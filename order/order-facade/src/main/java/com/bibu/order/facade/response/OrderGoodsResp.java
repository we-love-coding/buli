package com.bibu.order.facade.response;

import com.bibu.order.facade.parent.ParentResponse;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

/**
 * Author XY
 * Date 2024/4/27 下午3:30
 */
@Setter
@Getter
public class OrderGoodsResp extends ParentResponse {

    private Long goodsId;

    private String goodsName;

    private Integer goodsNum;

    private String goodsImage;

    private List<String> labelList;

    private BigDecimal listPrice;

    private BigDecimal salePrice;

    private BigDecimal totalSaleAmount;

    private BigDecimal totalListAmount;
}
