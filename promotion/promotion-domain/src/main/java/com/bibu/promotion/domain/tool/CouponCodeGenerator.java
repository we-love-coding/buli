package com.bibu.promotion.domain.tool;

import org.apache.commons.lang3.RandomUtils;

import java.util.Random;

/**
 * Author XY
 * Date 2024/5/10 上午11:38
 */
public class CouponCodeGenerator {

    private static final String CHAR_POOL = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    // 生成指定长度的随机字符串
    private static String generateRandomSequence(int length) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int index = RandomUtils.nextInt(0, CHAR_POOL.length());
            sb.append(CHAR_POOL.charAt(index));
        }
        return sb.toString();
    }

    // 生成带前缀的券编码
    public static String generateCouponCode(String prefix, Integer codeLength) {
        return prefix + generateRandomSequence(codeLength);
    }

//    // 测试生成多条券编码
//    public static void main(String[] args) {
//        int numberOfCodes = 5; // 生成5条券编码
//        for (int i = 0; i < numberOfCodes; i++) {
//            String couponCode = generateCouponCode();
//            System.out.println(couponCode);
//        }
//    }
}
