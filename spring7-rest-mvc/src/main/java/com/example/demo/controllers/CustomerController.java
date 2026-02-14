package com.example.demo.controllers;

import com.example.demo.model.CustomerDTO;
import com.example.demo.services.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@RestController
public class CustomerController {
    public static final String CUSTOMER_PATH = "/api/v1/customer";
    public static final String CUSTOMER_PATH_ID = CUSTOMER_PATH + "/{customerId}";

    private final CustomerService customerService;

    @PatchMapping(CUSTOMER_PATH_ID)
    public ResponseEntity updatePatchById(@PathVariable UUID customerId, @RequestBody CustomerDTO customer){
        customerService.updatePatchById(customerId, customer);
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping(CUSTOMER_PATH_ID)
    public ResponseEntity deleteById(@PathVariable UUID customerId){
        customerService.deleteCustomerById(customerId);
        return ResponseEntity.noContent().build();
    }
    @PutMapping(CUSTOMER_PATH_ID)
    public ResponseEntity updateById(@PathVariable UUID customerId, @RequestBody CustomerDTO customer){
        customerService.updateCustomerById(customerId,customer);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(CUSTOMER_PATH)
    public ResponseEntity handlePost(@RequestBody CustomerDTO customer){
        CustomerDTO savedCustomer = customerService.saveNewCustomer(customer);
        return ResponseEntity.created(URI.create(CUSTOMER_PATH + "/" + savedCustomer.getId())).build();
    }
    @GetMapping(CUSTOMER_PATH)
    public List<CustomerDTO> listCustomers(){
        return customerService.listCustomers();
    }

    @GetMapping(CUSTOMER_PATH_ID)
    public CustomerDTO getCustomerId(@PathVariable("customerId") UUID customerId){
        log.debug("Get Customer by Id - in controller");
        return customerService.getCustomerById(customerId);
    }
}
