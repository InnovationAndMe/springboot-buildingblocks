package com.stacksimplify.restservices.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stacksimplify.restservices.entities.User;
import com.stacksimplify.restservices.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public List<User> getAllUsers(){
		
		return userRepository.findAll();
	}

	public User createUSer(User users) {
		
		return userRepository.save(users);
			
		}
	
	public User getUserById(Long id) {
		
		return userRepository.findById(id).get();
			
	}


	public User updateUserById(String lastName, User user) {
		user.setLastName(lastName);
		return userRepository.save(user);
	}
	
	public void deleteUserById(Long id) {
		if(userRepository.findById(id).get() != null) {
			userRepository.deleteById(id);
		}
	}
	
 public User getUserByUserName(String userName) {
		
		return userRepository.findByUserName(userName);
			
	}

}
