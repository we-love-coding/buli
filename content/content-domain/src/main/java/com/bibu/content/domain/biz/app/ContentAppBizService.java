package com.bibu.content.domain.biz.app;

import com.bibu.content.facade.request.ContentPublishReq;
import com.bibu.content.facade.request.ContentRecommendGoodsReq;
import com.bibu.content.facade.request.ContentReq;
import com.bibu.content.facade.request.ContentSearchReq;
import com.bibu.content.facade.response.ContentInfoResp;

/**
 * Author XY
 * Date 2024/5/17 下午6:52
 */
public interface ContentAppBizService {

    ContentInfoResp findContent(ContentSearchReq req);

    Boolean publishContent(ContentPublishReq req);

    Boolean offerGoods(ContentRecommendGoodsReq req);

    Boolean likeContent(ContentReq req);

    Boolean viewContent(ContentReq req);

    Long likeCount(ContentReq req);

    Long viewCount(ContentReq req);
}
