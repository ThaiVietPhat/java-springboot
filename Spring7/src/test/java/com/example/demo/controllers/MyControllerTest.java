package com.example.demo.controllers;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MyControllerTest {

	@Test
	void testGreet() {
		MyController controller= new MyController();
		System.out.println(controller.greet());
	}

}
