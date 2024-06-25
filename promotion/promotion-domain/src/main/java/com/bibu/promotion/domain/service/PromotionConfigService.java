package com.bibu.promotion.domain.service;

import com.bibu.promotion.dal.entity.PromotionConfig;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bibu.promotion.dal.entity.PromotionConfig;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author YiXie
 * @since 2024-05-08
 */
public interface PromotionConfigService extends IService<PromotionConfig> {

    List<PromotionConfig> getPromotionConfigList(Long pid);

}
