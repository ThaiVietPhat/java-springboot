package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import com.example.demo.services.GreetingService;
@Controller

public class ConstructorInjectedController {
	
	private final GreetingService greetingService;
	
	public ConstructorInjectedController(@Qualifier("greetingServiceImpl") GreetingService greetingService) {
		
		this.greetingService = greetingService;
	}

	public String getGreeting() {
		return greetingService.sayGreeting();
	}
}
