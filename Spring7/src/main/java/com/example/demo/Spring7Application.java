package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.example.demo.controllers.MyController;

@SpringBootApplication
public class Spring7Application {

	public static void main(String[] args) {
		ApplicationContext ctx= SpringApplication.run(Spring7Application.class, args);
		MyController controller= ctx.getBean(MyController.class);
		System.out.println("hh");
		System.out.println(controller.greet());
	}

}
