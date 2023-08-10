package com.example.Spring_College.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Spring_College.dao.JwtAuthenticationResponse;
import com.example.Spring_College.dao.SignUpRequest;
import com.example.Spring_College.dao.SigninRequest;
import com.example.Spring_College.entities.User;
import com.example.Spring_College.services.AuthenticationService;
import com.example.Spring_College.services.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
	@Autowired
	private final AuthenticationService authenticationService;

	@Autowired
	private UserService userService;

//    @PostMapping("/signup")
//    public ResponseEntity<JwtAuthenticationResponse> signup(@RequestBody SignUpRequest request) {
//    	
//        return ResponseEntity.ok(authenticationService.signup(request));
//    }

//	@PostMapping("/signup")
//    public ResponseEntity<JwtAuthenticationResponse> signup(@RequestBody User request) {
//    	
//        return ResponseEntity.ok(authenticationService.signup(request));
//    }

	@PostMapping("/signup")
	public ResponseEntity<?> signup(@RequestBody SignUpRequest user) {
		try {
			JwtAuthenticationResponse response = authenticationService.signup(user);
			return ResponseEntity.ok(response);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Password Does not match");
		}
	}

	// Exception handler for IllegalArgumentException
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
		return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(ex.getMessage());
	}

	@PostMapping("/signin")
	public ResponseEntity<JwtAuthenticationResponse> signin(@RequestBody SigninRequest request) {
		return ResponseEntity.ok(authenticationService.signin(request));
	}

}
