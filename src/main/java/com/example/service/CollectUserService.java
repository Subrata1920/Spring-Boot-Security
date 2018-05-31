package com.example.service;

import com.example.domain.CollectUser;

public interface CollectUserService {
	
	CollectUser findByUserName(String userName);
}
