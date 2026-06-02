package phat.springframework.springsecurity.mappers;

import org.mapstruct.Mapper;
import phat.springframework.springsecurity.dto.CustomerDTO;
import phat.springframework.springsecurity.model.Customer;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    CustomerDTO customerToCustomerDTO(Customer customer);
    Customer customerDTOToCustomer(CustomerDTO customerDTO);
}
