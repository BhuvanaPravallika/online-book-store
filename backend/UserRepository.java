package com.nalajala.book.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nalajala.book.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	 Optional<User> findByEmail(String email);
	    boolean existsByEmail(String email);	
}
