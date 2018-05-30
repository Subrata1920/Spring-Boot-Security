package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.service.TestService;

@Controller
public class HomeController {
	
	@Autowired
	private TestService testService;
	
	@GetMapping(value={"/home","/", "/login"})
	public String home(){
		return "login";
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping(value="/secure")
	public String secure(){
		return "secure";
	}

	@GetMapping(value="/success")
	public String success(){
		return "success";
	}
	
	@GetMapping(value="/Access_Denied")
	public String accessDenied(){
		return "Access_Denied";
	}

}
