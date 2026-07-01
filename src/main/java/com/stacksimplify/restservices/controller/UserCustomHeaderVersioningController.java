package com.stacksimplify.restservices.controller;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stacksimplify.restservices.dto.UserDtoV1;
import com.stacksimplify.restservices.dto.UserDtoV2;
import com.stacksimplify.restservices.entities.User;
import com.stacksimplify.restservices.exceptions.UserNotFoundException;
import com.stacksimplify.restservices.services.UserService;

import jakarta.validation.constraints.Min;

@RestController
@RequestMapping("/versioning/header/users")
public class UserCustomHeaderVersioningController {

	@Autowired
	private UserService userService;

	@Autowired
	private ModelMapper modelMapper;

	//Custom Header based Versioning - V1
	@GetMapping(value = "/{id}",headers= "API-VERSION=1")
	public UserDtoV1 getUserById(@PathVariable("id") @Min(1) Long id) throws UserNotFoundException {
		Optional<User> userOptional = userService.getUserById(id);

		if (userOptional.isEmpty()) {
			throw new UserNotFoundException("User Not found");
		}

		User user = userOptional.get();

		UserDtoV1 userMmDto = modelMapper.map(user, UserDtoV1.class);
		return userMmDto;

	}

	//Custom Header based Versioning - V2
	@GetMapping(value = "/{id}",headers= "API-VERSION=2")
	public UserDtoV2 getUserById2(@PathVariable("id") @Min(1) Long id) throws UserNotFoundException {
		Optional<User> userOptional = userService.getUserById(id);

		if (userOptional.isEmpty()) {
			throw new UserNotFoundException("User Not found");
		}

		User user = userOptional.get();

		UserDtoV2 userMmDto = modelMapper.map(user, UserDtoV2.class);
		return userMmDto;

	}
}
