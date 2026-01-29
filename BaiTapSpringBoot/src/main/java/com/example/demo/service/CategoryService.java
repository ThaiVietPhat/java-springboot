package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Category;
import com.example.demo.repository.CategoryRepo;
@Service
public class CategoryService {
	@Autowired
	CategoryRepo categoryRepo;
	public List<Category> getListCategories(){
		return categoryRepo.findAll();
	}
	public void update(Category category) {
		categoryRepo.save(category);
	}

}
