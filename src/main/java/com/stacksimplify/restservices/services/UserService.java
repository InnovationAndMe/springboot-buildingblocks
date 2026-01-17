package com.stacksimplify.restservices.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.stacksimplify.restservices.entities.User;
import com.stacksimplify.restservices.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	//getAllUsers
	public List<User> getAllUsers(){
		List<User> list = userRepository.findAll();
		return list;
	}
	
	public User createUser(User user) {
		return userRepository.save(user);
	}

	public Optional<User> getUserById(Long id) {
		Optional<User> user =  userRepository.findById(id);
		return user;
	}
	
	public User updateUserById(Long id, User user) {
		user.setId(id);
		return userRepository.save(user);
	}
	
	public void deleteByUserById(@PathVariable("id") Long id) {
		if(userRepository.findById(id).isPresent()) {
			userRepository.deleteById(id);
		}
	}
	
	public User getUserByUserName(String userName) {
		return userRepository.findByUserName(userName);
		
	}
	
}
