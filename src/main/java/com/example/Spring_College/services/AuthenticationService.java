package com.example.Spring_College.services;

import java.io.UnsupportedEncodingException;

import org.springframework.http.ResponseEntity;

import com.example.Spring_College.dto.JwtAuthenticationResponse;
import com.example.Spring_College.dto.SignUpRequest;
import com.example.Spring_College.dto.SigninRequest;
import com.example.Spring_College.entities.User;

import jakarta.mail.MessagingException;

public interface AuthenticationService {
//    JwtAuthenticationResponse signup(SignUpRequest request);
	
//	ResponseEntity<?> signup(SignUpRequest request);

    JwtAuthenticationResponse signin(SigninRequest request);
    
    public User addUser(SignUpRequest user) throws UnsupportedEncodingException, MessagingException;

    public ResponseEntity<?> signup(SignUpRequest request);
    
}
