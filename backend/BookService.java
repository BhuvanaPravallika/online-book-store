package com.nalajala.book.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nalajala.book.entity.Book;
import com.nalajala.book.entity.Category;
import com.nalajala.book.repository.BookRepository;
import com.nalajala.book.repository.CategoryRepository;

@Service
public class BookService {
 
//	 @Autowired
//	    private BookRepository bookRepository;
//
//	 // Get all books
//	    public List<Book> getAllBooks() {
//	        return bookRepository.findAll();
//	    }
//
//	    // Get book by ID
//	    public Book getBookById(int book_id) {
//	        return bookRepository.findById(book_id)
//	                .orElseThrow(() -> new RuntimeException("Book not found with ID: " + book_id));
//	    }
//
//	    // Add a new book
//	    public Book addBook(Book book) {
//	        return bookRepository.save(book);
//	    }
//
//	    // Update existing book
//	    public Book updateBook(int book_id, Book updatedBook) {
//	        Book existingBook = getBookById(book_id);
//	        existingBook.setBook_Title(updatedBook.getBook_Title()); // field updated
//	        existingBook.setAuthor(updatedBook.getAuthor()); // field updated
//	        existingBook.setBook_Description(updatedBook.getBook_Description()); // field updated
//	        existingBook.setBook_Price(updatedBook.getBook_Price()); // field updated
//	        existingBook.setBook_Quantity(updatedBook.getBook_Quantity()); // field updated
//	        existingBook.setCategory(updatedBook.getCategory()); // field updated
//	        return bookRepository.save(existingBook);
//	    }
//
//	    // Delete book
//	    public void deleteBook(int book_id) {
//	        Book book = getBookById(book_id);
//	        bookRepository.delete(book);
//	    }
//
//	    // Search books by title (partial match, case-insensitive)
//	    public List<Book> searchBooksByTitle(String book_Title) {
//	        return bookRepository.findByBook_TitleContainingIgnoreCase(book_Title);
//	    }
//
//	    // Filter books by category name
//	    public List<Book> getBooksByCategory(String categoryName) {
//	        return bookRepository.findByCategory_Category_Name(categoryName);
//	    }
	
	 @Autowired
	    private BookRepository bookRepository;

	 @Autowired
	 private CategoryRepository categoryRepository;

	 public Book addBook(Book book) {
	     // Step 1: Get the category ID from the book (as Integer)
	     Integer categoryId = book.getCategory().getCategory_Id();

	     // Step 2: Fetch the managed Category from the database
	     Category category = categoryRepository.findById(categoryId)
	         .orElseThrow(() -> new RuntimeException("Category not found with ID: " + categoryId));

	     // Step 3: Set the managed Category into the Book
	     book.setCategory(category);

	     // Step 4: Save the book
	     return bookRepository.save(book);
	 }

	    public Book updateBook(Long id, Book updatedBook) {
	        Book book = bookRepository.findById(id)
	            .orElseThrow(() -> new RuntimeException("Book not found"));
	        book.setTitle(updatedBook.getTitle());
	        book.setAuthor(updatedBook.getAuthor());
	        book.setPrice(updatedBook.getPrice());
	        book.setCategory(updatedBook.getCategory());
	        book.setStock(updatedBook.getStock());
	        book.setDescription(updatedBook.getDescription());
	        return bookRepository.save(book);
	    }

	    public void deleteBook(Long id) {
	        bookRepository.deleteById(id);
	    }

	    public List<Book> getAllBooks() {
	        return bookRepository.findAll();
	    }

	    public Book getBookById(Long id) {
	        return bookRepository.findById(id)
	            .orElseThrow(() -> new RuntimeException("Book not found"));
	    }
	    
	    // Search books by title (case-insensitive, partial match)
	    public List<Book> searchBooksByTitle(String title) {
	        return bookRepository.findByBook_TitleContainingIgnoreCase(title);
	    }

	    // Get books by category name
	    public List<Book> getBooksByCategory(String categoryName) {
	        return bookRepository.findByCategory_Category_Name(categoryName);
	    }
	
	
}
