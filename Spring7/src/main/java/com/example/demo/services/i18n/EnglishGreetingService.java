package com.example.demo.services.i18n;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.example.demo.services.GreetingService;
@Profile({"EN","default"})
@Service("i18NService")
public class EnglishGreetingService implements GreetingService {

	@Override
	public String sayGreeting() {
		return "Hello - English Greeting Service" ;
	}

}
