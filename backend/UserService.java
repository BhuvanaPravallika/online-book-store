package com.nalajala.book.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nalajala.book.entity.User;
import com.nalajala.book.repository.UserRepository;

@Service
public class UserService {

//	 @Autowired
//	  private UserRepository userRepository;
//	  
//	   // Register a new user
//	    public User registerUser(User user) {
//	        if (userRepository.existsByEmail(user.getEmail())) {
//	            throw new RuntimeException("Email already exists");
//	        }
//	        return userRepository.save(user);
//	    }
//
//	    // Login user
//	    public User loginUser(String email, String password) {
//	        User user = userRepository.findByEmail(email);
//	        if (user == null || !user.getPassword().equals(password)) {
//	            throw new RuntimeException("Invalid email or password");
//	        }
//	        return user;
//	    }
//
//	    // Get user by ID
//	    public User getUserById(int id) {
//	        return userRepository.findById(id)
//	                .orElseThrow(() -> new RuntimeException("User not found with ID: " + id));
//	    }
//
//	    // Update user profile (excluding password)
//	    public User updateUser(int id, User updatedUser) {
//	        User existingUser = getUserById(id);
//	        existingUser.setName(updatedUser.getName());
//	        existingUser.setEmail(updatedUser.getEmail());
//	        existingUser.setAddress(updatedUser.getAddress());
//	        existingUser.setMobile(updatedUser.getMobile());
//	        return userRepository.save(existingUser);
//	    }

	
	   @Autowired
	    private UserRepository userRepository;

	    public User register(User user) {
	        return userRepository.save(user);
	    }

	    public Optional<User> findByEmail(String email) {
	        return userRepository.findByEmail(email);
	    }

	    public User getById(Long userId) {
	        return userRepository.findById(userId)
	            .orElseThrow(() -> new RuntimeException("User not found"));
	    }
	
	
}
