package phat.springframework.springsecurity.controllers;

import jdk.jfr.Registered;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import phat.springframework.springsecurity.dto.CustomerDTO;
import phat.springframework.springsecurity.mappers.CustomerMapper;
import phat.springframework.springsecurity.model.Customer;
import phat.springframework.springsecurity.repositories.CustomerRepository;

@RequiredArgsConstructor
@RestController
public class UserController {
    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;
    private final CustomerMapper customerMapper;
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody CustomerDTO customerDTO) {
        try {
            String hashPwd = passwordEncoder.encode(customerDTO.getPwd());
            customerDTO.setPwd(hashPwd);
            Customer customer = customerMapper.customerDTOToCustomer(customerDTO);
            Customer savedCustomer = customerRepository.save(customer);
            if(savedCustomer.getId()>0){
                return ResponseEntity.status(HttpStatus.CREATED)
                        .body("SUCCESSFULLY REGISTERED USER");
            }else{
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("FAILED TO REGISTER USER");
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }
}
