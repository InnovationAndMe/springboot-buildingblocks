package com.stacksimplify.restservices.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stacksimplify.restservices.entities.User;

//Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	User findByUserName(String userName);

}
