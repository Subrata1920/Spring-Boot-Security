package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.CollectUser;
import com.example.repository.CollectUserRepository;

@Service
public class CollectUserServiceImpl implements CollectUserService {
	
	@Autowired
	private CollectUserRepository collectUserRepository;
	

	@Override
	public CollectUser findByUserName(String userName) {
		return collectUserRepository.findByUserName(userName);
	}

}
