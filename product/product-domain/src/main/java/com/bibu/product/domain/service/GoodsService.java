package com.bibu.product.domain.service;

import com.bibu.product.dal.entity.Goods;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 商品表 服务类
 * </p>
 *
 * @author YiXie
 * @since 2024-05-01
 */
public interface GoodsService extends IService<Goods> {


    List<Goods> getGoodsList(List<Long> idList);
}
