package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class HELLO {
	@GetMapping("/")
	public String hello()
	{
		return "hi abhijith";
	}

}
