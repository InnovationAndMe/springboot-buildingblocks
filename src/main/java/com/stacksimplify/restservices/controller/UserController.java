package com.stacksimplify.restservices.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.stacksimplify.restservices.entities.User;
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
	public User createUser(@RequestBody User user) {
		
		return userService.createUSer(user);
			
	}
	
	@GetMapping("/users/{id}")
	public User getUserById(@PathVariable("id") Long id) {
		
		return userService.getUserById(id);
			
	}
	
	@PutMapping("/users/{id}/{lastName}")
	public User updateUserBydId(@PathVariable("id") Long id,@PathVariable("lastName") String lastName) {
		User user = userService.getUserById(id);
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
