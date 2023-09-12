package com.example.Spring_College.services;

import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {
    String extractUserName(String token);

    String generateToken(UserDetails userDetails);

    boolean isTokenValid(String token, UserDetails userDetails);
    
    public String extractJwtToken(String authorizationHeader);

	String extractPhoneNumberFromToken(String jwtToken);
}
