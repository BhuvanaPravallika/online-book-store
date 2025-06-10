package com.nalajala.book.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nalajala.book.entity.Order;
import com.nalajala.book.entity.User;
import com.nalajala.book.service.OrderService;

@RestController
@RequestMapping("/orders")
@CrossOrigin("*")
public class OrderController {
	

//    @Autowired
//    private OrderService orderService;
//
//    // Get all orders
//    @GetMapping
//    public ResponseEntity<List<Order>> getAllOrders() {
//        return ResponseEntity.ok(orderService.getAllOrders());
//    }
//
//    // Get order by ID
//    @GetMapping("/{id}")
//    public ResponseEntity<Order> getOrderById(@PathVariable int id) {
//        Order order = orderService.getOrderById(id);
//        return order != null ? ResponseEntity.ok(order) : ResponseEntity.notFound().build();
//    }
//
//    // Get orders by user (using user ID in request body)
//    @PostMapping("/user")
//    public ResponseEntity<List<Order>> getOrdersByUser(@RequestBody User user) {
//        return ResponseEntity.ok(orderService.getOrdersByUser(user));
//    }
//
//    // Place a new order
//    @PostMapping
//    public ResponseEntity<Order> placeOrder(@RequestBody Order order) {
//        return ResponseEntity.ok(orderService.placeOrder(order));
//    }
//
//    // Update an existing order
//    @PutMapping("/{id}")
//    public ResponseEntity<Order> updateOrder(@PathVariable int id, @RequestBody Order updatedOrder) {
//        Order result = orderService.updateOrder(id, updatedOrder);
//        return result != null ? ResponseEntity.ok(result) : ResponseEntity.notFound().build();
//    }
//
//    // Delete an order
//    @DeleteMapping("/{id}")
//    public ResponseEntity<String> deleteOrder(@PathVariable int id) {
//        orderService.deleteOrder(id);
//        return ResponseEntity.ok("Order deleted successfully");
//    }
	
	 @Autowired
	    private OrderService orderService;

	    // Endpoint to place an order for a specific user
	    @PostMapping("/place/{userId}")
	    public ResponseEntity<Order> placeOrder(@PathVariable Long userId) {
	        Order order = orderService.placeOrder(userId);
	        return ResponseEntity.ok(order);
	    }

	    // Endpoint to get all orders placed by a specific user
	    @GetMapping("/user/{userId}")
	    public ResponseEntity<List<Order>> getUserOrders(@PathVariable Long userId) {
	        List<Order> orders = orderService.getUserOrders(userId);
	        return ResponseEntity.ok(orders);
	    }
	    @DeleteMapping("/{orderId}")
	    public ResponseEntity<String> deleteOrder(@PathVariable Long orderId) {
	        orderService.deleteOrder(orderId);
	        return ResponseEntity.ok("Order deleted successfully");
	    }


}
