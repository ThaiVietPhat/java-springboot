package com.example.demo.mappers;

import com.example.demo.entities.Customer;
import com.example.demo.models.CustomerDTO;
import org.mapstruct.Mapper;

@Mapper
public interface CustomerMapper {
    Customer customerDTOToCustomer(CustomerDTO dto);
    CustomerDTO customerToCustomerDTO(Customer Customer);
}
