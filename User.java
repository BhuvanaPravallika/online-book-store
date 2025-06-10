package com.nalajala.book.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="user_tbl")
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {
	
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long userId;

	    private String firstName;
	    private String lastName;

	    @Column(unique = true, nullable = false)
	    private String email;

	    private String password;

	    private String address;

	    @Column(length = 15)
	    private String mobileNumber;

	    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	    private List<CartItem> cartItems = new ArrayList<>();

	    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	    private List<Order> orders = new ArrayList<>();

		public User() {
			super();
			// TODO Auto-generated constructor stub
		}

		public User(Long userId, String firstName, String lastName, String email, String password, String address,
				String mobileNumber, List<CartItem> cartItems, List<Order> orders) {
			super();
			this.userId = userId;
			this.firstName = firstName;
			this.lastName = lastName;
			this.email = email;
			this.password = password;
			this.address = address;
			this.mobileNumber = mobileNumber;
			this.cartItems = cartItems;
			this.orders = orders;
		}

		public Long getUserId() {
			return userId;
		}

		public void setUserId(Long userId) {
			this.userId = userId;
		}

		public String getFirstName() {
			return firstName;
		}

		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}

		public String getLastName() {
			return lastName;
		}

		public void setLastName(String lastName) {
			this.lastName = lastName;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		public String getMobileNumber() {
			return mobileNumber;
		}

		public void setMobileNumber(String mobileNumber) {
			this.mobileNumber = mobileNumber;
		}

		public List<CartItem> getCartItems() {
			return cartItems;
		}

		public void setCartItems(List<CartItem> cartItems) {
			this.cartItems = cartItems;
		}

		public List<Order> getOrders() {
			return orders;
		}

		public void setOrders(List<Order> orders) {
			this.orders = orders;
		}

		@Override
		public String toString() {
			return "User [userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
					+ ", password=" + password + ", address=" + address + ", mobileNumber=" + mobileNumber
					+ ", cartItems=" + cartItems + ", orders=" + orders + "]";
		}
	
	
	
	
	
}
