package com.stacksimplify.restservices.dto;

import java.util.List;

import com.stacksimplify.restservices.entities.Order;

public class UserMmDto {
	
	private Long id;
	private String username;
	private String firstName;
	private List<Order> order;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public List<Order> getOrder() {
		return order;
	}
	public void setOrder(List<Order> order) {
		this.order = order;
	}
	
	

}
