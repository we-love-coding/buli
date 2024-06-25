package com.bibu.user.domain.biz.app.impl;


import com.alibaba.fastjson.JSON;
import com.bibu.user.common.enums.LoginTypeEnum;
import com.bibu.user.common.enums.SignupTypeEnum;
import com.bibu.user.common.enums.UserExceptionEnum;
import com.bibu.user.common.exceptions.UserException;
import com.bibu.user.dal.dto.UserSignupDTO;
import com.bibu.user.dal.entity.UserInfo;
import com.bibu.user.domain.biz.app.UserInfoBizService;
import com.bibu.user.domain.service.UserInfoService;
import com.bibu.user.facade.request.UserLoginReq;
import com.bibu.user.facade.request.UserSignupReq;
import com.bibu.user.facade.response.UserInfoResp;
import com.x.common.context.UserInfoContext;
import com.x.common.context.dto.UserInfoDTO;
import com.x.common.utils.BeanUtils;
import com.x.common.utils.JwtUtils;
import com.x.common.utils.RegexUtils;
import com.x.common.enums.ExceptionEnum;
import org.apache.commons.lang3.StringUtils;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.time.Duration;
import java.util.Objects;

/**
 * Author XY
 * Date 2024/3/30 下午4:58
 */
@Service
public class UserInfoBizServiceImpl implements UserInfoBizService {

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RedissonClient redissonClient;

    @Override
    public String signup(UserSignupReq req) {
        SignupTypeEnum enumByType = SignupTypeEnum.getEnumByType(req.getSignupType());
        if (Objects.isNull(enumByType)) {
            throw new UserException(UserExceptionEnum.SIGNUP_FAIL_EXCEPTION);
        }

        switch (enumByType) {
            case PHONE:
                String phone = req.getPhone();
                if (StringUtils.isBlank(phone)) {
                    throw new UserException(UserExceptionEnum.PHONE_MISS_EXCEPTION);
                }
                if (!RegexUtils.isValidPhoneNumber(phone)) {
                    throw new UserException(UserExceptionEnum.PHONE_EXCEPTION);
                }
                break;
            case EMAIL:
                String email = req.getEmail();
                if (StringUtils.isBlank(email)) {
                    throw new UserException(UserExceptionEnum.EMAIL_MISS_EXCEPTION);
                }
                if (!RegexUtils.isValidEmail(email)) {
                    throw new UserException(UserExceptionEnum.EMAIL_EXCEPTION);
                }
                break;
            default:throw new UserException(UserExceptionEnum.SIGNUP_FAIL_EXCEPTION);
        }

        UserSignupDTO userSignupDTO = BeanUtils.convertBean(req, UserSignupDTO.class);
        userSignupDTO.setPassword(passwordEncoder.encode(req.getPassword()));
        boolean isSuccess = userInfoService.signup(userSignupDTO);
        if (!isSuccess) {
            throw new UserException(UserExceptionEnum.SIGNUP_FAIL_EXCEPTION);
        }
        return "注册成功";
    }

