package com.example.demo.controllers;
import org.springframework.stereotype.Controller;

import com.example.demo.services.GreetingService;
import com.example.demo.services.GreetingServiceImpl;

@Controller
public class MyController {
	private final GreetingService greetingService;
	public MyController() {
		this.greetingService = new GreetingServiceImpl();
	}
	public String greet() {
		System.out.println("Phat dz");
		return "Hello, World!";
	}

}
