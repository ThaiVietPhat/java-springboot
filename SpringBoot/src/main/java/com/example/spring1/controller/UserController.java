package com.example.spring1.controller;

import com.example.spring1.Service.UserService;
import com.example.spring1.dto.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class UserController {
	@Autowired //ID: dependency
    UserService userService;
	@GetMapping("/user/list")
	public String list(@RequestParam(required = false) String keyword, Model model) {
	    model.addAttribute("userList", userService.getUsers(keyword));
	    model.addAttribute("keyword", keyword);
	    return "users.html";
	}
	@GetMapping("/user/new")
	public String newUser(Model model) {
	    model.addAttribute("user", new User());
	    return "new-user.html";
	}
    @PostMapping("/user/new")
    public String newUser(
//                          @RequestParam ("id") int id,
//                          @RequestParam ("name") String name,
//                          @RequestParam ("old" )  int old
            @ModelAttribute("user") User user
    ){
        userService.create(user);
        return "redirect:/user/list";
    }
    @PostMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable("id") int id) {
    	userService.delete(id);
    	return "redirect:/user/list";
    }
    @GetMapping("user/edit/{id}")
    public String userEdit(@PathVariable("id")int id, Model model) {
    	User user=userService.getById(id);
    	model.addAttribute("user", user);
		return "edit-user";
    	
    }
    @PostMapping("/user/edit")
    public String userEdit(@ModelAttribute("user") User user) {
    	userService.update(user);
    	return "redirect:/user/list" ;
    }
}
