package com.example.demo.controllers.i18n;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
//@ActiveProfiles("")
@SpringBootTest
class Myi18NControllerTest {
	@Autowired
	Myi18NController controller;

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void testSayHello() {
		System.out.println(controller.sayHello());
	}

}
