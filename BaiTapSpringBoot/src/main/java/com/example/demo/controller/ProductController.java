package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.Product;
import com.example.demo.repository.ProductRepo;
import com.example.demo.service.CategoryService;
import com.example.demo.service.ProductService;


@Controller
public class ProductController {

    private final ProductRepo productRepo;
	@Autowired
    private ProductService productService;
	@Autowired
	private CategoryService categoryService;
    ProductController(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }
	@GetMapping("/product")
	public String productList(Model model) {
		model.addAttribute("productList", productService.getListProduct());
		model.addAttribute("product", new Product());
		model.addAttribute("categoryList", categoryService.getListCategories());
		return "product";
	}
	@PostMapping("/product/new")
	public String newProduct(@ModelAttribute("product") Product product) {
		productService.update(product);
		
		return "redirect:/product";
	}

}
