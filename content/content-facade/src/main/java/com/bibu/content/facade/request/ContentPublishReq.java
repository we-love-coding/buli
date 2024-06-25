package com.bibu.content.facade.request;

import com.bibu.content.facade.parent.ParentRequest;
import com.bibu.content.facade.response.ContentRecommendGoodsInfoResp;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

/**
 * Author XY
 * Date 2024/5/20 上午11:59
 */
@Setter
@Getter
public class ContentPublishReq extends ParentRequest {

    private String content;

    private Integer status;
}
