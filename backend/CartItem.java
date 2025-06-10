package com.nalajala.book.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="cart_tbl")
public class CartItem {
		@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long cartItemId;

	    @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "user_id")
	    @JsonIgnore
	    private User user;

	    @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "book_id")
	   // @JsonIgnore
	    private Book book;

	    private Integer quantity;

		public CartItem() {
			super();
			// TODO Auto-generated constructor stub
		}

		public CartItem(Long cartItemId, User user, Book book, Integer quantity) {
			super();
			this.cartItemId = cartItemId;
			this.user = user;
			this.book = book;
			this.quantity = quantity;
		}

		public Long getCartItemId() {
			return cartItemId;
		}

		public void setCartItemId(Long cartItemId) {
			this.cartItemId = cartItemId;
		}

		public User getUser() {
			return user;
		}

		public void setUser(User user) {
			this.user = user;
		}

		public Book getBook() {
			return book;
		}

		public void setBook(Book book) {
			this.book = book;
		}

		public Integer getQuantity() {
			return quantity;
		}

		public void setQuantity(Integer quantity) {
			this.quantity = quantity;
		}

		@Override
		public String toString() {
			return "CartItem [cartItemId=" + cartItemId + ", user=" + user + ", book=" + book + ", quantity=" + quantity
					+ "]";
		}
		
		
	
	    
	
	
	
}
