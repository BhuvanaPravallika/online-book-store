package com.nalajala.book.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="book_tbl")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Book {
	
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long bookId;

	    private String title;
	    private String author;
	    private Double price;
	    private String imageUrl;
	    private String description;
	    private Integer stock;

	    @JsonIgnoreProperties("cartItems")
	    @OneToMany(mappedBy = "book")
	    @JsonIgnore
	    private List<CartItem> cartItems = new ArrayList<>();

	    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
	    @JsonIgnore
	    private List<OrderItem> orderItems = new ArrayList<>();
	    
	    @ManyToOne
	    @JoinColumn(name = "category_id")
	    @JsonBackReference
	    private Category category;

		public Book() {
			super();
			// TODO Auto-generated constructor stub
		}

		public Book(Long bookId, String title, String author, Double price, String imageUrl, String description,
				Integer stock, List<CartItem> cartItems, List<OrderItem> orderItems, Category category) {
			super();
			this.bookId = bookId;
			this.title = title;
			this.author = author;
			this.price = price;
			this.imageUrl = imageUrl;
			this.description = description;
			this.stock = stock;
			this.cartItems = cartItems;
			this.orderItems = orderItems;
			this.category = category;
		}

		public Long getBookId() {
			return bookId;
		}

		public void setBookId(Long bookId) {
			this.bookId = bookId;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getAuthor() {
			return author;
		}

		public void setAuthor(String author) {
			this.author = author;
		}

		public Double getPrice() {
			return price;
		}

		public void setPrice(Double price) {
			this.price = price;
		}

		public String getImageUrl() {
			return imageUrl;
		}

		public void setImageUrl(String imageUrl) {
			this.imageUrl = imageUrl;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public Integer getStock() {
			return stock;
		}

		public void setStock(Integer stock) {
			this.stock = stock;
		}

		public List<CartItem> getCartItems() {
			return cartItems;
		}

		public void setCartItems(List<CartItem> cartItems) {
			this.cartItems = cartItems;
		}

		public List<OrderItem> getOrderItems() {
			return orderItems;
		}

		public void setOrderItems(List<OrderItem> orderItems) {
			this.orderItems = orderItems;
		}

		public Category getCategory() {
			return category;
		}

		public void setCategory(Category category) {
			this.category = category;
		}

		@Override
		public String toString() {
		    return "Book [bookId=" + bookId + ", title=" + title + ", author=" + author +
		           ", price=" + price + ", imageUrl=" + imageUrl +
		           ", description=" + description + ", stock=" + stock + "]";
		}


		
	    

	
	
}
