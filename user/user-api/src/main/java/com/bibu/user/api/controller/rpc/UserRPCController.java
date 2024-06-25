package com.bibu.user.api.controller.rpc;

import com.bibu.user.domain.biz.rpc.UserRPCService;
import com.bibu.user.facade.request.UserBatchSearchReq;
import com.bibu.user.facade.request.UserTokenReq;
import com.bibu.user.facade.response.UserAddressResp;
import com.bibu.user.facade.response.UserInfoResp;
import com.x.common.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Author XY
 * Date 2024/4/27 下午1:28
 */
@RestController
@RequestMapping("/rpc/user")
public class UserRPCController {

    @Autowired
    private UserRPCService userPRCservice;

    @PostMapping("/findUserInfoByRPC")
    public ResponseResult<UserInfoResp> findUserInfoByRPC(@RequestBody UserTokenReq req) {
        return ResponseResult.success(userPRCservice.getUserInfoByRPC(req));
    }

    @PostMapping("/findUserAddressByRPC")
    public ResponseResult<UserAddressResp> findUserAddressByRPC(@RequestParam("userId") Long userId) {
        return ResponseResult.success(userPRCservice.getUserAddressByRPC(userId));
    }

    @PostMapping("/findUserInfoMapByRPC")
    public ResponseResult<Map<Long, UserInfoResp>> findUserInfoMapByRPC(@RequestBody UserBatchSearchReq req) {
        return ResponseResult.success(userPRCservice.findUserInfoMapByRPC(req));
    }
}
