package com.example.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.domain.CollectUser;
import com.example.domain.RoleFeaturePermissionScheme;
import com.example.domain.UserAreaMapping;
import com.example.domain.UserRoleFeaturePermissionMapping;
import com.example.model.CollectUserModel;

/**
 * 
 * @author subrata
 *
 */
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

		Collection<UserAreaMapping> userAreaMappings = collectUser.getUserAreaMappings();
		
		Collection<GrantedAuthority> grantedAuthority = new ArrayList<>();
		
		for (UserAreaMapping userAreaMapping : userAreaMappings) {
			List<UserRoleFeaturePermissionMapping> userRoleFeaturePermissionMappings = userAreaMapping.getUserRoleFeaturePermissionMappings();
			
			for (UserRoleFeaturePermissionMapping userRoleFeaturePermissionMapping : userRoleFeaturePermissionMappings) {
				RoleFeaturePermissionScheme roleFeaturePermissionScheme = userRoleFeaturePermissionMapping.getRoleFeaturePermissionScheme();
				
				grantedAuthority.add(new SimpleGrantedAuthority(roleFeaturePermissionScheme.getRole().getRoleName().concat(":").
						
						concat(roleFeaturePermissionScheme.getFeaturePermissionMapping().getFeature().getFeatureName()).concat("(")
						
						.concat(roleFeaturePermissionScheme.getFeaturePermissionMapping().getPermission().getPermissionName()).concat(")")));
			}
		}
		
		return new CollectUserModel(collectUser.getUserName(), collectUser.getPassword(), collectUser.isEnabled(),
				!collectUser.isAccountExpired(), !collectUser.isCredentialexpired(), 
				!collectUser.isAccountLocked(), grantedAuthority,collectUser.getUserId(),userAreaMappings);
	}

}
