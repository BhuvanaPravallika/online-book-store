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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nalajala.book.entity.Book;
import com.nalajala.book.service.BookService;

@RestController
@RequestMapping("/books")
@CrossOrigin("*")
public class BookController {
	
	  @Autowired
	    private BookService bookService;

	    // Get all books
	    @GetMapping
	    public ResponseEntity<List<Book>> getAllBooks() {
	        return ResponseEntity.ok(bookService.getAllBooks());
	    }

	    // Get a book by ID
	    @GetMapping("/{bookid}")
	    public ResponseEntity<Book> getBookById(@PathVariable Long bookid) {
	        return ResponseEntity.ok(bookService.getBookById(bookid));
	    }

	    // Add a new book
	    @PostMapping("/addbook")
	    public ResponseEntity<Book> addBook(@RequestBody Book book) {
	        return ResponseEntity.ok(bookService.addBook(book));
	    }

	    // Update an existing book
	    @PutMapping("/update/{bookid}")
	    public ResponseEntity<Book> updateBook(@PathVariable Long bookid, @RequestBody Book updatedBook) {
	        return ResponseEntity.ok(bookService.updateBook(bookid, updatedBook));
	    }

	    // Delete a book
	    @DeleteMapping("/delete/{bookid}")
	    public ResponseEntity<String> deleteBook(@PathVariable Long bookid) {
	        bookService.deleteBook(bookid);
	        return ResponseEntity.ok("Book deleted successfully");
	    }

	    // Search books by title
	    @GetMapping("/search")
	    public ResponseEntity<List<Book>> searchBooksByTitle(@RequestParam("title") String title) {
	        return ResponseEntity.ok(bookService.searchBooksByTitle(title));
	    }

	    // Filter books by category
	    @GetMapping("/category/{categoryName}")
	    public ResponseEntity<List<Book>> getBooksByCategory(@PathVariable String categoryName) {
	        return ResponseEntity.ok(bookService.getBooksByCategory(categoryName));
	    }

}
