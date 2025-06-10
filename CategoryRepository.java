package com.nalajala.book.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nalajala.book.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer>{
	 @Query("SELECT c FROM Category c WHERE c.category_Name = :category_Name")
	    Category findByCategory_Name(@Param("category_Name") String category_Name);
}
