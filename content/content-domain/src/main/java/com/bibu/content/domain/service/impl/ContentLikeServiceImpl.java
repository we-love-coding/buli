package com.bibu.content.domain.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bibu.content.dal.entity.ContentLike;
import com.bibu.content.dal.mapper.ContentLikeMapper;
import com.bibu.content.domain.service.ContentLikeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.x.common.enums.YesOrNoEnum;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 内容点赞表 服务实现类
 * </p>
 *
 * @author YiXie
 * @since 2024-05-17
 */
@Service
public class ContentLikeServiceImpl extends ServiceImpl<ContentLikeMapper, ContentLike> implements ContentLikeService {

    @Override
    public long countLikeByContentId(Long contentId) {
        QueryWrapper<ContentLike> wrapper = new QueryWrapper<>();
        wrapper.eq("content_id", contentId);
        wrapper.eq("is_delete", YesOrNoEnum.NO.getStatus());
        return count(wrapper);
    }

    @Override
    public long countLikeByUserId(Long contentId, Long userId) {
        QueryWrapper<ContentLike> wrapper = new QueryWrapper<>();
        wrapper.eq("content_id", contentId);
        wrapper.eq("user_id", userId);
        wrapper.eq("is_delete", YesOrNoEnum.NO.getStatus());
        return count(wrapper);
    }

    @Override
    public ContentLike getContentLike(Long contentId, Long userId) {
        QueryWrapper<ContentLike> wrapper = new QueryWrapper<>();
        wrapper.eq("content_id", contentId);
        wrapper.eq("user_id", userId);
        wrapper.eq("is_delete", YesOrNoEnum.NO.getStatus());
        wrapper.last("limit 1");
        return getOne(wrapper);
    }
}
