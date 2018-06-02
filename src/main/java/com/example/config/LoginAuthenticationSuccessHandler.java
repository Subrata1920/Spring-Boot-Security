package com.example.config;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

/**
 * 
 * @author subrata
 *
 */
@Component
public class LoginAuthenticationSuccessHandler implements AuthenticationSuccessHandler{
	
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
								throws IOException, ServletException {
		
		Set<String> listOfAuthorities = new HashSet<>();
		authentication.getAuthorities().forEach(value->listOfAuthorities.add(value.getAuthority().split(":")[0]));
		
		if(listOfAuthorities.contains("State_level_user")){
			redirectStrategy.sendRedirect(request, response, "/stateSOE");
		}else if(listOfAuthorities.contains("District_level_user")){
			redirectStrategy.sendRedirect(request, response, "/districtSOE");
		}else if(listOfAuthorities.contains("NGO_level_user")){
			redirectStrategy.sendRedirect(request, response, "/ngoSOE");
		}else{
			redirectStrategy.sendRedirect(request, response, "/Access_Denied");
		}
		
		/*
		 Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		 
		 authorities.forEach(authority -> {
			if("District_level_user".equals(authority.getAuthority().split(":")[0]) || "Role_ADMIN".equals(authority.getAuthority())) {
				try {
					redirectStrategy.sendRedirect(request, response, "/success");
				} catch (Exception e) {
					e.printStackTrace();
				}
			} 
		});*/
		
	}

}
