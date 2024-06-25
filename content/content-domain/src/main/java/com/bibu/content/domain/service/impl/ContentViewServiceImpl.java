package com.bibu.content.domain.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bibu.content.dal.entity.ContentView;
import com.bibu.content.dal.mapper.ContentViewMapper;
import com.bibu.content.domain.service.ContentViewService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author YiXie
 * @since 2024-05-20
 */
@Service
public class ContentViewServiceImpl extends ServiceImpl<ContentViewMapper, ContentView> implements ContentViewService {

    @Override
    public long countView(Long contentId) {
        QueryWrapper<ContentView> wrapper = new QueryWrapper<>();
        wrapper.eq("content_id", contentId);
        return count(wrapper);
    }
}
