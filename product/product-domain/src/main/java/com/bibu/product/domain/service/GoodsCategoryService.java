package com.bibu.product.domain.service;

import com.bibu.product.dal.entity.GoodsCategory;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 商品分类表 服务类
 * </p>
 *
 * @author YiXie
 * @since 2024-05-01
 */
public interface GoodsCategoryService extends IService<GoodsCategory> {

    List<GoodsCategory> getGoodsCategoryList(Integer status);
}
