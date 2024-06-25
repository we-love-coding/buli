package com.bibu.promotion.domain.biz.app.impl;

import com.bibu.promotion.dal.dto.CouponAccountDTO;
import com.bibu.promotion.dal.entity.CouponAccount;
import com.bibu.promotion.dal.entity.CouponTemplate;
import com.bibu.promotion.domain.biz.app.CouponAccountAppBizService;
import com.bibu.promotion.domain.service.CouponAccountService;
import com.bibu.promotion.domain.service.CouponTemplateService;
import com.bibu.promotion.facade.request.UserCouponSearchReq;
import com.bibu.promotion.facade.response.UserCouponResp;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.x.common.context.UserInfoContext;
import com.x.common.context.dto.UserInfoDTO;
import com.x.common.utils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Author XY
 * Date 2024/5/8 下午4:51
 */
@Service
public class CouponAccountAppBizServiceImpl implements CouponAccountAppBizService {

    @Autowired
    private CouponAccountService couponAccountService;

    @Autowired
    private CouponTemplateService couponTemplateService;

    @Override
    public PageInfo<UserCouponResp> findUserCouponListByPage(UserCouponSearchReq req) {
        UserInfoDTO user = UserInfoContext.getUser();
        Page<UserCouponResp> page = PageHelper.startPage(req.getPageNum(), req.getPageSize());
        CouponAccountDTO couponAccountDTO = new CouponAccountDTO();
        couponAccountDTO.setStatus(req.getStatus());
        couponAccountDTO.setUserId(user.getUserId());
        couponAccountDTO.setEffectiveStartTime(req.getEffectiveStartTime());
        couponAccountDTO.setEffectiveEndTime(req.getEffectiveEndTime());
        List<CouponAccount> couponAccountList = couponAccountService.getCouponAccountList(couponAccountDTO);
        if (CollectionUtils.isEmpty(couponAccountList)) {
            return new PageInfo<>();
        }


        List<Long> couponTemplateIdList = couponAccountList.stream().map(CouponAccount::getCouponTemplateId).distinct().sorted(Long::compareTo).collect(Collectors.toList());
        List<CouponTemplate> couponTemplateList = couponTemplateService.getCouponTemplateList(couponTemplateIdList);
        if (CollectionUtils.isEmpty(couponTemplateList)) {
            return new PageInfo<>();
        }

        Map<Long, CouponTemplate> map = Maps.newHashMapWithExpectedSize(couponTemplateIdList.size());
        couponTemplateList.forEach(c -> map.put(c.getId(), c));

        List<UserCouponResp> list = Lists.newArrayListWithCapacity(couponAccountList.size());
        for (CouponAccount couponAccount : couponAccountList) {
            CouponTemplate couponTemplate = map.get(couponAccount.getCouponTemplateId());
            if (couponTemplate == null) {
                continue;
            }
            UserCouponResp userCouponResp = BeanUtils.convertBean(couponAccount, UserCouponResp.class);
            userCouponResp.setCouponTemplateCode(couponTemplate.getTemplateCode());
            userCouponResp.setCouponTemplateName(couponTemplate.getTemplateName());
            userCouponResp.setDiscountType(couponTemplate.getDiscountType());
            userCouponResp.setAchieveAmount(couponTemplate.getAchieveAmount());
            userCouponResp.setDiscountAmount(couponTemplate.getDiscountAmount());
            userCouponResp.setDiscountPercentage(couponTemplate.getDiscountPercentage());
            userCouponResp.setDiscountTypeName(StringUtils.EMPTY);
            list.add(userCouponResp);
        }

        PageInfo<UserCouponResp> pageInfo = new PageInfo<>(page);
        pageInfo.setList(list);
        PageHelper.clearPage();
        return pageInfo;
    }
}
