package com.bibu.product.domain.biz.app;

import com.bibu.product.facade.request.GoodsSearchReq;
import com.bibu.product.facade.response.GoodsResp;
import com.github.pagehelper.PageInfo;

/**
 * Author XY
 * Date 2024/5/6 下午2:02
 */
public interface GoodsAppService {

    PageInfo<GoodsResp> findGoodsListByPage(GoodsSearchReq req);
}
