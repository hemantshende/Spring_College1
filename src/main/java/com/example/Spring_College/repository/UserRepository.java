package com.example.Spring_College.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.Spring_College.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    // Since email is unique, we'll find users by email
    User findByEmail(String email);
    

//    @Query("SELECT u FROM User u WHERE u.verificationCode = ?1")
//    public User findByVerificationCode(User user);
	
}