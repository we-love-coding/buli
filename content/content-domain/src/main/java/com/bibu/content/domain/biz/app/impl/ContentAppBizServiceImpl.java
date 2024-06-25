package com.bibu.content.domain.biz.app.impl;

import com.bibu.content.common.enums.ContentExceptionEnum;
import com.bibu.content.common.exceptions.ContentException;
import com.bibu.content.dal.entity.Content;
import com.bibu.content.dal.entity.ContentLike;
import com.bibu.content.dal.entity.ContentRecommendGoods;
import com.bibu.content.dal.entity.ContentView;
import com.bibu.content.domain.biz.app.ContentAppBizService;
import com.bibu.content.domain.biz.app.ContentLikeAppBizService;
import com.bibu.content.domain.biz.app.ContentViewAppBizService;
import com.bibu.content.domain.manager.ProductManager;
import com.bibu.content.domain.manager.UserManager;
import com.bibu.content.domain.service.ContentLikeService;
import com.bibu.content.domain.service.ContentRecommendGoodsService;
import com.bibu.content.domain.service.ContentService;
import com.bibu.content.domain.service.ContentViewService;
import com.bibu.content.facade.request.ContentPublishReq;
import com.bibu.content.facade.request.ContentRecommendGoodsReq;
import com.bibu.content.facade.request.ContentReq;
import com.bibu.content.facade.request.ContentSearchReq;
import com.bibu.content.facade.response.ContentInfoResp;
import com.bibu.content.facade.response.ContentRecommendGoodsInfoResp;
import com.bibu.product.feign.request.GoodsQueryRemoteReq;
import com.bibu.product.feign.response.GoodsRemoteResp;
import com.bibu.user.facade.response.UserInfoResp;
import com.bibu.user.feign.request.UserBatchSearchRemoteReq;
import com.bibu.user.feign.response.UserInfoRemoteResp;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.x.common.context.UserInfoContext;
import com.x.common.context.dto.UserInfoDTO;
import com.x.common.utils.BeanUtils;
import com.x.common.enums.YesOrNoEnum;
import org.apache.commons.collections.CollectionUtils;
import org.redisson.api.RAtomicLong;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Author XY
 * Date 2024/5/17 下午6:53
 */
@Service
public class ContentAppBizServiceImpl implements ContentAppBizService {

    @Autowired
    private ContentService contentService;
    @Autowired
    private ContentLikeService contentLikeService;
    @Autowired
    private ContentViewService contentViewService;
    @Autowired
    private ContentRecommendGoodsService contentRecommendGoodsService;

    @Autowired
    private ProductManager productManager;
    @Autowired
    private UserManager userManager;

    @Autowired
    private RedissonClient redissonClient;

    @Autowired
    private ContentLikeAppBizService contentLikeAppBizService;
    @Autowired
    private ContentViewAppBizService contentViewAppBizService;

    @Override
    public ContentInfoResp findContent(ContentSearchReq req) {
        boolean isLike = false;
        boolean canEdit = false;
        UserInfoDTO user = UserInfoContext.getUser();
        Content content = contentService.getById(req.getId());
        if (Objects.isNull(content)) {
            return null;
        }

        Long contentId = content.getId();
        long likeNum = this.countLike(contentId).get();
        RAtomicLong viewNumAtomic = this.countView(contentId);
        long viewNum = viewNumAtomic.get();
        if (user != null) {
            isLike = contentLikeAppBizService.isLike(contentId, user.getUserId());
            canEdit = content.getUserId().compareTo(user.getUserId()) == 0;
            viewNum = viewNumAtomic.incrementAndGet();
        }

        List<ContentRecommendGoodsInfoResp> contentRecommendGoodsList = Lists.newArrayList();
        List<ContentRecommendGoods> recommendGoodsList = contentRecommendGoodsService.getRecommendGoodsList(contentId);
        if (CollectionUtils.isNotEmpty(recommendGoodsList)) {
            Map<Long, Long> map = Maps.newHashMap();
            List<Long> goodsIdList = Lists.newArrayList();
            List<Long> userIdList = Lists.newArrayList();
            for (ContentRecommendGoods contentRecommendGoods : recommendGoodsList) {
                Long goodsId = contentRecommendGoods.getGoodsId();
                Long userId = contentRecommendGoods.getUserId();
                map.put(goodsId, userId);
                goodsIdList.add(goodsId);
                userIdList.add(userId);
            }

            goodsIdList = goodsIdList.stream().sorted(Comparator.naturalOrder()).collect(Collectors.toList());
            userIdList = userIdList.stream().sorted(Comparator.naturalOrder()).collect(Collectors.toList());

            UserBatchSearchRemoteReq userBatchSearchRemoteReq = new UserBatchSearchRemoteReq();
            userBatchSearchRemoteReq.setUserIdList(userIdList);
            Map<Long, UserInfoResp> userInfoMap = userManager.findUserInfoMapByRPC(userBatchSearchRemoteReq);

            GoodsQueryRemoteReq queryRemoteReq = new GoodsQueryRemoteReq();
            queryRemoteReq.setGoodsIdList(goodsIdList);
            List<GoodsRemoteResp> goodsList = productManager.findGoodsListByRPC(queryRemoteReq);
            for (GoodsRemoteResp goodsRemoteResp : goodsList) {
                Long userId = map.get(goodsRemoteResp.getId());
                if (userId == null) {
                    continue;
                }
                UserInfoResp userInfoResp = userInfoMap.get(userId);
                if (userInfoResp == null) {
                    continue;
                }
                ContentRecommendGoodsInfoResp contentRecommendGoodsInfoResp = BeanUtils.convertBean(goodsRemoteResp, ContentRecommendGoodsInfoResp.class);
                contentRecommendGoodsInfoResp.setUserId(userId);
                contentRecommendGoodsInfoResp.setNickName(userInfoResp.getNickName());
                userInfoResp.setAvatarImage(userInfoResp.getEmail());
                contentRecommendGoodsList.add(contentRecommendGoodsInfoResp);
            }
        }

        ContentInfoResp contentInfoResp = new ContentInfoResp();
        contentInfoResp.setContent(content.getContent());
        contentInfoResp.setDeployTime(content.getDeployTime());
        contentInfoResp.setLike(isLike);
        contentInfoResp.setLikeNum(likeNum);
        contentInfoResp.setViewNum(viewNum);
        contentInfoResp.setRecommendGoodsList(contentRecommendGoodsList);
        contentInfoResp.setCanEdit(canEdit);
        return contentInfoResp;
    }

