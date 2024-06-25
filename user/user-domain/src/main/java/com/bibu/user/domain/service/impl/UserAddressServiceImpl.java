package com.bibu.user.domain.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bibu.user.dal.entity.UserAddress;
import com.bibu.user.dal.mapper.UserAddressMapper;
import com.bibu.user.domain.service.UserAddressService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.x.common.enums.YesOrNoEnum;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户收货地址 服务实现类
 * </p>
 *
 * @author YiXie
 * @since 2024-05-01
 */
@Service
public class UserAddressServiceImpl extends ServiceImpl<UserAddressMapper, UserAddress> implements UserAddressService {

    @Override
    public UserAddress getUserDefaultAddress(Long userId) {
        QueryWrapper<UserAddress> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", userId);
        wrapper.eq("is_default", YesOrNoEnum.YES.getStatus());
        wrapper.last("limit 1");
        return getOne(wrapper);
    }
}
