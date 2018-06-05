package com.example.service;

import com.example.domain.CollectUser;
/**
 * 
 * @author subrata
 *
 */
public interface CollectUserService {
	
	CollectUser findByUserName(String userName);
}
