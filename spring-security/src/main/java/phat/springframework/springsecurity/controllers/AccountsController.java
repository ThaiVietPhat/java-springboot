package phat.springframework.springsecurity.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountsController {
    @GetMapping("/accounts")
    public String getDetails() {
        return "Welcome to accouts";
    }

}
