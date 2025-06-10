package com.nalajala.book.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nalajala.book.entity.Order;
import com.nalajala.book.entity.OrderItem;
import com.nalajala.book.entity.User;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long>{

//	 @Query("SELECT oi FROM OrderItem oi WHERE oi.order = :order")
//	    List<OrderItem> findByOrder(@Param("order") Order order);
//	 
//	 @Query("SELECT o FROM OrderItem o WHERE o.book.book_Id = :bookId")
//	 List<OrderItem> findByBookId(@Param("bookId") int bookId);
	
	List<Order> findByUser(User user);

}
