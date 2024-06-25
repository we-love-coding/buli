package com.bibu.product.domain.service.impl;

import com.bibu.product.dal.entity.Goods;
import com.bibu.product.dal.mapper.GoodsMapper;
import com.bibu.product.domain.service.GoodsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 商品表 服务实现类
 * </p>
 *
 * @author YiXie
 * @since 2024-05-01
 */
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements GoodsService {

    @Override
    public List<Goods> getGoodsList(List<Long> idList) {
        return listByIds(idList);
    }
}
