package com.example.Spring_College.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OtpRequest {
	private String username;
    private String phoneNumber;
}
