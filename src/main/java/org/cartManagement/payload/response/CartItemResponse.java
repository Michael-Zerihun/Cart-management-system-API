package org.cartManagement.payload.response;

import org.cartManagement.entity.Cart;
import org.cartManagement.entity.Product;

import java.util.Date;

public class CartItemResponse {
    private int cartItemId;
    private int cartId;
    private int productId;
    private int quantity;
    private Date createdAt;

    public CartItemResponse() {
    }

    public CartItemResponse(int cartItemId, int cartId, int productId, int quantity, Date createdAt) {
        this.cartItemId = cartItemId;
        this.cartId = cartId;
        this.productId = productId;
        this.quantity = quantity;
        this.createdAt = createdAt;
    }

    public int getCartItemId() {
        return cartItemId;
    }

    public void setCartItemId(int cartItemId) {
        this.cartItemId = cartItemId;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
