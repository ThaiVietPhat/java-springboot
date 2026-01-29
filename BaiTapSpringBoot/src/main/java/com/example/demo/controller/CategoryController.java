package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Category;
import com.example.demo.service.CategoryService;

@Controller
public class CategoryController {
	@Autowired
	CategoryService categoryService;
	@GetMapping("/category")
	public String getListCategory(Model model) {
		model.addAttribute("listCategory",categoryService.getListCategories() );
		model.addAttribute("category", new Category());
		return "category";
	}
	@PostMapping("/category/new")
	public String newCategory(@ModelAttribute("category") Category category) {
	    categoryService.update(category);
	    return "redirect:/category";
	}

}
