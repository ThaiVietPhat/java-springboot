package com.example.demo.controllers.i18n;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import com.example.demo.services.GreetingService;
@Controller
public class Myi18NController {
	private final GreetingService greetingService;
	
	public Myi18NController(@Qualifier("i18NService") GreetingService greetingService) {
		
		this.greetingService = greetingService;
	}

	public String sayHello() {
		return greetingService.sayGreeting();
	}

}
