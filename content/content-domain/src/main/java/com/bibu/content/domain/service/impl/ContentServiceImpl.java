package com.bibu.content.domain.service.impl;

import com.bibu.content.dal.entity.Content;
import com.bibu.content.dal.mapper.ContentMapper;
import com.bibu.content.domain.service.ContentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 需求详细内容 服务实现类
 * </p>
 *
 * @author YiXie
 * @since 2024-05-17
 */
@Service
public class ContentServiceImpl extends ServiceImpl<ContentMapper, Content> implements ContentService {

}