    @Override
    public Boolean publishContent(ContentPublishReq req) {
        Date date = new Date();
        UserInfoDTO user = UserInfoContext.getUser();
        Content content = new Content();
        content.setContent(req.getContent());
        content.setUserId(user.getUserId());
        content.setDeployTime(date);
        content.setCreateTime(date);
        content.setUpdateTime(date);
        content.setStatus(req.getStatus());
        content.setIsSensible(YesOrNoEnum.NO.getStatus());
        content.setCreator(user.getUserId().toString());
        content.setUpdater(user.getUserId().toString());
        content.setIsDelete(YesOrNoEnum.NO.getStatus());
        return contentService.save(content);
    }

    @Override
    public Boolean offerGoods(ContentRecommendGoodsReq req) {
        Date date = new Date();
        UserInfoDTO user = UserInfoContext.getUser();
        Content content = contentService.getById(req.getId());
        if (content == null || content.getStatus() != YesOrNoEnum.YES.getStatus()) {
            throw new ContentException(ContentExceptionEnum.CONTENT_FIND_EXCEPTION);
        } else if (content.getUserId().compareTo(user.getUserId()) == 0) {
            throw new ContentException(ContentExceptionEnum.CONTENT_FIND_EXCEPTION, "不能为自己提供物品");
        }
        Long contentId = content.getId();
        Long userId = user.getUserId();
        Long goodsId = req.getGoodsId();
        Integer status = req.getStatus();

        ContentRecommendGoods contentRecommendGoods = new ContentRecommendGoods();
        contentRecommendGoods.setContentId(contentId);
        contentRecommendGoods.setGoodsId(goodsId);
        contentRecommendGoods.setUserId(userId);
        contentRecommendGoods.setStatus(status);
        contentRecommendGoods.setLikeNum(0);
        contentRecommendGoods.setCreateTime(date);
        contentRecommendGoods.setUpdateTime(date);
        contentRecommendGoods.setCreator(userId.toString());
        contentRecommendGoods.setUpdater(userId.toString());
        contentRecommendGoods.setIsDelete(YesOrNoEnum.NO.getStatus());
        return contentRecommendGoodsService.save(contentRecommendGoods);
    }

    @Override
    public Boolean likeContent(ContentReq req) {
        Date date = new Date();
        Long contentId = req.getId();
        Long userId = UserInfoContext.getUser().getUserId();
        ContentLike contentLike = contentLikeService.getContentLike(contentId, userId);
        if (contentLike == null) {
            // 点赞
            contentLike = new ContentLike();
            contentLike.setContentId(contentId);
            contentLike.setUserId(userId);
            contentLike.setCreateTime(date);
            contentLike.setUpdateTime(date);
            contentLike.setUpdater(userId.toString());
            contentLike.setCreator(userId.toString());
            contentLike.setIsDelete(YesOrNoEnum.NO.getStatus());
            boolean save = contentLikeService.save(contentLike);
            if (save) {
                this.countLike(contentId).incrementAndGet();
            }
            return save;
        } else {
            // 取消点赞
            boolean remove = contentLikeService.removeById(contentLike);
            if (remove) {
                this.countLike(contentId).decrementAndGet();
            }
            return remove;
        }
    }

    @Override
    public Boolean viewContent(ContentReq req) {
        Long contentId = req.getId();
        Long userId = UserInfoContext.getUser().getUserId();
        ContentView contentView = new ContentView();
        contentView.setContentId(contentId);
        contentView.setUserId(userId);
        boolean save = contentViewService.save(contentView);
        if (save) {
            this.countView(contentId).incrementAndGet();
        }
        return save;
    }

    @Override
    public Long likeCount(ContentReq req) {
        return this.countLike(req.getId()).get();
    }

    @Override
    public Long viewCount(ContentReq req) {
        return this.countView(req.getId()).get();
    }


    private RAtomicLong countLike(Long contentId) {
        Map<String, String> likeNumMap = Maps.newHashMap();
        likeNumMap.put("key", "likeNum:" + contentId);
        likeNumMap.put("contextId", contentId.toString());
        return contentLikeAppBizService.countLike(likeNumMap);
    }

    private RAtomicLong countView(Long contentId) {
        Map<String, String> viewNumMap = Maps.newHashMap();
        viewNumMap.put("key", "viewNum:" + contentId);
        viewNumMap.put("contextId", contentId.toString());
        return contentViewAppBizService.countView(viewNumMap);
    }
}
