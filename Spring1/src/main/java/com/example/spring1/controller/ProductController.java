package com.example.spring1.controller;

import com.example.spring1.Service.ProductService;
import com.example.spring1.dto.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ProductController {
    ProductService productService;
    @GetMapping("/product")
    public String product(){
        return "product";
    }
    @PostMapping("/product/new")
    public String createProduct(@ModelAttribute Product product){
        productService.createProduct(product);
        return "redirect:/product";
    }
    @GetMapping("/product/list")
    public String productList(Model model){
        List<Product> list=productService.getAllProduct();
        model.addAttribute("productList", list);
        return "product";
    }
}
