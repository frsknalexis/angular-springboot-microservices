package com.dev.app.microserviceusermanagement.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dev.app.microserviceusermanagement.entity.User;
import com.dev.app.microserviceusermanagement.enums.Role;
import com.dev.app.microserviceusermanagement.service.UserService;

@RestController
public class UserRestController {

	@Autowired
	@Qualifier("userService")
	private UserService userService;
	
	@PostMapping("/service/registration")
	ResponseEntity<?> saveUser(@RequestBody User user) {
		if (userService.findByUsername(user.getUsername()) != null) {
			//Status code: 409
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		//Default role USER
		user.setRole(Role.USER);
		User userReturn = userService.save(user);
		return new ResponseEntity<>(userReturn, HttpStatus.CREATED);
	}
	
	@GetMapping("/service/login")
	ResponseEntity<?> getUser(Principal principal) {
		if (principal == null || principal.getName() == null) {
			return new ResponseEntity<>(HttpStatus.OK);
		}
		//username = principal.getName()
		User user = userService.findByUsername(principal.getName());
		return ResponseEntity.ok(user);
	}
	
	@PostMapping("/services/names")
	ResponseEntity<?> getNamesOfUsers(@RequestBody List<Long> idList) {
		List<String> userNames = userService.findUsers(idList);
		return ResponseEntity.ok(userNames);
	}
}
