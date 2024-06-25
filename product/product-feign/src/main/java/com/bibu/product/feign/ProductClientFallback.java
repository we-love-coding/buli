package com.bibu.product.feign;

import com.bibu.product.feign.request.GoodsQueryRemoteReq;
import com.bibu.product.feign.response.GoodsRemoteResp;
import com.x.common.ResponseResult;
import org.springframework.cloud.client.circuitbreaker.NoFallbackAvailableException;

import java.util.List;

/**
 * Author XY
 * Date 2024/5/3 下午2:36
 */
public class ProductClientFallback implements ProductClient {

    @Override
    public ResponseResult<List<GoodsRemoteResp>> findGoodsListByRPC(GoodsQueryRemoteReq remoteReq) {
        throw new NoFallbackAvailableException("Boom!", new Exception());
    }
}
