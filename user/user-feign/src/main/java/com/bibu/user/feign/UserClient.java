package com.bibu.user.feign;

import com.bibu.user.feign.request.UserBatchSearchRemoteReq;
import com.bibu.user.feign.request.UserTokenRemoteReq;
import com.bibu.user.feign.response.UserAddressRemoteResp;
import com.bibu.user.feign.response.UserInfoRemoteResp;
import com.x.common.ResponseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * Author XY
 * Date 2024/4/27 下午1:23
 */
@FeignClient(name = "user", fallbackFactory = UserFallbackFactory.class)
public interface UserClient {

    @PostMapping("/rpc/user/findUserInfoByRPC")
    ResponseResult<UserInfoRemoteResp> findUserInfoByRPC(@RequestBody UserTokenRemoteReq req);

    @PostMapping("/rpc/user/findUserAddressByRPC")
    ResponseResult<UserAddressRemoteResp> findUserAddressByRPC(@RequestParam("userId") Long userId);

    @PostMapping("/rpc/user/findUserInfoMapByRPC")
    ResponseResult<Map<Long, UserInfoRemoteResp>> findUserInfoMapByRPC(@RequestBody UserBatchSearchRemoteReq req);
}
