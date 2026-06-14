package com.stacksimplify.restservices.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.WebProperties.Resources;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stacksimplify.restservices.entities.Order;
import com.stacksimplify.restservices.entities.User;
import com.stacksimplify.restservices.exceptions.UserNotFoundException;
import com.stacksimplify.restservices.repositories.OrderRepository;
import com.stacksimplify.restservices.repositories.UserRepository;
import org.springframework.hateoas.CollectionModel;

@RestController
@RequestMapping(value = "/hateoas/users")
public class OrderHateoasController {
	
	@Autowired
	private UserRepository userRepository;

	// get All Orders for a user
	//The following method is deprecated
	/*
	 * @GetMapping("/{userid}/orders") public Resources<Order>
	 * getAllOrders(@PathVariable Long userid) throws UserNotFoundException {
	 * 
	 * Optional<User> userOptional = userRepository.findById(userid); if
	 * (!userOptional.isPresent()) throw new
	 * UserNotFoundException("User Not Found");
	 * 
	 * List<Order> allorders = userOptional.get().getOrders(); Resources<Order>
	 * finalResources = new Resources<Order>(allorders);
	 * 
	 * return finalResources; }
	 */
	
	
	@GetMapping("/{userid}/orders")
	public CollectionModel<Order> getAllOrders(@PathVariable Long userid)
	        throws UserNotFoundException {

	    User user = userRepository.findById(userid)
	            .orElseThrow(() -> new UserNotFoundException("User Not Found"));

	    List<Order> allOrders = user.getOrder();

	    return CollectionModel.of(allOrders);
	}
}