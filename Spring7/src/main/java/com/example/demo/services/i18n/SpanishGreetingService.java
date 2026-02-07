package com.example.demo.services.i18n;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.example.demo.services.GreetingService;
@Profile("ES")
@Service("i18NService")
public class SpanishGreetingService implements GreetingService {

	@Override
	public String sayGreeting() {
		return "Spannnnnn";
	}

}
