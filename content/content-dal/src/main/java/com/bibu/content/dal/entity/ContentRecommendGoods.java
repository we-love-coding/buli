package com.bibu.content.dal.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author YiXie
 * @since 2024-05-17
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("content_recommend_goods")
public class ContentRecommendGoods implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Long userId;

    private Long contentId;

    private Long goodsId;

    private Integer likeNum;

    private Integer status;

    private Date updateTime;

    private Date createTime;

    private String updater;

    private String creator;

    private Integer isDelete;


}
