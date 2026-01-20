package com.example.spring1.controller;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.coyote.Request;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

@Controller
public class LoginController {
    @GetMapping("/login")
    public String login(){
        return "login.html";
    }
    @PostMapping("/login")
    public String login(HttpSession session,
                      @RequestParam("username") String username,
                      @RequestParam("password") String password
     ) throws IOException {
        if(username.equals("admin")&& password.equals("123")){
            session.setAttribute("username",username);
            return "redirect:/hello";
        }else{
            return "redirect:/login";
        }

    }
}
