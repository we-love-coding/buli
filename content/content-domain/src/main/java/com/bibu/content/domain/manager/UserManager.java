package com.bibu.content.domain.manager;

import com.bibu.content.common.exceptions.ContentException;
import com.bibu.user.facade.response.UserAddressResp;
import com.bibu.user.facade.response.UserInfoResp;
import com.bibu.user.feign.UserClient;
import com.bibu.user.feign.request.UserBatchSearchRemoteReq;
import com.bibu.user.feign.request.UserTokenRemoteReq;
import com.bibu.user.feign.response.UserAddressRemoteResp;
import com.bibu.user.feign.response.UserInfoRemoteResp;
import com.google.common.collect.Maps;
import com.x.common.ResponseResult;
import com.x.common.enums.ExceptionEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Author XY
 * Date 2024/5/1 下午10:19
 */
@Component
public class UserManager {

    @Autowired
    private UserClient userClient;

    public UserAddressResp findUserAddressByRPC(Long userId) {
        ResponseResult<UserAddressRemoteResp> result = userClient.findUserAddressByRPC(userId);
        if (result == null || !result.getSuccess() || result.getData() == null) {
            throw new ContentException(ExceptionEnum.NETWORK_ERROR);
        }
        return result.getData();
    }

    public UserInfoResp findUserInfoByRPC(UserTokenRemoteReq req) {
        ResponseResult<UserInfoRemoteResp> result = userClient.findUserInfoByRPC(req);
        if (result == null || !result.getSuccess() || result.getData() == null) {
            throw new ContentException(ExceptionEnum.NETWORK_ERROR);
        }
        return result.getData();
    }

    public Map<Long, UserInfoResp> findUserInfoMapByRPC(UserBatchSearchRemoteReq req) {
        ResponseResult<Map<Long, UserInfoRemoteResp>> result = userClient.findUserInfoMapByRPC(req);
        if (result == null || !result.getSuccess() || result.getData() == null) {
            throw new ContentException(ExceptionEnum.NETWORK_ERROR);
        }

        Map<Long, UserInfoRemoteResp> data = result.getData();
        Map<Long, UserInfoResp> map = Maps.newHashMapWithExpectedSize(data.size());
        for (Map.Entry<Long, UserInfoRemoteResp> entry : data.entrySet()) {
            map.put(entry.getKey(), entry.getValue());
        }
        return map;
    }

}
