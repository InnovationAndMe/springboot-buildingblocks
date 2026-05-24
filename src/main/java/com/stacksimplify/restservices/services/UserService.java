package com.stacksimplify.restservices.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.stacksimplify.restservices.entities.User;
import com.stacksimplify.restservices.exceptions.UserExistsException;
import com.stacksimplify.restservices.exceptions.UserNotFoundException;
import com.stacksimplify.restservices.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public List<User> getAllUsers(){
		
		return userRepository.findAll();
	}

	public User createUSer(User users) throws UserExistsException {
		if(userRepository.findByUserName(users.getUserName()) != null) {
			throw new UserExistsException("User already exists");
		}
		return userRepository.save(users);
			
	}
	
	public User getUserById(Long id) throws UserNotFoundException{
		Optional<User> optionalUser = userRepository.findById(id);
	    User user = null;
		if(optionalUser.isEmpty()) {
			throw new UserNotFoundException("No user is returned from the repository");
		}else {
			user = optionalUser.get();
		}
		
		return user;			
	}


	public User updateUserById(String lastName, User user) {
		user.setLastName(lastName);
		return userRepository.save(user);
	}
	
	public void deleteUserById(Long id) {
		Optional<User> optionalUser = userRepository.findById(id);
		if(optionalUser.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No user is returned from the repository");
		}else {
			userRepository.deleteById(id);
		}
	}
	
	public User getUserByUserName(String userName) {
		
		return userRepository.findByUserName(userName);
			
	}
	

}
