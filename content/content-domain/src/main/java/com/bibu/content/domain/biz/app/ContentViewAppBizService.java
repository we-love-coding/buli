package com.bibu.content.domain.biz.app;

import org.redisson.api.RAtomicLong;

import java.util.Map;

/**
 * Author XY
 * Date 2024/5/22 下午1:44
 */
public interface ContentViewAppBizService {

    RAtomicLong countView(Map<String, String> map);
}
