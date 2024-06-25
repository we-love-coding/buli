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
 * 需求详细内容
 * </p>
 *
 * @author YiXie
 * @since 2024-05-17
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("content")
public class Content implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String content;

    private Long userId;

    private Integer likeNum;

    private Integer viewNum;

    private Integer status;

    private Date deployTime;

    private Integer isSensible;

    private Date createTime;

    private Date updateTime;

    private String updater;

    private String creator;

    private Integer isDelete;


}
