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

import com.nalajala.book.entity.Category;
import com.nalajala.book.service.CategoryService;

@RestController
@RequestMapping("/categories")
@CrossOrigin("*")
public class CategoryController {
	
//	 @Autowired
//	    private CategoryService categoryService;
//
//	    // Get all categories
//	    @GetMapping
//	    public ResponseEntity<List<Category>> getAllCategories() {
//	        List<Category> categories = categoryService.getAllCategories();
//	        return new ResponseEntity<>(categories, HttpStatus.OK);
//	    }
//
//	    // Get category by ID
//	    @GetMapping("/{id}")
//	    public ResponseEntity<Category> getCategoryById(@PathVariable int id) {
//	        Category category = categoryService.getCategoryById(id);
//	        if (category != null) {
//	            return new ResponseEntity<>(category, HttpStatus.OK);
//	        }
//	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//	    }
//
//	    // Get category by name
//	    @GetMapping("/name/{name}")
//	    public ResponseEntity<Category> getCategoryByName(@PathVariable String name) {
//	        Category category = categoryService.getCategoryByName(name);
//	        if (category != null) {
//	            return new ResponseEntity<>(category, HttpStatus.OK);
//	        }
//	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//	    }
//
//	    // Add a new category
//	    @PostMapping
//	    public ResponseEntity<Category> addCategory(@RequestBody Category category) {
//	        Category savedCategory = categoryService.addCategory(category);
//	        return new ResponseEntity<>(savedCategory, HttpStatus.CREATED);
//	    }
//
//	    // Delete category by ID
//	    @DeleteMapping("/{id}")
//	    public ResponseEntity<String> deleteCategory(@PathVariable int id) {
//	        categoryService.deleteCategory(id);
//	        return new ResponseEntity<>("Category deleted successfully", HttpStatus.NO_CONTENT);
//	    }
//
//	    // Update category
//	    @PutMapping("/{id}")
//	    public ResponseEntity<Category> updateCategory(@PathVariable int id, @RequestBody Category updatedCategory) {
//	        Category updated = categoryService.updateCategory(id, updatedCategory);
//	        if (updated != null) {
//	            return new ResponseEntity<>(updated, HttpStatus.OK);
//	        }
//	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//	    }

	
	 @Autowired
	    private CategoryService categoryService;

	    // Get all categories
	    @GetMapping
	    public ResponseEntity<List<Category>> getAllCategories() {
	        return ResponseEntity.ok(categoryService.getAllCategories());
	    }

	    // Get category by ID
	    @GetMapping("/{id}")
	    public ResponseEntity<Category> getCategoryById(@PathVariable int id) {
	        Category category = categoryService.getCategoryById(id);
	        if (category != null) {
	            return ResponseEntity.ok(category);
	        } else {
	            return ResponseEntity.notFound().build();
	        }
	    }

	    // Get category by name
	    @GetMapping("/search")
	    public ResponseEntity<Category> getCategoryByName(@RequestParam String name) {
	        Category category = categoryService.getCategoryByName(name);
	        if (category != null) {
	            return ResponseEntity.ok(category);
	        } else {
	            return ResponseEntity.notFound().build();
	        }
	    }

	    // Add a new category
	    @PostMapping("add")
	    public ResponseEntity<Category> addCategory(@RequestBody Category category) {
	        return ResponseEntity.ok(categoryService.addCategory(category));
	    }

	    // Update category
	    @PutMapping("/update/{id}")
	    public ResponseEntity<Category> updateCategory(@PathVariable int id, @RequestBody Category updatedCategory) {
	        Category category = categoryService.updateCategory(id, updatedCategory);
	        if (category != null) {
	            return ResponseEntity.ok(category);
	        } else {
	            return ResponseEntity.notFound().build();
	        }
	    }

	    // Delete category
	    @DeleteMapping("/delete/{id}")
	    public ResponseEntity<String> deleteCategory(@PathVariable int id) {
	        categoryService.deleteCategory(id);
	        return ResponseEntity.ok("Category Deleted Successfully");
	    }
	
}
