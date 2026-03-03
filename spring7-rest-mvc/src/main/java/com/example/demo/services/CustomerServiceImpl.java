package com.example.demo.services;

import com.example.demo.model.CustomerDTO;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.*;
@Service
public class CustomerServiceImpl implements CustomerService{
    private Map<UUID, CustomerDTO> customerMap;

    public CustomerServiceImpl(){
        this.customerMap=new HashMap<>();
        CustomerDTO customer1= CustomerDTO.builder()
                .id(UUID.randomUUID())
                .customerName("Phat")
                .version(11)
                .createdDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now())
                .build();
        CustomerDTO customer2= CustomerDTO.builder()
                .id(UUID.randomUUID())
                .customerName("Quynh")
                .version(11)
                .createdDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now())
                .build();
        CustomerDTO customer3= CustomerDTO.builder()
                .id(UUID.randomUUID())
                .customerName("Diem")
                .version(11)
                .createdDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now())
                .build();
        this.customerMap.put(customer1.getId(),customer1);
        this.customerMap.put(customer2.getId(),customer2);
        this.customerMap.put(customer3.getId(),customer3);
    }
    @Override
    public List<CustomerDTO> listCustomers() {
        return new ArrayList<>(customerMap.values());
    }

    @Override
    public Optional<CustomerDTO> getCustomerById(UUID id) {
        return Optional.of(customerMap.get(id));
    }

    @Override
    public CustomerDTO saveNewCustomer(CustomerDTO customer) {
        CustomerDTO savedCustomer = CustomerDTO.builder()
                .id(UUID.randomUUID())
                .customerName(customer.getCustomerName())
                .version(1)
                .lastModifiedDate(LocalDateTime.now())
                .createdDate(LocalDateTime.now())
                .build();
        customerMap.put(savedCustomer.getId(),savedCustomer);
        return savedCustomer;

    }

    @Override
    public Optional<CustomerDTO> updateCustomerById(UUID customerId, CustomerDTO customer) {
        CustomerDTO existing = customerMap.get(customerId);
        existing.setCustomerName(customer.getCustomerName());;
        customerMap.put(existing.getId(), existing);
        return Optional.of(existing);
    }

    @Override
    public boolean deleteCustomerById(UUID customerId) {
        customerMap.remove(customerId);
        return true;
    }
    @Override
    public Optional<CustomerDTO> updatePatchById(UUID customerId, CustomerDTO customer) {
        CustomerDTO existing = customerMap.get(customerId);
        if (StringUtils.hasText(customer.getCustomerName())){
            existing.setCustomerName(customer.getCustomerName());

        }
        return Optional.of(existing);
    }
}
