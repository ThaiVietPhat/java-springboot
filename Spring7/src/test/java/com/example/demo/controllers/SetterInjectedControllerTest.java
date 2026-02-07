package com.example.demo.controllers;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.services.GreetingServiceImpl;
@SpringBootTest
class SetterInjectedControllerTest {
	@Autowired
	SetterInjectedController setterInjectedController;

	/*
	 * @BeforeEach void setUp() throws Exception { setterInjectedController= new
	 * SetterInjectedController(); setterInjectedController.setGreetingService(new
	 * GreetingServiceImpl()); }
	 */
	

	@Test
	void testGetGreeting() {
		System.out.println(setterInjectedController.getGreeting());
	}

}
