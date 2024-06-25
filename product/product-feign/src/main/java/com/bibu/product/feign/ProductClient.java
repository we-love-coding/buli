package com.bibu.product.feign;

import com.bibu.product.feign.request.GoodsQueryRemoteReq;
import com.bibu.product.feign.response.GoodsRemoteResp;
import com.x.common.ResponseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * Author XY
 * Date 2024/5/3 下午2:36
 */
@FeignClient(name = "product", fallbackFactory = ProductFallbackFactory.class)
public interface ProductClient {

    @PostMapping("/rpc/goods/findGoodsListByRPC")
    ResponseResult<List<GoodsRemoteResp>> findGoodsListByRPC(@RequestBody GoodsQueryRemoteReq remoteReq);
}
