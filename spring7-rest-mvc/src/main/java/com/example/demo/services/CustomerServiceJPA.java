package com.example.demo.services;

import com.example.demo.mappers.CustomerMapper;
import com.example.demo.model.CustomerDTO;
import com.example.demo.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
@Service
@Primary
@RequiredArgsConstructor
public class CustomerServiceJPA implements CustomerService{
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    @Override
    public List<CustomerDTO> listCustomers() {
        return customerRepository.findAll()
                .stream()
                .map(customerMapper::customerToCustomerDTO)
                .toList();
    }

    @Override
    public CustomerDTO getCustomerById(UUID id) {
        return null;
    }

    @Override
    public CustomerDTO saveNewCustomer(CustomerDTO customer) {
        return null;
    }

    @Override
    public void updateCustomerById(UUID customerId, CustomerDTO customer) {

    }

    @Override
    public void deleteCustomerById(UUID customerId) {

    }

    @Override
    public void updatePatchById(UUID customerId, CustomerDTO customer) {

    }
}
