package com.bibu.content.domain.biz.app;

import org.redisson.api.RAtomicLong;

import java.util.Map;

/**
 * Author XY
 * Date 2024/5/22 下午12:51
 */
public interface ContentLikeAppBizService {

    RAtomicLong countLike(Map<String, String> map);

    boolean isLike(Long contentId, Long userId);
}
