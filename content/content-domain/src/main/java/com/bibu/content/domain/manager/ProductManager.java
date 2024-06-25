package com.bibu.content.domain.manager;

import com.bibu.content.common.exceptions.ContentException;
import com.bibu.product.feign.ProductClient;
import com.bibu.product.feign.request.GoodsQueryRemoteReq;
import com.bibu.product.feign.response.GoodsRemoteResp;
import com.x.common.ResponseResult;
import com.x.common.enums.ExceptionEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Author XY
 * Date 2024/5/3 下午4:36
 */
@Component
public class ProductManager {

    @Autowired
    private ProductClient productClient;

    public List<GoodsRemoteResp> findGoodsListByRPC(GoodsQueryRemoteReq req) {
        ResponseResult<List<GoodsRemoteResp>> result = productClient.findGoodsListByRPC(req);
        if (result == null || !result.getSuccess() || result.getData() == null) {
            throw new ContentException(ExceptionEnum.NETWORK_ERROR);
        }
        return result.getData();
    }
}
