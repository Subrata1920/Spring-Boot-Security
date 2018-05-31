package com.example.config;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.service.CustomUserDetailsService;

@Component
public class CustomAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider{
	
	@Autowired
	private CustomUserDetailsService  customUserDetailsService;
	
//	@Autowired
//	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails,	UsernamePasswordAuthenticationToken authenticationToken) throws AuthenticationException {
		if (authenticationToken.getCredentials() == null || userDetails.getPassword() == null) {
			throw new BadCredentialsException("Credentials cannot be null");
		}
		Collection<GrantedAuthority> authorities = authenticationToken.getAuthorities();
		boolean authenticated = authenticationToken.isAuthenticated();
//		if (!bCryptPasswordEncoder.matches((String) authenticationToken.getCredentials(),userDetails.getPassword())) {
//			throw new BadCredentialsException("Invalid Credentials !");
//		}
		
		System.out.println("Is Authenticated - > "+authenticated);
	}

	@Override
	protected UserDetails retrieveUser(String userName,	
			UsernamePasswordAuthenticationToken token) throws AuthenticationException {
		
		return customUserDetailsService.loadUserByUsername(userName);
	}

	
}
