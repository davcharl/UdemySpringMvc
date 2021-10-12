package com.luv2code.springbootdemo.mycoolapp.rest;

import java.time.LocalDateTime;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FunRestController {
	
	// expose an endpoint that will return "hello world"
	@GetMapping("/")
	public String sayHello() {
		return "Hello World at: " + LocalDateTime.now();
	}

}
