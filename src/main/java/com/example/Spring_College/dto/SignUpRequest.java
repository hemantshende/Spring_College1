package com.example.Spring_College.dto;


import com.example.Spring_College.entities.Gender;
import com.example.Spring_College.entities.Role;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class SignUpRequest {
	@NotBlank(message = "first name can not be null")
    private String firstName;
	@NotBlank
    private String lastName;
    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String password;
    @NotBlank
    private String confirmPassword;
    @NotBlank
    private String phoneNumber;
    @NotBlank
    private String address;
    @NotBlank
    private Role role;
    @NotBlank
    private Gender gender;
   
}