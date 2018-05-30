package com.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@Order(1)
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private LoginAuthenticationSuccessHandler successHandler;
	
	@Autowired
	protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("subrata").password("subrata").roles("ADMIN");
		auth.inMemoryAuthentication().withUser("root").password("root").roles("DB");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		 http.authorizeRequests()
	        .antMatchers("/", "/home","/login").permitAll()
//	        .antMatchers("/secure").access("hasRole('ADMIN')")
//	        .antMatchers("/db/**").access("hasRole('ADMIN') and hasRole('DBA')")
	        .and().formLogin().loginPage("/login").successHandler(successHandler)
	        .usernameParameter("ssoId").passwordParameter("password")
	        .and().csrf().disable()
	        .exceptionHandling().accessDeniedPage("/Access_Denied");
		 
		 http.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login");
	}

}
