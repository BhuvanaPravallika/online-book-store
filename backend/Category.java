package com.nalajala.book.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="category_tbl")
public class Category {
	@Id
	private int category_Id;
	@Column(name="categoryName")
	private String category_Name;

	@OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference
	private List<Book> books;

	public Category() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Category(int category_Id, String category_Name, List<Book> books) {
		super();
		this.category_Id = category_Id;
		this.category_Name = category_Name;
		this.books = books;
	}

	public int getCategory_Id() {
		return category_Id;
	}

	public void setCategory_Id(int category_Id) {
		this.category_Id = category_Id;
	}

	public String getCategory_Name() {
		return category_Name;
	}

	public void setCategory_Name(String category_Name) {
		this.category_Name = category_Name;
	}

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

	@Override
	public String toString() {
	    return "Category [category_Id=" + category_Id + ", category_Name=" + category_Name + "]";
	}



	
}
