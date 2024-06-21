package com.example.springboot;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	@GetMapping("/api")
	public String api() {
		return "Greetings from Spring Boot!";
	}

	@GetMapping("/hello")
	public String hello() {
		return "Hello from Spring Boot!";
	}

}
