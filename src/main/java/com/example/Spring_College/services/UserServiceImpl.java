package com.example.Spring_College.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.Spring_College.dao.SignUpRequest;
import com.example.Spring_College.entities.User;
import com.example.Spring_College.repository.UserRepository;


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
    public String isValidPassword(User user) {
		String plainPassword = user.getPassword();
		String repeatPassword = user.getComfirmPass();

		if (plainPassword != null && plainPassword.equals(repeatPassword)) {
			user.setPassword(repeatPassword);
			return repeatPassword;
		}
		return null;
	}
    
//    public User findByEmail(String email) {
//		User emailCheck = userRepository.findByEmail(email);
//		if (emailCheck == null)
//			return null;
//		else
//			return emailCheck;
//	}

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
	

//	public User addUser(User user) {
//		String encodedPassword =encoder.encode(user.getPassword());
//		user.setPassword(encodedPassword);
//		userRepository.save(user);
//		return user;
//	}



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
	public String isValidPassword(SignUpRequest request) {
		// TODO Auto-generated method stub
		String plainPassword = request.getPassword();
		String repeatPassword = request.getComfirmPass();

		if (plainPassword != null && plainPassword.equals(repeatPassword)) {
			request.setPassword(repeatPassword);
			return repeatPassword;
		}
		return null;
	}
	@Override
	public User addUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}