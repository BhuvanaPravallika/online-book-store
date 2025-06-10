package com.nalajala.book.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nalajala.book.entity.Order;
import com.nalajala.book.entity.User;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>{
	 @Query("SELECT o FROM Order o WHERE o.user = :user")
	    List<Order> findByUser(@Param("user") User user);
	
}
