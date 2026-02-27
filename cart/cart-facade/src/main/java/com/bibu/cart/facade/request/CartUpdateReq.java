// This is a request object for updating cart details.

package com.bibu.cart.facade.request;

public class CartUpdateReq {
    private String cartId;
    private String itemId;
    private int quantity;

    public CartUpdateReq() {}

    public String getCartId() {
        return cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}