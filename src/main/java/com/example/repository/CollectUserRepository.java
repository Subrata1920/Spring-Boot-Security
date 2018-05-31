package com.example.repository;

import org.springframework.data.repository.RepositoryDefinition;

import com.example.domain.CollectUser;

@RepositoryDefinition(domainClass=CollectUser.class, idClass=Integer.class)
public interface CollectUserRepository {

	CollectUser findByUserName(String userName);
	
}
