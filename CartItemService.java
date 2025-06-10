package com.nalajala.book.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nalajala.book.entity.Book;
import com.nalajala.book.entity.CartItem;
import com.nalajala.book.entity.User;
import com.nalajala.book.repository.BookRepository;
import com.nalajala.book.repository.CartItemRepository;
import com.nalajala.book.repository.UserRepository;

@Service
public class CartItemService {
//	 	@Autowired
//	    private CartItemRepository cartItemRepository;
//
//	    // Get all cart items for a user
//	    public List<CartItem> getCartItemsByUser(User user) {
//	        return cartItemRepository.findByUser(user);
//	    }
//
//	    // Add book to cart
//	    public CartItem addToCart(User user, Book book, int quantity) {
//	        // Check if item already exists
//	        List<CartItem> existingItems = cartItemRepository.findByUser(user);
//	        for (CartItem item : existingItems) {
//	            if (item.getBook().getBook_Id() == book.getBook_Id()) {
//	                item.setCartQuantity(item.getCartQuantity() + quantity);
//	                return cartItemRepository.save(item);
//	            }
//	        }
//	        // If not exists, create new
//	        CartItem newItem = new CartItem();
//	        newItem.setUser(user);
//	        newItem.setBook(book);
//	        newItem.setCartQuantity(quantity);
//	        return cartItemRepository.save(newItem);
//	    }
//
//	    // Update cart quantity
//	    public CartItem updateCartItemQuantity(int cartItemId, int quantity) {
//	        CartItem item = cartItemRepository.findById(cartItemId)
//	                .orElseThrow(() -> new RuntimeException("Cart item not found"));
//	        item.setCartQuantity(quantity);
//	        return cartItemRepository.save(item);
//	    }
//
//	    // Remove item by cart ID
//	    public void removeItemFromCart(int cartItemId) {
//	        cartItemRepository.deleteById(cartItemId);
//	    }
//
//	    // Clear all items for a user
//	    public void clearCartByUser(User user) {
//	        cartItemRepository.deleteByUser(user);
//	    }
	
	
	 @Autowired
	    private CartItemRepository cartItemRepository;

	    @Autowired
	    private BookRepository bookRepository;

	    @Autowired
	    private UserRepository userRepository;

	    // Get all cart items for a user
	    public List<CartItem> getCartItems(Long userId) {
	        User user = userRepository.findById(userId)
	            .orElseThrow(() -> new RuntimeException("User not found"));
	        return cartItemRepository.findByUser(user);
	    }

	    // Add a book to the cart
	    public CartItem addToCart(Long userId, Long bookId, int quantity) {
	        User user = userRepository.findById(userId)
	            .orElseThrow(() -> new RuntimeException("User not found"));
	        Book book = bookRepository.findById(bookId)
	            .orElseThrow(() -> new RuntimeException("Book not found"));

	        // Check if the book already exists in the cart
	        List<CartItem> existingItems = cartItemRepository.findByUser(user);
	        for (CartItem item : existingItems) {
	            if (item.getBook().getBookId().equals(book.getBookId())) {
	                item.setQuantity(item.getQuantity() + quantity); // Increment quantity
	                return cartItemRepository.save(item); // Update existing item
	            }
	        }

	        // If book doesn't exist in the cart, create new CartItem
	        CartItem newItem = new CartItem();
	        newItem.setUser(user);
	        newItem.setBook(book);
	        newItem.setQuantity(quantity);

	        return cartItemRepository.save(newItem);
	    }

	    // Remove a cart item by cartItemId
	    public void removeItemFromCart(Long cartItemId) {
	        cartItemRepository.deleteById(cartItemId); // Delete the cart item by ID
	    }
	    
	    
	    public void decrementCartItemQuantity(Long cartItemId) {
	        CartItem cartItem = cartItemRepository.findById(cartItemId)
	            .orElseThrow(() -> new RuntimeException("Cart item not found"));

	        int currentQuantity = cartItem.getQuantity();
	        int newQuantity = currentQuantity - 1;

	        if (newQuantity <= 0) {
	            // If quantity is 0 or less after decrement, delete item
	            cartItemRepository.delete(cartItem);
	        } else {
	            // Otherwise update quantity
	            cartItem.setQuantity(newQuantity);
	            cartItemRepository.save(cartItem);
	        }
	    }


	    // Clear all cart items for a user
	    @Transactional
	    public void clearCart(Long userId) {
	        User user = userRepository.findById(userId)
	            .orElseThrow(() -> new RuntimeException("User not found"));
	        cartItemRepository.deleteByUser(user); // Delete all cart items for the user
	    }

	    // Calculate the total amount for a user's cart
	    public double calculateTotalAmount(Long userId) {
	        User user = userRepository.findById(userId)
	            .orElseThrow(() -> new RuntimeException("User not found"));

	        List<CartItem> cartItems = cartItemRepository.findByUser(user);
	        double totalAmount = 0;

	        // Loop through each item and calculate the total price
	        for (CartItem cartItem : cartItems) {
	            double itemTotal = cartItem.getBook().getPrice() * cartItem.getQuantity(); // Price * Quantity
	            totalAmount += itemTotal;
	        }

	        return totalAmount; // Return the total amount
	    }

}
