package com.example.repository;

import javax.transaction.Transactional;

import org.springframework.data.repository.RepositoryDefinition;

import com.example.domain.RoleInPostgresql;


/**
 * 
 * @author subrata
 *
 */
@RepositoryDefinition(domainClass=RoleInPostgresql.class, idClass=Integer.class)
public interface RoleInPostgresqlRepository {

	@Transactional
	void save(Iterable<RoleInPostgresql> list);
	
	
}
