package com.bibu.content.domain.biz.app.impl;

import com.alibaba.fastjson2.JSON;
import com.bibu.content.domain.biz.app.ContentLikeAppBizService;
import com.bibu.content.domain.service.ContentLikeService;
import com.x.common.annotations.DistributedLock;
import com.x.common.enums.ControlTypeEnum;
import org.redisson.api.RAtomicLong;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Map;

/**
 * Author XY
 * Date 2024/5/22 下午12:52
 */
@Service
public class ContentLikeAppBizServiceImpl implements ContentLikeAppBizService {

    @Autowired
    private ContentLikeService contentLikeService;

    @Autowired
    private RedissonClient redissonClient;

    @Override
    @DistributedLock(lockKey = "countLike:", controlType = ControlTypeEnum.ACCURATE_CONTROL)
    public RAtomicLong countLike(Map<String, String> map) {
        RAtomicLong atomicLong = redissonClient.getAtomicLong(map.get("key"));
        if (atomicLong.isExists()) {
            return atomicLong;
        }
        long likeNum = contentLikeService.countLikeByContentId(Long.parseLong(map.get("contentId")));
        atomicLong.set(likeNum);
        atomicLong.expireIfSet(Duration.ofDays(30));
        return atomicLong;
    }

    @Override
    public boolean isLike(Long contentId, Long userId) {
        RBucket<Boolean> bucket = redissonClient.getBucket("isLike:" + contentId + "_" + userId);
        if (bucket.isExists()) {
            return bucket.get();
        }
        boolean isLike;
        long likeNum = contentLikeService.countLikeByUserId(contentId, userId);
        if (likeNum > 0) {
            isLike = true;
        } else {
            isLike = false;
        }
        redissonClient.getBucket("isLike:"+contentId+"_"+userId).set(isLike, Duration.ofHours(24));
        return isLike;
    }
}
