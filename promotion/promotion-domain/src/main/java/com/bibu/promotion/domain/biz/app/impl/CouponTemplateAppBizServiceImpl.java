package com.bibu.promotion.domain.biz.app.impl;

import com.alibaba.fastjson2.JSON;
import com.bibu.promotion.common.enums.PromotionExceptionEnum;
import com.bibu.promotion.common.enums.RedisKeyEnum;
import com.bibu.promotion.common.exceptions.PromotionException;
import com.bibu.promotion.dal.entity.CouponAccount;
import com.bibu.promotion.dal.entity.CouponTemplate;
import com.bibu.promotion.domain.biz.app.CouponTemplateAppBizService;
import com.bibu.promotion.domain.dto.CouponObtainDto;
import com.bibu.promotion.domain.redis.RedisClient;
import com.bibu.promotion.domain.service.CouponAccountService;
import com.bibu.promotion.domain.service.CouponTemplateService;
import com.bibu.promotion.facade.request.CouponTemplateObtainReq;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.x.common.annotations.DistributedLock;
import com.x.common.context.UserInfoContext;
import com.x.common.context.dto.UserInfoDTO;
import com.x.common.enums.ControlTypeEnum;
import com.x.common.enums.ExceptionEnum;
import com.x.common.enums.YesOrNoEnum;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

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
    private RedisClient redisClient;

    @Autowired
    private CouponTemplateAppBizService couponTemplateAppBizService;

    @Override
    public boolean obtainCoupon(CouponTemplateObtainReq req) {
        String templateCode = req.getTemplateCode();
        CouponTemplate couponTemplate = couponTemplateService.getCouponTemplate(templateCode);
        if (couponTemplate == null) {
            throw new PromotionException(PromotionExceptionEnum.ACTIVITY_IS_OVER);
        }

        UserInfoDTO user = UserInfoContext.getUser();
        boolean isObtain = this.obtainCoupon(new CouponObtainDto(couponTemplate, user));
        if (!isObtain) {
            throw new PromotionException(PromotionExceptionEnum.COUPON_OBTAIN_FAIL);
        }
        try {
            CouponAccount couponAccount = this.convertCouponAccount(couponTemplate, user);
            couponAccountService.save(couponAccount);
            return true;
        } catch (Exception e) {
            String userCouponObtainTotalNumKey = RedisKeyEnum.USER_COUPON_OBTAIN_TOTAL_NUM.buildKey(couponTemplate.getId(), user.getUserId());
            String couponObtainTotalNumKey = RedisKeyEnum.COUPON_OBTAIN_TOTAL_NUM.buildKey(couponTemplate.getId());
            redisClient.decrement(userCouponObtainTotalNumKey);
            redisClient.decrement(couponObtainTotalNumKey);
            return false;
        }
    }

    @Override
    public boolean obtainCouponBatch(CouponTemplateObtainReq req) {
        List<String> templateCodeList = req.getTemplateCodeList();
        if (CollectionUtils.isEmpty(templateCodeList)) {
            throw new PromotionException(ExceptionEnum.PARAM_ERROR.getCode(), "编码不能为空");
        }

        List<CouponTemplate> couponTemplateList = couponTemplateService.getCouponTemplateListByCodeList(templateCodeList);
        if (CollectionUtils.isEmpty(couponTemplateList)) {
            throw new PromotionException(ExceptionEnum.DATA_NOT_EXIST_ERROR.getCode(), "券不存在");
        }

        UserInfoDTO user = UserInfoContext.getUser();

        List<CompletableFuture<CouponAccount>> futureList = Lists.newArrayListWithExpectedSize(couponTemplateList.size());
        for (CouponTemplate couponTemplate : couponTemplateList) {
            CompletableFuture<CouponAccount> future = CompletableFuture.supplyAsync(() -> {
                boolean isObtain = this.obtainCoupon(new CouponObtainDto(couponTemplate, user));
                if (!isObtain) {
                    return null;
                }
                return this.convertCouponAccount(couponTemplate, user);
            });
            futureList.add(future);
        }

        List<CouponAccount> couponAccountList = Lists.newArrayListWithExpectedSize(couponTemplateList.size());
        for (CompletableFuture<CouponAccount> future : futureList) {
            CouponAccount couponAccount = future.join();
            if (couponAccount == null) {
                continue;
            }
            couponAccountList.add(couponAccount);
        }

        boolean rollbackIfNecessary = this.rollbackIfNecessary(couponAccountList, couponTemplateList.size());
        if (rollbackIfNecessary) {
            couponAccountList.parallelStream().forEach(couponTemplate -> {
                String userCouponObtainTotalNumKey = RedisKeyEnum.USER_COUPON_OBTAIN_TOTAL_NUM.buildKey(couponTemplate.getId(), user.getUserId());
                String couponObtainTotalNumKey = RedisKeyEnum.COUPON_OBTAIN_TOTAL_NUM.buildKey(couponTemplate.getId());
                redisClient.decrement(userCouponObtainTotalNumKey);
                redisClient.decrement(couponObtainTotalNumKey);
            });
            return false;
        }

        return true;
    }

    @Override
    @DistributedLock(lockKey = "setCouponObtainNum:", controlType = ControlTypeEnum.ACCURATE_CONTROL)
    public void setCouponObtainNum(Map<String, String> map) {
        String couponObtainNumStr = redisClient.get(map.get("key"));
        if (StringUtils.isNotBlank(couponObtainNumStr)) {
            // 该优惠券已经开始领取了
            return;
        }
        long couponTemplateId = Long.parseLong(map.get("couponTemplateId"));
        long couponObtainNum = couponAccountService.countCoupon(couponTemplateId);
        String redisKey = "couponTemplateId:" + couponTemplateId;
        redisClient.setWithTTL(redisKey, JSON.toJSONString(couponObtainNum),
                RedisKeyEnum.COUPON_OBTAIN_TOTAL_NUM.getExpire(),
                RedisKeyEnum.COUPON_OBTAIN_TOTAL_NUM.getTimeUnit());
    }

    @Override
    @DistributedLock(lockKey = "setUserCouponObtainNum:", controlType = ControlTypeEnum.ACCURATE_CONTROL)
    public void setUserCouponObtainNum(Map<String, String> map) {
        String userCouponObtainNumStr = redisClient.get(map.get("key"));
        if (StringUtils.isNotBlank(userCouponObtainNumStr)) {
            // 该优惠券已经开始领取了
            return;
        }
        long couponTemplateId = Long.parseLong(map.get("couponTemplateId"));
        long userId = Long.parseLong(map.get("userId"));
        long couponObtainNum = couponAccountService.countUserCoupon(couponTemplateId, userId);
        String redisKey = "couponTemplateId:" + couponTemplateId;
        redisClient.setWithTTL(redisKey, JSON.toJSONString(couponObtainNum),
                RedisKeyEnum.USER_COUPON_OBTAIN_TOTAL_NUM.getExpire(),
                RedisKeyEnum.USER_COUPON_OBTAIN_TOTAL_NUM.getTimeUnit());
    }

    private boolean obtainCoupon(CouponObtainDto dto) {
        CouponTemplate couponTemplate = dto.getCouponTemplate();
        UserInfoDTO user = dto.getUser();
        Integer offerNum = couponTemplate.getOfferNum();

        String userCouponObtainTotalNumKey = RedisKeyEnum.USER_COUPON_OBTAIN_TOTAL_NUM.buildKey(couponTemplate.getId(), user.getUserId());
        String userCouponObtainNumStr = redisClient.get(userCouponObtainTotalNumKey);
        if (StringUtils.isBlank(userCouponObtainNumStr)) {
            Map<String, String> map = Maps.newHashMapWithExpectedSize(3);
            map.put("key", userCouponObtainNumStr);
            map.put("couponTemplateId", couponTemplate.getId().toString());
            map.put("userId", user.getUserId().toString());
            couponTemplateAppBizService.setUserCouponObtainNum(map);
        }
        long userCouponObtainNum = redisClient.increment(userCouponObtainTotalNumKey);

        String couponObtainTotalNumKey = RedisKeyEnum.COUPON_OBTAIN_TOTAL_NUM.buildKey(couponTemplate.getId());
        String couponObtainNumStr = redisClient.get(couponObtainTotalNumKey);
        if (StringUtils.isBlank(couponObtainNumStr)) {
            Map<String, String> map = Maps.newHashMapWithExpectedSize(2);
            map.put("key", couponObtainTotalNumKey);
            map.put("couponTemplateId", couponTemplate.getId().toString());
            couponTemplateAppBizService.setCouponObtainNum(map);
        }
        long couponObtainNum = redisClient.increment(couponObtainTotalNumKey);
        try {
            long limitNum = couponTemplate.getLimitNum().longValue();
            if (limitNum >= NumberUtils.LONG_ZERO && userCouponObtainNum > limitNum) {
                throw new PromotionException(PromotionExceptionEnum.USER_COUPON_OBTAIN_COMPLETED);
            }

            if (couponObtainNum > offerNum.longValue()) {
                throw new PromotionException(PromotionExceptionEnum.COUPON_OBTAIN_COMPLETED);
            }
            return true;
        } catch (Exception e) {
            redisClient.decrement(userCouponObtainTotalNumKey);
            redisClient.decrement(couponObtainTotalNumKey);
            return false;
        }
    }

    private CouponAccount convertCouponAccount(CouponTemplate couponTemplate, UserInfoDTO user) {
        Date date = new Date();
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
        return couponAccount;
    }

    private boolean rollbackIfNecessary(List<CouponAccount> couponAccountList, int obtainNum) {
        if (couponAccountList.size() != obtainNum) {
            return false;
        }
        try {
            couponAccountService.saveBatch(couponAccountList, 100);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
