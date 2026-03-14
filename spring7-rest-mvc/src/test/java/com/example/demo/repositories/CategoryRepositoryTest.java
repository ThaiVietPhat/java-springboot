package com.example.demo.repositories;

import com.example.demo.entities.Beer;
import com.example.demo.entities.Category;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
@SpringBootTest
class CategoryRepositoryTest {
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    BeerRepository beerRepository;
    Beer testBeer;
    @BeforeEach
    void setUp() {
        testBeer = beerRepository.findAll().get(0);
    }
    @Test
    @Transactional
    void testAddCategory(){
        Category savedCat = categoryRepository.save(Category.builder()
                        .description("test")
                .build());
        testBeer.getCategories().add(savedCat);
        Beer savedBeer = beerRepository.save(testBeer);
        System.out.println(savedBeer.getBeerName());
    }

}