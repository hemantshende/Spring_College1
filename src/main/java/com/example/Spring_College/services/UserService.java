package com.example.Spring_College.services;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.example.Spring_College.dto.JwtAuthenticationResponse;
import com.example.Spring_College.dto.SignUpRequest;
import com.example.Spring_College.entities.User;

import jakarta.mail.MessagingException;

public interface UserService {
	UserDetailsService userDetailsService();

	ResponseEntity<?> getAllUsers(Integer pageNumber, Integer pageSize);
	
	public User findUserById(int userId);

//	String isValidPassword(User user);

	User findByEmail(String email);

	User findByPhno(String phoneNumber);

	boolean isValidPassword(SignUpRequest request);

//	public void register(User user, String siteURL) throws UnsupportedEncodingException, MessagingException;

//	public boolean sendVerificationEmail(SignUpRequest request) throws MessagingException, UnsupportedEncodingException;

//	JwtAuthenticationResponse signup(User user);

}