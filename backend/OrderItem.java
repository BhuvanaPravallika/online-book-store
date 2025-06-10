package com.nalajala.book.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="orderItem_tbl")
public class OrderItem {
	
	 	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long orderItemId;

	    private Integer quantity;
	    private Double priceAtPurchase;

	    @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "order_id")
	    private Order order;

	    @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "book_id")
	    private Book book;
	    
	    @ManyToOne
	    @JoinColumn(name = "user_id") // Assuming the foreign key column is user_id
	    private User user;

		public OrderItem() {
			super();
			// TODO Auto-generated constructor stub
		}

		public OrderItem(Long orderItemId, Integer quantity, Double priceAtPurchase, Order order, Book book,
				User user) {
			super();
			this.orderItemId = orderItemId;
			this.quantity = quantity;
			this.priceAtPurchase = priceAtPurchase;
			this.order = order;
			this.book = book;
			this.user = user;
		}

		public Long getOrderItemId() {
			return orderItemId;
		}

		public void setOrderItemId(Long orderItemId) {
			this.orderItemId = orderItemId;
		}

		public Integer getQuantity() {
			return quantity;
		}

		public void setQuantity(Integer quantity) {
			this.quantity = quantity;
		}

		public Double getPriceAtPurchase() {
			return priceAtPurchase;
		}

		public void setPriceAtPurchase(Double priceAtPurchase) {
			this.priceAtPurchase = priceAtPurchase;
		}

		public Order getOrder() {
			return order;
		}

		public void setOrder(Order order) {
			this.order = order;
		}

		public Book getBook() {
			return book;
		}

		public void setBook(Book book) {
			this.book = book;
		}

		public User getUser() {
			return user;
		}

		public void setUser(User user) {
			this.user = user;
		}

		@Override
		public String toString() {
			return "OrderItem [orderItemId=" + orderItemId + ", quantity=" + quantity + ", priceAtPurchase="
					+ priceAtPurchase + ", order=" + order + ", book=" + book + ", user=" + user + "]";
		}

		
	
	

}
