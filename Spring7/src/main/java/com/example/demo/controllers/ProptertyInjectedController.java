package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.example.demo.services.GreetingService;
@Controller
public class ProptertyInjectedController {
	@Autowired
	GreetingService greetingService;
	public String getGreeting() {
		return greetingService.sayGreeting();
	}
}
