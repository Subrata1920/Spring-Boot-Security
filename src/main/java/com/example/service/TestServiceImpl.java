package com.example.service;

import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements TestService {
	
	@Override
	public String getString() {
		System.out.println("I am in getString()");
		return "Secured page";
	}
	
	@Override
	public String permitAll() {
		System.out.println("I am in permitAll()");
		return "permitAll page";
	}

}
