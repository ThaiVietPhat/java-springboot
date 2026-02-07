package com.example.demo.controllers;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.services.GreetingServiceImpl;
@SpringBootTest
class ProptertyInjectedControllerTest {
	
	/*
	 * @BeforeAll static void setUpBeforeClass() throws Exception { }
	 * 
	 * @BeforeEach void setUp() throws Exception { propertyInjectedController= new
	 * ProptertyInjectedController(); propertyInjectedController.greetingService=
	 * new GreetingServiceImpl(); }
	 */
	@Autowired
	ProptertyInjectedController propertyInjectedController;

	@Test
	void testGetGreeting() {
		System.out.println(propertyInjectedController.getGreeting());
	}

}
