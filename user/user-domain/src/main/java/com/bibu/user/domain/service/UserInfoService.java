package com.bibu.user.domain.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bibu.user.dal.dto.UserSignupDTO;
import com.bibu.user.dal.entity.UserInfo;

import java.util.List;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author YiXie
 * @since 2024-05-01
 */
public interface UserInfoService extends IService<UserInfo> {

    boolean signup(UserSignupDTO signupDTO);

    UserInfo getUserInfoByNickName(String nickName);

    UserInfo getUserInfoByPhone(String phone);

    UserInfo getUserInfoByEmail(String email);

    void updateUserInfo(UserInfo userInfo);

    boolean deleteUser(Long userId);

    UserInfo getUserInfoByUserId(Long userId);

    List<UserInfo> getUserInfoList(List<Long> userIdList);
}
