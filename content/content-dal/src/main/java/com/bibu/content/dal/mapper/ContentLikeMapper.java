package com.bibu.content.dal.mapper;

import com.bibu.content.dal.entity.ContentLike;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 内容点赞表 Mapper 接口
 * </p>
 *
 * @author YiXie
 * @since 2024-05-17
 */
@Mapper
public interface ContentLikeMapper extends BaseMapper<ContentLike> {

}
