package org.cartManagement.service;

import org.cartManagement.entity.Cart;
import org.cartManagement.entity.CartItem;
import org.cartManagement.entity.User;
import org.cartManagement.payload.request.CartItemRequest;
import org.cartManagement.payload.response.CartItemResponse;
import org.cartManagement.payload.response.CartResponse;
import org.cartManagement.repository.CartItemRepository;
import org.cartManagement.repository.CartRepository;
import org.cartManagement.repository.ProductRepository;
import org.cartManagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CartItemRepository cartItemRepository;
    @Autowired
    private ProductRepository productRepository;

    public List<CartResponse> getAllCarts() {
        List<Cart> carts = cartRepository.findAll();
        List<CartResponse> cartResponses = new ArrayList<>();
        for (Cart cart:carts) {
            CartResponse cartResponse = new CartResponse();
            cartResponse.setCartId(cart.getCartId());
            cartResponse.setUserId(cart.getUser().getUserId());
            ArrayList<Integer> itemIds = new ArrayList<>();
            for (CartItem item: cart.getCartItems()) {
                itemIds.add(item.getCartItemId());
            }
            cartResponse.setCartItemIds(itemIds);
            cartResponse.setCreatedAt(cart.getCreatedAt());
            cartResponses.add(cartResponse);
        }
        return cartResponses;
    }
    public CartResponse getCartById(int id) {
        Cart cart = cartRepository.findById(id).get();
        CartResponse response = new CartResponse();
        response.setCartId(cart.getCartId());
        response.setUserId(cart.getUser().getUserId());
        response.setCreatedAt(cart.getCreatedAt());
        return response;
    }
    public Cart addNewCart(int userId) {
        User user = new User();
        user = userRepository.findById(userId).get();
        Cart cart = new Cart();
        cart.setUser(user);
        CartItem cartItem = new CartItem();
        cart.getCartItems().add(cartItem);
        return cartRepository.save(cart);
    }

    public Cart addCartAndUser(Cart cart) {
        User user = cart.getUser();
        userRepository.save(user);

        return cartRepository.save(cart);
    }

    public CartResponse getCartByUserId(int userId) {
        User user = userRepository.findById(userId).get();
        Cart cart = cartRepository.findById(user.getCart().getCartId()).get();
        CartResponse response = new CartResponse();
        response.setCartId(cart.getCartId());
        response.setUserId(cart.getUser().getUserId());
        response.setCreatedAt(user.getCreatedAt());
        List<Integer> cartItems = new ArrayList<>();
        for (CartItem item: user.getCart().getCartItems()) {
            cartItems.add(item.getCartItemId());
        }
        response.setCartItemIds(cartItems);
        return response;
    }

    public List<CartItemResponse> getAllItemsInCart(int cartId) {
        List<CartItem> cartItems = cartItemRepository.findByCartCartId(cartId);
        List<CartItemResponse> cartItemResponses = new ArrayList<>();
        for (CartItem cartItem : cartItems) {
            CartItemResponse response = new CartItemResponse();
            response.setCartItemId(cartItem.getCartItemId());
            response.setCartId(cartItem.getCart().getCartId());
            response.setProductId(cartItem.getProduct().getProductId());
            response.setQuantity(cartItem.getQuantity());
            response.setCreatedAt(cartItem.getCreatedAt());
            cartItemResponses.add(response);
        }
        return cartItemResponses;
    }

    public CartItemResponse getCartItemById(int id) {
        CartItem cartItem = cartItemRepository.findById(id).get();
        CartItemResponse itemResponse = new CartItemResponse();
        itemResponse.setCartItemId(cartItem.getCartItemId());
        itemResponse.setCartId(cartItem.getCart().getCartId());
        itemResponse.setProductId(cartItem.getProduct().getProductId());
        itemResponse.setQuantity(cartItem.getQuantity());
        itemResponse.setCreatedAt(cartItem.getCreatedAt());
        return itemResponse;
    }

    public List<CartItemResponse> getAllItems() {
        List<CartItem> cartItems = cartItemRepository.findAll();
        List<CartItemResponse> itemResponses = new ArrayList<>();
        for (CartItem item: cartItems) {
            CartItemResponse response = new CartItemResponse();
            response.setCartItemId(item.getCartItemId());
            response.setCartId(item.getCart().getCartId());
            response.setProductId(item.getProduct().getProductId());
            response.setQuantity(item.getQuantity());
            response.setCreatedAt(item.getCreatedAt());
            itemResponses.add(response);
        }
        return itemResponses;
    }

    public CartItemResponse ddItemToCart(CartItemRequest cartItem, int id) {
        CartItem item = new CartItem();
        item.setCart(cartRepository.findById(id).get());
        item.setProduct(productRepository.findById(cartItem.getProductId()).get());
        item.setQuantity(cartItem.getQuantity());
        CartItem newItem = cartItemRepository.save(item);
        CartItemResponse response = new CartItemResponse();
        response.setCartItemId(newItem.getCartItemId());
        response.setCartId(newItem.getCart().getCartId());
        response.setProductId(newItem.getProduct().getProductId());
        response.setQuantity(newItem.getQuantity());
        response.setCreatedAt(newItem.getCreatedAt());

        return response;
    }

    public CartItemResponse updateCartItem(CartItemRequest cartItemRequest, int id) {
        CartItem item = cartItemRepository.findById(id).get();
        item.setQuantity(cartItemRequest.getQuantity());
        item = cartItemRepository.save(item);
        CartItemResponse response = new CartItemResponse();
        response.setCreatedAt(item.getCreatedAt());
        response.setQuantity(item.getQuantity());
        response.setProductId(item.getProduct().getProductId());
        response.setCartItemId(item.getCartItemId());
        response.setCartId(item.getCart().getCartId());
        return response;
    }

    public void deleteItem(int id) {
        CartItem item = cartItemRepository.findById(id).get();
        cartItemRepository.delete(item);
    }

    public void deleteCart(int id) {
        Cart cart =  cartRepository.findById(id).get();
        List<CartItem> cartItems = cart.getCartItems();
        for (CartItem item: cartItems) {
            cartItemRepository.delete(item);
        }
        cartRepository.delete(cart);
    }
}
