package com.bibu.product.dal.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bibu.product.dal.entity.Goods;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 商品表 Mapper 接口
 * </p>
 *
 * @author YiXie
 * @since 2024-05-01
 */
@Mapper
public interface GoodsMapper extends BaseMapper<Goods> {

}
