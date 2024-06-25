package com.bibu.content.domain.biz.app.impl;

import com.bibu.content.domain.biz.app.ContentViewAppBizService;
import com.bibu.content.domain.service.ContentViewService;
import com.x.common.annotations.DistributedLock;
import com.x.common.enums.ControlTypeEnum;
import org.redisson.api.RAtomicLong;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Map;

/**
 * Author XY
 * Date 2024/5/22 下午1:44
 */
@Service
public class ContentViewAppBizServiceImpl implements ContentViewAppBizService {
    @Autowired
    private ContentViewService contentViewService;
    @Autowired
    private RedissonClient redissonClient;

    @Override
    @DistributedLock(lockKey = "countView:", controlType = ControlTypeEnum.ACCURATE_CONTROL)
    public RAtomicLong countView(Map<String, String> map) {
        RAtomicLong atomicLong = redissonClient.getAtomicLong(map.get("key"));
        if (atomicLong.isExists()) {
            return atomicLong;
        }
        long viewNum = contentViewService.countView(Long.parseLong(map.get("contentId")));
        atomicLong.set(viewNum);
        atomicLong.expireIfSet(Duration.ofDays(30));
        return atomicLong;
    }
}
