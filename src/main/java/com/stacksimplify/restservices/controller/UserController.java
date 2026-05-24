package com.stacksimplify.restservices.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import com.stacksimplify.restservices.entities.User;
import com.stacksimplify.restservices.exceptions.UserExistsException;
import com.stacksimplify.restservices.exceptions.UserNotFoundException;
import com.stacksimplify.restservices.services.UserService;

//Controller
@RestController
public class UserController {

	@Autowired
	private UserService userService;
	
	//getAllUSers
	@GetMapping("/users")
	public List<User> getAllUsers(){
		return userService.getAllUsers();
		
	}
	
	@PostMapping("/users")
	public ResponseEntity<Void> createUser(@RequestBody User user, UriComponentsBuilder builder) {
		try {
			userService.createUSer(user);
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(builder.path("/users/{id}").
					buildAndExpand(user.getId()).toUri());
			return new ResponseEntity<Void>(headers,HttpStatus.CREATED);
		} catch (UserExistsException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}			
	}
	
	@GetMapping("/users/{id}")
	public User getUserById(@PathVariable("id") Long id) {
		
		try {
			return userService.getUserById(id);
		} catch (UserNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
			
	}
	
	@PutMapping("/users/{id}/{lastName}")
	public User updateUserBydId(@PathVariable("id") Long id,@PathVariable("lastName") String lastName) {
		User user;
		try {
			user = userService.getUserById(id);
		} catch (UserNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
		return userService.updateUserById(lastName,user);
			
	}
	
	@DeleteMapping("/users/{id}")
	public void updateUserBydId(@PathVariable("id") Long id) {
		userService.deleteUserById(id);
			
	}

	@GetMapping("/users/userName/{userName}")
	public User updateUserBydId(@PathVariable("userName") String userName) {
		return userService.getUserByUserName(userName);
			
	}


}
