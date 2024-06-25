package com.bibu.promotion.domain.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bibu.promotion.dal.entity.PromotionManagement;
import com.bibu.promotion.dal.mapper.PromotionManagementMapper;
import com.bibu.promotion.domain.service.PromotionManagementService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.x.common.enums.YesOrNoEnum;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 促销活动表 服务实现类
 * </p>
 *
 * @author YiXie
 * @since 2024-05-08
 */
@Service
public class PromotionManagementServiceImpl extends ServiceImpl<PromotionManagementMapper, PromotionManagement> implements PromotionManagementService {

    @Override
    public PromotionManagement getPromotionManagement(String pCode) {
        QueryWrapper<PromotionManagement> wrapper = new QueryWrapper<>();
        wrapper.eq("code", pCode);
        wrapper.eq("is_delete", YesOrNoEnum.NO.getStatus());
        wrapper.last("limit 1");
        return getOne(wrapper);
    }
}
