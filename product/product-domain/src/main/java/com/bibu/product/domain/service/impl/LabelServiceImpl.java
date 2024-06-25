package com.bibu.product.domain.service.impl;

import com.bibu.product.dal.entity.Label;
import com.bibu.product.dal.mapper.LabelMapper;
import com.bibu.product.domain.service.LabelService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 标签表 服务实现类
 * </p>
 *
 * @author YiXie
 * @since 2024-05-01
 */
@Service
public class LabelServiceImpl extends ServiceImpl<LabelMapper, Label> implements LabelService {

}
