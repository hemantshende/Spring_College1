package com.example.Spring_College.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

	public User findUserById(int userId) {
		User user1 = userRepository.findById(userId).orElse(null);
		return user1;
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

	public List<User> getAllUsers(Integer pageNumber, Integer pageSize) {

		Pageable p = PageRequest.of(pageNumber, pageSize);

		Page<User> pagePost = userRepository.findAll(p);
		List<User> userList = pagePost.getContent();
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

}