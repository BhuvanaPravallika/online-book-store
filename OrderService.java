package com.nalajala.book.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nalajala.book.entity.CartItem;
import com.nalajala.book.entity.Order;
import com.nalajala.book.entity.OrderItem;
import com.nalajala.book.entity.OrderStatus;
import com.nalajala.book.entity.User;
import com.nalajala.book.repository.BookRepository;
import com.nalajala.book.repository.CartItemRepository;
import com.nalajala.book.repository.OrderRepository;
import com.nalajala.book.repository.UserRepository;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository; // Repository to save the order
    @Autowired
    private CartItemRepository cartItemRepository; // Repository for cart items
    @Autowired
    private UserRepository userRepository; // Repository for users
    @Autowired
    private BookRepository bookRepository; // Repository for books (optional if needed)

    // Method to place an order for a user
    @Transactional
    public Order placeOrder(Long userId) {
        // Step 1: Get the user by ID
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("User not found"));

        // Step 2: Fetch cart items for the user
        List<CartItem> cartItems = cartItemRepository.findByUser(user);
        
        // Step 3: Create an order object
        Order order = new Order();
        order.setUser(user);
        order.setOrderDate(LocalDateTime.now()); // Set the current date and time
        order.setStatus(OrderStatus.PLACED); // Set the status of the order (you can modify it based on your needs)

        double totalPrice = 0.0; // Initialize total price of the order

        // Step 4: Iterate through cart items to create order items
        for (CartItem cartItem : cartItems) {
            OrderItem orderItem = new OrderItem();
            orderItem.setBook(cartItem.getBook()); // Set the book details
            orderItem.setQuantity(cartItem.getQuantity()); // Set quantity of the book
            orderItem.setOrder(order); // Link the order item to the order

            // Step 5: Calculate the total price of this order item
            double itemTotal = cartItem.getBook().getPrice() * cartItem.getQuantity(); // Price * Quantity
            order.setTotalPrice(itemTotal); // Set the total price for this order item

            // Step 6: Add the order item to the order and update the total price of the order
            order.getOrderItems().add(orderItem); // Add the order item to the order
            totalPrice += itemTotal; // Add the item total to the overall order total price
        }

        // Step 7: Set the total price of the order
        order.setTotalPrice(totalPrice); // Set the final total price for the order

        // Step 8: Save the order to the database
        orderRepository.save(order);

        // Step 9: Clear the user's cart after placing the order
        cartItemRepository.deleteByUser(user); // Delete all cart items for the user

        // Step 10: Return the created order
        return order;
    }

    // Optional: Get orders by userId if needed
    public List<Order> getUserOrders(Long userId) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("User not found"));
        return orderRepository.findByUser(user); // Return the list of orders for the user
    }
    
    public void deleteOrder(Long orderId) {
        Order order = orderRepository.findById(orderId)
            .orElseThrow(() -> new RuntimeException("Order not found"));
        orderRepository.delete(order);
    }


}
