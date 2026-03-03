package com.example.demo.services;

import com.example.demo.model.CustomerDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CustomerService {
    List<CustomerDTO> listCustomers();
    Optional<CustomerDTO> getCustomerById(UUID id);
    CustomerDTO saveNewCustomer(CustomerDTO customer);
    Optional<CustomerDTO> updateCustomerById(UUID customerId, CustomerDTO customer);
    boolean deleteCustomerById(UUID customerId);
    Optional<CustomerDTO> updatePatchById(UUID customerId, CustomerDTO customer);
}
