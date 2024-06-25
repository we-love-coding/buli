package com.bibu.product.domain.biz.ground;

import com.bibu.product.facade.request.GoodsCategorySaveReq;
import com.bibu.product.facade.response.GoodsCategoryResp;

import java.util.List;

/**
 * Author XY
 * Date 2024/5/1 下午7:54
 */
public interface GoodsCategoryGroundService {
    GoodsCategoryResp saveGoodsCategory(GoodsCategorySaveReq req);

    List<GoodsCategoryResp> findGoodsCategoryList();
}
