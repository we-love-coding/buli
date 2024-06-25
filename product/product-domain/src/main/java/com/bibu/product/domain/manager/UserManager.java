package com.bibu.product.domain.manager;

import com.bibu.product.common.exceptions.ProductException;
import com.bibu.user.facade.response.UserInfoResp;
import com.bibu.user.feign.UserClient;
import com.bibu.user.feign.request.UserTokenRemoteReq;
import com.bibu.user.feign.response.UserInfoRemoteResp;
import com.x.common.ResponseResult;
import com.x.common.enums.ExceptionEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Author XY
 * Date 2024/5/1 下午10:19
 */
@Component
public class UserManager {

    @Autowired
    private UserClient userClient;

    public UserInfoResp findUserInfoByRPC(UserTokenRemoteReq req) {
        ResponseResult<UserInfoRemoteResp> result = userClient.findUserInfoByRPC(req);
        if (result == null || !result.getSuccess() || result.getData() == null) {
            throw new ProductException(ExceptionEnum.NETWORK_ERROR);
        }
        return result.getData();
    }

}
