package com.bibu.content.facade.response;

import com.bibu.content.facade.parent.ParentResponse;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

/**
 * Author XY
 * Date 2024/5/17 下午8:44
 */
@Setter
@Getter
public class ContentRecommendGoodsInfoResp extends ParentResponse {

    private Long userId;
    private String nickName;
    private String avatarImage;
    private Long goodsId;
    private String goodsName;
    private String description;
    private BigDecimal listPrice;
    private BigDecimal salePrice;
    private String categoryName;
    private List<String> goodsLabelList;
}
