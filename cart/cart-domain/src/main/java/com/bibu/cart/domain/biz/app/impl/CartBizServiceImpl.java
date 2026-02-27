package com.bibu.cart.domain.biz.app.impl;

import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bibu.cart.domain.biz.CartBizService;

@Service
public class CartBizServiceImpl implements CartBizService {

    private static final String CART_KEY_PREFIX = "cart:";
    private static final String CART_PRICE_PREFIX = "cart_price:";

    private final RedissonClient redissonClient;

    @Autowired
    public CartBizServiceImpl(RedissonClient redissonClient) {
        this.redissonClient = redissonClient;
    }

    // Method implementations will go here
    
}