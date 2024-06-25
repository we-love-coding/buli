package com.bibu.content.facade.response;

import com.bibu.content.facade.parent.ParentResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

/**
 * Author XY
 * Date 2024/5/17 下午7:22
 */
@Setter
@Getter
public class ContentInfoResp extends ParentResponse {

    private String content;

    private Long likeNum;

    private Long viewNum;

    private Date deployTime;

    private boolean like;

    private boolean canEdit;

    private List<ContentRecommendGoodsInfoResp> recommendGoodsList;

}
