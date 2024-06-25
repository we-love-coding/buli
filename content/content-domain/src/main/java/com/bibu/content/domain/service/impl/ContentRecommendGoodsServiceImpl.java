package com.bibu.content.domain.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bibu.content.dal.entity.ContentRecommendGoods;
import com.bibu.content.dal.mapper.ContentRecommendGoodsMapper;
import com.bibu.content.domain.service.ContentRecommendGoodsService;
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
 * @since 2024-05-17
 */
@Service
public class ContentRecommendGoodsServiceImpl extends ServiceImpl<ContentRecommendGoodsMapper, ContentRecommendGoods> implements ContentRecommendGoodsService {

    @Override
    public List<ContentRecommendGoods> getRecommendGoodsList(Long contentId) {
        QueryWrapper<ContentRecommendGoods> wrapper = new QueryWrapper<>();
        wrapper.eq("content_id", contentId);
        wrapper.eq("status", YesOrNoEnum.YES.getStatus());
        wrapper.eq("is_delete", YesOrNoEnum.NO.getStatus());
        return list(wrapper);
    }
}
