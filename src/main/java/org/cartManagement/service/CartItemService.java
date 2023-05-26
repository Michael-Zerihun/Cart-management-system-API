package org.cartManagement.service;

import org.cartManagement.entity.CartItem;
import org.cartManagement.repository.CartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartItemService {
    @Autowired
    private CartItemRepository cartItemRepository;

    public List<CartItem> getAllCartItems() {
        return cartItemRepository.findAll();
    }

    public CartItem getCartItemById(int id) {
        return cartItemRepository.findById(id).get();
    }

    public CartItem addNewCartItem(CartItem cartItem) {
        return cartItemRepository.save(cartItem);
    }
}
