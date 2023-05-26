package org.cartManagement.service;

import org.cartManagement.entity.User;
import org.cartManagement.payload.response.UserResponse;
import org.cartManagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<UserResponse> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserResponse> responses = new ArrayList<>();
        for (User user : users) {
            UserResponse response = new UserResponse();
            response.setUserId(user.getUserId());
            response.setName(user.getName());
            response.setEmail(user.getEmail());
            response.setPassword(user.getPassword());
            response.setAddress(user.getAddress());
            if (user.getCart() != null) {
                response.setCartId(user.getCart().getCartId());
            }
            response.setCreatedAt(user.getCreatedAt());
            responses.add(response);
        }
        return responses;
    }
    public UserResponse getUserById(int id) {
        User user = userRepository.findById(id).get();
        UserResponse response = new UserResponse();
        response.setUserId(user.getUserId());
        response.setName(user.getName());
        response.setEmail(user.getEmail());
        response.setPassword(user.getPassword());
        response.setAddress(user.getAddress());
        response.setCartId(user.getCart().getCartId());
        response.setCreatedAt(user.getCreatedAt());
        return response;
    }
    public User addUser(User user) {
        return userRepository.save(user);
    }
}
