package com.example.Spring_College.services;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.Spring_College.dto.SignUpRequest;
import com.example.Spring_College.entities.User;
import com.example.Spring_College.repository.UserRepository;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	@Autowired
	private final UserRepository userRepository;
	
	@Autowired
	private JavaMailSender mailSender;

	@Override
	public UserDetailsService userDetailsService() {
		return new UserDetailsService() {
			@Override
			public UserDetails loadUserByUsername(String username) {
				try {
					return userRepository.findByEmail(username);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					throw new RuntimeException("User Not Found");
				}

			}
		};
	}

	public User findByEmail(String email) {
		User emailCheck = userRepository.findByEmail(email);
		if (emailCheck == null)
			return null;
		else
			return emailCheck;
	}

	public User findByPhno(String email) {
		User PhoneCheck = userRepository.findByEmail(email);
		if (PhoneCheck == null)
			return null;
		else
			return PhoneCheck;
	}

	public String deleteUser(int userId) {
		userRepository.deleteById(userId);
		return "Successfully Deleted";
	}

	public Optional<User> findUserById(int userId) {
		Optional<User> user1 = userRepository.findById(userId);
		if (user1.isPresent()) {
			return user1;
		} else {
			return null;
		}
	}

	public User updateUser(int userId) {
		Optional<User> user = userRepository.findById(userId);
		if (user.isPresent()) {
			User user1 = new User();
			userRepository.save(user1);
			return user1;
		} else {
			return null;
		}
	}

	public List<User> getAllUsers() {
		List<User> userList = userRepository.findAll();
		return userList;
	}

	@Override
	public boolean isValidPassword(SignUpRequest request) {
		// TODO Auto-generated method stub
		String plainPassword = request.getPassword();
		String repeatPassword = request.getConfirmPassword();

		if (plainPassword != null && plainPassword.equals(repeatPassword)) {

			request.setPassword(repeatPassword);

			return true;
		} else {
			return false;
		}

	}
//	
//	  public void register(User user, String siteURL) 
//		        throws UnsupportedEncodingException, MessagingException {
//		    String encodedPassword = passwordEncoder.encode(user.getPassword());
//		    user.setPassword(encodedPassword);
//		   
//		    String randomCode = RandomString().;
//		    user.setVerificationCode(randomCode);
//		    user.set
//		     
//		    userRepository.save(user);
//		     
//		    sendVerificationEmail(user, siteURL);
//		}

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