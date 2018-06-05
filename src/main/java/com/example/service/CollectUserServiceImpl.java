package com.example.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.CollectUser;
import com.example.domain.RoleInPostgresql;
import com.example.mysqldomain.RoleInMysql;
import com.example.mysqlrepository.RoleInMysqlRepository;
import com.example.repository.CollectUserRepository;
import com.example.repository.RoleInPostgresqlRepository;

/**
 * 
 * @author subrata
 *
 */
@Service
public class CollectUserServiceImpl implements CollectUserService {
	
	@Autowired
	private CollectUserRepository collectUserRepository;
	
	@Autowired
	private RoleInPostgresqlRepository testRepository;
	
	@Autowired
	private RoleInMysqlRepository testRepository1;

	@Override
	public CollectUser findByUserName(String userName) {
		return collectUserRepository.findByUserName(userName);
	}
	/**
	 * Remove the Transactional annotation and check both the Database.
	 */
	@Override
	@Transactional
	public void save() {
		
		List<String> list = new ArrayList<>(Arrays.asList("A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P"));
		
		List<RoleInPostgresql> listOfTest = new ArrayList<>();
		list.forEach(value->{
			RoleInPostgresql test = new RoleInPostgresql();
			test.setRoleName(value);
			listOfTest.add(test);
		});
		int i = 8;
		testRepository.save(listOfTest);
		
		for (String string : list) {
			RoleInMysql test = new RoleInMysql();
			test.setRoleName(string);
			testRepository1.save(test);
			i++;
			if(i==10)
				throw new RuntimeException();
		}
		
	}

}
