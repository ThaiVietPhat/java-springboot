package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class GreettingServicePropertyInjected implements GreetingService {
	@Autowired
	GreetingService greetingService;
	@Override
	public String sayGreeting() {
		// TODO Auto-generated method stub
		return "Hello World - Property";
	}

}
