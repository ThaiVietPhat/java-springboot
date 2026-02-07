package com.example.demo.services;

import org.springframework.stereotype.Service;

@Service("greetingServiceImpl")
public class GreetingServiceImpl implements GreetingService {

	@Override
	public String sayGreeting() {
		// TODO Auto-generated method stub
		return "huan hoe" ;
	}

}
