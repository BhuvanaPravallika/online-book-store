package com.nalajala.book.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nalajala.book.entity.CartItem;
import com.nalajala.book.entity.User;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long>{

//	  @Query("SELECT c FROM CartItem c WHERE c.user = :user")
//	    List<CartItem> findByUser(@Param("user") User user);
//
//	    // Custom query to delete CartItems by User
//	    @Query("DELETE FROM CartItem c WHERE c.user = :user")
//	    void deleteByUser(@Param("user") User user);
	
	
	  List<CartItem> findByUser(User user);
	    void deleteByUser(User user);
	    
}
