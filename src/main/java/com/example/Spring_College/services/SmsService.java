package com.example.Spring_College.services;


import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.Spring_College.config.TwilioConfig;
import com.example.Spring_College.dto.OtpResponseDto;
import com.example.Spring_College.dto.OtpStatus;
import com.example.Spring_College.dto.SignUpRequest;
import com.example.Spring_College.entities.User;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.verify.v2.service.Verification;
import com.twilio.rest.verify.v2.service.VerificationCheck;
import com.twilio.type.PhoneNumber;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SmsService {

	private User user;
	@Autowired
	private TwilioConfig twilioConfig;
	
	@Autowired
	private JwtService jwtService;
	
    Map<String, String> otpMap = new HashMap<>();


	public OtpResponseDto sendSMS(SignUpRequest otpRequest) {
		OtpResponseDto otpResponseDto = null;
		try {
			PhoneNumber to = new PhoneNumber(otpRequest.getPhoneNumber());//to
			PhoneNumber from = new PhoneNumber(twilioConfig.getPhoneNumber()); // from
//			String otp = generateOTP();
			 String otp = generateOtp(4);
			String otpMessage = "Dear "+otpRequest.getFirstName()+" , Your OTP "+otp+" is Delivered Successfully...!!!";
			Message message = Message
			        .creator(to, from,
			                otpMessage)
			        .create();
			otpMap.put(otpRequest.getEmail(), otp);
			otpResponseDto = new OtpResponseDto(OtpStatus.DELIVERED, "OTP Delivered..!!!");
		} catch (Exception e) {
			e.printStackTrace();
			otpResponseDto = new OtpResponseDto(OtpStatus.FAILED, e.getMessage());
		}
		return otpResponseDto;
	}
	


    public static String generateOtp(int length) {
        // Generate a random numeric OTP of the specified length
        return RandomStringUtils.randomNumeric(length);
    }

    @Value("${token.signing.key}")
    private String jwtSecret; // Replace with your secret key

    public String validateOtp(String otp, String jwtToken) {
        // Extract the username from the JWT token.
        String authenticatedUsername =jwtService.extractUserName(jwtToken);
        System.out.println(authenticatedUsername);

        if (authenticatedUsername != null && otpMap.containsKey(authenticatedUsername) && otpMap.get(authenticatedUsername).equals(otp)) {
            otpMap.remove(authenticatedUsername); // Remove the OTP entry once it's validated.
            // Assuming 'user' is a reference to the user for whom OTP is being validated.
            
            if (user != null)
            user.setOtpVerification(true);
            
            return "OTP is valid!";
        } else {
            return "OTP is invalid!";
        }
    }
	
//    public boolean verifyOtpWithTwilio(String otp, String phoneNumber) {
//        try {
//
//            Verification verification = Verification.creator(twilioConfig.getServiceSid(), phoneNumber, "sms")
//                .setTo(phoneNumber)
//                .create();
//
//            return verification.getStatus().equals("approved");
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        }
//    }
    
 /*   public boolean verifyOtpWithTwilio(String otp, String phoneNumber) {
//        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        VerificationCheck verificationCheck = VerificationCheck.creator(
        		twilioConfig.getServiceSid())
            .setTo(phoneNumber)
            .setCode(otp)
            .create();

        System.out.println(verificationCheck.getStatus());
		return false;
    }
 */   

//	
//	private String generateOTP() {
//        return new DecimalFormat("000000")
//                .format(new Random().nextInt(999999));
//    }
}