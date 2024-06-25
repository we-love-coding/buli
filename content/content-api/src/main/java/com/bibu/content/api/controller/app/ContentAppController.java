package com.bibu.content.api.controller.app;


import com.bibu.content.domain.biz.app.ContentAppBizService;
import com.bibu.content.facade.request.ContentPublishReq;
import com.bibu.content.facade.request.ContentRecommendGoodsReq;
import com.bibu.content.facade.request.ContentReq;
import com.bibu.content.facade.request.ContentSearchReq;
import com.bibu.content.facade.response.ContentInfoResp;
import com.x.common.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 需求详细内容 前端控制器
 * </p>
 *
 * @author YiXie
 * @since 2024-05-17
 */
@RestController
@RequestMapping("/app/content")
public class ContentAppController {

    @Autowired
    private ContentAppBizService contentAppBizService;

    @PostMapping("/findContent")
    public ResponseResult<ContentInfoResp> findContent(@RequestBody ContentSearchReq req) {
        return ResponseResult.success(contentAppBizService.findContent(req));
    }

    @PostMapping("/publishContent")
    public ResponseResult<Boolean> publishContent(@RequestBody ContentPublishReq req) {
        return ResponseResult.success(contentAppBizService.publishContent(req));
    }

    @PostMapping("/offerGoods")
    public ResponseResult<Boolean> offerGoods(@RequestBody ContentRecommendGoodsReq req) {
        return ResponseResult.success(contentAppBizService.offerGoods(req));
    }

    @PostMapping("/likeContent")
    public ResponseResult<Boolean> likeContent(@RequestBody ContentReq req) {
        return ResponseResult.success(contentAppBizService.likeContent(req));
    }

    @PostMapping("/viewContent")
    public ResponseResult<Boolean> viewContent(@RequestBody ContentReq req) {
        return ResponseResult.success(contentAppBizService.viewContent(req));
    }

    @PostMapping("/likeCount")
    public ResponseResult<Long> likeCount(@RequestBody ContentReq req) {
        return ResponseResult.success(contentAppBizService.likeCount(req));
    }

    @PostMapping("/viewCount")
    public ResponseResult<Long> viewCount(@RequestBody ContentReq req) {
        return ResponseResult.success(contentAppBizService.viewCount(req));
    }
}

