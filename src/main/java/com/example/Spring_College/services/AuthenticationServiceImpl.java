package com.example.Spring_College.services;

import java.io.UnsupportedEncodingException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.Spring_College.dto.JwtAuthenticationResponse;
import com.example.Spring_College.dto.OtpRequest;
import com.example.Spring_College.dto.OtpResponseDto;
import com.example.Spring_College.dto.SignUpRequest;
import com.example.Spring_College.dto.SigninRequest;
import com.example.Spring_College.entities.Role;
import com.example.Spring_College.entities.User;
import com.example.Spring_College.repository.UserRepository;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
	@Autowired
	private final UserRepository userRepository;
	@Autowired
	private final PasswordEncoder passwordEncoder;
	@Autowired
	private final JwtService jwtService;
	@Autowired
	private final AuthenticationManager authenticationManager;
	@Autowired
	private UserService userService;
	@Autowired
	private SmsService smsService;
	

	
	//signup
	public ResponseEntity<?> signup(SignUpRequest request) {
		
		User user;
		try {
			user = addUser(request);
			var token = jwtService.generateToken(user);
			String message = "User Added Successfully";
			OtpResponseDto OtpResponse=smsService.sendSMS(request);
			
			// Create a Map to hold the response data
//		Map<String, Object> response = new HashMap<>(); --->It does not maintain insertion order
			
			Map<String, Object> response = new LinkedHashMap<>();
			response.put("user", user);
//			response.put("userId", user.getId());
//			response.put("emailId", user.getEmail());
//			response.put("phoneNumber", user.getPhoneNumber());
			response.put("token", token);
			response.put("message", message);
			response.put("OTP", OtpResponse);
			
			// Return the Map as the response entity with HTTP status code 200 OK
			return ResponseEntity.ok(response);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			String error=e.getMessage();
			
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(error);
		}
	}

	//signin
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
		String message="SignIn successfull...!!!";
		user.setEnabled(true);
		return JwtAuthenticationResponse.builder().token(jwt).message(message).build();
	}

	//addUser
	public User addUser(SignUpRequest user) throws UnsupportedEncodingException, MessagingException {
		
		boolean EmailVerification=userService.sendVerificationEmail(user);

	    if ( userService.isValidPassword(user)&& EmailVerification==true) {
	        var user1 = User.builder()
	                .firstName(user.getFirstName())
	                .lastName(user.getLastName())
	                .email(user.getEmail())
	                .password(passwordEncoder.encode(user.getPassword()))
	                .phoneNumber(user.getPhoneNumber())
	                .gender(user.getGender())
		            .role(Role.USER)
	                .build();
	        userRepository.save(user1);
	        user1.setEnabled(true);//-----after adding user to database we set 
	        return user1;
	        
	    } else {
	        throw new IllegalArgumentException("Password and confirm password do not match");
	    }
	}
}