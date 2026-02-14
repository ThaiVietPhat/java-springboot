package com.example.demo.repositories;

import com.example.demo.entities.Beer;
import com.example.demo.entities.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class CustomerRepositoryTest {
    @Autowired
    CustomerRepository customerRepository;
    @Test
    void testSaveBeer(){
        Customer savedCustomer = customerRepository.save(Customer.builder()
                .customerName("Galaxy Cat")
                .build());

        assertThat(savedCustomer).isNotNull();
        assertThat(savedCustomer.getId()).isNotNull();
    }

}