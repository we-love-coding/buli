package com.bibu.user.api.controller.app;


import com.bibu.user.domain.biz.app.UserInfoBizService;
import com.bibu.user.facade.request.UserLoginReq;
import com.bibu.user.facade.request.UserSignupReq;
import com.bibu.user.facade.response.UserInfoResp;
import com.x.common.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author YiXie
 * @since 2024-05-01
 */
@RestController
@RequestMapping("/app/userInfo")
public class UserInfoController {

    @Autowired
    private UserInfoBizService userInfoBizService;

    @PostMapping("/signup")
    public ResponseResult<String> signup(@RequestBody UserSignupReq req) {
        return ResponseResult.success(userInfoBizService.signup(req));
    }

    @PostMapping("/login")
    public ResponseResult<UserInfoResp> login(@RequestBody UserLoginReq req) {
        return ResponseResult.success(userInfoBizService.login(req));
    }

    @PostMapping("/logout")
    public ResponseResult<Void> logout() {
        userInfoBizService.logout();
        return ResponseResult.success();
    }

    @PostMapping("/findUserInfo")
    public ResponseResult<UserInfoResp> findUserInfo() {
        return ResponseResult.success(userInfoBizService.findUserInfo());

    }

}