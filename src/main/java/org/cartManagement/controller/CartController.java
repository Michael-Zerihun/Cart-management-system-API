package org.cartManagement.controller;

import org.cartManagement.entity.Cart;
import org.cartManagement.entity.CartItem;
import org.cartManagement.payload.request.CartItemRequest;
import org.cartManagement.payload.response.CartItemResponse;
import org.cartManagement.payload.response.CartResponse;
import org.cartManagement.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CartController {
    @Autowired
    private CartService cartService;

    @GetMapping("/cart")
    public List<CartResponse> getAllCarts() {
        return cartService.getAllCarts();
    }

    @GetMapping("/cart/{id}")
    public CartResponse getCartById(@PathVariable int id) {
        return cartService.getCartById(id);
    }

    @GetMapping("/cart/user/{id}")
    public CartResponse getCartByUserId(@PathVariable int id) {
        return cartService.getCartByUserId(id);
    }

    @PostMapping("/cart/user/{id}")
    public Cart createCartForUser(@PathVariable int id) {
        return cartService.addNewCart(id);
    }

    @PostMapping("/cart")
    public Cart createCartAndUser(@RequestBody Cart cart) {
        return cartService.addCartAndUser(cart);
    }

    @GetMapping("/cart/{id}/item")
    public List<CartItemResponse> getAllItemsInCart(@PathVariable int id) {
        return cartService.getAllItemsInCart(id);
    }

    @GetMapping("/cart/item/{id}")
    public CartItemResponse getCartItemById(@PathVariable int id) {
        return cartService.getCartItemById(id);
    }

    @GetMapping("/cart/item")
    public List<CartItemResponse> getAllItems() {
        return cartService.getAllItems();
    }

    @PostMapping("/cart/{id}/item")
    public CartItemResponse addItemToCart(@RequestBody CartItemRequest cartItem, @PathVariable int id) {
        return cartService.ddItemToCart(cartItem, id);
    }

    @PutMapping("/cart/item/{id}")
    public CartItemResponse changeItem(@RequestBody CartItemRequest cartItemRequest, @PathVariable int id) {
        return cartService.updateCartItem(cartItemRequest, id);
    }

    @DeleteMapping("/cart/item/{id}")
    public void deleteItem(@PathVariable int id) {
        cartService.deleteItem(id);
    }

    @DeleteMapping("/cart/{id}")
    public void  deleteCart(@PathVariable int id) {
        cartService.deleteCart(id);
//        return "Cart " + id + " has been deleted along with it items";
    }

}
