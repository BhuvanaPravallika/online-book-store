package com.nalajala.book.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nalajala.book.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

	 @Query("SELECT b FROM Book b WHERE b.category.category_Name = :category_Name")
	    List<Book> findByCategory_Category_Name(@Param("category_Name") String category_Name);

	    // Correcting the field reference for title to match entity field
	    @Query("SELECT b FROM Book b WHERE LOWER(b.title) LIKE LOWER(CONCAT('%', :book_Title, '%'))")
	    List<Book> findByBook_TitleContainingIgnoreCase(@Param("book_Title") String book_Title);
	    
	    
	    
}
