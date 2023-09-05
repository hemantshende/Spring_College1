package com.example.Spring_College.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Spring_College.dto.JwtAuthenticationResponse;
import com.example.Spring_College.dto.SignUpRequest;
import com.example.Spring_College.dto.SigninRequest;
import com.example.Spring_College.services.AuthenticationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
	@Autowired
	private final AuthenticationService authenticationService;

	

//	    @PostMapping("/signup")
//	    public ResponseEntity<JwtAuthenticationResponse> signup(@RequestBody SignUpRequest request) {
//	        return ResponseEntity.ok(authenticationService.signup(request));
//	    }

	@PostMapping("/signup")
	public ResponseEntity<?> signup(@RequestBody SignUpRequest request) {
		return ResponseEntity.ok(authenticationService.signup(request));
	
	}

	@PostMapping("/signin")
	public ResponseEntity<JwtAuthenticationResponse> signin(@RequestBody SigninRequest request) {
		return ResponseEntity.ok(authenticationService.signin(request));
	}

}
