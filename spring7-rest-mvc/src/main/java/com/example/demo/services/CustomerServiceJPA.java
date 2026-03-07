package com.example.demo.services;

import com.example.demo.mappers.CustomerMapper;
import com.example.demo.models.CustomerDTO;
import com.example.demo.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
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
    public Optional<CustomerDTO> getCustomerById(UUID id) {
        return customerRepository.findById(id)
                .map(customerMapper::customerToCustomerDTO);
    }

    @Override
    public CustomerDTO saveNewCustomer(CustomerDTO customer) {
       return customerMapper.customerToCustomerDTO(customerRepository.save(customerMapper.customerDTOToCustomer(customer)));
    }

    @Override
    public Optional<CustomerDTO> updateCustomerById(UUID customerId, CustomerDTO customerDTO) {
        return customerRepository.findById(customerId)
                .map(foundCustomer -> {
                    foundCustomer.setCustomerName(customerDTO.getCustomerName());
                    foundCustomer.setLastModifiedDate(LocalDateTime.now());
                    return customerMapper.customerToCustomerDTO(customerRepository.save(foundCustomer));
                });
    }

    @Override
    public boolean deleteCustomerById(UUID customerId) {
        if(customerRepository.existsById(customerId)){
            customerRepository.deleteById(customerId);
            return true;
        }return false;
    }

    @Override
    public Optional<CustomerDTO> updatePatchById(UUID customerId, CustomerDTO customer) {
        return customerRepository.findById(customerId)
                .map(foundCustomer -> {
                    if(StringUtils.hasText(customer.getCustomerName())){
                        foundCustomer.setCustomerName(customer.getCustomerName());
                    }
                    if (customer.getCustomerEmail() != null){
                        foundCustomer.setCustomerEmail(customer.getCustomerEmail());
                    }
                    foundCustomer.setLastModifiedDate(LocalDateTime.now());
                    return customerMapper.customerToCustomerDTO(customerRepository.save(foundCustomer));
                });
    }
}
