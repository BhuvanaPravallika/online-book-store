package com.nalajala.book.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nalajala.book.entity.Category;
import com.nalajala.book.repository.CategoryRepository;

@Service
public class CategoryService {
	
	 @Autowired
	    private CategoryRepository categoryRepository;

	    public List<Category> getAllCategories() {
	        return categoryRepository.findAll();
	    }

	    public Category getCategoryById(int id) {
	        return categoryRepository.findById(id).orElse(null);
	    }

	    public Category getCategoryByName(String name) {
	        return categoryRepository.findByCategory_Name(name);
	    }

	    public Category addCategory(Category category) {
	        return categoryRepository.save(category);
	    }

	    public void deleteCategory(int id) {
	        categoryRepository.deleteById(id);
	    }

	    public Category updateCategory(int id, Category updatedCategory) {
	        Category existing = categoryRepository.findById(id).orElse(null);
	        if (existing != null) {
	            existing.setCategory_Name(updatedCategory.getCategory_Name());
	            return categoryRepository.save(existing);
	        }
	        return null;
	    }
	
	
}
