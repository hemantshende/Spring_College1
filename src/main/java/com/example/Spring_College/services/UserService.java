package com.example.Spring_College.services;


import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.example.Spring_College.dao.JwtAuthenticationResponse;
import com.example.Spring_College.dao.SignUpRequest;
import com.example.Spring_College.entities.User;


public interface UserService {
    UserDetailsService userDetailsService();

	List<User> getAllUsers();

	String isValidPassword(User user);

	User findByEmail(String email);

	User findByPhno(String phoneNumber);

	String isValidPassword(SignUpRequest request);

	User addUser(User user);

//	JwtAuthenticationResponse signup(User user);

//	void addUser(User user);
}