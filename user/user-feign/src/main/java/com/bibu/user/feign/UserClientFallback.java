package com.bibu.user.feign;

import com.bibu.user.feign.request.UserBatchSearchRemoteReq;
import com.bibu.user.feign.request.UserTokenRemoteReq;
import com.bibu.user.feign.response.UserAddressRemoteResp;
import com.bibu.user.feign.response.UserInfoRemoteResp;
import com.x.common.ResponseResult;
import org.springframework.cloud.client.circuitbreaker.NoFallbackAvailableException;

import java.util.Map;

/**
 * Author XY
 * Date 2024/4/27 下午1:26
 */
public class UserClientFallback implements UserClient {
    @Override
    public ResponseResult<UserInfoRemoteResp> findUserInfoByRPC(UserTokenRemoteReq req) {
        throw new NoFallbackAvailableException("Boom!", new RuntimeException());
    }

    @Override
    public ResponseResult<UserAddressRemoteResp> findUserAddressByRPC(Long userId) {
        throw new NoFallbackAvailableException("Boom!", new RuntimeException());
    }

    @Override
    public ResponseResult<Map<Long, UserInfoRemoteResp>> findUserInfoMapByRPC(UserBatchSearchRemoteReq req) {
        throw new NoFallbackAvailableException("Boom!", new RuntimeException());
    }
}
