package com.bibu.user.domain.biz.rpc;

import com.bibu.user.facade.request.UserBatchSearchReq;
import com.bibu.user.facade.request.UserTokenReq;
import com.bibu.user.facade.response.UserAddressResp;
import com.bibu.user.facade.response.UserInfoResp;

import java.util.Map;

/**
 * Author XY
 * Date 2024/4/27 下午1:49
 */
public interface UserRPCService {

    UserInfoResp getUserInfoByRPC(UserTokenReq req);

    UserAddressResp getUserAddressByRPC(Long userId);

    Map<Long, UserInfoResp> findUserInfoMapByRPC(UserBatchSearchReq req);
}
