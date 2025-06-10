package com.nalajala.book.controller;
import org.springframework.http.HttpStatus;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nalajala.book.entity.User;
import com.nalajala.book.service.UserService;

@RestController
@RequestMapping("/users")
@CrossOrigin("*")
public class UserController {
	 @Autowired
	    private UserService userService;

	    // ✅ Register a new user
	 @PostMapping("/register")
	 public ResponseEntity<?> registerUser(@RequestBody User user) {
	     System.out.println("Received user: " + user);
	     Optional<User> existing = userService.findByEmail(user.getEmail());
	     if (existing.isPresent()) {
	         return ResponseEntity.badRequest().body("Email already exists");
	     }
	     User savedUser = userService.register(user);
	     return ResponseEntity.ok(savedUser);
	 }

	    // ✅ Login user
	    @PostMapping("/login")
	    public ResponseEntity<?> loginUser(@RequestBody User user) {
	        Optional<User> existingUser = userService.findByEmail(user.getEmail());
	        if (existingUser.isEmpty() || !existingUser.get().getPassword().equals(user.getPassword())) {
	            return ResponseEntity.status(401).body("Invalid email or password");
	        }
	        return ResponseEntity.ok(existingUser.get());
	    }

	    // ✅ Get user by ID
	    @GetMapping("/profile/{id}")
	    public ResponseEntity<User> getUserById(@PathVariable Long id) {
	        try {
	            User user = userService.getById(id);
	            return ResponseEntity.ok(user);
	        } catch (RuntimeException e) {
	            return ResponseEntity.notFound().build();
	        }
	    }

	    // ✅ Update user profile
	    @PutMapping("/update/{id}")
	    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
	        try {
	            User existingUser = userService.getById(id); // Fetch existing user by ID
	            if (existingUser == null) {
	                return ResponseEntity.notFound().build(); // If user is not found, return 404
	            }
	            existingUser.setFirstName(updatedUser.getFirstName());
	            existingUser.setLastName(updatedUser.getLastName());
	            existingUser.setEmail(updatedUser.getEmail());
	            existingUser.setAddress(updatedUser.getAddress());
	            existingUser.setMobileNumber(updatedUser.getMobileNumber());
	            
	            // Save the updated user
	            User savedUser = userService.register(existingUser); // Assuming register method saves the user

	            return ResponseEntity.ok(savedUser); // Return the updated user in the response body
	        } catch (RuntimeException e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating profile");
	        }
	    }


}
