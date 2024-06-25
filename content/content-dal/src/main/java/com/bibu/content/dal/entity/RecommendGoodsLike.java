package com.bibu.content.dal.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 推荐商品点赞表
 * </p>
 *
 * @author YiXie
 * @since 2024-05-17
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("recommend_goods_like")
public class RecommendGoodsLike implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Long userId;

    private Long recommedGoodsId;

    private LocalDateTime updateTime;

    private LocalDateTime createTime;

    private String updater;

    private String creator;

    private Integer isDelete;


}
