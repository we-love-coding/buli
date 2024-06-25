package com.bibu.promotion.domain.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bibu.promotion.dal.entity.PromotionConfig;
import com.bibu.promotion.dal.mapper.PromotionConfigMapper;
import com.bibu.promotion.domain.service.PromotionConfigService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.x.common.enums.YesOrNoEnum;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author YiXie
 * @since 2024-05-08
 */
@Service
public class PromotionConfigServiceImpl extends ServiceImpl<PromotionConfigMapper, PromotionConfig> implements PromotionConfigService {

    @Override
    public List<PromotionConfig> getPromotionConfigList(Long pid) {
        QueryWrapper<PromotionConfig> wrapper = new QueryWrapper<>();
        wrapper.eq("promotion_id", pid);
        wrapper.eq("is_delete", YesOrNoEnum.NO.getStatus());
        return list(wrapper);
    }
}
