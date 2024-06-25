package com.bibu.order.domain.tool;

import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.redisson.api.RAtomicLong;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Author XY
 * Date 2024/5/4 下午7:17
 */
@Component
public class CodeGenerateTool {

    @Autowired
    private RedissonClient redissonClient;


    public String generateCode(String prefix) {
        String yyyyMMdd = DateFormatUtils.format(new Date(), "yyyyMMdd");
        String key = prefix + yyyyMMdd;
        RAtomicLong atomicLong = redissonClient.getAtomicLong(key);
        if (!atomicLong.isExists()) {
            redissonClient.getAtomicLong(key).expire(Duration.ofHours(24));
        }
        return prefix + yyyyMMdd + RandomUtils.nextInt(100, 999) + String.format("%05d", atomicLong.incrementAndGet());
    }
}
