package com.example.demo.repositories;

import com.example.demo.entities.Beer;
import com.example.demo.entities.BeerOrder;
import com.example.demo.entities.BeerOrderShipment;
import com.example.demo.entities.Customer;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BeerOrderRepositoryTest {
    @Autowired
    BeerOrderRepository beerOrderRepository;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    BeerRepository beerRepository;
    Customer testCustomer;
    Beer testBeer;
    @BeforeEach
    void setUp() {
        testCustomer = customerRepository.findAll().get(0);
        testBeer = beerRepository.findAll().get(0);
    }
    @Test
    void testBeerOrderRepository(){
        System.out.println(beerOrderRepository.count());
        System.out.println(customerRepository.count());
        System.out.println(beerRepository.count());
        System.out.println(testBeer.getBeerName());
        System.out.println(testCustomer.getCustomerName());
    }
    @Transactional
    @Test
    void testBeerOrders(){
        BeerOrder beerOrder = BeerOrder.builder()
                .customerRef("test order")
                .build();
        beerOrder.setCustomer(testCustomer);
        beerOrder.setBeerOrderShipment(BeerOrderShipment.builder()
                        .trackingNumber("123456789")
                .build());
        BeerOrder savedBeerOrder = beerOrderRepository.saveAndFlush(beerOrder);
        System.out.println(savedBeerOrder.getCustomerRef());
    }
}