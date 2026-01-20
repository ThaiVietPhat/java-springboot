package com.example.spring1.Service;

import com.example.spring1.dto.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductService {
    private static List<Product> listProduct= new ArrayList<>();
    public void createProduct(Product product){
        listProduct.add(product);
    }
    public List<Product> getAllProduct(){
        return listProduct;
    }
}
