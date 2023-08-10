package com.example.Spring_College.services;

import com.example.Spring_College.dao.JwtAuthenticationResponse;
import com.example.Spring_College.dao.SignUpRequest;
import com.example.Spring_College.dao.SigninRequest;
import com.example.Spring_College.entities.User;

public interface AuthenticationService {
    JwtAuthenticationResponse signup(SignUpRequest request);

    JwtAuthenticationResponse signin(SigninRequest request);
    
//    JwtAuthenticationResponse signup(User request);
}
