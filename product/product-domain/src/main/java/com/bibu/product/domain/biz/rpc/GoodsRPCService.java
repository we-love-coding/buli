package com.bibu.product.domain.biz.rpc;

import com.bibu.product.facade.request.GoodsQueryReq;
import com.bibu.product.facade.response.GoodsResp;

import java.util.List;

/**
 * Author XY
 * Date 2024/5/3 下午2:21
 */
public interface GoodsRPCService {

    List<GoodsResp> getGoodsListByRPC(GoodsQueryReq req);
}