    @Override
    public UserInfoResp login(UserLoginReq req) {
        String username = req.getUsername();
        String password = req.getPassword();
        LoginTypeEnum typeEnum;
        if (RegexUtils.isValidEmail(username)) {
            typeEnum = LoginTypeEnum.EMAIL;
        } else if (RegexUtils.isValidPhoneNumber(username)) {
            typeEnum = LoginTypeEnum.PHONE;
        } else {
            typeEnum = LoginTypeEnum.NICKNAME;
        }

        UserInfo userInfo;
        switch (typeEnum) {
            case EMAIL:
                userInfo = userInfoService.getUserInfoByEmail(username);
                break;
            case PHONE:
                userInfo = userInfoService.getUserInfoByPhone(username);
                break;
            case NICKNAME:
                userInfo = userInfoService.getUserInfoByNickName(username);
                break;
            default:throw new UserException(UserExceptionEnum.LOGIN_FAIL_EXCEPTION);
        }

        String encodedPassword = userInfo.getPassword();

        boolean matches = passwordEncoder.matches(password, encodedPassword);
        if (!matches) {
            throw new UserException(UserExceptionEnum.LOGIN_PASSWORD_EXCEPTION);
        }

        if (passwordEncoder.upgradeEncoding(encodedPassword)) {
            // 再次加密密码
            String encodePassword = passwordEncoder.encode(password);
            userInfo.setPassword(encodePassword);
            userInfoService.updateUserInfo(userInfo);
        }

        String userIdStr = userInfo.getId().toString();
        String token = JwtUtils.generateLongToken(userIdStr);
        String refreshToken = JwtUtils.generateLongToken(userIdStr);
        UserInfoResp userInfoResp = new UserInfoResp();
        userInfoResp.setUserId(userInfo.getId());
        userInfoResp.setNickName(userInfo.getNickName());
        userInfoResp.setAvatarImage(userInfo.getAvatarImage());
        userInfoResp.setGender(userInfo.getGender());
        userInfoResp.setToken(token);
        userInfoResp.setRefreshToken(refreshToken);
        String userIdKey = "userIdKey:" +  userIdStr;
        String refreshTokenKey = "refreshTokenKey:" +  refreshToken;
        redissonClient.getBucket(userIdKey).set(JSON.toJSONString(userInfoResp), Duration.ofDays(60));
        redissonClient.getBucket(refreshTokenKey).set(JSON.toJSONString(Boolean.TRUE), Duration.ofDays(61));
        return userInfoResp;
    }

    @Override
    public void logout() {
        UserInfoDTO user = UserInfoContext.getUser();
        Long userId = user.getUserId();
        boolean isSuccess = userInfoService.deleteUser(userId);
        if (!isSuccess) {
            throw new UserException(ExceptionEnum.NETWORK_ERROR);
        }

        String userIdKey = "userIdKey:" + userId;
        redissonClient.getBucket(userIdKey).delete();
    }

    @Override
    public UserInfoResp findUserInfo() {
        UserInfoDTO user = UserInfoContext.getUser();
        Long userId = user.getUserId();
        UserInfo userInfo = userInfoService.getUserInfoByUserId(userId);
        if (userInfo == null) {
            return null;
        }
        UserInfoResp userInfoResp = new UserInfoResp();
        userInfoResp.setUserId(userInfo.getId());
        userInfoResp.setNickName(userInfo.getNickName());
        userInfoResp.setAvatarImage(userInfo.getAvatarImage());
        userInfoResp.setPhone(userInfo.getPhone());
        userInfoResp.setEmail(userInfo.getEmail());
        userInfoResp.setGender(userInfo.getGender());
        return userInfoResp;
    }

    @Override
    public String findTokenByRefreshToken(String refreshToken) {
        String refreshTokenKey = "refreshTokenKey:" + refreshToken;
        Boolean isBool = (Boolean)redissonClient.getBucket(refreshTokenKey).get();
        if (isBool == null || !isBool) {
            return null;
        }
        String userIdStr = JwtUtils.getDecodedJWT(refreshToken).getSubject();
        String token = JwtUtils.generateLongToken(userIdStr);
        String newRefreshToken = JwtUtils.generateLongToken(userIdStr);
        UserInfo userInfo = userInfoService.getUserInfoByUserId(Long.parseLong(userIdStr));
        if (userInfo == null) {
            return null;
        }

        UserInfoResp userInfoResp = new UserInfoResp();
        userInfoResp.setUserId(userInfo.getId());
        userInfoResp.setNickName(userInfo.getNickName());
        userInfoResp.setAvatarImage(userInfo.getAvatarImage());
        userInfoResp.setGender(userInfo.getGender());
        userInfoResp.setToken(token);
        userInfoResp.setRefreshToken(newRefreshToken);

        String userIdKey = "userIdKey:" +  userIdStr;
        String newRefreshTokenKey = "refreshTokenKey" + newRefreshToken;
        redissonClient.getBucket(userIdKey).set(JSON.toJSONString(userInfoResp), Duration.ofDays(60));
        redissonClient.getBucket(newRefreshTokenKey).set(JSON.toJSONString(Boolean.TRUE), Duration.ofDays(61));
        return token;
    }
}
