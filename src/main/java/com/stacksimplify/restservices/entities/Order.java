package com.stacksimplify.restservices.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.FetchType;

import org.springframework.hateoas.RepresentationModel;

@Entity
@Table(name = "orders")
public class Order  extends RepresentationModel<Order> { //ResourceSupport deprecated now 
/*
 * 	New classes 
ResourceSupport → RepresentationModel

Resource → EntityModel

Resources → CollectionModel

PagedResources → PagedModel  
*/	
	@Id
	@GeneratedValue
	private Long orderId;
	private String orderDescription;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	private User user;

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getOrderDescription() {
		return orderDescription;
	}

	public void setOrderDescription(String orderDescription) {
		this.orderDescription = orderDescription;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", orderDescription=" + orderDescription + ", user=" + user + "]";
	}

}
