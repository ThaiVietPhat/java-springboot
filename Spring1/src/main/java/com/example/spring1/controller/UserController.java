package com.example.spring1.controller;

import com.example.spring1.Service.UserService;
import com.example.spring1.dto.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class UserController {
    UserService userService=new UserService();
    @GetMapping("/user/list")
    public String list(Model model){
        List<User> users=userService.getAll();
        model.addAttribute("userList", users);
        return "users.html";
    }
    @GetMapping("/user/new")
    public String newUser(){
        return "new-user.html";
    }
    @PostMapping("/user/new")
    public String newUser(
//                          @RequestParam ("id") int id,
//                          @RequestParam ("name") String name,
//                          @RequestParam ("old" )  int old
            @ModelAttribute User user
    ){
        userService.create(user);
        return "redirect:/user/list";
    }
}
