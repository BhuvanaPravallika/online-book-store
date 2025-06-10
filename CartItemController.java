package com.nalajala.book.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nalajala.book.entity.Book;
import com.nalajala.book.entity.CartItem;
import com.nalajala.book.entity.User;
import com.nalajala.book.service.CartItemService;

@RestController
@RequestMapping("/cart")
@CrossOrigin(origins = "http://localhost:4200")
public class CartItemController {
	
//	 @Autowired
//	    private CartItemService cartItemService;
//
//	    // Get all cart items for a user
//	    @GetMapping("/user/{userId}")
//	    public ResponseEntity<List<CartItem>> getCartItemsByUser(@PathVariable int userId) {
//	        User user = new User();
//	        user.setUser_Id(userId);  // Assuming User entity has userId as an identifier
//	        List<CartItem> cartItems = cartItemService.getCartItemsByUser(user);
//	        return new ResponseEntity<>(cartItems, HttpStatus.OK);
//	    }
//
//	    // Add book to cart
//	    @PostMapping("/add")
//	    public ResponseEntity<CartItem> addToCart(@RequestParam int userId, @RequestParam int bookId, @RequestParam int quantity) {
//	        User user = new User();
//	        user.setUser_Id(userId);
//	        Book book = new Book();
//	        
//	        book.setBook_Id(bookId);
//	        CartItem addedItem = cartItemService.addToCart(user, book, quantity);
//	        return new ResponseEntity<>(addedItem, HttpStatus.CREATED);
//	    }
//
//	    // Update cart quantity
//	    @PutMapping("/update/{cartItemId}")
//	    public ResponseEntity<CartItem> updateCartItemQuantity(@PathVariable int cartItemId, @RequestParam int quantity) {
//	        try {
//	            CartItem updatedItem = cartItemService.updateCartItemQuantity(cartItemId, quantity);
//	            return new ResponseEntity<>(updatedItem, HttpStatus.OK);
//	        } catch (RuntimeException e) {
//	            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
//	        }
//	    }
//
//	    // Remove item from cart by cart item ID
//	    @DeleteMapping("/remove/{cartItemId}")
//	    public ResponseEntity<String> removeItemFromCart(@PathVariable int cartItemId) {
//	        try {
//	            cartItemService.removeItemFromCart(cartItemId);
//	            return new ResponseEntity<>("Item removed successfully", HttpStatus.NO_CONTENT);
//	        } catch (RuntimeException e) {
//	            return new ResponseEntity<>("Cart item not found", HttpStatus.NOT_FOUND);
//	        }
//	    }
//
//	    // Clear all items for a user
//	    @DeleteMapping("/clear/{userId}")
//	    public ResponseEntity<String> clearCart(@PathVariable int userId) {
//	        User user = new User();
//	        user.setUser_Id(userId);
//	        cartItemService.clearCartByUser(user);
//	        return new ResponseEntity<>("All items cleared from cart", HttpStatus.NO_CONTENT);
//	    }
	
	   @Autowired
	    private CartItemService cartItemService;

	    // Endpoint to get all cart items for a user
	    @GetMapping("/user/{userId}")
	    public ResponseEntity<List<CartItem>> getCartItems(@PathVariable Long userId) {
	        List<CartItem> cartItems = cartItemService.getCartItems(userId);
	        return ResponseEntity.ok(cartItems); // Return list of cart items
	    }

	    // Endpoint to add a book to the cart
	    @PostMapping("/add/{userId}/{bookId}")
	    public ResponseEntity<?> addToCart(@PathVariable Long userId, @PathVariable Long bookId, @RequestParam int quantity) {
	        if (quantity <= 0) {
	            return ResponseEntity.badRequest().body("Quantity must be greater than zero.");
	        }

	        try {
	            CartItem cartItem = cartItemService.addToCart(userId, bookId, quantity);
	            return ResponseEntity.status(HttpStatus.CREATED).body(cartItem);
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to add item to cart: " + e.getMessage());
	        }
	    }

	    // Endpoint to remove a book from the cart
	    @DeleteMapping("/remove/{cartItemId}")
	    public ResponseEntity<String> removeFromCart(@PathVariable Long cartItemId) {
	        cartItemService.removeItemFromCart(cartItemId);
	        return ResponseEntity.ok("Item removed Successfully"); // Successfully removed item
	    }

	    // Endpoint to clear the cart for a user
	    @DeleteMapping("/clear/{userId}")
	    public ResponseEntity<String> clearCart(@PathVariable Long userId) {
	        cartItemService.clearCart(userId);
	        return ResponseEntity.ok("Cart Cleared Successfully"); // Successfully cleared cart
	    }

	    // Endpoint to calculate the total amount for a user's cart
	    @GetMapping("/total/{userId}")
	    public ResponseEntity<Double> calculateTotalAmount(@PathVariable Long userId) {
	        double totalAmount = cartItemService.calculateTotalAmount(userId);
	        return ResponseEntity.ok(totalAmount); // Return total amount
	    }
	    
	    @PutMapping("/decrementQuantity/{cartItemId}")
	    public ResponseEntity<String> decrementCartItemQuantity(@PathVariable Long cartItemId) {
	        try {
	            cartItemService.decrementCartItemQuantity(cartItemId);
	            return ResponseEntity.ok("Quantity decremented successfully");
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to decrement quantity: " + e.getMessage());
	        }
	    }



}