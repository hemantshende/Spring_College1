package com.example.Spring_College.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SigninRequest {
	@NotNull(message = "Email can not be null")
    private String email;
	
	@NotNull(message = "Password can not be null")
    private String password;
}