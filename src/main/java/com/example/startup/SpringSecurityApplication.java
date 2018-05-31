package com.example.startup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@SpringBootApplication
@EnableGlobalMethodSecurity(
		  prePostEnabled = true, //This property enables Spring Security pre/post annotations
		  securedEnabled = true, //This property determines if the @Secured annotation should be enabled
		  jsr250Enabled = true)  //This property allows us to use the @RoleAllowed annotation
@ComponentScan(value={"com.example"})
public class SpringSecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityApplication.class, args);
	}
}

