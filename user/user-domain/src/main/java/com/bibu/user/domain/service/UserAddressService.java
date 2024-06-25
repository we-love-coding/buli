package com.bibu.user.domain.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bibu.user.dal.entity.UserAddress;

/**
 * <p>
 * 用户收货地址 服务类
 * </p>
 *
 * @author YiXie
 * @since 2024-05-01
 */
public interface UserAddressService extends IService<UserAddress> {

    UserAddress getUserDefaultAddress(Long userId);
}
