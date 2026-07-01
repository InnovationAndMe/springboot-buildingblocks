package com.stacksimplify.restservices.controller;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.stacksimplify.restservices.dto.UserDtoV1;
import com.stacksimplify.restservices.dto.UserDtoV2;
import com.stacksimplify.restservices.dto.UserMmDto;
import com.stacksimplify.restservices.entities.User;
import com.stacksimplify.restservices.exceptions.UserNotFoundException;
import com.stacksimplify.restservices.services.UserService;

import jakarta.validation.constraints.Min;

@RestController
@RequestMapping("/versioning/uri/users")
public class UserUriVersioningController {

	@Autowired
	private UserService userService;

	@Autowired
	private ModelMapper modelMapper;

	// URI based Versioning - V1
	@GetMapping({ "/v1.0/{id}", "/v1.1/{id}" })
	public UserDtoV1 getUserById(@PathVariable("id") @Min(1) Long id) throws UserNotFoundException {
		Optional<User> userOptional = userService.getUserById(id);

		if (userOptional.isEmpty()) {
			throw new UserNotFoundException("User Not found");
		}

		User user = userOptional.get();

		UserDtoV1 userMmDto = modelMapper.map(user, UserDtoV1.class);
		return userMmDto;

	}

	// URI based Versioning - V2
	@GetMapping({ "/v2.0/{id}"})
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
