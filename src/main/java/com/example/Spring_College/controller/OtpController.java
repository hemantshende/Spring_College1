package com.example.Spring_College.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Spring_College.dto.OtpRequest;
import com.example.Spring_College.dto.OtpResponseDto;
import com.example.Spring_College.dto.OtpValidationRequest;
import com.example.Spring_College.dto.SignUpRequest;
import com.example.Spring_College.services.JwtService;
import com.example.Spring_College.services.SmsService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/otp")
@Slf4j
public class OtpController {

	@Autowired
	private SmsService smsService;
	
	@Autowired
	private JwtService jwtService;
	
	@GetMapping("/process")
	public String processSMS() {
		return "SMS sent";
	}

	@PostMapping("/send-otp")
	public OtpResponseDto sendOtp(@RequestBody SignUpRequest otpRequest) {
		log.info("inside sendOtp :: "+otpRequest.getEmail());
		return smsService.sendSMS(otpRequest);
	}
	
//	@GetMapping("/validate-otp")
//    public String validateOtp(
//        @RequestParam("otp") String otp,
//        @RequestHeader("Authorization") String authorizationHeader) {
//		
//		System.out.println(otp);
//
//        // Extract the JWT token from the Authorization header.
//        String jwtToken = jwtService.extractJwtToken(authorizationHeader);
//        System.out.println(jwtToken);
//
//        if (jwtToken != null) {
//            // Verify the OTP using Twilio Verify API
//        	String phoneNumber = jwtService.extractPhoneNumberFromToken(jwtToken);
//            boolean isOtpValid = smsService.verifyOtpWithTwilio(otp, phoneNumber);
//
//            if (isOtpValid) {
//                log.info("OTP is valid: " + otp);
//                return "OTP is valid!";
//            } else {
//                log.info("OTP is invalid: " + otp);
//                return "OTP is invalid!";
//            }
//        } else {
//            // Handle the case where there's no valid JWT token in the request.
//            return "Invalid JWT token.";
//        }
//    }
	
	
	@GetMapping("/validate-otp")
	public String validateOtp(
	    @RequestParam("OTP") String otp,
	    @RequestHeader("Authorization") String authorizationHeader) {

	    // Extract the JWT token from the Authorization header.
	    String jwtToken = null;
			jwtToken = jwtService.extractJwtToken(authorizationHeader); 
			System.out.println(jwtToken);
		

	    if (jwtToken != null) {
	        log.info("inside validateOtp " + otp);
	        return smsService.validateOtp(otp, jwtToken);
	    } else {
	        // Handle the case where there's no valid JWT token in the request.
	        return "Invalid JWT token.";
	    }
	}
	
}
