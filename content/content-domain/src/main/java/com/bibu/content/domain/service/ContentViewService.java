package com.bibu.content.domain.service;

import com.bibu.content.dal.entity.ContentView;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author YiXie
 * @since 2024-05-20
 */
public interface ContentViewService extends IService<ContentView> {

    long countView(Long contentId);
}
