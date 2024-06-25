package com.bibu.user.domain.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bibu.user.dal.dto.UserSignupDTO;
import com.bibu.user.dal.entity.UserInfo;
import com.bibu.user.dal.mapper.UserInfoMapper;
import com.bibu.user.domain.service.UserInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.x.common.enums.YesOrNoEnum;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author YiXie
 * @since 2024-05-01
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {

    @Override
    public boolean signup(UserSignupDTO signupDTO) {
        Date date = new Date();
        UserInfo userInfo = new UserInfo();
        userInfo.setAvatarImage(signupDTO.getAvatarImage());
        userInfo.setEmail(signupDTO.getEmail());
        userInfo.setPhone(signupDTO.getPhone());
        userInfo.setNickName(signupDTO.getNickName());
        userInfo.setCreateTime(date);
        userInfo.setSignupTime(date);
        userInfo.setUpdateTime(date);
        userInfo.setPassword(signupDTO.getPassword());
        userInfo.setUpdater(signupDTO.getNickName());
        userInfo.setCreator(signupDTO.getNickName());
        userInfo.setGender(signupDTO.getGender());
        return save(userInfo);
    }

    @Override
    public UserInfo getUserInfoByNickName(String nickName) {
        QueryWrapper<UserInfo> wrapper = new QueryWrapper<UserInfo>();
        wrapper.eq("nick_name", nickName);
        wrapper.eq("is_delete", YesOrNoEnum.NO.getStatus());
        wrapper.last("limit 1");
        return getOne(wrapper);
    }

    @Override
    public UserInfo getUserInfoByPhone(String phone) {
        QueryWrapper<UserInfo> wrapper = new QueryWrapper<UserInfo>();
        wrapper.eq("phone", phone);
        wrapper.eq("is_delete", YesOrNoEnum.NO.getStatus());
        wrapper.last("limit 1");
        return getOne(wrapper);
    }

    @Override
    public UserInfo getUserInfoByEmail(String email) {
        QueryWrapper<UserInfo> wrapper = new QueryWrapper<UserInfo>();
        wrapper.eq("email", email);
        wrapper.eq("is_delete", YesOrNoEnum.NO.getStatus());
        wrapper.last("limit 1");
        return getOne(wrapper);
    }

    @Override
    public void updateUserInfo(UserInfo userInfo) {
        updateById(userInfo);
    }

    @Override
    public boolean deleteUser(Long userId) {
        Date date = new Date();
        UserInfo userInfo = new UserInfo();
        userInfo.setId(userId);
        userInfo.setIsDelete(YesOrNoEnum.YES.getStatus());
        userInfo.setUpdateTime(date);
        userInfo.setUpdater(userId.toString());
        return updateById(userInfo);
    }

    @Override
    public UserInfo getUserInfoByUserId(Long userId) {
        return getById(userId);
    }

    @Override
    public List<UserInfo> getUserInfoList(List<Long> userIdList) {
        QueryWrapper<UserInfo> wrapper = new QueryWrapper<>();
        wrapper.eq("id", userIdList);
        wrapper.eq("is_delete", YesOrNoEnum.NO.getStatus());
        return list(wrapper);
    }
}
