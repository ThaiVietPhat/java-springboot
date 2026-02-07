package com.example.demo.services;

import org.springframework.stereotype.Service;

@Service("setterGreetingBean")
public class GreettingServiceSetterInjection implements GreetingService {

	@Override
	public String sayGreeting() {
		// TODO Auto-generated method stub
		return "Hello from the SETTER INJECTION Bean" ;
	}
	

}
