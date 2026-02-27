package com.bibu.cart.domain.biz.app;

public interface CartBizService {
    void addCart();
    List<Cart> listCart();
    CartSummary getCartSummary();
    void updateCart();
    void deleteCart();
    void clearCart();
    int getCartCount();
}