package com.bibu.content.domain.service;

import com.bibu.content.dal.entity.ContentLike;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 内容点赞表 服务类
 * </p>
 *
 * @author YiXie
 * @since 2024-05-17
 */
public interface ContentLikeService extends IService<ContentLike> {

    long countLikeByContentId(Long contentId);

    long countLikeByUserId(Long contentId, Long userId);

    ContentLike getContentLike(Long contentId, Long userId);

}
