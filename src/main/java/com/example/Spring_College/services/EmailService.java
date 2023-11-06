package com.example.Spring_College.services;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.example.Spring_College.dto.SignUpRequest;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {
	
	@Autowired
	private JavaMailSender mailSender;
	
	public boolean sendVerificationEmail(SignUpRequest user)

			throws MessagingException, UnsupportedEncodingException {
		String toAddress = user.getEmail();
		String fromAddress = "shendehc11@gmail.com";
		String senderName = "College Management";
		String subject = "Please verify your registration";
		String content = "Dear [[name]],<br>" + "Please click the link below to verify your registration:<br>"
				+ "<h3><a href=\"[[URL]]\" target=\"_self\">VERIFY</a></h3>" + "Thank you,<br>" + "Your company name.";

		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);

		helper.setTo(toAddress);
		helper.setFrom(fromAddress, senderName);
		helper.setSubject(subject);

		content = content.replace("[[name]]", user.getFirstName());
//		String verifyURL = "/verify?code=" + userRepository.findByVerificationCode(user);

		String verifyURL = "http://localhost:8080/api/v1/auth/signin";
		content = content.replace("[[URL]]", verifyURL);

		helper.setText(content, true);

		if (user.getEmail() == null) {
			return false;
		} else {
			mailSender.send(message);
			return true;
		}
	}

}
