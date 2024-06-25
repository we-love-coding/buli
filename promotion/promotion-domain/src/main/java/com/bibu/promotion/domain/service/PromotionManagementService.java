package com.bibu.promotion.domain.service;

import com.bibu.promotion.dal.entity.PromotionManagement;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 促销活动表 服务类
 * </p>
 *
 * @author YiXie
 * @since 2024-05-08
 */
public interface PromotionManagementService extends IService<PromotionManagement> {

    PromotionManagement getPromotionManagement(String pCode);
}
