package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Product;
import com.example.demo.repository.ProductRepo;

@Service
public class ProductService {
	@Autowired
	ProductRepo productRepo;
	public List<Product> getListProduct() {
		return productRepo.findAll();
	}
	public void update(Product product) {
		productRepo.save(product);
	}
}
