package com.bibu.content.domain.service;

import com.bibu.content.dal.entity.ContentRecommendGoods;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bibu.content.dal.entity.ContentRecommendGoods;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author YiXie
 * @since 2024-05-17
 */
public interface ContentRecommendGoodsService extends IService<ContentRecommendGoods> {

    List<ContentRecommendGoods> getRecommendGoodsList(Long contentId);
}
