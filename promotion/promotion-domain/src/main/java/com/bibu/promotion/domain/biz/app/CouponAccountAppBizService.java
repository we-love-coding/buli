package com.bibu.promotion.domain.biz.app;

import com.bibu.promotion.facade.request.UserCouponSearchReq;
import com.bibu.promotion.facade.response.UserCouponResp;
import com.github.pagehelper.PageInfo;

/**
 * Author XY
 * Date 2024/5/8 下午4:51
 */
public interface CouponAccountAppBizService {
    PageInfo<UserCouponResp> findUserCouponListByPage(UserCouponSearchReq req);
}
