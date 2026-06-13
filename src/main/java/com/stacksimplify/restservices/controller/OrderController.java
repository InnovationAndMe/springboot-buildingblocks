package com.stacksimplify.restservices.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stacksimplify.restservices.entities.Order;
import com.stacksimplify.restservices.entities.User;
import com.stacksimplify.restservices.exceptions.UserNotFoundException;
import com.stacksimplify.restservices.repositories.OrderRepository;
import com.stacksimplify.restservices.repositories.UserRepository;

@RestController
@RequestMapping(value="/users")
public class OrderController {
	
	@Autowired
	private UserRepository  userRepository;
	
	@Autowired
	private OrderRepository  orderRepository;
	
	//get All orders for user
	@GetMapping("/{userId}/orders")
	public List<Order> getAllOrders(@PathVariable Long userId) throws UserNotFoundException{
		Optional<User> userOptional = userRepository.findById(userId);
		
		if(!userOptional.isPresent()) {
			throw new UserNotFoundException("User Not Found Exception");
		}
		return userOptional.get().getOrder();
		
	}
	
	@PostMapping("/{userId}/orders")
	public Order createOrder(@PathVariable Long userId, @RequestBody Order order) throws UserNotFoundException {
		Optional<User> userOptional = userRepository.findById(userId);
		
		if(!userOptional.isPresent()) {
			throw new UserNotFoundException("User Not Found Exception");
		}
		
		User user = userOptional.get();
		order.setUser(user);
		
		return orderRepository.save(order);
	}
	
	//get All orders for user
		@GetMapping("/{userid}/orders/{orderid}")
		public String getOrderByOrderId(@PathVariable Long userid, @PathVariable Long orderid) throws UserNotFoundException{
			Optional<User> userOptional = userRepository.findById(userid);
			
			if(!userOptional.isPresent()) {
				throw new UserNotFoundException("User Not Found Exception");
			}
			List<Order> orders = userOptional.get().getOrder();
			
			Order returnedOrder = null;
			for(Order order : orders) {
				System.out.println("Order id "+ order.getOrderId()  + "Web Input Order "+ orderid);
				if(Long.valueOf(order.getOrderId()).longValue() == Long.valueOf(orderid).longValue()) {
					returnedOrder = order;
				}
				System.out.println("Order "+returnedOrder.toString()+returnedOrder);
			}
			
			return returnedOrder.toString();
			
		}

}
