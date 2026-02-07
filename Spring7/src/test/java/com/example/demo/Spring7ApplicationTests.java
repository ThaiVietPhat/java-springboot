package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import com.example.demo.controllers.MyController;

@SpringBootTest
class Spring7ApplicationTests {
	@Autowired
	ApplicationContext applicationContext;
	@Autowired
	MyController controller;
	@Test
	void testGetControllerFromctx(){
		MyController controller= applicationContext.getBean(MyController.class);
		System.out.println("hh");
		System.out.println(controller.greet());
	}
	@Test
	void testAutoWiredController(){
		System.out.println(controller.greet());
	}
	@Test
	void contextLoads() {
	}

}
