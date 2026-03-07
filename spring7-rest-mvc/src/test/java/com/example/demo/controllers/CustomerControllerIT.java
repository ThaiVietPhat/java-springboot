package com.example.demo.controllers;

import com.example.demo.entities.Customer;
import com.example.demo.mappers.CustomerMapper;
import com.example.demo.models.CustomerDTO;
import com.example.demo.repositories.CustomerRepository;
import com.example.demo.services.CustomerService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CustomerControllerIT {
    @Autowired
    CustomerController customerController;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    private CustomerMapper customerMapper;
    @Qualifier("customerServiceJPA")
    @Autowired
    private CustomerService customerService;

    @Rollback
    @Transactional
    @Test
    void testNewCustomer(){
        CustomerDTO customerDTO = CustomerDTO.builder()
                .customerName("Galaxy Cat")
                .build();
        ResponseEntity responseEntity = customerController.handlePost(customerDTO);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(201);
        assertThat(responseEntity.getHeaders().getLocation()).isNotNull();
        String [] locationUUID = responseEntity.getHeaders().getLocation().toString().split("/");
        UUID savedUUID = UUID.fromString(locationUUID[4]);
        Customer customer = customerRepository.findById(savedUUID).get();
        assertThat(customer).isNotNull();
    }
    @Test
    void testGetId(){
        Customer customer = customerRepository.findAll().get(0);
        CustomerDTO customerDTO = customerController.getCustomerId(customer.getId());
        assertThat(customerDTO).isNotNull();
    }

}