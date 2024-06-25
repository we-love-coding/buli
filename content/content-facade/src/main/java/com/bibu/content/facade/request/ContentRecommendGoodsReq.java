package com.bibu.content.facade.request;

import lombok.Getter;
import lombok.Setter;

/**
 * Author XY
 * Date 2024/5/20 下午3:22
 */
@Setter
@Getter
public class ContentRecommendGoodsReq {

    private Long id;

    private Long goodsId;

    private Integer status;
}
