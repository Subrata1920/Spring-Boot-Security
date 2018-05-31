package com.example.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.example.domain.CollectUser;
import com.example.domain.Role;
import com.example.model.CollectUserModel;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService{
	
	@Autowired
	private CollectUserService collectUserService;

	@Override
	public UserDetails loadUserByUsername(String userName)	throws UsernameNotFoundException {
		
		CollectUser collectUser = collectUserService.findByUserName(userName);
		
		if (collectUser == null) {
			throw new UsernameNotFoundException("Invalid username or password !");
		}

		Collection<Role> roles = collectUser.getRole();
		
		Collection<GrantedAuthority> grantedAuthority = new ArrayList<>();
		for (Role role : roles) {
			grantedAuthority.add(new SimpleGrantedAuthority("Role_"+role.getRoleName()));
			System.out.println(role.getRoleName());
		}
		/*roles.forEach(role->{
			grantedAuthority.add(new SimpleGrantedAuthority(role.getRoleName()));
			System.out.println(role.getRoleName());
		});*/
		
		return new CollectUserModel(collectUser.getUserName(), collectUser.getPassword(), collectUser.isEnabled(),
				!collectUser.isAccountExpired(), !collectUser.isCredentialexpired(), 
				!collectUser.isAccountLocked(), grantedAuthority,collectUser.getUserId(),roles);
	}

}
