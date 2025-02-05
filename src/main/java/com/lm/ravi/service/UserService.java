package com.lm.ravi.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lm.ravi.entity.User;
import com.lm.ravi.repository.UserRepository;

@Service
public class UserService {
	
	private static final Logger log=LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	private UserRepository userRepository;
	
	//Add a new User
	public void addUser(User user) {
		 userRepository.save(user);
	}
	
	//Get all users
	public List<User> getAllUsers(){
		return userRepository.findAll();
	}
	
	//Find user by email
	public Optional<User> getUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}

}
