package com.dev.app.microserviceusermanagement.service;

import java.util.List;

import com.dev.app.microserviceusermanagement.entity.User;

public interface UserService {

	User save(User user);
	
	User findByUsername(String username);
	
	List<String> findUsers(List<Long> idList);
}
