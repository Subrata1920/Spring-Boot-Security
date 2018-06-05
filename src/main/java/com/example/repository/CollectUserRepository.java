package com.example.repository;

import org.springframework.data.repository.RepositoryDefinition;

import com.example.domain.CollectUser;

/**
 * 
 * @author subrata
 *
 */
@RepositoryDefinition(domainClass=CollectUser.class, idClass=Integer.class)
public interface CollectUserRepository {

	CollectUser findByUserName(String userName);
	
}
