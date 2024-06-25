package com.bibu.user.domain.biz.app;

import com.bibu.user.facade.request.UserLoginReq;
import com.bibu.user.facade.request.UserSignupReq;
import com.bibu.user.facade.response.UserInfoResp;

/**
 * Author XY
 * Date 2024/3/30 下午4:58
 */
public interface UserInfoBizService {
    String signup(UserSignupReq req);

    UserInfoResp login(UserLoginReq req);

    void logout();

    UserInfoResp findUserInfo();

    String findTokenByRefreshToken(String refreshToken);
}
