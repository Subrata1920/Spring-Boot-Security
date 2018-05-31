package com.example.model;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.example.domain.Role;


public class CollectUserModel extends User{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4431662893129587625L;

	private Integer userId;
	
	private Collection<Role> role;
	private CollectUserModel(String username, String password, boolean enabled, boolean accountNonExpired,
			boolean credentialsNonExpired, boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
		
	}
	
	
	public CollectUserModel(String username, String password, boolean enabled, boolean accountNonExpired,
			boolean credentialsNonExpired, boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities,Integer userId,Collection<Role> role) {
		
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
		
		this.userId=userId;
		this.role=role;
		
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Collection<Role> getRole() {
		return role;
	}

	public void setRole(Collection<Role> role) {
		this.role = role;
	}
	
}