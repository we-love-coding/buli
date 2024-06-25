package com.bibu.product.domain.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bibu.product.dal.entity.GoodsCategory;
import com.bibu.product.dal.mapper.GoodsCategoryMapper;
import com.bibu.product.domain.service.GoodsCategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.x.common.enums.YesOrNoEnum;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 商品分类表 服务实现类
 * </p>
 *
 * @author YiXie
 * @since 2024-05-01
 */
@Service
public class GoodsCategoryServiceImpl extends ServiceImpl<GoodsCategoryMapper, GoodsCategory> implements GoodsCategoryService {

    @Override
    public List<GoodsCategory> getGoodsCategoryList(Integer status) {
        QueryWrapper<GoodsCategory> wrapper = new QueryWrapper<>();
        if (status != null) {
            wrapper.eq("status", status);
        }
        wrapper.eq("is_delete", YesOrNoEnum.NO.getStatus());
        return list(wrapper);
    }
}
