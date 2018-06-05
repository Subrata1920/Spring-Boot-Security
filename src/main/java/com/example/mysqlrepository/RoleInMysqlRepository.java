package com.example.mysqlrepository;

import javax.transaction.Transactional;

import org.springframework.data.repository.RepositoryDefinition;

import com.example.mysqldomain.RoleInMysql;



/**
 * 
 * @author subrata
 *
 */
@RepositoryDefinition(domainClass=RoleInMysql.class, idClass=Integer.class)
public interface RoleInMysqlRepository {

	@Transactional
	void save(RoleInMysql roleInMysql);
	
	
}
