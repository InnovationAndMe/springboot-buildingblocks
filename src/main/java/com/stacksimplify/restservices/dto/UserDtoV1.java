package com.stacksimplify.restservices.dto;

import java.util.List;

import com.stacksimplify.restservices.entities.Order;


public class UserDtoV1 {

	private Long id;
	
	private String userName;
	
	private String firstName;
	
	private String lastName;
	
	private String email;
	
	private String role;
	
	private String ssn;

	private List<Order> order;

	public UserDtoV1() {
		super();
	}

	public UserDtoV1(Long id, String userName, String firstName, String lastName, String email, String role, String ssn,
			List<Order> order) {
		super();
		this.id = id;
		this.userName = userName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.role = role;
		this.ssn = ssn;
		this.order = order;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public List<Order> getOrder() {
		return order;
	}

	public void setOrder(List<Order> order) {
		this.order = order;
	}
	
	
	
}
