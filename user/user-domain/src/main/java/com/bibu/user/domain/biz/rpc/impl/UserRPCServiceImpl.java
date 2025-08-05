package com.bibu.user.domain.biz.rpc.impl;

import com.alibaba.fastjson.JSON;
import com.bibu.user.common.enums.UserExceptionEnum;
import com.bibu.user.common.exceptions.UserException;
import com.bibu.user.dal.entity.UserInfo;
import com.bibu.user.domain.biz.rpc.UserRPCService;
import com.bibu.user.dal.entity.UserAddress;
import com.bibu.user.domain.redis.RedisClient;
import com.bibu.user.domain.service.UserInfoService;
import com.bibu.user.facade.request.UserBatchSearchReq;
import com.bibu.user.facade.request.UserTokenReq;
import com.bibu.user.facade.response.UserAddressResp;
import com.bibu.user.facade.response.UserInfoResp;
import com.bibu.user.domain.service.UserAddressService;
import com.google.common.collect.Maps;
import com.x.common.utils.BeanUtils;
import com.x.common.utils.JwtUtils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Author XY
 * Date 2024/4/27 下午1:50
 */
@Service
public class UserRPCServiceImpl implements UserRPCService {

    @Autowired
    private RedisClient redisClient;
    @Autowired
    private UserAddressService userAddressService;
    @Autowired
    private UserInfoService userInfoService;

    @Override
    public UserInfoResp getUserInfoByRPC(UserTokenReq req) {
        String token = req.getToken();
        String userIdStr = JwtUtils.getDecodedJWT(token).getSubject();
        String userIdKey = "userIdKey:" +  userIdStr;
        String json = redisClient.get(userIdKey);
        UserInfoResp userInfoResp = JSON.parseObject(json, UserInfoResp.class);
        if (Objects.isNull(userInfoResp)) {
            throw new UserException(UserExceptionEnum.USER_NOT_EXIST);
        }
        return userInfoResp;
    }

    @Override
    public UserAddressResp getUserAddressByRPC(Long userId) {
        UserAddress userAddress = userAddressService.getUserDefaultAddress(userId);
        if (Objects.isNull(userAddress)) {
            return null;
        }
        return BeanUtils.convertBean(userAddress, UserAddressResp.class);
    }

    @Override
    public Map<Long, UserInfoResp> findUserInfoMapByRPC(UserBatchSearchReq req) {
        List<Long> userIdList = req.getUserIdList();
        List<UserInfo> userInfoList = userInfoService.getUserInfoList(userIdList);
        if (CollectionUtils.isEmpty(userInfoList)) {
            return null;
        }
        Map<Long, UserInfoResp> map = Maps.newHashMapWithExpectedSize(userInfoList.size());
        for (UserInfo userInfo : userInfoList) {
            UserInfoResp userInfoResp = BeanUtils.convertBean(userInfo, UserInfoResp.class);
            userInfoResp.setUserId(userInfo.getId());
            map.put(userInfo.getId(), userInfoResp);
        }
        return map;
    }
}
