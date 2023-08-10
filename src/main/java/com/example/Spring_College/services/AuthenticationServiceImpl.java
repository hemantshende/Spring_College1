package com.example.Spring_College.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.Spring_College.dao.JwtAuthenticationResponse;
import com.example.Spring_College.dao.SignUpRequest;
import com.example.Spring_College.dao.SigninRequest;
import com.example.Spring_College.entities.Role;
import com.example.Spring_College.entities.User;
import com.example.Spring_College.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtService jwtService;
	private final AuthenticationManager authenticationManager;
	@Autowired
	private UserService userService;

//	public JwtAuthenticationResponse signup(SignUpRequest request) {
//		var user = User.builder().firstName(request.getFirstName()).lastName(request.getLastName())
//				.email(request.getEmail()).password(passwordEncoder.encode(request.getPassword())).role(Role.USER)
//				.build();
//		userRepository.save(user);
//		var jwt = jwtService.generateToken(user);
//		return JwtAuthenticationResponse.builder().token(jwt).build();
//	}

	@Override
	public JwtAuthenticationResponse signin(SigninRequest request) {
		authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
		User user;
		try {
			user = userRepository.findByEmail(request.getEmail());
		} catch (Exception e) {
			throw new IllegalArgumentException("No Ueser Found");
		}
				
		var jwt = jwtService.generateToken(user);
		return JwtAuthenticationResponse.builder().token(jwt).build();
	}

	public JwtAuthenticationResponse signup(SignUpRequest user) {
	    // To validate password
		String s=userService.isValidPassword(user);
	    if (s !=null) {
	        var user1 = User.builder()
	                .firstName(user.getFirstName())
	                .lastName(user.getLastName())
	                .email(user.getEmail())
	                .password(passwordEncoder.encode(user.getPassword()))
	                .role(Role.USER)
	                .build();
	        userRepository.save(user1);
	        var jwt = jwtService.generateToken(user1);
	        return JwtAuthenticationResponse.builder().token(jwt).build();
	    } else {
	        throw new IllegalArgumentException("Password and confirm password do not match");
	    }
	}
}