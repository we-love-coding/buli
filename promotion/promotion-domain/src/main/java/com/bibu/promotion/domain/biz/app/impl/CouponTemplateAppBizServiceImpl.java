package com.bibu.promotion.domain.biz.app.impl;

import com.bibu.promotion.common.enums.PromotionExceptionEnum;
import com.bibu.promotion.common.exceptions.PromotionException;
import com.bibu.promotion.dal.entity.CouponAccount;
import com.bibu.promotion.dal.entity.CouponTemplate;
import com.bibu.promotion.domain.biz.app.CouponTemplateAppBizService;
import com.bibu.promotion.domain.service.CouponAccountService;
import com.bibu.promotion.domain.service.CouponTemplateService;
import com.bibu.promotion.facade.request.CouponTemplateObtainReq;
import com.google.common.collect.Maps;
import com.x.common.annotations.DistributedLock;
import com.x.common.context.UserInfoContext;
import com.x.common.context.dto.UserInfoDTO;
import com.x.common.enums.ControlTypeEnum;
import com.x.common.enums.YesOrNoEnum;
import org.redisson.api.RAtomicLong;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Date;
import java.util.Map;

/**
 * Author XY
 * Date 2024/5/8 下午1:46
 */
@Service
public class CouponTemplateAppBizServiceImpl implements CouponTemplateAppBizService {

    @Autowired
    private CouponTemplateService couponTemplateService;

    @Autowired
    private CouponAccountService couponAccountService;

    @Autowired
    private RedissonClient redissonClient;

    @Autowired
    private CouponTemplateAppBizService couponTemplateAppBizService;

    @Override
    @DistributedLock(lockKey = "obtainCoupon:user:", controlType = ControlTypeEnum.USER_CONTROL)
    public boolean obtainCoupon(CouponTemplateObtainReq req) {
        String templateCode = req.getTemplateCode();
        CouponTemplate couponTemplate = couponTemplateService.getCouponTemplate(templateCode);
        if (couponTemplate == null) {
            throw new PromotionException(PromotionExceptionEnum.ACTIVITY_IS_OVER);
        }

        Integer offerNum = couponTemplate.getOfferNum();

        String key = "couponObtainNum:" + couponTemplate.getId();
        RAtomicLong atomicLong = redissonClient.getAtomicLong(key);
        boolean exists = atomicLong.isExists();
        if (!exists) {
            Map<String, String> map = Maps.newHashMapWithExpectedSize(2);
            map.put("key", key);
            map.put("couponTemplateId", couponTemplate.getId().toString());
            couponTemplateAppBizService.setCouponObtainNum(map);
        }
        long couponObtainNum = atomicLong.incrementAndGet();
        try {
            if (couponObtainNum > offerNum.longValue()) {
                throw new PromotionException(PromotionExceptionEnum.ACTIVITY_IS_OVER);
            }

            Date date = new Date();
            UserInfoDTO user = UserInfoContext.getUser();
            CouponAccount couponAccount = new CouponAccount();
            couponAccount.setCouponTemplateId(couponTemplate.getId());
            couponAccount.setUserId(user.getUserId());
            couponAccount.setObtainTime(date);
            couponAccount.setEffectiveStartTime(couponTemplate.getStartTime());
            couponAccount.setEffectiveEndTime(couponTemplate.getEndTime());
            couponAccount.setCreateTime(date);
            couponAccount.setUpdateTime(date);
            couponAccount.setUpdater(user.getNickName());
            couponAccount.setCreator(user.getNickName());
            couponAccount.setStatus(1);
            couponAccount.setIsDelete(YesOrNoEnum.NO.getStatus());
            return couponAccountService.save(couponAccount);
        } catch (Exception e) {
            atomicLong.decrementAndGet();
            return false;
        }
    }

    @Override
    @DistributedLock(lockKey = "setCouponObtainNum:", controlType = ControlTypeEnum.ACCURATE_CONTROL)
    public void setCouponObtainNum(Map<String, String> map) {
        RAtomicLong atomicLong = redissonClient.getAtomicLong(map.get("key"));
        if (atomicLong.isExists()) {
            return;
        }
        long couponObtainNum = couponAccountService.countCoupon(Long.parseLong(map.get("couponTemplateId")));
        atomicLong.set(couponObtainNum);
        atomicLong.expireIfSet(Duration.ofHours(24));
    }
}
