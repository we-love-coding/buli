package com.bibu.user.dal.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bibu.user.dal.entity.UserInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author YiXie
 * @since 2024-05-01
 */
@Mapper
public interface UserInfoMapper extends BaseMapper<UserInfo> {

}
